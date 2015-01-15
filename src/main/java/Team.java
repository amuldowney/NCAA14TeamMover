/**
 * Created by Clues on 1/15/15.
 */
public class Team {

    private String _teamName;
    private Conference _conferenceIn;
    private int _ovr;
    private int _record;
    private int _standing;

    public int get_record() {
        return _record;
    }

    public void set_record(int _record) {
        this._record = _record;
    }

    public int get_standing() {
        return _standing;
    }

    public void set_standing(int _standing) {
        this._standing = _standing;
    }

    public String get_teamName() {
        return _teamName;
    }

    public void set_teamName(String _teamName) {
        this._teamName = _teamName;
    }

    public Conference get_conferenceIn() {
        return _conferenceIn;
    }

    public void set_conferenceIn(Conference _conferenceIn) {
        this._conferenceIn = _conferenceIn;
    }

    public int get_ovr() {
        return _ovr;
    }

    public void set_ovr(int _ovr) {
        this._ovr = _ovr;
    }
}
