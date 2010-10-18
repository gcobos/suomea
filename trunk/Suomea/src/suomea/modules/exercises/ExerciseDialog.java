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
 * ExerciseDialog.java
 *
 * Created on 10-oct-2010, 17:18:44
 */
package suomea.modules.exercises;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

/**
 *
 * @author bicha
 */
public class ExerciseDialog extends javax.swing.JDialog implements ActionListener {

    private int questionID = 0;
    private IExercise exercise;
    private IQuestion question;
    private JTextArea results;
    private int failCount = 0;
    private int correctCount = 0;

    /** Creates new form ExerciseDialog */
    public ExerciseDialog(java.awt.Frame parent, boolean modal, IExercise exercise, JTextArea results) {
        super(parent, modal);
        this.exercise = exercise;
        initComponents();
        createNextQuestion();
        this.results = results;
        results.setText("Exercise results \n");

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
        wordLabel = new javax.swing.JLabel();
        answerLabel = new javax.swing.JLabel();
        possibilitiesPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        nextButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(suomea.SuomeaApp.class).getContext().getResourceMap(ExerciseDialog.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setMinimumSize(new java.awt.Dimension(500, 200));
        setName("Form"); // NOI18N
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new java.awt.BorderLayout());

        textPanel.setName("textPanel"); // NOI18N

        mainWordPanel.setMaximumSize(new java.awt.Dimension(100, 100));
        mainWordPanel.setName("mainWordPanel"); // NOI18N
        mainWordPanel.setLayout(new java.awt.BorderLayout());

        wordLabel.setText(resourceMap.getString("wordLabel.text")); // NOI18N
        wordLabel.setName("wordLabel"); // NOI18N
        mainWordPanel.add(wordLabel, java.awt.BorderLayout.PAGE_START);

        answerLabel.setText(resourceMap.getString("answerLabel.text")); // NOI18N
        answerLabel.setName("answerLabel"); // NOI18N
        mainWordPanel.add(answerLabel, java.awt.BorderLayout.PAGE_END);

        textPanel.add(mainWordPanel);

        possibilitiesPanel.setMaximumSize(new java.awt.Dimension(400, 32767));
        possibilitiesPanel.setName("possibilitiesPanel"); // NOI18N

        javax.swing.GroupLayout possibilitiesPanelLayout = new javax.swing.GroupLayout(possibilitiesPanel);
        possibilitiesPanel.setLayout(possibilitiesPanelLayout);
        possibilitiesPanelLayout.setHorizontalGroup(
            possibilitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 248, Short.MAX_VALUE)
        );
        possibilitiesPanelLayout.setVerticalGroup(
            possibilitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 244, Short.MAX_VALUE)
        );

        textPanel.add(possibilitiesPanel);

        jPanel1.add(textPanel, java.awt.BorderLayout.CENTER);

        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.setMaximumSize(new java.awt.Dimension(378, 40));
        jPanel3.setMinimumSize(new java.awt.Dimension(378, 40));
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(50, 50));

        nextButton.setText(resourceMap.getString("nextButton.text")); // NOI18N
        nextButton.setName("nextButton"); // NOI18N
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });
        jPanel3.add(nextButton);

        closeButton.setText(resourceMap.getString("closeButton.text")); // NOI18N
        closeButton.setName("closeButton"); // NOI18N
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        jPanel3.add(closeButton);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        createNextQuestion();
    }//GEN-LAST:event_nextButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        this.exercise.finish();
        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    /**
     * Creates the components for one question:
     * - label with the main word
     * - radio buttons with the possible meanings
     */
    private void createNextQuestion() {
        if (questionID == exercise.getNumberOfQuestions()) {
            this.exercise.finish();
            this.dispose();
            return;
        }

        this.answerLabel.setText("");

        if (exercise != null) {
            question = exercise.getQuestion(questionID);

            if (question != null) {
                try {
                    this.wordLabel.setText(question.getWord());

                    // Removes all the components from the panel where the possible answers are writen
                    this.possibilitiesPanel.removeAll();
                    this.possibilitiesPanel.setLayout(new GridLayout(0, 1));

                    // Adds a new group of possibilities
                    ButtonGroup group = new ButtonGroup();

                    // Creates the radio buttons and adds them to the group and to the panel
                    for (String option : question.getOptions()) {
                        JRadioButton opt = new JRadioButton(option);

                        opt.addActionListener(this);
                        group.add(opt);

                        this.possibilitiesPanel.add(opt);
                    }

                    // Increments the question ID
                    questionID++;

                    this.validate();
                } catch (NullPointerException exception) {
                    System.out.println("It is not possible to show the questions");
                    dispose();
                }
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel answerLabel;
    private javax.swing.JButton closeButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel mainWordPanel;
    private javax.swing.JButton nextButton;
    private javax.swing.JPanel possibilitiesPanel;
    private javax.swing.JPanel textPanel;
    private javax.swing.JLabel wordLabel;
    // End of variables declaration//GEN-END:variables

    public void actionPerformed(ActionEvent e) {

        try {
            // Checks whether the answer is correct or not
            String correctAnswer = question.getOptions().get(question.getCorrectAnswers().get(0));
            String givenAnswer = e.getActionCommand();

            if (correctAnswer.equals(givenAnswer)) {
                question.SetIsCorrect(true);
                correctCount++;
            } else {
                this.answerLabel.setText("Wrong!");
                question.addFail();
                failCount++;
            }

            // Writes the statistics of the current exercise in the main windows using the text area givem in the class arguments
            String statistics = "Exercise results:\n Correct Answers: " + correctCount
                    + "\n Wrong Answers: " + failCount;
            
            statistics = statistics.concat("\n Exercise score: " + exercise.getScore());
            this.results.setText(statistics);

        } catch (NullPointerException exception) {
            System.out.println("It is not possible to show the questions");
            exception.printStackTrace();
        }

        // To get the last stats printed before closing the test window
        if (question.isCorrect()) {
            this.createNextQuestion();
        }

    }
}