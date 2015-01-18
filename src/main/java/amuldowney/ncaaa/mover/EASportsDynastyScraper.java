package amuldowney.ncaaa.mover;

import com.jaunt.JauntException;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clues on 1/16/15.
 */
public class EASportsDynastyScraper {

    private UserAgent ua;

    public EASportsDynastyScraper() {
        ua = new UserAgent();
    }

    public List<Team> GetAllTeamData() throws IOException, ResponseException {
        File allTeams = new File("TestData/AllTeamsData.json");
        ua.download("http://www.easports.com/dynasty/data/all-teams", allTeams);

        List<Team> teams = new ArrayList<>();
        try (InputStream is = new FileInputStream(allTeams);JsonReader rdr = Json.createReader(is)) {
            JsonObject obj = rdr.readObject();
            JsonArray results = obj.getJsonArray("allTeamsList");
            for (JsonObject result : results.getValuesAs(JsonObject.class)) {
                teams.add(new Team(result));
            }
        }
        return teams;
    }

    public void Login() {
        try {
            ua.visit("http://www.easports.com/dynasty", 5);
            ua.doc.apply("muldowney@gatech.edu","!Jamestown11");
            ua.doc.submit();
            ua.visit("http://www.easports.com/dynasty/user/dynasty/set/288659");
        } catch (JauntException e) {
            e.printStackTrace();
        }
    }
}
