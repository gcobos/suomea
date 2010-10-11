/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package suomea;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
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

    public ArrayList<String[]> getDictionaryList ()
    {
        ArrayList<String[]> list = new ArrayList<String[]>();
        Database db = Database.getInstance();
        Integer id;
        ResultSet rs;

        try {
            rs = db.query("SELECT id, original || '->' || translation as name FROM dictionary ORDER BY id;");
            while (rs.next()) {
                // Check if the word is ok
                id = rs.getInt("id");
                String[] option = { id.toString(), rs.getString("name") };
                list.add(option);
            }
            rs.close();
        } catch (Exception e) {
            System.err.println("Error retrieving list of dictionaries");
        }
        return list;
    }

    // Changes the default dictionary
    public void setDictionary (int dictionaryId)
    {
        this.dictionaryId = dictionaryId;
    }

    // Retrieves a non-used word from the selected dictionary
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
                System.out.println("Error in query "+e.toString());
            }
        }

        return result;
    }
    
    // Increments the 'used' column in the words used in the exercises
    public void updateUsedWords (Vector<String> words)
    {
        Database db = Database.getInstance();

        for (String word : words) {
            Hashtable<String,String> vars = new Hashtable<String,String>();
            vars.put("used","(used + 1)");      // Sqlite doesn't get the previous value, so always reach only 1
            db.update("words", vars, "original = '"+ word + "'");
        }
    }

    private static Dictionary ref;
}
