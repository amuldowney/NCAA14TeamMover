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
        Document document = new Document(DocumentType.XHTMLStrict);
        for (Conference conference : year.get_conferences().values()) {
            Table confTable = new Table();

            confTable.setBorder("1");
            confTable.appendChild(new Tr().appendText(conference.get_name()));

            for (Team team : conference.get_teams()) {
                confTable.appendChild(new Tr().appendChild(new Td().appendText(team.toString())));
            }
            document.body.appendChild(confTable);
        }

        System.out.println( document.write() );
    }

    public static void writeUpAndDownGroups(){
        Document document = new Document(DocumentType.XHTMLStrict);
        Table transTable = new Table();
        transTable.setBorder("1");
        transTable.appendChild(new Tr().appendText("Conference Moves"));

        //foreach in the list







    }

}
