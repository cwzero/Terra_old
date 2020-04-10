package com.liquidforte.terra.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HttpContext;

import com.liquidforte.terra.model.lock.FileLock;

public class DownloadUtil {
	public static void downloadMod(Path modsDir, FileLock lock) throws IOException {
		downloadFile(modsDir, lock.getFileName(), lock.getDownloadUrl(), lock.getFingerprint());
	}

	public static void downloadFile(Path dir, String filename, String downloadUrl, long fingerprint)
			throws IOException {
		Path destPath = dir.resolve(filename);
		downloadFile(destPath, downloadUrl, fingerprint);
	}

	public static void downloadFile(Path destPath, String downloadUrl, long fingerprint) throws IOException {
		downloadFile(destPath, downloadUrl, fingerprint, 0);
	}

	public static void downloadFile(Path destPath, String downloadUrl, long fingerprint, int retry) throws IOException {
		if (destPath.toFile().exists()) {
			if (fingerprint > 0) {
				long check = FingerprintUtil.getFileHash(destPath);
				if (check == fingerprint)
					return;
			}
		}

		downloadFile(destPath, downloadUrl);

		if (fingerprint > 0) {
			long check = FingerprintUtil.getFileHash(destPath);
			if (check > 0 && check != fingerprint && retry < 5) {
				downloadFile(destPath, downloadUrl, fingerprint, (retry + 1));
				return;
			}
		}
	}

	public static void downloadFile(Path destPath, String downloadUrl) throws IOException {
		destPath.toFile().getParentFile().mkdirs();
		destPath.toFile().createNewFile();

		CloseableHttpClient client = HttpClientBuilder.create().setRedirectStrategy(new DefaultRedirectStrategy() {
			@Override
			public boolean isRedirected(HttpRequest request, HttpResponse response, HttpContext context)
					throws ProtocolException {
				boolean result = super.isRedirected(request, response, context);
				int responseCode = response.getStatusLine().getStatusCode();
				return result || (responseCode >= 300 && responseCode < 400);
			}
		}).build();

		HttpGet request = new HttpGet(
				downloadUrl.replace(" ", "%20").replace("+", "%2B").replace("[", "%5b").replace("]", "%5d"));

		HttpResponse response = client.execute(request);

		int responseCode = response.getStatusLine().getStatusCode();

		HttpEntity entity = response.getEntity();

		if (responseCode == 403 && downloadUrl.contains("https://edge.forgecdn.net/files/")) {
			downloadFile(destPath,
					downloadUrl.replace("https://edge.forgecdn.net/files/", "https://media.forgecdn.net/files/"));
			return;
		}

		InputStream is = entity.getContent();
		FileOutputStream fos = new FileOutputStream(destPath.toFile());

		int inByte;
		while ((inByte = is.read()) != -1) {
			fos.write(inByte);
		}

		is.close();
		fos.close();

		client.close();
	}
}
