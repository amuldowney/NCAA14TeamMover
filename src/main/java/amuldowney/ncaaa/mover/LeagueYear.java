package amuldowney.ncaaa.mover;

import java.util.*;

/**
 * Created by Clues on 1/15/15.
 */
public class LeagueYear {

    private int _year;
    private Map<MoverUtils.ConferenceNames, Conference> _conferences;

    public LeagueYear(int _year) {
        this._year = _year;
        this._conferences = new HashMap<>();
        for (MoverUtils.ConferenceNames conferenceName : MoverUtils.ConferenceNames.values()) {
            _conferences.put(conferenceName, new Conference(conferenceName.toString()));
        }
    }

    public int get_year() {
        return _year;
    }

    public void set_year(int _year) {
        this._year = _year;
    }

    public Map<MoverUtils.ConferenceNames, Conference> get_conferences() {
        return _conferences;
    }

    public void set_conferences(Map<MoverUtils.ConferenceNames, Conference> _conferences) {
        this._conferences = _conferences;
    }

    public Conference get_conference(MoverUtils.ConferenceNames confName) {

        return _conferences.get(confName);

    }

    public void AddTeamWherever(Team team) {
        this.get_conference(randomName()).addTeam(team);
    }

    private MoverUtils.ConferenceNames randomName() {
        int pick = new Random().nextInt(MoverUtils.ConferenceNames.values().length);
        return MoverUtils.ConferenceNames.values()[pick];
    }

    public void addConferencedTeams(List<Team> teams) {
        teams.forEach(team -> _conferences.get(MoverUtils.byID(team.get_conferenceId())).addTeam(team));

        _conferences.values().forEach(conf -> Collections.sort(conf.get_teams()));
    }
}
