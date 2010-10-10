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
package suomea;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author drone
 */
public class TestExercise {

    private int numQuestions = 20;
    private int numOptions = 4;  // Always greater than 1
    private Vector<TestQuestion> questions = null;

    // Generates the exercise and return a vector
    public TestExercise() {
        // Reset the previous exercise
        Random rand = new Random();
        questions = new Vector<TestQuestion>();
        Dictionary dict = new Dictionary();

        for (int i = 0; i < numQuestions; i++) {
            TestQuestion question = new TestQuestion();

            // Fill the question
            question.correct = rand.nextInt(numOptions);
            question.options = new ArrayList<String>();
            for (int j = 0; j < numOptions; j++ ) {
                try {
                    String[] res = dict.getRandomWord();
                    if (j == question.correct) {
                        question.word = new String(res[0]);
                    }
                    //System.out.println("The word "+res[1]);
                    question.options.add(res[1]);
                } catch (Exception e) {
                    System.out.println("Something is wrong with the word "+e.toString());
                    System.exit(0);
                }
            }

            questions.addElement(question);
        }
    }

    public TestQuestion getQuestion(int ID) {
        return this.questions.get(ID);
    }
    // Tools for correcting the exercise
}
