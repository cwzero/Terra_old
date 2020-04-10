package com.liquidforte.terra.mode;

public interface Mode {
	void init();

	void doRun();

	default void run() {
		init();
		doRun();
	}
	
	void runCommand(String command);
}
