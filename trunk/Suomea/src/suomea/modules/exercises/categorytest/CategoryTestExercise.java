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
package suomea.modules.exercises.categorytest;

import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import suomea.Database;
import suomea.Dictionary;
import suomea.modules.exercises.IExercise;
import suomea.modules.exercises.IQuestion;

/**
 *
 * @author drone
 */
public class CategoryTestExercise implements IExercise {

    private int numQuestions = 20;
    private int numOptions = 4;  // Always greater than 1
    private Vector<IQuestion> questions = null;
    // Evaluation
    private int numCorrects = 0;
    private int numFails = 0;
    private int notAnswered = numQuestions;
    private double score = 0.0;

    // Generates the exercise and return a vector
    public CategoryTestExercise() {
        this.prepare();
    }

    public void prepare ()
    {
        // Reset the previous exercise
        Random rand = new Random();
        questions = new Vector<IQuestion>();
        Dictionary dict = Dictionary.getInstance();

        for (int i = 0; i < numQuestions; i++) {
            IQuestion question = new TestQuestion();
            // Fill the question
            int numberOfCorrectAnswers = rand.nextInt(numOptions - 1) + 1;
            String category = dict.getRandomCategory();
            question.setWord(category);
            List<String[]> questionFromDict = dict.prepareCategoryTest(category, numberOfCorrectAnswers, numOptions);


            for (int j = 0; j < questionFromDict.size(); j++) {
                try {
                    if (questionFromDict.get(j)[1].equals(category)) {
                        question.addCorrectAnswer(j);
                    }
                    question.addOption(questionFromDict.get(j)[0]);
                    System.out.println(questionFromDict.get(j)[0]);
                } catch (Exception e) {
                    System.out.println("Something is wrong with the word " + e.toString());
                    System.exit(0);
                }
            }

            questions.addElement(question);
        }

    }

    public IQuestion getQuestion(int ID) {
        return this.questions.get(ID);
    }

    public int getNumberOfQuestions() {
        return numQuestions;
    }

    public double getScore() {
        numCorrects = 0;
        numFails = 0;
        notAnswered = 0;
        for (IQuestion question : questions) {
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
        return score;
    }

    // Closes the exercise and update statistics
    public void finish() {

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
