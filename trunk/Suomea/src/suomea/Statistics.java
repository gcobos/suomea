/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package suomea;

import java.sql.ResultSet;
import java.text.DateFormat;
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
    public enum GroupBy { DAY, WEEK, MONTH, YEAR };
    public GroupBy groupBy = GroupBy.DAY;

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

        if (exerciseType!=0) {  // Select only a type of exercise?
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
            case DAY:
                query = query.concat(" GROUP BY Date(cdate) ORDER BY Date(cdate);");
                break;
            case WEEK:
                query = query.concat(" GROUP BY Date(cdate) ORDER BY Date(cdate);");
                break;
            case MONTH:
                query = query.concat(" GROUP BY Date(cdate) ORDER BY Date(cdate);");
                break;
            default:
                query = query.concat(" GROUP BY Date(cdate) ORDER BY Date(cdate);");
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
