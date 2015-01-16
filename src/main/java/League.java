import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by Clues on 1/15/15.
 */
public class League {

    private Dictionary<Integer, LeagueYear> _years = new Hashtable<>();
    public LeagueYear currentYear;

    public League(int year){
        addYear(year);
    }

    public void addYear(int year) {
        LeagueYear temp = new LeagueYear(year);
        currentYear = temp;
        _years.put(year, temp);
    }

    public LeagueYear getYear(int year) {
        return _years.get(year);
    }
}
