/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package suomea;

import java.sql.*;

/**
 *
 * @author drone
 */
public class Database {

    private Connection conn;
    private Statement stat;

    private Database() throws Exception
    {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:data/suomea.sqlite");
        stat = conn.createStatement();
    }

    public static Database getInstance ()
    {
        if (ref == null) {
            // it's ok, we can call this constructor
            try {
                ref = new Database();
            } catch (Exception e) {
                System.out.println("getInstance dice "+e.toString());
                return null;
            }
        }
        return ref;
    }

    public boolean test () throws Exception {

        Statement stat = conn.createStatement();
        stat.executeUpdate("drop table if exists people;");
        stat.executeUpdate("create table people (name, occupation);");
        PreparedStatement prep = conn.prepareStatement(
          "insert into people values (?, ?);");

        prep.setString(1, "Gandhi");
        prep.setString(2, "politics");
        prep.addBatch();
        prep.setString(1, "Turing");
        prep.setString(2, "computers");
        prep.addBatch();
        prep.setString(1, "Wittgenstein");
        prep.setString(2, "smartypants");
        prep.addBatch();

        conn.setAutoCommit(false);
        prep.executeBatch();
        conn.setAutoCommit(true);

        ResultSet rs = stat.executeQuery("select * from people;");
        while (rs.next()) {
          System.out.println("name = " + rs.getString("name"));
          System.out.println("job = " + rs.getString("occupation"));
        }
        rs.close();

        return true;
    }

    /* Returns a ResultSet */
    public ResultSet query (String queryString)
    {
        try {
            ResultSet rs = stat.executeQuery(queryString);
            return rs;
        } catch (Exception e) {
            System.out.println("El query dice "+e.toString());
            return null;
        }
    }

    public void close ()
    {
        try {
            conn.close();
        } catch (Exception e) {
            // pass
        }
    }

    private static Database ref;

}
