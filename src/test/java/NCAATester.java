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
        Reader in = new FileReader("path/to/file.csv");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
        for (CSVRecord record : records) {
            String lastName = record.get("Last Name");
            String firstName = record.get("First Name");
        }

    }
}
