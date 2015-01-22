package amuldowney.ncaaa.mover;

import com.jaunt.JauntException;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Clues on 1/16/15.
 */
public class EASportsDynastyScraper {

    private UserAgent ua;
    private String username;
    private String password;

    public EASportsDynastyScraper(String username, String password) {
        ua = new UserAgent();
        this.username = username;
        this.password = password;
    }

    public List<Team> GetAllTeamData() throws IOException, ResponseException {
        File allTeams = new File("TestData/AllTeamsData.json");
        ua.download("http://www.easports.com/dynasty/data/all-teams", allTeams);

        try (InputStream is = new FileInputStream(allTeams);JsonReader rdr = Json.createReader(is)) {
            JsonObject obj = rdr.readObject();
            return MoverUtils.parseJsonIntoTeams(obj);
        }
    }

    public void Login() {
        try {
            ua.visit("http://www.easports.com/dynasty", 5);
            ua.doc.apply(username, password);
            ua.doc.submit();
            ua.visit("http://www.easports.com/dynasty/user/dynasty/set/288659");
        } catch (JauntException e) {
            e.printStackTrace();
        }
    }
}
