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
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author bicha
 */
public class StatisticsDialog extends javax.swing.JDialog {

    private StatisticsDataModel statistics;
    private List<String[]> dictionaryList;
    private Dictionary dictionary;
    private Calendar cal;
    private DateFormat df;
    private ChartPanel chartPanel;
    private JFreeChart chart;

    /** Creates new form StatisticsDialog */
    public StatisticsDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        statistics = new StatisticsDataModel();
        cal = GregorianCalendar.getInstance();
        df = new SimpleDateFormat("dd/MM/yyyy");
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
        statistics.setDateFrom(cal.getTime());

        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
        statistics.setDateTo(cal.getTime());

        dictionary = Dictionary.getInstance();
        dictionaryList = dictionary.getDictionaryList();
        String[] dictionaries = new String[dictionaryList.size() + 1];
        dictionaries[0] = "All dictionaries";
        for (int i = 0; i < dictionaryList.size(); i++) {
            dictionaries[i + 1] = dictionaryList.get(i)[1];
        }

        initComponents();

        dateFrom.setText(df.format(statistics.getDateFrom()));
        dateTo.setText(df.format(statistics.getDateTo()));

        this.dictionaryId.setModel(new DefaultComboBoxModel(dictionaries));
        this.jTable1.setModel(statistics);

