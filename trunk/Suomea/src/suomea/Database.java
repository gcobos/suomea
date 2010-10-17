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

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author drone
 */
public class Database {

    private Connection conn;
    private Statement stat;
    private PreparedStatement prep;

    private Database() throws Exception
    {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:data/suomea.sqlite");
    }

    public static Database getInstance ()
    {
        if (ref == null) {
            // it's ok, we can call this constructor
            try {
                ref = new Database();
            } catch (Exception e) {
                System.out.println("getInstance says "+e.toString());
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
            stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(queryString);
            return rs;
        } catch (Exception e) {
            System.out.println("Query says "+e.toString());
            return null;
        }
    }

    /* Returns a ResultSet, same with positional quoted string parameters, instead each sign '?' */
    public ResultSet query (String queryString, String[] parameters)
    {
        try {
            prep = conn.prepareStatement(queryString);
            for (int i = 1; i <= parameters.length; i++) {
                prep.setString(i,parameters[i-1]);
            }
            ResultSet rs = prep.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println("Query says "+e.toString());
            return null;
        }
    }

    public void insert (String table, Hashtable<String, String> vars)
    {
        String query = "INSERT INTO \""+table+"\" ";

        Set<String> set = vars.keySet();
        Iterator<String> itr = set.iterator();
        String str;
        StringBuffer bufferColumns = new StringBuffer();
        StringBuffer bufferValues = new StringBuffer();
        while (itr.hasNext()) {
            str = itr.next();
            bufferColumns.append("'" + str + "'");
            bufferValues.append("'" + vars.get(str) + "'");
            if (itr.hasNext()) {
                bufferColumns.append(",");
                bufferValues.append(",");
            }
        }
        query = query.concat("("+bufferColumns.toString()+")");
        query = query.concat("VALUES ("+bufferValues.toString()+")");
        /*if (where.length()>0) {
            query = query.concat(" WHERE "+where+";");
        }*/

        try {
            PreparedStatement prep = conn.prepareStatement(query);
            conn.setAutoCommit(false);
            prep.execute();
            conn.setAutoCommit(true);
        } catch (Exception e) {
            System.err.println("Error on insert " + e.toString());
        }
    }

    public void update (String table, Hashtable<String, String> vars, String where)
    {
        String query = "UPDATE \""+table+"\" SET ";
        
        Set<String> set = vars.keySet();
        Iterator<String> itr = set.iterator();
        String str;
        StringBuffer buffer = new StringBuffer();
        while (itr.hasNext()) {
            str = itr.next();
            // Use carefully, no quotes!
            buffer.append(str + "=" + vars.get(str));
            if (itr.hasNext()) {
                buffer.append(",");
            }
        }
        query = query.concat(buffer.toString());
        if (where.length()>0) {
            query = query.concat(" WHERE "+where+";");
        }
        
        try {
            PreparedStatement prep = conn.prepareStatement(query);
            conn.setAutoCommit(false);
            prep.execute();
            conn.setAutoCommit(true);
        } catch (Exception e) {
            System.err.println("Error on update " + e.toString());
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
