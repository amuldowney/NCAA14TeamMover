/**
 * Created by Clues on 1/15/15.
 */
public class MoverUtils {


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

    public enum CSVHeaders {TeamName, confRec, confStanding, coachPollRank, divStanding, overallRec, pointsAgainst, pointsFor, winPercent}

    public enum AllTeamsThing {
        id,
        name,
        logoUrl,
        nickName,
        shortName,
        conferenceId,
        color,
        overallRating,
        offenseRating,
        defenseRating,
        specialTeamRating,
        coachPollRank,
        overallRec,
        confRec
    }

    /*
        "isUserTeam": false,
            "isMyTeam": false,
            "isFriendsTeam": false,
            "id": 1,
            "name": "Air Force",
            "logoUrl": "\/dynasty\/bundles\/ncaaonlinedynasty14\/images\/logos\/teams\/45px\/team1.png",
            "logoId": 0,
            "nickName": "Falcons",
            "shortName": "AF",
            "conferenceId": 7,
            "divisionId": 3,
            "color": "27458e",
            "overallRating": "C",
            "offenseRating": "B",
            "defenseRating": "C-",
            "specialTeamRating": "B-",
            "personaName": null,
            "nucleusPersonaId": null,
            "coachPollRank": null,
            "coachPollRankSortIndex": 10000,
            "overallRec": "2-1",
            "overallRecSortIndex": 2001,
            "confRec": "2-0"
     */
}
