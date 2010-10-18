/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package suomea.modules.exercises;

import java.util.List;

/**
 *
 * @author drone
 */
public interface IQuestion {

    public String getWord ();

    public void setWord (String word);

    public List<String> getOptions ();

    public void addOption (String option);

    public List<Integer> getCorrectAnswers ();

    public void addCorrectAnswer (int index);

    public void addFail ();

    public int getNumberOfFails ();

    public void SetIsCorrect(boolean answer);

    public boolean isCorrect();

}
