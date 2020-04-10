package com.liquidforte.terra.command;

import com.liquidforte.terra.mode.Mode;

public abstract class AbstractCommand implements Command {
	private Mode mode;

	public AbstractCommand(Mode mode) {
		this.mode = mode;
	}

	@Override
	public Mode getMode() {
		return mode;
	}
}
