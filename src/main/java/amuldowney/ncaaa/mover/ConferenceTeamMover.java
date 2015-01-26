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
        this.EstablishConferenceGroups();
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

    public void GenerateTransitionTeams2(){
        Conference firstConf = null, secondConf = null;
        Team firstDownTeam = null, secondDownTeam = null;
        for (int i = 0; i < MoverUtils.ConferenceNames.values().length-3; i = i + 1) {
            Conference tempC1 = _leagueYear.get_conference(ConferenceNames.values()[i]);
            Conference tempC2 = _leagueYear.get_conference(ConferenceNames.values()[i+2]);

            String top = tempC1.get_teams().get(tempC1.get_teams().size()-1) + tempC1.get_name();
            String bot = tempC2.get_teams().get(0) + tempC2.get_name();

            System.out.println(String.format("%s <-> %s",top, bot));

            top = tempC1.get_teams().get(tempC1.get_teams().size()-2) + tempC1.get_name();
            bot = tempC2.get_teams().get(1) + tempC2.get_name();

            System.out.println(String.format("%s <-> %s",top, bot));
        }

    }
}