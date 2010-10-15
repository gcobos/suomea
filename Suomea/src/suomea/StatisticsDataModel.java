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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author drone
 */
public class StatisticsDataModel extends AbstractTableModel {

    private Date dateFrom = null;
    private Date dateTo = null;
    private int exerciseType = 0;
    private int dictionaryId = 0;
    public enum GroupBy { 
        EXERCISE (0), DAY (1), WEEK (2), MONTH (3), YEAR (4);
        
        private int value;

        GroupBy (int value)
        {
            this.value = value;
        }

        public int getValue ()
        {
            return value;
        }

    }

    private GroupBy groupBy = GroupBy.EXERCISE;

    private List<Object[]> data = null;

    private static String[] columnNames = {"Exercises",
        "Date",
        "Correct Answers",
        "Failed answers",
        "Evaluation",
        "Dictionary",
    };

    public StatisticsDataModel ()
    {

        data = new ArrayList<Object[]>();
    }

    public Date getDateFrom ()
    {
        return dateFrom;
    }

    public void setDateFrom (Date from)
    {
        dateFrom = from;
    }

    public Date getDateTo ()
    {
        return dateTo;
    }
    
    public void setDateTo (Date to)
    {
        dateTo = to;
    }

    public int getExerciseType ()
    {
        return exerciseType;
    }

    public void setExerciseType (int exerciseType)
    {
        this.exerciseType = exerciseType;
    }

    public int getDictionaryId ()
    {
        return dictionaryId;
    }

    public void setDictionaryId (int dictionaryId)
    {
        this.dictionaryId = dictionaryId;
    }

    public GroupBy getGroupBy ()
    {
        return groupBy;
    }

    public void setGroupBy (GroupBy groupBy)
    {
        this.groupBy = groupBy;
    }


    // Retrieves statistics table grouping by day
    public boolean retrieveStatistics ()
    {
        data = new ArrayList<Object[]>();
        Database db = Database.getInstance();
        ResultSet rs;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        String query = "SELECT COUNT(s.rowid) exercises, MIN(s.cdate) as date,SUM(s.corrects) as corrects," +
                "SUM(s.fails) as fails,AVG(s.evaluation) as evaluation, d.original || '->' || d.translation as dictionary " +
                "FROM statistics as s, dictionary as d WHERE d.id = s.dictionaryId";

        if (exerciseType!=0) {  // Select only a exercise
            query = query.concat(" AND s.type=" + exerciseType);
        }

        if (dictionaryId!=0) {  // Select only a type of exercise?
            query = query.concat(" AND s.dictionaryId=" + dictionaryId);
        }

        if (dateFrom!=null) {  // Select a range beginning from selected date
            query = query.concat(" AND Date(s.cdate)>='" + df.format(dateFrom) + "'");
        }

        if (dateTo!=null) {  // Select a range beginning from selected date
            query = query.concat(" AND Date(s.cdate)<='" + df.format(dateTo) + "'");
        }

        switch (this.groupBy) {
            case EXERCISE:
                query = query.concat(" GROUP BY s.rowid,s.dictionaryId ORDER BY s.cdate, s.dictionaryId;");
                break;
            case DAY:
                query = query.concat(" GROUP BY Date(s.cdate),s.dictionaryId ORDER BY Date(s.cdate), s.dictionaryId;");
                break;
            case WEEK:
                query = query.concat(" GROUP BY strftime('%Y-%W',s.cdate),s.dictionaryId ORDER BY strftime('%Y-%W',s.cdate), s.dictionaryId;");
                break;
            case MONTH:
                query = query.concat(" GROUP BY strftime('%Y-%m',s.cdate),s.dictionaryId ORDER BY strftime('%Y-%m',s.cdate), s.dictionaryId;");
                break;
            default:
                query = query.concat(" GROUP BY strftime('%Y',s.cdate),s.dictionaryId ORDER BY strftime('%Y',s.cdate), s.dictionaryId;");
        }

        //System.out.println("Query: "+query);

        try {
           
            rs = db.query(query);
            while (rs.next()) {
                Object[] row = new Object[6];
                row[0] = rs.getInt("exercises");
                row[1] = rs.getString("date");
                row[2] = rs.getInt("corrects");
                row[3] = rs.getInt("fails");
                row[4] = rs.getFloat("evaluation");
                row[5] = rs.getString("dictionary");
               
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
