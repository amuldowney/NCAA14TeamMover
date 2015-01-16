import com.jaunt.ResponseException;

import java.io.IOException;
import java.util.List;

/**
 * Created by Clues on 1/16/15.
 */
public class TopLevelClass {

    public static void main() throws IOException, ResponseException {
        EASportsDynastyScraper scraper = new EASportsDynastyScraper();

        scraper.Login();
        List<Team> teams = scraper.GetAllTeamData();
        League league = new League(2014);
        league.getYear(2014).addConferencedTeams(teams);
    }
}
