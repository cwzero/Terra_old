package com.liquidforte.terra.command;

import java.util.function.Function;

import com.liquidforte.terra.mode.Mode;

public interface Command extends Function<Mode, Object> {
	default String getCommand() {
		String name = getClass().getName();
		
		if (name.contains(".")) {
			name = name.substring(name.lastIndexOf(".") + 1);
		}

		if (name.contains("Command")) {
			name = name.replace("Command", "");
		}

		return name.toLowerCase();
	}

	default String[] getAlias() {
		return new String[0];
	}

	Mode getMode();

	default Object call() {
		return apply(getMode());
	}
}
