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
 * Guineu; if not, write to the Free Software Foundation, Inc., 51 Franklin St,
 * Fifth Floor, Boston, MA 02110-1301 USA
 */

/*
 * TestDialog.java
 *
 * Created on 10-oct-2010, 17:18:44
 */
package suomea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

/**
 *
 * @author bicha
 */
public class TestDialog extends javax.swing.JDialog implements ActionListener {

    private int questionID = 0;
    private List<TestQuestion> questions;
    private ButtonGroup group;
    
    /** Creates new form TestDialog */
    public TestDialog(java.awt.Frame parent, boolean modal, List<TestQuestion> questions) {
        super(parent, modal);
        this.questions = questions;
        initComponents();
        createNextQuestion();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        textPanel = new javax.swing.JPanel();
        mainWordPanel = new javax.swing.JPanel();
        possibilitiesPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        nextButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));

        textPanel.setName("textPanel"); // NOI18N
        textPanel.setLayout(new javax.swing.BoxLayout(textPanel, javax.swing.BoxLayout.LINE_AXIS));

        mainWordPanel.setName("mainWordPanel"); // NOI18N

        javax.swing.GroupLayout mainWordPanelLayout = new javax.swing.GroupLayout(mainWordPanel);
        mainWordPanel.setLayout(mainWordPanelLayout);
        mainWordPanelLayout.setHorizontalGroup(
            mainWordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 283, Short.MAX_VALUE)
        );
        mainWordPanelLayout.setVerticalGroup(
            mainWordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 185, Short.MAX_VALUE)
        );

        textPanel.add(mainWordPanel);

        possibilitiesPanel.setName("possibilitiesPanel"); // NOI18N
        possibilitiesPanel.setLayout(new java.awt.GridLayout());
        textPanel.add(possibilitiesPanel);

        jPanel1.add(textPanel);

        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.setMaximumSize(new java.awt.Dimension(1000, 40000));
        jPanel3.setMinimumSize(new java.awt.Dimension(378, 40));
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel3.setLayout(new java.awt.BorderLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(suomea.SuomeaApp.class).getContext().getResourceMap(TestDialog.class);
        nextButton.setText(resourceMap.getString("nextButton.text")); // NOI18N
        nextButton.setName("nextButton"); // NOI18N
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });
        jPanel3.add(nextButton, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed

        createNextQuestion();
    }//GEN-LAST:event_nextButtonActionPerformed

    private void createNextQuestion() {
        if (questions != null && questions.size() > 0) {
            TestQuestion question = this.questions.get(questionID);

            this.mainWordPanel.removeAll();
            this.mainWordPanel.add(new JLabel(question.word));


            this.possibilitiesPanel.removeAll();
            group = new ButtonGroup();

            for (String option : question.options) {
                JRadioButton opt = new JRadioButton(option);
                opt.addActionListener(this);
                group.add(opt);
                this.possibilitiesPanel.add(opt);
            }

            questionID++;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel mainWordPanel;
    private javax.swing.JButton nextButton;
    private javax.swing.JPanel possibilitiesPanel;
    private javax.swing.JPanel textPanel;
    // End of variables declaration//GEN-END:variables

    public void actionPerformed(ActionEvent e) {
    }
}
