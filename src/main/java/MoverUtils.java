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

        ConferenceNames(int i){
            conferenceID = i;
        }
    }

    public ConferenceNames byID(int i){
        for (ConferenceNames conferenceNames : ConferenceNames.values()) {
            if( conferenceNames.conferenceID == i){
                return conferenceNames;
            }
        }
        return null;
    }

    public enum CSVHeaders {TeamName, confRec, confStanding, coachPollRank, divStanding, overallRec, pointsAgainst, pointsFor, winPercent}
}
