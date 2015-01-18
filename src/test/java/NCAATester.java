import amuldowney.ncaaa.mover.*;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Clues on 1/15/15.
 */
public class NCAATester {

    @org.junit.Test
    public void testLeague() throws Exception {

        League amuls = new League(2000);

        amuls.addYear(2015);

        LeagueYear twentyfifteen = amuls.getYear(2015);

        assert twentyfifteen.get_conferences().size() == MoverUtils.ConferenceNames.values().length;

        Conference acc = twentyfifteen.get_conference(MoverUtils.ConferenceNames.ACC);

        assert acc.get_teams().size() == 0;


    }

    @org.junit.Test
    public void testCSV() throws Exception {
        Reader in = new FileReader("TestData/TeamStandings_CBKool.csv");
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader().parse(in);
        for (CSVRecord record : records) {
            String teamName = record.get(MoverUtils.CSVHeaders.TeamName);
            String teamRec = record.get(MoverUtils.CSVHeaders.overallRec);
            String teamStanding = record.get(MoverUtils.CSVHeaders.coachPollRank);

            Team nTeam = new Team(teamName,teamRec, teamStanding);
        }
    }

    @Test
    public void testCSVReader() throws Exception {
        TeamStandingsCSVReader csvReader = new TeamStandingsCSVReader();

        LeagueYear yr = csvReader.ReadInTeamStandings("TestData/TeamStandings_CBKool.csv");

    }

    @Test
    public void testDynastyScraper() throws Exception {
        EASportsDynastyScraper scraper = new EASportsDynastyScraper();

        scraper.Login();
        List<Team> teams = scraper.GetAllTeamData();
        League league = new League(2014);
        league.getYear(2014).addConferencedTeams(teams);
        assert teams.size() > 0;
    }

    @Test
    public void testSorting() throws Exception {
        List<OVRRating> overs = new ArrayList<>();

        overs.add(new OVRRating("A"));
        overs.add(new OVRRating("B"));
        overs.add(new OVRRating("C"));
        overs.add(new OVRRating("D"));
        overs.add(new OVRRating("A-"));
        overs.add(new OVRRating("B-"));
        overs.add(new OVRRating("C-"));
        overs.add(new OVRRating("D-"));
        overs.add(new OVRRating("A+"));
        overs.add(new OVRRating("B+"));
        overs.add(new OVRRating("C+"));
        overs.add(new OVRRating("D+"));

        Collections.sort(overs);
    }

    @Test
    public void testJauntScrape() throws Exception {
        try {
            UserAgent userAgent = new UserAgent();
            userAgent.visit("http://www.easports.com/dynasty",5);
            userAgent.doc.apply("muldowney@gatech.edu","!Jamestown11");
            userAgent.doc.submit();
            //http://www.easports.com/dynasty/data/all-teams
            userAgent.visit("http://www.easports.com/dynasty/standings");
//            Document docLoggedIn = userAgent.sendPOST("https://www.easports.com/dynasty/login_check", "",
//                    "_username:muldowney@gatech.edu",
//                    "_password:!Jamestown11",
//                    "_target_path:/dynasty/home",
//                    "login:Login"
//            );
//
//            //Document stand = new UserAgent().visit("http://www.easports.com/dynasty/standings");
//            userAgent.visit("http://www.easports.com/dynasty/teams");
//            //Document standings = userAgent.doc.getHyperlink("Standings").follow();
//            //userAgent.visit("http://www.easports.com/dynasty/standings");
//            //Element div = standings.findFirst("<div class=\"standings\" ng-controller=\"StandingsController\">");
//
            System.out.println(userAgent.doc.innerHTML());               //print the document as HTML
        } catch (JauntException e) {         //if an HTTP/connection error occurs, handle JauntException.
            System.err.println(e);
        }






}
   /*
   https://www.easports.com/dynasty/login_check
    _username:muldowney@gatech.edu
    _password:!Jamestown11
    _target_path:/dynasty/home
    login:Login

    http://www.easports.com/dynasty/standings
    */
}
