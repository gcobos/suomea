/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package suomea;

import java.util.Vector;

/**
 *
 * @author drone
 */
public class TestExercise {

    private int numQuestions = 20;
    private Vector<TestQuestion> questions = null;

    // Generates the exercise and retur a vector
    public Vector<TestQuestion> getExercise ()
    {
        // Reset the previous exercise
        questions = new Vector<TestQuestion>(numQuestions);
        for (int i = 0; i<numQuestions; i++) {
            TestQuestion question = new TestQuestion();

            // Fill the question
            questions.set(i, question);
        }

        return questions;
    }

    // Tools for correcting the exercise

    

}
