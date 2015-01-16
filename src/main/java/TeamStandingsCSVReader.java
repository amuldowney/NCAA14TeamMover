import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;

/**
 * Created by Clues on 1/16/15.
 */
public class TeamStandingsCSVReader {




    public LeagueYear ReadInTeamStandings(String fileLocation) throws  Exception{
        Reader in = new FileReader("TestData/TeamStandings_CBKool.csv");
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader().parse(in);
        LeagueYear yr = new LeagueYear(2014);

        for (CSVRecord record : records) {
            String teamName = record.get(MoverUtils.CSVHeaders.TeamName);
            String teamRec = record.get(MoverUtils.CSVHeaders.overallRec);
            String teamStanding = record.get(MoverUtils.CSVHeaders.coachPollRank);

            Team nTeam = new Team(teamName, teamRec, teamStanding);
            yr.AddTeamWherever(nTeam);
        }

        return yr;
    }
}
