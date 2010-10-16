/*
 * Copyright 2007-2010
 * This file is part of Suomea.
 *
 * Suomea is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * Suomea is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Suomea; if not, write to the Free Software Foundation, Inc., 51 Franklin St,
 * Fifth Floor, Boston, MA 02110-1301 USA
 */
package suomea;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author drone
 */
public class Dictionary {

    private int dictionaryId;
    private Hashtable<Integer, Integer> usedWords;       // word id => number of times used
    private static Dictionary ref;

    private Dictionary() {
        dictionaryId = 1;
        usedWords = new Hashtable<Integer, Integer>();
    }

    public static Dictionary getInstance() {
        if (ref == null) {
            // it's ok, we can call this constructor
            ref = new Dictionary();
        }
        return ref;
    }

    public int getId() {
        return dictionaryId;
    }

    public List<String[]> getDictionaryList() {
        List<String[]> list = new ArrayList<String[]>();
        Database db = Database.getInstance();
        Integer id;
        ResultSet rs;

        try {
            rs = db.query("SELECT id, original || '->' || translation as name FROM dictionary ORDER BY id;");
            while (rs.next()) {
                // Check if the word is ok
                id = rs.getInt("id");
                String[] option = {id.toString(), rs.getString("name")};
                list.add(option);
            }
            rs.close();
        } catch (Exception e) {
            System.err.println("Error retrieving list of dictionaries");
        }
        return list;
    }

    // Changes the default dictionary
    public void setDictionary(int dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    // Retrieves a non-used word from the selected dictionary
    public String[] getRandomWord() {
        Database db = Database.getInstance();

        ResultSet rs;
        String[] result = new String[2];
        boolean isOk = false;
        int rowid;

        while (!isOk) {
            try {

                rs = db.query("SELECT rowid, original, translation FROM words WHERE dictionaryId=" + this.getId() + " ORDER BY used,random() LIMIT 1;");
                if (rs.next()) {
                    // Check if the word is ok
                    rowid = rs.getInt("rowid");
                    if (!usedWords.containsKey(rowid)) {
                        result[0] = rs.getString("original");
                        StringTokenizer st = new StringTokenizer(rs.getString("translation"), ";");
                        result[1] = st.nextToken();
                        while (st.hasMoreTokens()) {
                            result[1] = result[1] + "; " + st.nextToken();
                            if (result[1].length() > 40) {
                                break;
                            }
                        }
                        if (result[0].length() > 0 && result[1].length() > 0) {
                            isOk = true;
                            usedWords.put(rowid, 1);
                        }
                    }
                }
                rs.close();

            } catch (Exception e) {
                System.out.println("Error in query " + e.toString());
            }
        }

        return result;
    }

    // Retrieves a non-used word from the selected dictionary
    public String getRandomCategory() {
        Database db = Database.getInstance();

        ResultSet rs;
        String result = "";

        try {

            rs = db.query("SELECT category FROM words WHERE dictionaryId=" + this.getId() + " ORDER BY used,random() LIMIT 1;");
            if (rs.next()) {
                result = rs.getString("category");
            }
            rs.close();

        } catch (Exception e) {
            System.out.println("Error in query " + e.toString());
        }


        return result;
    }

    // Increments the 'used' column in the words used in the exercises
    public void updateUsedWords(Vector<String> words) {
        Database db = Database.getInstance();

        for (String word : words) {
            Hashtable<String, String> vars = new Hashtable<String, String>();
            vars.put("used", "(used + 1)");      // Sqlite doesn't get the previous value, so always reach only 1
            db.update("words", vars, "original = '" + word + "'");
        }
    }

    public ArrayList<String[]> prepareCategoryTest (String category, int numWords, int totalQuestions)
    {
        Database db = Database.getInstance();

        ArrayList<String[]> list = new ArrayList<String[]>();

        ResultSet rs;
        String[] result;

        String query = "SELECT original,category, rorder FROM (SELECT original, category, random() as rorder FROM words WHERE dictionaryId=" +this.getId() +
               " AND category = '" + category + "' ORDER BY used, random() LIMIT "+numWords+") UNION" +
               " SELECT original,category, rorder FROM (SELECT original, category, random() as rorder FROM words WHERE dictionaryId=" + this.getId() +
               " AND category != '" + category + "' ORDER BY used, random() LIMIT "+totalQuestions+  /* Limit cuts the total result, instead of the subquery :S */
               ") ORDER BY rorder LIMIT "+totalQuestions;

        //System.out.println("El query "+query);
        try {
            rs = db.query(query);

            while (rs.next()) {
                result = new String[2];
                result[0] = rs.getString("original");
                result[1] = rs.getString("category");
                list.add(result);
            }
            rs.close();

        } catch (Exception e) {
            System.out.println("Error in query " + e.toString());
        }

        return list;
    }
}
