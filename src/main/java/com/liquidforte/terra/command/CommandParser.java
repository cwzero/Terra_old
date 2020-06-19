package com.liquidforte.terra.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.google.inject.Inject;
import com.liquidforte.terra.config.AppOptions;
import com.liquidforte.terra.mode.Mode;

public class CommandParser {
	private AppOptions options;
	private Mode mode;
	private Set<Command> commands;

	@Inject
	public CommandParser(AppOptions options, Mode mode, Set<Command> commands) {
		this.options = options;
		this.mode = mode;
		this.commands = commands;
	}

	public Object[] run(String[] c) {
		List<Object> result = new ArrayList<>();
		
		for (String i : c) {
			result.add(run(i));
		}
		
		return result.toArray();
	}

	public Object run(String invoked) {
		for (Command command : commands) {
			String c = command.getCommand();
			List<String> a = Arrays.asList(command.getAlias());
			if (c.contentEquals(invoked) || a.contains(invoked)) {
				return command.apply(mode);
			}
		}

		for (Command command : commands) {
			if (command.getCommand().contentEquals("help")) {
				return command.apply(mode);
			}
		}

		return null;

	}

	public Object run() {
		return run(options.getCommand().split(" "));
	}
}
