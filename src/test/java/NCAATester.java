import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;


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

    @org.junit.Test
    public void testCSV() throws Exception {
        Reader in = new FileReader("TestData/TeamStandings_CBKool.csv");
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader().parse(in);
        for (CSVRecord record : records) {
            String teamName = record.get(MoverUtils.CSVHeaders.TeamName);
            String teamRec = record.get(MoverUtils.CSVHeaders.overallRec);
            String teamStanding = record.get(MoverUtils.CSVHeaders.coachPollRank);

            Team nTeam = new Team(teamName,Integer.parseInt(teamRec),Integer.parseInt(teamStanding));
        }
    }
}
