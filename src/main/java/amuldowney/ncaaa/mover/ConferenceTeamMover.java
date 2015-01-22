package amuldowney.ncaaa.mover;

import java.util.*;

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

        for (Map.Entry<Integer,List<MoverUtils.ConferenceNames>> conferenceGroup : MoverUtils.ConferenceGroups.entrySet()) {
            List<Team> teams = new ArrayList<>();
            conferenceGroup.getValue().forEach(confName -> teams.addAll(_leagueYear.get_conference(confName).get_teams()));
            Collections.sort(teams);
            _teamsInConferenceGroups.put(conferenceGroup.getKey(), teams);
        }
    }

    private void GenerateTransitionTeams(int numberOfTeams){
        //some kind of up/down object per set of teams

        //Top level has no ups,

        //Bottom has no downs

    }

    private class UpDownGroup {
        private List<Team> _downTeams;
        private List<Team> _upTeams;

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

        private boolean shouldHaveUpTeams(){
            return _confGroup > 0;
        }

        private boolean shouldHaveDownTeams(){
            return _confGroup + 1 < MoverUtils.ConferenceNames.values().length / 2;
        }



    }


}
