package amuldowney.teammover;

import java.util.List;

/**
 * Created by Clues on 1/15/15.
 */
public class Conference {

    private String _name;
    private List<Team> _teams;

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

}
