/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package suomea;

import java.sql.ResultSet;
import java.util.Random;



/**
 *
 * @author drone
 */
public class Dictionary {

    public String[] getRandomWord ()
    {
        Database db = Database.getInstance();

        ResultSet rs;
        String[] result = new String[2];
        int num = 0;
        Random rand;

        try {
            rs = db.query("SELECT COUNT(*) FROM words;");
            if (rs.next()) {
                num = rs.getInt(1);
            }
            rand = new Random();

            rs = db.query("SELECT original, translation FROM words WHERE rowid="+ rand.nextInt(num) + ";");
            if (rs.next()) {
                 result[0] = rs.getString("original");
                 result[1] = rs.getString("translation");
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Fallo en el query "+e.toString());
        }

        return result;
    }
}
