import java.util.Dictionary;

/**
 * Created by Clues on 1/15/15.
 */
public class League {

    private Dictionary<Integer, LeagueYear> _years;

    public void addYear(int year) {
        LeagueYear temp = new LeagueYear(year);

    }

    public LeagueYear getYear(int year) {
        return _years.get(year);
    }
}
