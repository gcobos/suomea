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

package suomea.modules.translator;

import suomea.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author drone
 */
public class TranslatorDataModel extends AbstractTableModel {

    private List<Object[]> data = null;

    private int dictionaryId;

    private static String[] columnNames = {"Original", "Translation"};

    public TranslatorDataModel (int dictionaryId)
    {
        this.dictionaryId = dictionaryId;

        data = new ArrayList<Object[]>();
    }

    public int getDictionaryId ()
    {
        return dictionaryId;
    }

    public void setDictionaryId (int dictionaryId)
    {
        this.dictionaryId = dictionaryId;
    }

    /* Search translations for a word */
    public boolean retrieveTranslations (String word)
    {
        data = new ArrayList<Object[]>();

        if (word.length()==0) {
            return false;
        }

        Database db = Database.getInstance();
        String[] parameters = {"%"+word+"%", "%"+word+"%"};

        ResultSet rs;
        String[] result;

        String query = "SELECT words.original, words.translation FROM words,dictionary" +
               " WHERE words.dictionaryId=dictionary.id" +
               " AND (words.original LIKE ? " +
               " OR words.translation LIKE ?)";

        if (dictionaryId!=0) {
            query=query.concat(" AND words.dictionaryId="+Integer.toString(dictionaryId));
        }

        //System.out.println("El query "+query);
        try {
            rs = db.query(query, parameters);
            while (rs.next()) {
                result = new String[2];
                result[0] = rs.getString("original");
                result[1] = rs.getString("translation");
                data.add(result);
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
