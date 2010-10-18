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
package suomea.modules.exercises;

/**
 *
 * @author drone
 */
public interface IExercise {

    /**
     * Fills the question's ArrayList with new questions takin the words from the database.
     */
    public void prepare();

    /**
     * Returns one of the questions of exercise which is in the position id in the ArrayList.
     * @param id position of the question in the ArrayList.
     * @return IQuestion
     */
    public IQuestion getQuestion(int id);

    /**
     * 
     * @return Number of questions of the exercise
     */
    public int getNumberOfQuestions();

    /**
     * Calculates the ratio of right questions to the total number of questions.
     * @return % of correct answers
     */
    public double getScore();

    /**
     * Closes the exercise and update statistics
     */
    public void finish();
}
