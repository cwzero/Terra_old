package com.liquidforte.terra.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class AppOptions {
	private static Options options;
	private String[] args;
	private CommandLine cmd;

	public AppOptions(String[] args) {
		this.args = args;
	}

	public static Options getOptions() {
		if (options == null) {
			options = new Options();

			OptionGroup modeGroup = new OptionGroup();

			Option defaultOption = new Option("default", "default mode");
			modeGroup.addOption(defaultOption);

			Option appModeOption = new Option("mode", "appMode", true, "App Mode");
			modeGroup.addOption(appModeOption);

			options.addOptionGroup(modeGroup);

			Option cacheConfigOption = new Option("cc", "cacheConfig", true, "path to cache config");
			options.addOption(cacheConfigOption);

			Option appConfigOption = new Option("ac", "appConfig", true, "path to application configuration json");
			options.addOption(appConfigOption);

			Option commandOption = new Option("C", "command", true, "application command");
			options.addOption(commandOption);

			Option modsDirOption = new Option("md", "modsDir", true, "path to dir for mods");
			options.addOption(modsDirOption);

			Option groupsDirOption = new Option("gd", "groupsDir", true, "path to dir for groups");
			options.addOption(groupsDirOption);
		}

		return options;
	}

	public CommandLine parse() {
		if (cmd == null) {
			cmd = parse(args);
		}
		return cmd;
	}

	public CommandLine parse(String[] args) {
		return parse(getOptions(), args);
	}

	public CommandLine parse(Options options, String[] args) {
		return parse(getParser(), getHelp(), options, args);
	}

	public CommandLine parse(CommandLineParser parser, HelpFormatter formatter, Options options, String[] args) {
		CommandLine cmd = null;

		try {
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			formatter.printHelp("terra", options);

			System.exit(1);
		}

		return cmd;
	}

	public HelpFormatter getHelp() {
		return new HelpFormatter();
	}

	public CommandLineParser getParser() {
		return new DefaultParser();
	}

	public String getMode() {
		parse();

		boolean defaultMode = cmd.hasOption("default");
		String mode = "default";

		if (defaultMode) {
		}

		mode = cmd.getOptionValue("mode", mode);
		return mode;
	}

	public String getAppConfigPath() {
		parse();

		return cmd.getOptionValue("appConfig", "app.json");
	}

	public String getCacheConfigPath() {
		parse();

		return cmd.getOptionValue("cacheConfig", System.getProperty("user.home") + "/.terra/cache.json");
	}

	public String getCommand() {
		parse();

		return cmd.getOptionValue("command", "install");
	}

	public Path getModsDir() {
		parse();

		return Paths.get(cmd.getOptionValue("modsDir", "mods"));
	}

	public Path getGroupsDir() {
		parse();

		return Paths.get(cmd.getOptionValue("groupsDir", "groups"));
	}
}
