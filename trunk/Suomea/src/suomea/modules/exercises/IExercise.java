/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package suomea.modules.exercises;

/**
 *
 * @author drone
 */
public interface IExercise {

    public void prepare ();

    public IQuestion getQuestion (int id);

    public int getNumberOfQuestions();

    public double getScore();



    // Closes the exersize and update statistics
    public void finish();

}
