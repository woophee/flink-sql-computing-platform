package com.woophee.flinksql.service.cli;

import org.apache.commons.cli.*;

public class CliOptionsParser {
    public static final Option OPTION_JOB_ID = Option
            .builder("j")
            .required(true)
            .longOpt("job_id")
            .numberOfArgs(1)
            .argName("job id")
            .desc("The platform job id")
            .build();

    public static final Options CLIENT_OPTIONS = getClientOptions(new Options());

    public static Options getClientOptions(Options options) {
        options.addOption(OPTION_JOB_ID);
        return options;
    }

    public static CliOptions parseClient(String[] args) {
        if (args.length < 1) {
            throw new RuntimeException("./sql-submit -w <work_space_dir> -f <sql-file>");
        }
        try {
            DefaultParser parser = new DefaultParser();
            CommandLine line = parser.parse(CLIENT_OPTIONS, args, true);
            String jobIdStr = line.getOptionValue(CliOptionsParser.OPTION_JOB_ID.getOpt());
            int jobId = Integer.parseInt(jobIdStr);
            return new CliOptions(jobId);
        } catch (ParseException | NumberFormatException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
