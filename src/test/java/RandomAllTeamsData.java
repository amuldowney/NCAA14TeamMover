import amuldowney.ncaaa.mover.MoverUtils;

import javax.json.*;
import java.util.*;

import static amuldowney.ncaaa.mover.MoverUtils.AllTeamsHeaders;

/**
 * Created by Clues on 1/21/15.
 */
public class RandomAllTeamsData {

    private Random rand = new Random();
    private List<Integer> unusedRankings = new ArrayList<>();

    public RandomAllTeamsData(){
        allRankings.forEach(name -> unusedRankings.add(name));
    }

    public JsonObject createRandomAllTeamsList(){
        JsonBuilderFactory jbf = Json.createBuilderFactory(new HashMap<>());
        JsonObjectBuilder objectBuilder = jbf.createObjectBuilder();

        JsonArrayBuilder teamJsonArray = jbf.createArrayBuilder();


        for (String name : AllNames) {
            teamJsonArray.add(jbf.createObjectBuilder()
                            .add(AllTeamsHeaders.name.name(), name)
                            .add(AllTeamsHeaders.conferenceId.name(), RandomConferenceID())
                            .add(AllTeamsHeaders.overallRec.name(), RandomRecord())
                            .add(AllTeamsHeaders.coachPollRank.name(), RandomRanking())
                            .add(AllTeamsHeaders.overallRating.name(), RandomGrade())
                            .add(AllTeamsHeaders.offenseRating.name(), RandomGrade())
                            .add(AllTeamsHeaders.defenseRating.name(), RandomGrade())
                            .add(AllTeamsHeaders.specialTeamRating.name(), RandomGrade())
            );
        }
        JsonObject jso = jbf.createObjectBuilder()
                .add("allTeamsList", teamJsonArray).build();


        return jso;
    }

    private String RandomGrade(){
        return grades.get(rand.nextInt(grades.size()));
    }

    private String RandomRanking(){
        if(unusedRankings.isEmpty()){
            return "UNR";
        } else {
            return "UNR";
            //return "#" + unusedRankings.remove(rand.nextInt(unusedRankings.size())).toString();
        }
    }

    private String RandomRecord() {
        Integer wins = rand.nextInt(13);
        Integer losses = 13-wins;

        return String.format("%s-%s", wins, losses);
    }

    private Integer RandomConferenceID(){
        if(confIDs.isEmpty()){
            confIDs = MoverUtils.conferenceIDs();
        }
        return confIDs.remove(rand.nextInt(confIDs.size()));
    }

    private static List<Integer> confIDs = MoverUtils.conferenceIDs();

    private static List<Integer> allRankings = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25);

    private static List<String> grades = Arrays.asList("A+","A","A-","B+","B","B-","C+","C","C-","D");

    private static List<String> AllNames = Arrays.asList("Boise State",
            "Bowling Green",
            "Buffalo",
            "BYU",
            "Colorado State",
            "Marshall",
            "Nevada",
            "NC State",
            "San Jose State",
            "UTEP",
            "Troy",
            "Texas State",
            "Akron",
            "ECU",
            "Houston",
            "Kansas",
            "Mississippi State",
            "New Mexico State",
            "North Texas",
            "Purdue",
            "SMU",
            "Toledo",
            "UAB",
            "South Alabama",
            "UCF",
            "Central Michigan",
            "Cincinnati",
            "Iowa State",
            "Louisiana Tech",
            "Louisville",
            "Minnesota",
            "North Carolina",
            "Oregon State",
            "Pittsburgh",
            "UL Lafayette",
            "FIU",
            "Kent State",
            "Air Force",
            "Boston College",
            "Memphis",
            "Miami University",
            "Mid Tenn State",
            "Missouri",
            "New Mexico",
            "Northwestern",
            "Rutgers",
            "Temple",
            "Tulane",
            "Vanderbilt",
            "Alabama",
            "Auburn",
            "Florida",
            "Georgia",
            "Miami",
            "Michigan",
            "Notre Dame",
            "Ole Miss",
            "Oregon",
            "South Carolina",
            "Texas A&M",
            "UCLA",
            "USC",
            "Virginia Tech",
            "Ball State",
            "Eastern Michigan",
            "Idaho",
            "Ohio",
            "Rice",
            "Utah State",
            "Western Michigan",
            "Wyoming",
            "UMass",
            "UTSA",
            "Georgia State",
            "Old Dominion",
            "Army",
            "Baylor",
            "Duke",
            "UL Monroe",
            "Syracuse",
            "Tennessee",
            "Tulsa",
            "UNLV",
            "Wake Forest",
            "Washington",
            "Wisconsin",
            "Western Kentucky",
            "Arkansas",
            "Clemson",
            "Florida State",
            "Georgia Tech",
            "Iowa",
            "Michigan State",
            "Nebraska",
            "Ohio State",
            "Oklahoma",
            "Texas",
            "Utah",
            "Flordia Atlantic",
            "Southern Miss",
            "Hawaii",
            "Arkansas State",
            "California",
            "Colorado",
            "Fresno State",
            "Kansas State",
            "Kentucky",
            "LSU",
            "Northern Illinois",
            "Penn State",
            "San Diego State",
            "Connecticut",
            "USF",
            "Arizona",
            "Arizona State",
            "Illinois",
            "Indiana",
            "Maryland",
            "Navy",
            "Oklahoma State",
            "Stanford",
            "TCU",
            "Texas Tech",
            "Virginia",
            "Washington State",
            "West Virginia");

}
