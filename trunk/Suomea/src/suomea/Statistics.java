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
 * Sumea; if not, write to the Free Software Foundation, Inc., 51 Franklin St,
 * Fifth Floor, Boston, MA 02110-1301 USA
 */

package suomea;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author drone
 */
public class Statistics {

    public Date dateFrom = null;
    public Date dateTo = null;
    public int exerciseType = 0;
    public int dictionaryId = 0;
    public enum GroupBy { EXERCISE, DAY, WEEK, MONTH, YEAR };
    public GroupBy groupBy = GroupBy.EXERCISE;

    public Statistics ()
    {
    }

    // Retrieves statistics table grouping by day
    public List retrieveStatistics ()
    {
        List table = new ArrayList<Object[]>();
        Database db = Database.getInstance();
        ResultSet rs;

        String query = "SELECT MIN(cdate) as date,AVG(evaluation) evaluation,SUM(corrects) as corrects,SUM(fails) as fails FROM statistics WHERE 1=1 ";

        if (exerciseType!=0) {  // Select only a exercise
            query = query.concat("AND type=" + exerciseType);
        }

        if (dictionaryId!=0) {  // Select only a type of exercise?
            query = query.concat("AND dictionaryId=" + dictionaryId);
        }

        if (dateFrom!=null) {  // Select a range beginning from selected date
            query = query.concat("AND cdate>='" + dateFrom.toString() + "'");
        }

        if (dateTo!=null) {  // Select a range beginning from selected date
            query = query.concat("AND cdate>='" + dateFrom.toString() + "'");
        }

        switch (this.groupBy) {
            case EXERCISE:
                query = query.concat(" GROUP BY rowid ORDER BY cdate;");
                break;
            case DAY:
                query = query.concat(" GROUP BY Date(cdate) ORDER BY Date(cdate);");
                break;
            case WEEK:
                query = query.concat(" GROUP BY strftime('%Y-%W',cdate) ORDER BY strftime('%Y-%W',cdate);");
                break;
            case MONTH:
                query = query.concat(" GROUP BY strftime('%Y-%m',cdate) ORDER BY strftime('%Y-%m',cdate);");
                break;
            default:
                query = query.concat(" GROUP BY strftime('%Y',cdate) ORDER BY strftime('%Y',cdate);");
        }

        //System.out.println("Query: "+query);

        try {
            rs = db.query(query);
            while (rs.next()) {
                Object[] row = new Object[4];
                row[0] = rs.getString("date");
                row[1] = rs.getFloat("evaluation");
                row[2] = rs.getInt("corrects");
                row[3] = rs.getInt("fails");
                table.add(row);
            }
            rs.close();
        } catch (Exception e) {

        }
        return table;
    }

}
