/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package suomea;

import java.sql.ResultSet;



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

        try {
            rs = db.query("SELECT original, translation FROM words WHERE rowid=34;");
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
