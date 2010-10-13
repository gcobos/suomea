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
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author drone
 */
public class StatisticsDataModel extends AbstractTableModel {

    public Date dateFrom = null;
    public Date dateTo = null;
    public int exerciseType = 0;
    public int dictionaryId = 0;
    public enum GroupBy { EXERCISE, DAY, WEEK, MONTH, YEAR };
    public GroupBy groupBy = GroupBy.EXERCISE;

    private List<Object[]> data = null;

    private static String[] columnNames = {"Exercises",
        "Date",
        "Correct Answers",
        "Failed answers",
        "Evaluation"};


    public StatisticsDataModel ()
    {
        retrieveStatistics();

    }

    // Retrieves statistics table grouping by day
    public boolean retrieveStatistics ()
    {
        data = new ArrayList<Object[]>();
        Database db = Database.getInstance();
        ResultSet rs;

        String query = "SELECT COUNT(rowid) exercises, MIN(cdate) as date,SUM(corrects) as corrects,SUM(fails) as fails,AVG(evaluation) evaluation FROM statistics WHERE 1=1 ";

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
                Object[] row = new Object[5];
                row[0] = rs.getInt("exercises");
                row[1] = rs.getString("date");
                row[2] = rs.getInt("corrects");
                row[3] = rs.getInt("fails");
                row[4] = rs.getFloat("evaluation");
               
                data.add(row);
               
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public String getColumnName (int col)
    {
        return columnNames[col].toString();
    }

    public int getRowCount()
    {
    	return data.size();
    }

    public int getColumnCount()
    {
    	return columnNames.length;
    }

    public Object getValueAt (int row, int col)
    {
    	Object value = data.get(row)[col];
    	return value;
    }

    public Object[] getElementAt (int row)
    {
    	return data.get(row);
    }

    @Override
    public boolean isCellEditable (int row, int col)
    {
    	return false;
    }

    public void addElement (Object[] row)
    {
    	data.add(row);
    	fireTableRowsInserted(0,data.size()-1);
    }

    @Override
    public void setValueAt (Object value, int row, int col)
    {
    }

}
