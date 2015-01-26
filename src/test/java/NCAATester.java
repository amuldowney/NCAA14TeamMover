import amuldowney.ncaaa.mover.*;
import org.junit.Test;

import java.io.File;
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

    @Test
    public void testDynastyScraper() throws Exception {
        EASportsDynastyScraper scraper = new EASportsDynastyScraper("what","no");

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
    public void testTopLevel() throws Exception {
        String[] args = {"-y","2014","-u", "muldowney@gatech.edu","-pw", "!Jamestown11"};
        TopLevelClass.main(args);

    }

    @Test
    public void testRandomAllTeams() throws Exception {
        RandomAllTeamsData rand = new RandomAllTeamsData();
        League league = new League(2014);
        league.getYear(2014).addConferencedTeams(MoverUtils.parseJsonIntoTeams(rand.createRandomAllTeamsList()));

        LeagueLoaderSaver.SaveLeague(league,new File("TestData/League.json"));

        ConferenceTeamMover ctm = new ConferenceTeamMover(league.getYear(2014));

        ctm.GenerateTransitionTeams2();

        league.getYear(2014).get_conferences().values().forEach(conf -> {System.out.println(conf.get_name());conf.get_teams().forEach(team -> System.out.println(team));});
    }

    @Test
    public void testConferenceNamesIterator() throws Exception {
        for (MoverUtils.ConferenceNames conferenceNames : MoverUtils.ConferenceNames.values()) {
            System.out.println(conferenceNames);
        }
        //works
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
