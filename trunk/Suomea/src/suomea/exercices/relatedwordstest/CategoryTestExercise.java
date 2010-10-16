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
package suomea.exercices.relatedwordstest;

import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;
import suomea.Database;
import suomea.Dictionary;

/**
 *
 * @author drone
 */
public class CategoryTestExercise {

    private int numQuestions = 20;
    private int numOptions = 8;  // Always greater than 1
    private Vector<TestQuestion> questions = null;
    // Evaluation
    private int numCorrects = 0;
    private int numFails = 0;
    private int notAnswered = numQuestions;
    private double score = 0.0;

    // Generates the exercise and return a vector
    public CategoryTestExercise() {
        // Reset the previous exercise
        Random rand = new Random();
        questions = new Vector<TestQuestion>();
        Dictionary dict = Dictionary.getInstance();

        for (int i = 0; i < numQuestions; i++) {
            TestQuestion question = new TestQuestion();

            // Fill the question
            int numberOfCorrectAnswers = rand.nextInt(numOptions - 1) + 1;
            for (int n = 0; n < numberOfCorrectAnswers; n++) {
                int randomNumber = rand.nextInt(numOptions);
                if (question.getCorrectAnswers().contains(randomNumber)) {
                    n--;
                } else {
                    question.addCorrectAnswer(randomNumber);
                }
            }

            int cont = 0;
            for (int j = 0; j < numOptions+1; j++) {
                try {
                    String[] res = new String[2];
                    if (question.answersContains(j)) {
                        if (cont == 0) {
                            //res = dict.getRandomCategory();
                            question.setWord(new String(res[0]));
                            System.out.println(res[0]);
                            cont++;
                        } else {
                            question.addOption(dict.getRandomWordFromCategory(question.getWord(), true));
                        }
                    } else {
                        question.addOption(dict.getRandomWordFromCategory(question.getWord(), false));
                    }
                    //System.out.println("The word "+res[1]);

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
        return score;
    }

    // Closes the exersize and update statistics
    public void finish() {

        // Calcule evaluation results
        this.doEvaluation();

        // Register those results
        this.registerResults();
    }

    // Tools for correcting the exercise
    public void doEvaluation() {
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
