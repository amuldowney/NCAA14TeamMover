package amuldowney.ncaaa.mover;

import com.jaunt.ResponseException;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by Clues on 1/16/15.
 */
public class TopLevelClass {

    public static void main(String[] args) throws IOException, ResponseException, ParseException {
        CommandLineParser parser = new BasicParser();
        CommandLine cmd = parser.parse(createOptions(), args);

        Integer year = Integer.parseInt(cmd.getOptionValue("y"));

        EASportsDynastyScraper scraper = new EASportsDynastyScraper(cmd.getOptionValue("u"),cmd.getOptionValue("pw"));

        scraper.Login();
        List<Team> teams = scraper.GetAllTeamData();
        League league = new League(year);
        league.getYear(year).addConferencedTeams(teams);

        HTMLer.writeLeagueYear(league.getYear(year));

    }

    private static Options createOptions() {

        Options op = new Options();

        op.addOption("y", "year", true, "Year to pull in for");
        op.addOption("u", "username", true, "EA Sports username");
        op.addOption("pw", "password", true, "EA Sports password");
        return op;
    }
}
