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

/*
 * StatisticsDialog.java
 *
 * Created on 10-oct-2010, 23:03:42
 */
package suomea;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author bicha
 */
public class StatisticsDialog extends javax.swing.JDialog {

    StatisticsDataModel statistics;
    List<String[]> dictionaryList;
    Dictionary dictionary;
    Calendar cal;
    DateFormat df;

    /** Creates new form StatisticsDialog */
    public StatisticsDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        statistics = new StatisticsDataModel();
        cal = GregorianCalendar.getInstance();
        df = new SimpleDateFormat("dd/MM/yyyy");
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)-1);
        statistics.setDateFrom(cal.getTime());

        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)+1);
        statistics.setDateTo(cal.getTime());

        dictionary = Dictionary.getInstance();
        dictionaryList = dictionary.getDictionaryList();
        String[] dictionaries = new String[dictionaryList.size()+1];
        dictionaries[0] = "All dictionaries";
        for (int i = 0; i < dictionaryList.size(); i++) {
            dictionaries[i + 1] = dictionaryList.get(i)[1];
        }

        initComponents();

        dateFrom.setText(df.format(statistics.getDateFrom()));
        dateTo.setText(df.format(statistics.getDateTo()));

        this.dictionaryId.setModel(new DefaultComboBoxModel(dictionaries));
        this.jTable1.setModel(statistics);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dateFrom = new javax.swing.JFormattedTextField();
        dateTo = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        exerciseType = new javax.swing.JComboBox();
        groupBy = new javax.swing.JComboBox();
        dictionaryId = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        RefreshStat = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N
        getContentPane().setLayout(new java.awt.FlowLayout());

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.PAGE_AXIS));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(suomea.SuomeaApp.class).getContext().getResourceMap(StatisticsDialog.class);
        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 100));

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        dateFrom.setText(resourceMap.getString("dateFrom.text")); // NOI18N
        dateFrom.setName("dateFrom"); // NOI18N
        dateFrom.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateFromPropertyChange(evt);
            }
        });

        dateTo.setText(resourceMap.getString("dateTo.text")); // NOI18N
        dateTo.setName("dateTo"); // NOI18N
        dateTo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateToPropertyChange(evt);
            }
        });

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        exerciseType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All types", "Vocabulary test" }));
        exerciseType.setName("exerciseType"); // NOI18N
        exerciseType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exerciseTypeActionPerformed(evt);
            }
        });

        groupBy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Exercise", "Day", "Week", "Month", "Year" }));
        groupBy.setName("groupBy"); // NOI18N
        groupBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                groupByActionPerformed(evt);
            }
        });

        dictionaryId.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        dictionaryId.setName("dictionaryId"); // NOI18N
        dictionaryId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dictionaryIdActionPerformed(evt);
            }
        });

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        RefreshStat.setText(resourceMap.getString("RefreshStat.text")); // NOI18N
        RefreshStat.setName("RefreshStat"); // NOI18N
        RefreshStat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshStatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dateTo)
                    .addComponent(dateFrom, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(groupBy, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exerciseType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(dictionaryId, 0, 155, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(RefreshStat, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(dateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exerciseType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(dictionaryId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(dateTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(groupBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addComponent(RefreshStat)))
        );

        jPanel3.add(jPanel1);

        jPanel2.setName("jPanel2"); // NOI18N

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setName("jScrollPane2"); // NOI18N
        jScrollPane2.setPreferredSize(new java.awt.Dimension(700, 200));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setPreferredSize(null);
        jScrollPane2.setViewportView(jTable1);

        jPanel2.add(jScrollPane2);

        jPanel3.add(jPanel2);

        getContentPane().add(jPanel3);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RefreshStatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshStatActionPerformed
        StatisticsDataModel statsModel = (StatisticsDataModel) jTable1.getModel();
        statsModel.retrieveStatistics();

        statsModel.fireTableDataChanged();

        jTable1.revalidate();
    }//GEN-LAST:event_RefreshStatActionPerformed

    private void dictionaryIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dictionaryIdActionPerformed
        int index = this.dictionaryId.getSelectedIndex();
        String dictionaryID = this.dictionaryList.get(index)[0];
      
        this.statistics.setDictionaryId(Integer.parseInt(dictionaryID));
    }//GEN-LAST:event_dictionaryIdActionPerformed

    private void exerciseTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exerciseTypeActionPerformed
        this.statistics.setExerciseType(this.exerciseType.getSelectedIndex());
    }//GEN-LAST:event_exerciseTypeActionPerformed

    private void groupByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_groupByActionPerformed
        this.statistics.setGroupBy(StatisticsDataModel.GroupBy.values()[this.groupBy.getSelectedIndex()]);
    }//GEN-LAST:event_groupByActionPerformed

    private void dateFromPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateFromPropertyChange
        try {
            statistics.setDateFrom(this.df.parse(dateFrom.getText()));

        } catch (ParseException e) {
            //e.printStackTrace();
        }
    }//GEN-LAST:event_dateFromPropertyChange

    private void dateToPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateToPropertyChange
        try {
            statistics.setDateTo(this.df.parse(dateTo.getText()));

        } catch (ParseException e) {
            //e.printStackTrace();
        }
    }//GEN-LAST:event_dateToPropertyChange

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton RefreshStat;
    private javax.swing.JFormattedTextField dateFrom;
    private javax.swing.JFormattedTextField dateTo;
    private javax.swing.JComboBox dictionaryId;
    private javax.swing.JComboBox exerciseType;
    private javax.swing.JComboBox groupBy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
