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
