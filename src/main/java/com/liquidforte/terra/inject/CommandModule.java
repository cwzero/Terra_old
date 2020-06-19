package com.liquidforte.terra.inject;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.liquidforte.terra.command.BuildCommand;
import com.liquidforte.terra.command.Command;
import com.liquidforte.terra.command.DownloadCommand;
import com.liquidforte.terra.command.InstallCommand;
import com.liquidforte.terra.command.ResolveCommand;
import com.liquidforte.terra.command.UpdateCommand;

public class CommandModule extends AbstractModule {
	@Override
	protected void configure() {
		Multibinder<Command> commandBinder = Multibinder.newSetBinder(binder(), Command.class);
		commandBinder.addBinding().to(DownloadCommand.class);
		commandBinder.addBinding().to(InstallCommand.class);
		commandBinder.addBinding().to(ResolveCommand.class);
		commandBinder.addBinding().to(UpdateCommand.class);
		commandBinder.addBinding().to(BuildCommand.class);
	}
}
