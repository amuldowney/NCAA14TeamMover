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

/**
 * Created by Clues on 1/16/15.
 */
public class EASportsDynastyScraper {

    private UserAgent ua;

    public EASportsDynastyScraper() {
        ua = new UserAgent();
    }

    //public

    public void GetAllTeamData() throws IOException, ResponseException {
        File allTeams = new File("TestData/AllTeamsData.json");
        ua.download("http://www.easports.com/dynasty/data/all-teams", allTeams);

        try (InputStream is = new FileInputStream(allTeams);
             JsonReader rdr = Json.createReader(is)) {
            JsonObject obj = rdr.readObject();
            JsonArray results = obj.getJsonArray("allTeamsList");
            for (JsonObject result : results.getValuesAs(JsonObject.class)) {

                System.out.println(result.values().toString());

                //System.out.print(result.getJsonObject("from").getString("name"));
            }
        }
    }

    public void Login() {
        try {
            ua.visit("http://www.easports.com/dynasty", 5);
            ua.doc.apply("muldowney@gatech.edu","!Jamestown11");
            ua.doc.submit();
        } catch (JauntException e) {
            e.printStackTrace();
        }
    }
}
