import amuldowney.ncaaa.mover.League;
import amuldowney.ncaaa.mover.Team;

import javax.swing.*;

/**
 * Created by Clues on 1/16/15.
 */
public class ConferenceDisplays {
    private JTextArea penFifteenTextArea;
    private JTextArea penFifteenTextArea1;
    private JTextArea penFifteenTextArea2;
    private JTextArea penFifteenTextArea3;
    private JTextArea penFifteenTextArea4;


    public void setData(League data) {
        for(Team X : data.getYear(2014).get_conferences().elements().nextElement().get_teams()){
            penFifteenTextArea.append(X.get_teamName());
        }
    }

    public void getData(League data) {
    }

    public boolean isModified(League data) {
        return false;
    }
}
