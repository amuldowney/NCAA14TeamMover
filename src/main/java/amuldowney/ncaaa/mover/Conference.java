package amuldowney.ncaaa.mover;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clues on 1/15/15.
 */
public class Conference {

    private String _name;
    private List<Team> _teams = new ArrayList<>();

    public Conference(String _name) {
        this._name = _name;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public List<Team> get_teams() {
        return _teams;
    }

    public void set_teams(List<Team> _teams) {
        this._teams = _teams;
    }

    public void addTeam(Team team) {
        team.set_conferenceIn(this);
        _teams.add(team);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s (%d) [",_name,_teams.size()));
        _teams.forEach(team -> sb.append(team));
        sb.append("]");
        return sb.toString();
    }
}
