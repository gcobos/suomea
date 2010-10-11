/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package suomea;

import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.Vector;



/**
 *
 * @author drone
 */
public class Dictionary {

    private int dictionaryId;
    private Hashtable<Integer,Integer> usedWords;       // word id => number of times used

    private Dictionary()
    {
        dictionaryId = 1;
        usedWords = new Hashtable<Integer,Integer>();
    }

    public static Dictionary getInstance ()
    {
        if (ref == null) {
            // it's ok, we can call this constructor
            ref = new Dictionary();
        }
        return ref;
    }

    public int getId ()
    {
        return dictionaryId;
    }

    public String[] getRandomWord ()
    {
        Database db = Database.getInstance();

        ResultSet rs;
        String[] result = new String[2];
        Random rand;
        boolean isOk =false;
        int rowid;

        while (!isOk) {
            try {

                rs = db.query("SELECT rowid, original, translation FROM words WHERE dictionaryId="+ this.getId() +" ORDER BY used,random() LIMIT 1;");
                if (rs.next()) {
                    // Check if the word is ok
                    rowid=rs.getInt("rowid");
                    if (!usedWords.containsKey(rowid)) {
                        result[0] = rs.getString("original");
                        result[1] = rs.getString("translation");
                        if (result[0].length()>0 && result[1].length()>0) {
                            isOk=true;
                            usedWords.put(rowid,1);
                        }
                    }
                }
                rs.close();

            } catch (Exception e) {
                System.out.println("Fallo en el query "+e.toString());
            }
        }

        return result;
    }
    
    /* Increments the 'used' column in the words used in the exercises */
    public void updateUsedWords (Vector<String> words)
    {
        Database db = Database.getInstance();

        for (String word : words) {
            Hashtable<String,String> vars = new Hashtable<String,String>();
            vars.put("used","(used + 1)");
            db.update("words", vars, "original = '"+ word + "'");
        }
    }

    private static Dictionary ref;
}
