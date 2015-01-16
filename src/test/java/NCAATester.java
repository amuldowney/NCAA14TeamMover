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

    public String teamDiv = "<div class=\"standings\" ng-controller=\"StandingsController\"> <div class=\"every-three-conf\"> <div class=\"title\"> <p><b>ACC</b></p> <table> <tbody> <tr> <th>ACC (Atlantic)</th> <th>Conf</th> <th>Overall</th> </tr> <tr> <td> <div class=\"smallUserTeamIcon\"> <div class=\"friendsTeam\"></div> </div> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=902\" class=\"schedule-link\"> #17 Southern Miss</a> </div> </td> <td>1-0</td> <td>2-0</td> </tr> <tr> <td> <div class=\"smallUserTeamIcon\"> <div class=\"yourTeam\"></div> </div> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=901\" class=\"schedule-link\"> #12 Flordia Atlantic</a> </div> </td> <td>1-0</td> <td>2-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=70\" class=\"schedule-link\"> #20 Ohio State</a> </div> </td> <td>1-0</td> <td>2-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=28\" class=\"schedule-link\"> #4 Florida State</a> </div> </td> <td>0-0</td> <td>0-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=37\" class=\"schedule-link\"> #13 Iowa</a> </div> </td> <td>0-0</td> <td>0-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=52\" class=\"schedule-link\"> Michigan State</a> </div> </td> <td>0-0</td> <td>0-1</td> </tr> <tr> <td> <div class=\"smallUserTeamIcon\"> <div class=\"friendsTeam\"></div> </div> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=903\" class=\"schedule-link\"> Hawaii</a> </div> </td> <td>0-1</td> <td>1-1</td> </tr> <tr> <th>ACC (Coastal)</th> <th>Conf</th> <th>Overall</th> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=71\" class=\"schedule-link\"> #24 Oklahoma</a> </div> </td> <td>1-1</td> <td>1-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=92\" class=\"schedule-link\"> Texas</a> </div> </td> <td>1-1</td> <td>1-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=6\" class=\"schedule-link\"> #15 Arkansas</a> </div> </td> <td>1-1</td> <td>1-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=31\" class=\"schedule-link\"> Georgia Tech</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=21\" class=\"schedule-link\"> Clemson</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=58\" class=\"schedule-link\"> #10 Nebraska</a> </div> </td> <td>0-1</td> <td>1-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=103\" class=\"schedule-link\"> #18 Utah</a> </div> </td> <td>0-1</td> <td>1-1</td> </tr> </tbody> </table> </div> <div class=\"title\"> <p><b>AMERICAN</b></p> <table> <tbody> <tr> <th>American (Div A)</th> <th>Conf</th> <th>Overall</th> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=24\" class=\"schedule-link\"> Duke</a> </div> </td> <td>1-0</td> <td>2-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=114\" class=\"schedule-link\"> Wisconsin</a> </div> </td> <td>1-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=88\" class=\"schedule-link\"> Syracuse</a> </div> </td> <td>1-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=101\" class=\"schedule-link\"> UNLV</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=109\" class=\"schedule-link\"> Wake Forest</a> </div> </td> <td>0-0</td> <td>1-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=110\" class=\"schedule-link\"> Washington</a> </div> </td> <td>0-0</td> <td>0-1</td> </tr> <tr> <th>American (Div B)</th> <th>Conf</th> <th>Overall</th> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=8\" class=\"schedule-link\"> Army</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=97\" class=\"schedule-link\"> Tulsa</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=11\" class=\"schedule-link\"> Baylor</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=65\" class=\"schedule-link\"> UL Monroe</a> </div> </td> <td>0-0</td> <td>0-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=211\" class=\"schedule-link\"> Western Kentucky</a> </div> </td> <td>0-1</td> <td>0-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=91\" class=\"schedule-link\"> Tennessee</a> </div> </td> <td>0-2</td> <td>0-2</td> </tr> </tbody> </table> </div> <div class=\"title\"> <p><b>BIG 12</b></p> <table> <tbody> <tr> <th>Big 12 (Div A)</th> <th>Conf</th> <th>Overall</th> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=43\" class=\"schedule-link\"> Louisiana Tech</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=18\" class=\"schedule-link\"> UCF</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=44\" class=\"schedule-link\"> Louisville</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=54\" class=\"schedule-link\"> Minnesota</a> </div> </td> <td>0-0</td> <td>0-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=38\" class=\"schedule-link\"> Iowa State</a> </div> </td> <td>0-0</td> <td>0-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=86\" class=\"schedule-link\"> UL Lafayette</a> </div> </td> <td>0-0</td> <td>0-1</td> </tr> <tr> <th>Big 12 (Div B)</th> <th>Conf</th> <th>Overall</th> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=62\" class=\"schedule-link\"> North Carolina</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=77\" class=\"schedule-link\"> Pittsburgh</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=20\" class=\"schedule-link\"> Cincinnati</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=75\" class=\"schedule-link\"> #14 Oregon State</a> </div> </td> <td>0-0</td> <td>0-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=230\" class=\"schedule-link\"> FIU</a> </div> </td> <td>0-0</td> <td>0-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=19\" class=\"schedule-link\"> Central Michigan</a> </div> </td> <td>0-0</td> <td>0-2</td> </tr> </tbody> </table> </div> </div> <div class=\"every-three-conf\"> <div class=\"title\"> <p><b>BIG TEN</b></p> <table> <tbody> <tr> <th>Big Ten (Leaders)</th> <th>Conf</th> <th>Overall</th> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=4\" class=\"schedule-link\"> Arizona</a> </div> </td> <td>1-0</td> <td>2-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=72\" class=\"schedule-link\"> Oklahoma State</a> </div> </td> <td>0-0</td> <td>2-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=112\" class=\"schedule-link\"> #9 West Virginia</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=89\" class=\"schedule-link\"> #8 TCU</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=107\" class=\"schedule-link\"> Virginia</a> </div> </td> <td>0-0</td> <td>0-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=36\" class=\"schedule-link\"> Indiana</a> </div> </td> <td>0-0</td> <td>0-2</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=87\" class=\"schedule-link\"> Stanford</a> </div> </td> <td>0-1</td> <td>0-1</td> </tr> <tr> <th>Big Ten (Legends)</th> <th>Conf</th> <th>Overall</th> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=57\" class=\"schedule-link\"> Navy</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=5\" class=\"schedule-link\"> Arizona State</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=111\" class=\"schedule-link\"> Washington State</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=35\" class=\"schedule-link\"> Illinois</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=47\" class=\"schedule-link\"> Maryland</a> </div> </td> <td>0-0</td> <td>0-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=94\" class=\"schedule-link\"> Texas Tech</a> </div> </td> <td>0-0</td> <td>0-1</td> </tr> </tbody> </table> </div> <div class=\"title\"> <p><b>C-USA</b></p> <table> <tbody> <tr> <th>C-USA (East)</th> <th>Conf</th> <th>Overall</th> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=143\" class=\"schedule-link\"> Troy</a> </div> </td> <td>1-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=82\" class=\"schedule-link\"> San Jose State</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=63\" class=\"schedule-link\"> NC State</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=46\" class=\"schedule-link\"> Marshall</a> </div> </td> <td>0-0</td> <td>0-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=16\" class=\"schedule-link\"> BYU</a> </div> </td> <td>0-0</td> <td>0-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=59\" class=\"schedule-link\"> Nevada</a> </div> </td> <td>0-0</td> <td>0-1</td> </tr> <tr> <th>C-USA (West)</th> <th>Conf</th> <th>Overall</th> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=218\" class=\"schedule-link\"> Texas State</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=12\" class=\"schedule-link\"> Boise State</a> </div> </td> <td>0-0</td> <td>1-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=14\" class=\"schedule-link\"> Bowling Green</a> </div> </td> <td>0-0</td> <td>0-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=105\" class=\"schedule-link\"> UTEP</a> </div> </td> <td>0-0</td> <td>0-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=15\" class=\"schedule-link\"> Buffalo</a> </div> </td> <td>0-0</td> <td>0-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=23\" class=\"schedule-link\"> Colorado State</a> </div> </td> <td>0-1</td> <td>0-2</td> </tr> </tbody> </table> </div> <div class=\"title\"> <p><b>MAC</b></p> <table> <tbody> <tr> <th>MAC (East)</th> <th>Conf</th> <th>Overall</th> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=56\" class=\"schedule-link\"> Missouri</a> </div> </td> <td>1-0</td> <td>2-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=96\" class=\"schedule-link\"> Tulane</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=90\" class=\"schedule-link\"> Temple</a> </div> </td> <td>0-0</td> <td>0-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=60\" class=\"schedule-link\"> New Mexico</a> </div> </td> <td>0-1</td> <td>0-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=53\" class=\"schedule-link\"> Mid Tenn State</a> </div> </td> <td>0-1</td> <td>0-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=48\" class=\"schedule-link\"> Memphis</a> </div> </td> <td>0-2</td> <td>0-2</td> </tr> <tr> <th>MAC (West)</th> <th>Conf</th> <th>Overall</th> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=106\" class=\"schedule-link\"> Vanderbilt</a> </div> </td> <td>2-0</td> <td>2-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=67\" class=\"schedule-link\"> Northwestern</a> </div> </td> <td>1-0</td> <td>2-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=13\" class=\"schedule-link\"> Boston College</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=80\" class=\"schedule-link\"> Rutgers</a> </div> </td> <td>0-0</td> <td>1-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=50\" class=\"schedule-link\"> Miami University</a> </div> </td> <td>0-0</td> <td>0-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=1\" class=\"schedule-link\"> Air Force</a> </div> </td> <td>0-0</td> <td>0-1</td> </tr> </tbody> </table> </div> </div> <div class=\"every-three-conf\"> <div class=\"title\"> <p><b>MWC</b></p> <table> <tbody> <tr> <th>MWC (Mountain)</th> <th>Conf</th> <th>Overall</th> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=83\" class=\"schedule-link\"> SMU</a> </div> </td> <td>1-0</td> <td>1-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=33\" class=\"schedule-link\"> Houston</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=98\" class=\"schedule-link\"> UAB</a> </div> </td> <td>0-0</td> <td>0-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=2\" class=\"schedule-link\"> Akron</a> </div> </td> <td>0-0</td> <td>0-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=235\" class=\"schedule-link\"> South Alabama</a> </div> </td> <td>0-1</td> <td>0-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=39\" class=\"schedule-link\"> Kansas</a> </div> </td> <td>0-2</td> <td>0-2</td> </tr> <tr> <th>MWC (West)</th> <th>Conf</th> <th>Overall</th> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=55\" class=\"schedule-link\"> Mississippi State</a> </div> </td> <td>2-0</td> <td>2-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=61\" class=\"schedule-link\"> New Mexico State</a> </div> </td> <td>1-0</td> <td>1-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=78\" class=\"schedule-link\"> Purdue</a> </div> </td> <td>0-0</td> <td>1-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=95\" class=\"schedule-link\"> Toledo</a> </div> </td> <td>0-0</td> <td>0-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=25\" class=\"schedule-link\"> ECU</a> </div> </td> <td>0-0</td> <td>0-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=64\" class=\"schedule-link\"> North Texas</a> </div> </td> <td>0-1</td> <td>0-2</td> </tr> </tbody> </table> </div> <div class=\"title\"> <p><b>PAC-12</b></p> <table> <tbody> <tr> <th>Pac-12 (North)</th> <th>Conf</th> <th>Overall</th> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=45\" class=\"schedule-link\"> #19 LSU</a> </div> </td> <td>1-1</td> <td>1-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=76\" class=\"schedule-link\"> #22 Penn State</a> </div> </td> <td>0-0</td> <td>2-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=66\" class=\"schedule-link\"> Northern Illinois</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=17\" class=\"schedule-link\"> California</a> </div> </td> <td>0-1</td> <td>1-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=144\" class=\"schedule-link\"> USF</a> </div> </td> <td>0-1</td> <td>0-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=100\" class=\"schedule-link\"> Connecticut</a> </div> </td> <td>0-1</td> <td>0-1</td> </tr> <tr> <th>Pac-12 (South)</th> <th>Conf</th> <th>Overall</th> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=42\" class=\"schedule-link\"> #21 Kentucky</a> </div> </td> <td>2-0</td> <td>2-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=22\" class=\"schedule-link\"> Colorado</a> </div> </td> <td>1-0</td> <td>2-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=40\" class=\"schedule-link\"> Kansas State</a> </div> </td> <td>1-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=81\" class=\"schedule-link\"> San Diego State</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=29\" class=\"schedule-link\"> Fresno State</a> </div> </td> <td>0-0</td> <td>0-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=7\" class=\"schedule-link\"> Arkansas State</a> </div> </td> <td>0-1</td> <td>1-1</td> </tr> </tbody> </table> </div> <div class=\"title\"> <p><b>SEC</b></p> <table> <tbody> <tr> <th>SEC (East)</th> <th>Conf</th> <th>Overall</th> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=102\" class=\"schedule-link\"> #2 USC</a> </div> </td> <td>2-0</td> <td>2-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=108\" class=\"schedule-link\"> #1 Virginia Tech</a> </div> </td> <td>1-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=99\" class=\"schedule-link\"> #23 UCLA</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=51\" class=\"schedule-link\"> Michigan</a> </div> </td> <td>0-0</td> <td>0-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=49\" class=\"schedule-link\"> Miami</a> </div> </td> <td>0-1</td> <td>1-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=9\" class=\"schedule-link\"> #11 Auburn</a> </div> </td> <td>0-1</td> <td>0-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=30\" class=\"schedule-link\"> #16 Georgia</a> </div> </td> <td>0-2</td> <td>0-2</td> </tr> <tr> <th>SEC (West)</th> <th>Conf</th> <th>Overall</th> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=84\" class=\"schedule-link\"> #3 South Carolina</a> </div> </td> <td>2-0</td> <td>2-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=93\" class=\"schedule-link\"> #7 Texas A&amp;M</a> </div> </td> <td>2-0</td> <td>2-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=74\" class=\"schedule-link\"> #25 Oregon</a> </div> </td> <td>1-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=27\" class=\"schedule-link\"> Florida</a> </div> </td> <td>1-1</td> <td>1-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=3\" class=\"schedule-link\"> #6 Alabama</a> </div> </td> <td>0-1</td> <td>1-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=68\" class=\"schedule-link\"> #5 Notre Dame</a> </div> </td> <td>0-1</td> <td>0-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=73\" class=\"schedule-link\"> Ole Miss</a> </div> </td> <td>0-2</td> <td>0-2</td> </tr> </tbody> </table> </div> </div> <div class=\"every-three-conf\"> <div class=\"title\"> <p><b>SUN BELT</b></p> <table> <tbody> <tr> <th>Sun Belt (Div A)</th> <th>Conf</th> <th>Overall</th> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=69\" class=\"schedule-link\"> Ohio</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=232\" class=\"schedule-link\"> UTSA</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=233\" class=\"schedule-link\"> Georgia State</a> </div> </td> <td>0-0</td> <td>0-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=104\" class=\"schedule-link\"> Utah State</a> </div> </td> <td>0-0</td> <td>0-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=234\" class=\"schedule-link\"> Old Dominion</a> </div> </td> <td>0-0</td> <td>0-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=34\" class=\"schedule-link\"> Idaho</a> </div> </td> <td>0-0</td> <td>0-1</td> </tr> <tr> <th>Sun Belt (Div B)</th> <th>Conf</th> <th>Overall</th> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=10\" class=\"schedule-link\"> Ball State</a> </div> </td> <td>0-0</td> <td>1-0</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=115\" class=\"schedule-link\"> Wyoming</a> </div> </td> <td>0-0</td> <td>0-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=181\" class=\"schedule-link\"> UMass</a> </div> </td> <td>0-0</td> <td>0-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=26\" class=\"schedule-link\"> Eastern Michigan</a> </div> </td> <td>0-0</td> <td>0-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=79\" class=\"schedule-link\"> Rice</a> </div> </td> <td>0-0</td> <td>0-1</td> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=113\" class=\"schedule-link\"> Western Michigan</a> </div> </td> <td>0-0</td> <td>0-2</td> </tr> </tbody> </table> </div> <div class=\"title\"> <p><b>INDEPENDENTS</b></p> <table> <tbody> <tr> <th>Team</th> <th>Conf</th> <th>Overall</th> </tr> <tr> <td> <div class=\"name\"> <a href=\"/dynasty/schedule?teamId=41\" class=\"schedule-link\"> Kent State</a> </div> </td> <td>0-0</td> <td>0-2</td> </tr> </tbody> </table> </div> </div> </div>";

   /*
   https://www.easports.com/dynasty/login_check
    _username:muldowney@gatech.edu
    _password:!Jamestown11
    _target_path:/dynasty/home
    login:Login

    http://www.easports.com/dynasty/standings
    */
}
