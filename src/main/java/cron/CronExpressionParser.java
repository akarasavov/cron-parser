package cron;

import cron.model.CronCommand;
import cron.parser.CronParser;

import java.sql.SQLOutput;

public class CronExpressionParser {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Parameter list should contains one value. Value should be a cron expression");
            System.out.println("For example: java -jar target/cronParser.jar \"15-16 0 1,15 * 1-5 /usr/bin/find\"");
        } else {
            CronParser cronParser = CronParser.createCronParser();
            try {
                CronCommand cronCommand = cronParser.parse(args[0]);
                System.out.println(cronCommand);
            } catch (Exception ex) {
                System.out.println("Cron expression is in illegal format");
                System.out.println("Read Readme.md for more details");
            }
        }
    }
}
