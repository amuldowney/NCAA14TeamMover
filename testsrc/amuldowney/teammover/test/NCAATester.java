package amuldowney.teammover.test;

import amuldowney.teammover.Conference;
import amuldowney.teammover.League;
import amuldowney.teammover.LeagueYear;
import amuldowney.teammover.MoverUtils;

/**
 * Created by Clues on 1/15/15.
 */
public class NCAATester {

    @org.junit.Test
    public void testLeague() throws Exception {

        League amuls = new League();

        amuls.addYear(2015);

        LeagueYear twentyfifteen = amuls.getYear(2015);

        assert twentyfifteen.get_conferences().size() == MoverUtils.ConferenceNames.values().length;

        Conference acc = twentyfifteen.get_conference(MoverUtils.ConferenceNames.ACC);

        assert acc.get_teams().size() == 0;



    }

}
