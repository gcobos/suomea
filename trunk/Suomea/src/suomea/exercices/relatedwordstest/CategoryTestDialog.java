/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CategoryTestDialog.java
 *
 * Created on 15-oct-2010, 23:03:04
 */
package suomea.exercices.relatedwordstest;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;

/**
 *
 * @author bicha
 */
public class CategoryTestDialog extends javax.swing.JDialog implements ActionListener {

    private int questionID = 0;
    private CategoryTestExercise exercise;
    private TestQuestion question;
    private JTextArea results;
    private int failCount = 0;
    private int correctCount = 0;
    private List<JCheckBox> checkboxes = new ArrayList<JCheckBox>();

    /** Creates new form CategoryTestDialog */
    public CategoryTestDialog(java.awt.Frame parent, boolean modal, CategoryTestExercise exercise, JTextArea results) {
        super(parent, modal);
        this.exercise = exercise;
        initComponents();
        createNextQuestion();
        this.results = results;
        results.setText("Test Results \n");
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
        jButton1 = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N
        getContentPane().setLayout(new java.awt.FlowLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setMinimumSize(new java.awt.Dimension(417, 300));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 400));
        jPanel1.setLayout(new java.awt.BorderLayout());

        textPanel.setName("textPanel"); // NOI18N

        mainWordPanel.setMaximumSize(new java.awt.Dimension(100, 100));
        mainWordPanel.setName("mainWordPanel"); // NOI18N
        mainWordPanel.setLayout(new java.awt.BorderLayout());

        wordLabel.setName("wordLabel"); // NOI18N
        mainWordPanel.add(wordLabel, java.awt.BorderLayout.PAGE_START);

        answerLabel.setName("answerLabel"); // NOI18N
        mainWordPanel.add(answerLabel, java.awt.BorderLayout.PAGE_END);

        textPanel.add(mainWordPanel);

        possibilitiesPanel.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        possibilitiesPanel.setMinimumSize(new java.awt.Dimension(390, 62));
        possibilitiesPanel.setName("possibilitiesPanel"); // NOI18N
        possibilitiesPanel.setPreferredSize(new java.awt.Dimension(275, 316));

        javax.swing.GroupLayout possibilitiesPanelLayout = new javax.swing.GroupLayout(possibilitiesPanel);
        possibilitiesPanel.setLayout(possibilitiesPanelLayout);
        possibilitiesPanelLayout.setHorizontalGroup(
            possibilitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );
        possibilitiesPanelLayout.setVerticalGroup(
            possibilitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 316, Short.MAX_VALUE)
        );

        textPanel.add(possibilitiesPanel);

        jPanel1.add(textPanel, java.awt.BorderLayout.CENTER);

        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.setMaximumSize(new java.awt.Dimension(378, 40));
        jPanel3.setMinimumSize(new java.awt.Dimension(378, 40));
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(50, 50));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(suomea.SuomeaApp.class).getContext().getResourceMap(CategoryTestDialog.class);
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        List<Integer> correctAnswerIndexes = question.getCorrectAnswers();
        for (int j = 0; j < this.checkboxes.size(); j++) {
            for (Integer i : correctAnswerIndexes) {
                if (i == j && this.checkboxes.get(j).isSelected()) {
                    this.answerLabel.setText("Good!");
                    correctCount++;
                } else if (this.checkboxes.get(j).isSelected()) {
                    this.answerLabel.setText("Wrong!");
                    question.addFail();
                    failCount++;
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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

        if (exercise != null) {for (int j = 0; j < this.checkboxes.size(); j++) {
            question = exercise.getQuestion(questionID);

            if (question != null) {
                try {
                    this.wordLabel.setText(question.getWord());

                    // Removes all the components from the panel where the possible answers are writen
                    this.possibilitiesPanel.removeAll();
                    this.possibilitiesPanel.setLayout(new GridLayout(0, 1));


                    // Creates the radio buttons and adds them to the group and to the panel
                    for (String option : question.getOptions()) {
                        JCheckBox opt = new JCheckBox(option);
                        opt.addActionListener(this);
                        this.possibilitiesPanel.add(opt);
                        this.checkboxes.add(opt);
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
    }}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel answerLabel;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel mainWordPanel;
    private javax.swing.JButton nextButton;
    private javax.swing.JPanel possibilitiesPanel;
    private javax.swing.JPanel textPanel;
    private javax.swing.JLabel wordLabel;
    // End of variables declaration//GEN-END:variables

    public void actionPerformed(ActionEvent e) {
        // try {
        /*if (correctCount == question.getCorrectAnswers().size()) {
        question.SetIsCorrect(true);
        }
        // Writes the statistics of the current test in the main windows using the text area givem in the class arguments
        String statistics = "Test Results:\n Correct Answers: " + correctCount
        + "\n Wrong Answers: " + failCount;
        exercise.doEvaluation();
        statistics = statistics.concat("\n Test score: " + exercise.getScore());
        this.results.setText(statistics);

        } catch (NullPointerException exception) {
        System.out.println("It is not possible to show the questions");
        exception.printStackTrace();
        }

        // To get the last stats printed before closing the test window
        if (question.isCorrect()) {
        this.createNextQuestion();
        }*/
    }
}