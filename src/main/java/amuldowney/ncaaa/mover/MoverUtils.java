package amuldowney.ncaaa.mover;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Clues on 1/15/15.
 */
public class MoverUtils {

    public static Hashtable<Integer,List<ConferenceNames>> ConferenceGroups = new Hashtable<Integer,List<ConferenceNames>>()
    {{
        put(0,new ArrayList<ConferenceNames>(Arrays.asList(ConferenceNames.ACC,ConferenceNames.SEC)));
        put(1,new ArrayList<ConferenceNames>(Arrays.asList(ConferenceNames.BigTen,ConferenceNames.PAC_12)));
        put(2,new ArrayList<ConferenceNames>(Arrays.asList(ConferenceNames.BigTwelve,ConferenceNames.American)));
        put(3,new ArrayList<ConferenceNames>(Arrays.asList(ConferenceNames.C_USA,ConferenceNames.MAC)));
        put(4,new ArrayList<ConferenceNames>(Arrays.asList(ConferenceNames.MWC,ConferenceNames.Sunbelt)));
        //put(5,new ArrayList<ConferenceNames>(Arrays.asList(ConferenceNames.Independent,ConferenceNames.FCS)));
    }};

    public enum ConferenceNames {
        ACC(0),
        SEC(11),
        BigTen(1),
        PAC_12(10),
        American(3),
        BigTwelve(2),
        Sunbelt(13),
        MAC(7),
        MWC(9),
        C_USA(4),
        Independent(5),
        FCS(17);

        public int conferenceID;

        ConferenceNames(int i) {
            conferenceID = i;
        }
    }

    public static ConferenceNames byID(int i) {
        for (ConferenceNames conferenceNames : ConferenceNames.values()) {
            if (conferenceNames.conferenceID == i) {
                return conferenceNames;
            }
        }
        return null;
    }

    public static List<Integer> conferenceIDs(){
        List<Integer> confIDs = new ArrayList<>(ConferenceNames.values().length);
        for (ConferenceNames conferenceNames : ConferenceNames.values()) {
                confIDs.add(conferenceNames.conferenceID);
        }
        return confIDs;
    }

    public static List<Team> parseJsonIntoTeams(JsonObject json){
        List<Team> teams = new ArrayList<>();
            JsonArray results = json.getJsonArray("allTeamsList");
            for (JsonObject result : results.getValuesAs(JsonObject.class)) {
                teams.add(new Team(result));
            }
        return teams;
    }

    public enum CSVHeaders {TeamName, confRec, confStanding, coachPollRank, divStanding, overallRec, pointsAgainst, pointsFor, winPercent}

    public enum AllTeamsHeaders {
        id,logoUrl, nickName, shortName, conferenceId, color, overallRating, offenseRating, defenseRating, specialTeamRating, coachPollRank, overallRec, confRec, name
    }
}
