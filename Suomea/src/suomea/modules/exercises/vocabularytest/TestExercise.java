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
package suomea.modules.exercises.vocabularytest;

import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;
import suomea.Database;
import suomea.Dictionary;
import suomea.modules.exercises.IExercise;

/**
 *
 * @author drone
 */
public class TestExercise implements IExercise {

    private int numQuestions = 20;
    private int numOptions = 4;  // Always greater than 1
    private Vector<TestQuestion> questions = null;
    // Evaluation
    private int numCorrects = 0;
    private int numFails = 0;
    private int notAnswered = numQuestions;
    private double score = 0.0;

    // Generates the exercise and return a vector
    public TestExercise() {
        this.prepare();
    }

    public void prepare ()
    {
        // Reset the previous exercise
        Random rand = new Random();
        questions = new Vector<TestQuestion>();
        Dictionary dict = Dictionary.getInstance();

        for (int i = 0; i < numQuestions; i++) {
            TestQuestion question = new TestQuestion();

            // Fill the question
            question.addCorrectAnswer(rand.nextInt(numOptions));
            for (int j = 0; j < numOptions; j++) {
                try {
                    String[] res = dict.getRandomWord();
                    if (j == question.getCorrectAnswers().get(0)) {
                        question.setWord(res[0]);
                    }
                    //System.out.println("The word "+res[1]);
                    question.addOption(res[1]);
                } catch (Exception e) {
                    System.out.println("Something is wrong with the word " + e.toString());
                    System.exit(0);
                }
            }

            questions.addElement(question);
        }
    }

    public TestQuestion getQuestion(int ID) {
        return this.questions.get(ID);
    }

    public int getNumberOfQuestions() {
        return numQuestions;
    }

    public double getScore() {
        numCorrects = 0;
        numFails = 0;
        notAnswered = 0;
        for (TestQuestion question : questions) {
            if (question.isCorrect() || question.getNumberOfFails() > 0) {
                if (question.isCorrect() && question.getNumberOfFails() == 0) {
                    numCorrects++;
                } else {
                    numFails++;
                }
            } else {
                notAnswered++;
            }
        }
        score = 10.0 * numCorrects / (double) (numQuestions);
        //score = score - 5.0 * notAnswered / (double)numQuestions;
        //if (score<0) score=0;
        return score;
    }

    // Closes the exersize and update statistics
    public void finish() {
        Dictionary dict = Dictionary.getInstance();

        // Mark learnt words as used in database
        Vector<String> learnt = new Vector<String>();
        for (TestQuestion question : questions) {
            if (question.isCorrect() && question.getNumberOfFails() == 0) {
                learnt.add(question.getWord());
            }
        }
        dict.updateUsedWords(learnt);

        // Calcule evaluation results
        this.getScore();

        // Register those results
        this.registerResults();
    }

    // Write evaluation results in the database
    public void registerResults() {
        Database db = Database.getInstance();
        Dictionary dict = Dictionary.getInstance();
        Hashtable<String, String> vars = new Hashtable<String, String>();
        String[] columns = {"type", "questions", "corrects", "fails", "evaluation", "dictionaryId"};
        String[] values = {"1", new Integer(numQuestions).toString(), new Integer(numCorrects).toString(),
            new Integer(numFails).toString(), new Float(score).toString(),
            new Integer(dict.getId()).toString()};
        for (int i = 0; i < columns.length; i++) {
            vars.put(columns[i], values[i]);
        }
        db.insert("statistics", vars);
    }
}
