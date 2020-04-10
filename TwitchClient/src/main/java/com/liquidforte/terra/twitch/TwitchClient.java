package com.liquidforte.terra.twitch;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;

import com.google.inject.Inject;
import com.liquidforte.terra.client.AbstractClient;
import com.liquidforte.terra.model.lock.FileLock;
import com.liquidforte.terra.model.lock.ModLock;

public class TwitchClient extends AbstractClient {
	private static final int pageSize = 1000;
	private static final int sort = 5;
	private static final int gameId = 432;
	private static final String gameVersion = "1.12.2";
	private static final int categoryId = 0;
	private static final int sectionId = 6;

	private Twitch twitch;
	private TwitchMapper mapper;

	@Inject
	public TwitchClient(Twitch twitch, TwitchMapper mapper) {
		this.twitch = twitch;
		this.mapper = mapper;
	}

	private ModLock searchAddon(Predicate<TwitchAddon> check, String searchFilter) {
		List<TwitchAddon> searchResult;

		int index = 0;

		do {
			searchResult = twitch.addonSearch(categoryId, gameId, gameVersion, index, pageSize, searchFilter, sectionId,
					sort);
			index += pageSize;
			if (!searchResult.isEmpty()) {
				for (TwitchAddon addon : searchResult) {
					if (check.test(addon)) {
						return mapper.mapAddon(addon);
					}
				}
			}
		} while (searchResult.size() == pageSize);

		return null;
	}

	@Override
	public ModLock getMod(String slug) {
		Predicate<TwitchAddon> check = addon -> addon.getSlug().trim().toLowerCase()
				.contentEquals(slug.trim().toLowerCase());

		ModLock slugSearch = searchAddon(check, slug);

		if (slugSearch != null) {
			return slugSearch;
		}

		slugSearch = searchAddon(check, slug.replace("-", " "));

		if (slugSearch != null) {
			return slugSearch;
		}

		slugSearch = searchAddon(check, "");

		if (slugSearch != null) {
			return slugSearch;
		}

		throw new RuntimeException("Couldn't find addon \"" + slug + "\" by slug");
	}

	@Override
	public ModLock getMod(long addonId) {
		return mapper.mapAddon(twitch.getAddon(addonId));
	}

	@Override
	public FileLock getLatestFile(long addonId) {
		return getLatestFile(addonId, "true");
	}

	@Override
	public FileLock getLatestFile(long addonId, String fileNameFilter) {
		List<TwitchFile> files = twitch.getFiles(addonId);

		JexlEngine jexl = new JexlBuilder().create();
		JexlExpression filterExpression = jexl.createExpression(fileNameFilter);

		BiPredicate<String, String> checkName = (file, name) -> file.trim().toLowerCase()
				.contentEquals(name.trim().toLowerCase());

		Predicate<String> filterName = file -> {
			JexlContext context = new MapContext();
			context.set("file", file);
			context.set("checkName", checkName);

			return (Boolean) filterExpression.evaluate(context);
		};

		Predicate<TwitchFile> filterFile = file -> file.getGameVersion().contains("1.12.2")
				&& filterName.test(file.getFileName());

		Collections.sort(files);
		Optional<TwitchFile> result = files.stream().filter(filterFile).findFirst();

		if (result.isPresent()) {
			result.get().setAddonId(addonId);
			FileLock res = mapper.mapFile(result.get());
			return res;
		}

		return null;
	}

	@Override
	public FileLock getFile(long addonId, long fileId) {
		return mapper.mapFile(twitch.getFile(addonId, fileId));
	}
}
