package amuldowney.ncaaa.mover;

import java.util.*;

import static amuldowney.ncaaa.mover.MoverUtils.ConferenceGroups;
import static amuldowney.ncaaa.mover.MoverUtils.ConferenceNames;


/**
 * Created by Clues on 1/17/15.
 */
public class ConferenceTeamMover {

    private LeagueYear _leagueYear;
    private Map<Integer,List<Team>> _teamsInConferenceGroups;

    public ConferenceTeamMover(LeagueYear year){
        _leagueYear = year;
    }

    private void EstablishConferenceGroups(){
        _teamsInConferenceGroups = new HashMap<>();

        for (Map.Entry<Integer,List<MoverUtils.ConferenceNames>> conferenceGroup : ConferenceGroups.entrySet()) {
            List<Team> teams = new ArrayList<>();
            conferenceGroup.getValue().forEach(confName -> teams.addAll(_leagueYear.get_conference(confName).get_teams()));
            Collections.sort(teams);
            _teamsInConferenceGroups.put(conferenceGroup.getKey(), teams);
        }
    }

    //for each transition group

    //The conferenceNames enum is in order, we can work through them in pairs, keep track of the previous conferences looked at, and the teams moving down

    private void GenerateTransitionTeams2(){
        Conference firstConf = null, secondConf = null;
        Team firstDownTeam = null, secondDownTeam = null;
        for (int i = 0; i < MoverUtils.ConferenceNames.values().length; i = i + 2) {
            List<Team> teams = new ArrayList<>();
            Conference tempC1 = _leagueYear.get_conference(ConferenceNames.values()[i]);
            Conference tempC2 = _leagueYear.get_conference(ConferenceNames.values()[i + 1]);

            teams.addAll(tempC1.get_teams());
            teams.addAll(tempC2.get_teams());

            Collections.sort(teams);

            if(firstConf == null && secondConf == null){//At the top
                firstDownTeam = teams.get(teams.size() - 1);
                secondDownTeam = teams.get(teams.size() - 2);
            } else {

                //second level

                teams.get(0);

                teams.get(1);

                System.out.println(String.format("| %s | %s | <-> | %s | %s |",teams.get(0),"XXConf",teams.get(1),"YYYConf"));
            }

            firstConf = tempC1;
            secondConf = tempC2;
        }

    }

    private void GenerateTransitionTeams(){
        List<UpDownGroup> whatever = new ArrayList<>(ConferenceGroups.values().size());
        Map<String,String> upDownResults = new HashMap<>();
        //some kind of up/down object per set of teams
        Map<Integer,AbstractMap.SimpleEntry<List<Team>,List<Team>>> xx = new HashMap<>();
        for(Map.Entry < Integer, List < Team >> teamsInConfEntry :_teamsInConferenceGroups.entrySet()){
            UpDownGroup udg = new UpDownGroup(teamsInConfEntry);
            whatever.add(udg);

            //get the up teams, and have them go from their conference into one from the upper conferencegroup
            if(udg.shouldHaveUpTeams()){
               // xx.get(udg._confGroup - 1).getKey().addAll();//TransitionTeamToNewConference(udg.get_upTeams(),udg._confGroup - 1);
            }

            if(udg.shouldHaveDownTeams()){
                TransitionTeamToNewConference(udg.get_downTeams(), udg._confGroup + 1);
            }

            //get the bottom teams and have them go from heir conference into one from he lower conferencgroup
        }
        //Top level has no ups,

        //Bottom has no downs
    }

    private void TransitionTeamToNewConference(List<Team> teams,  Integer newConfGroup) {

    }

    private class UpDownGroup {
        private List<Team> _downTeams;
        private List<Team> _upTeams;

        public List<Team> get_downTeams() {
            return _downTeams;
        }

        public List<Team> get_upTeams() {
            return _upTeams;
        }

        private Integer _confGroup;

        public UpDownGroup(Map.Entry<Integer,List<Team>> entrySet){
            List<Team> teams = entrySet.getValue();
            _confGroup = entrySet.getKey();
            if(shouldHaveUpTeams()){
                _upTeams.add(teams.remove(0));
                _upTeams.add(teams.remove(0));
            }

            if(shouldHaveDownTeams()){
                _downTeams.add(teams.remove(teams.size() - 1));
                _downTeams.add(teams.remove(teams.size() - 1));
            }
        }

        public boolean shouldHaveUpTeams(){
            return _confGroup > 0;
        }

        public boolean shouldHaveDownTeams(){
            return _confGroup + 1 < MoverUtils.ConferenceNames.values().length / 2;
        }
    }
}