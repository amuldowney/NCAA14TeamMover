package amuldowney.ncaaa.mover;

import com.hp.gagawa.java.Document;
import com.hp.gagawa.java.DocumentType;
import com.hp.gagawa.java.elements.Table;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Tr;

/**
 * Created by Clues on 1/18/15.
 */
public class HTMLer {


    public static void writeLeagueYear(LeagueYear year){
       writeLeagueYearWithOptions(year, false);
    }

    public static void writeLeagueYearTransitions(LeagueYear year){
        writeLeagueYearWithOptions(year, true);
    }

    private static void writeLeagueYearWithOptions(LeagueYear year, boolean highlightMoves){
        Document document = new Document(DocumentType.XHTMLStrict);
        for (Conference conference : year.get_conferences().values()) {
            Table confTable = new Table();

            confTable.setBorder("1");
            confTable.appendChild(new Tr().appendText(conference.get_name()));

            for (int i = 0; i < conference.get_teams().size(); i++) {
                Team team = conference.get_teams().get(i);
                Tr row = new Tr().appendChild(new Td().appendText(team.toString()));

                if(highlightMoves && i <= 1) row.setBgcolor("Green");
                if(highlightMoves && i >= conference.get_teams().size() - 2) row.setBgcolor("Red");

                confTable.appendChild(row);
            }
            document.body.appendChild(confTable);
        }

        System.out.println( document.write() );
    }
}
