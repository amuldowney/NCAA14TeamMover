package amuldowney.teammover;

import java.util.Dictionary;

/**
 * Created by Clues on 1/15/15.
 */
public class LeagueYear {

    private int _year;
    private Dictionary<MoverUtils.ConferenceNames,Conference> _conferences;

    public LeagueYear(int _year) {
        this._year = _year;
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

    public Dictionary<MoverUtils.ConferenceNames, Conference> get_conferences() {
        return _conferences;
    }

    public void set_conferences(Dictionary<MoverUtils.ConferenceNames, Conference> _conferences) {
        this._conferences = _conferences;
    }

    public Conference get_conference(MoverUtils.ConferenceNames confName) {

        return _conferences.get(confName);

    }
}
