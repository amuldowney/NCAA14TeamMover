package amuldowney.ncaaa.mover;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Clues on 1/16/15.
 */
public class LeagueLoaderSaver {

    public static void SaveLeague(League league, File file) throws IOException {
        FileOutputStream outFile = new FileOutputStream(file);
        JsonWriter jw = new JsonWriter(outFile);
        jw.write(league);
        jw.close();
    }

    public static League LoadLeague(File file) throws IOException {
        FileInputStream inFile = new FileInputStream(file);
        JsonReader jr = new JsonReader(inFile);
        return (League) jr.readObject();
    }
}