        chart = createChart(statistics.createDataset());
        chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(null);
        graphPane.setViewportView(chartPanel);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        labelFromPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        inputFromPanel = new javax.swing.JPanel();
        dateFrom = new javax.swing.JFormattedTextField();
        labelTypePanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        inputTypePanel = new javax.swing.JPanel();
        exerciseType = new javax.swing.JComboBox();
        labelDictionaryPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        inputDictionaryPanel = new javax.swing.JPanel();
        dictionaryId = new javax.swing.JComboBox();
        labelToPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        inputToPanel = new javax.swing.JPanel();
        dateTo = new javax.swing.JFormattedTextField();
        lavelGroupPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        inputGroupPanel = new javax.swing.JPanel();
        groupBy = new javax.swing.JComboBox();
        spacerRefreshPanel = new javax.swing.JPanel();
        inputRefreshPanel = new javax.swing.JPanel();
        RefreshStat = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        graphPane = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N
        getContentPane().setLayout(new java.awt.BorderLayout(10, 10));

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.PAGE_AXIS));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(suomea.SuomeaApp.class).getContext().getResourceMap(StatisticsDialog.class);
        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        labelFromPanel.setName("labelFromPanel"); // NOI18N
        labelFromPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setMaximumSize(null);
        jLabel1.setMinimumSize(null);
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.setPreferredSize(null);
        labelFromPanel.add(jLabel1);

        jPanel1.add(labelFromPanel, new java.awt.GridBagConstraints());

        inputFromPanel.setName("inputFromPanel"); // NOI18N

        dateFrom.setText(resourceMap.getString("dateFrom.text")); // NOI18N
        dateFrom.setMinimumSize(new java.awt.Dimension(100, 25));
        dateFrom.setName("dateFrom"); // NOI18N
        dateFrom.setPreferredSize(new java.awt.Dimension(100, 25));
        dateFrom.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateFromPropertyChange(evt);
            }
        });
        inputFromPanel.add(dateFrom);

        jPanel1.add(inputFromPanel, new java.awt.GridBagConstraints());

        labelTypePanel.setName("labelTypePanel"); // NOI18N
        labelTypePanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jLabel3.setMaximumSize(null);
        jLabel3.setMinimumSize(null);
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setPreferredSize(null);
        labelTypePanel.add(jLabel3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel1.add(labelTypePanel, gridBagConstraints);

        inputTypePanel.setName("inputTypePanel"); // NOI18N
        inputTypePanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        exerciseType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All types", "Vocabulary test", "CategoryTest" }));
        exerciseType.setMinimumSize(new java.awt.Dimension(200, 25));
        exerciseType.setName("exerciseType"); // NOI18N
        exerciseType.setPreferredSize(new java.awt.Dimension(200, 25));
        exerciseType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exerciseTypeActionPerformed(evt);
            }
        });
        inputTypePanel.add(exerciseType);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(inputTypePanel, gridBagConstraints);

        labelDictionaryPanel.setName("labelDictionaryPanel"); // NOI18N
        labelDictionaryPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jLabel5.setMaximumSize(null);
        jLabel5.setMinimumSize(null);
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setPreferredSize(null);
        labelDictionaryPanel.add(jLabel5);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel1.add(labelDictionaryPanel, gridBagConstraints);

        inputDictionaryPanel.setName("inputDictionaryPanel"); // NOI18N
        inputDictionaryPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        dictionaryId.setMinimumSize(new java.awt.Dimension(200, 25));
        dictionaryId.setName("dictionaryId"); // NOI18N
        dictionaryId.setPreferredSize(new java.awt.Dimension(200, 25));
        dictionaryId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dictionaryIdActionPerformed(evt);
            }
        });
        inputDictionaryPanel.add(dictionaryId);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel1.add(inputDictionaryPanel, gridBagConstraints);

        labelToPanel.setName("labelToPanel"); // NOI18N
        labelToPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setMaximumSize(null);
        jLabel2.setMinimumSize(null);
        jLabel2.setName("jLabel2"); // NOI18N
        jLabel2.setPreferredSize(null);
        labelToPanel.add(jLabel2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel1.add(labelToPanel, gridBagConstraints);

        inputToPanel.setName("inputToPanel"); // NOI18N

        dateTo.setText(resourceMap.getString("dateTo.text")); // NOI18N
        dateTo.setMinimumSize(new java.awt.Dimension(100, 25));
        dateTo.setName("dateTo"); // NOI18N
        dateTo.setPreferredSize(new java.awt.Dimension(100, 25));
        dateTo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateToPropertyChange(evt);
            }
        });
        inputToPanel.add(dateTo);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel1.add(inputToPanel, gridBagConstraints);

        lavelGroupPanel.setName("lavelGroupPanel"); // NOI18N
        lavelGroupPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setMaximumSize(null);
        jLabel4.setMinimumSize(null);
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setPreferredSize(null);
        lavelGroupPanel.add(jLabel4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel1.add(lavelGroupPanel, gridBagConstraints);

        inputGroupPanel.setName("inputGroupPanel"); // NOI18N
        inputGroupPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        groupBy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Exercise", "Day", "Week", "Month", "Year" }));
        groupBy.setName("groupBy"); // NOI18N
        groupBy.setPreferredSize(new java.awt.Dimension(120, 25));
        groupBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                groupByActionPerformed(evt);
            }
        });
        inputGroupPanel.add(groupBy);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(inputGroupPanel, gridBagConstraints);

        spacerRefreshPanel.setName("spacerRefreshPanel"); // NOI18N
        spacerRefreshPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        jPanel1.add(spacerRefreshPanel, gridBagConstraints);

        inputRefreshPanel.setMinimumSize(new java.awt.Dimension(100, 37));
        inputRefreshPanel.setName("inputRefreshPanel"); // NOI18N
        inputRefreshPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        RefreshStat.setText(resourceMap.getString("RefreshStat.text")); // NOI18N
        RefreshStat.setAlignmentX(300.0F);
        RefreshStat.setHideActionText(true);
        RefreshStat.setMaximumSize(null);
        RefreshStat.setMinimumSize(new java.awt.Dimension(100, 27));
        RefreshStat.setName("RefreshStat"); // NOI18N
        RefreshStat.setPreferredSize(new java.awt.Dimension(100, 27));
        RefreshStat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshStatActionPerformed(evt);
            }
        });
        inputRefreshPanel.add(RefreshStat);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel1.add(inputRefreshPanel, gridBagConstraints);

        jPanel3.add(jPanel1);

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(0, 350));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.PAGE_AXIS));

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 1, 1, 1));
        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setName("jScrollPane2"); // NOI18N

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

        jTabbedPane1.addTab(resourceMap.getString("jScrollPane2.TabConstraints.tabTitle"), jScrollPane2); // NOI18N

        graphPane.setName("graphPane"); // NOI18N
        graphPane.setPreferredSize(null);
        jTabbedPane1.addTab(resourceMap.getString("graphPane.TabConstraints.tabTitle"), graphPane); // NOI18N

        jPanel2.add(jTabbedPane1);

        jPanel3.add(jPanel2);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dictionaryIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dictionaryIdActionPerformed
        int index = this.dictionaryId.getSelectedIndex();
        int dictionaryIdSelected;
        if (index == 0) {
            dictionaryIdSelected = 0;
        } else {
            dictionaryIdSelected = Integer.parseInt(this.dictionaryList.get(index - 1)[0]);
        }
        this.statistics.setDictionaryId(dictionaryIdSelected);
}//GEN-LAST:event_dictionaryIdActionPerformed

    private void groupByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_groupByActionPerformed
        this.statistics.setGroupBy(StatisticsDataModel.GroupBy.values()[this.groupBy.getSelectedIndex()]);
}//GEN-LAST:event_groupByActionPerformed

    private void exerciseTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exerciseTypeActionPerformed
        this.statistics.setExerciseType(this.exerciseType.getSelectedIndex());
}//GEN-LAST:event_exerciseTypeActionPerformed

    private void dateToPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateToPropertyChange
        try {
            statistics.setDateTo(this.df.parse(dateTo.getText()));

        } catch (ParseException e) {
            //e.printStackTrace();
        }
}//GEN-LAST:event_dateToPropertyChange

    private void dateFromPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateFromPropertyChange
        try {
            statistics.setDateFrom(this.df.parse(dateFrom.getText()));

        } catch (ParseException e) {
            //e.printStackTrace();
        }
}//GEN-LAST:event_dateFromPropertyChange

    private void RefreshStatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshStatActionPerformed
        statistics.retrieveStatistics();
        statistics.fireTableDataChanged();
        
        chart = createChart(statistics.createDataset());
        chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(null);
        graphPane.setViewportView(chartPanel);
        chartPanel.revalidate();
        jTable1.revalidate();

}//GEN-LAST:event_RefreshStatActionPerformed

    private JFreeChart createChart (XYSeriesCollection dataset) {
        // create the chart...
        chart = ChartFactory.createXYLineChart(
                null, // chart title
                "Date/Exercise", // domain axis label
                "Score", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips?
                false // URLs?
                );
        return chart;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton RefreshStat;
    private javax.swing.JFormattedTextField dateFrom;
    private javax.swing.JFormattedTextField dateTo;
    private javax.swing.JComboBox dictionaryId;
    private javax.swing.JComboBox exerciseType;
    private javax.swing.JScrollPane graphPane;
    private javax.swing.JComboBox groupBy;
    private javax.swing.JPanel inputDictionaryPanel;
    private javax.swing.JPanel inputFromPanel;
    private javax.swing.JPanel inputGroupPanel;
    private javax.swing.JPanel inputRefreshPanel;
    private javax.swing.JPanel inputToPanel;
    private javax.swing.JPanel inputTypePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel labelDictionaryPanel;
    private javax.swing.JPanel labelFromPanel;
    private javax.swing.JPanel labelToPanel;
    private javax.swing.JPanel labelTypePanel;
    private javax.swing.JPanel lavelGroupPanel;
    private javax.swing.JPanel spacerRefreshPanel;
    // End of variables declaration//GEN-END:variables
}