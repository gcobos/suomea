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

import java.util.ArrayList;
import java.util.List;
import suomea.modules.exercises.IQuestion;
import suomea.modules.exercises.WidgetType;

/**
 *
 * @author bicha
 */
public class TestQuestion implements IQuestion {

    private String word;
    private List<String> options;
    private List<Integer> correct;
    private int fails = 0;
    private boolean isCorrect = false;
    private int numberOfCorrectAnswer = 0;

    public TestQuestion() {
        this.options = new ArrayList<String>();
        this.correct = new ArrayList<Integer>();
    }

    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void addCorrectOption() {
        this.numberOfCorrectAnswer++;
    }

    public void resetCorrectOptions() {
        this.numberOfCorrectAnswer = 0;
    }

    public boolean isCorrectAllOptions() {
        if (this.numberOfCorrectAnswer == this.correct.size()) {
            return true;
        } else {
            return false;
        }
    }

    public List<String> getOptions() {
        return this.options;
    }

    public void addOption(String option) {
        this.options.add(option);
    }

    public List<Integer> getCorrectAnswers() {
        return this.correct;
    }

    public void addCorrectAnswer(int index) {
        this.correct.add(new Integer(index));
    }

    public void addFail() {
        this.fails++;
    }

    public int getNumberOfFails() {
        return this.fails;
    }

    public void setIsCorrect(boolean answer) {
        this.isCorrect = answer;
    }

    public boolean isCorrect() {
        return this.isCorrect;
    }

    public boolean answersContains(int index) {
        for (Integer i : this.getCorrectAnswers()) {
            if (i == index) {
                return true;
            }
        }
        return false;
    }

    public boolean checkAnswer (List<Integer> answers) {
        int cont = 0;

        if (answers.size() == 0) {
            return false;
        }

        for (Integer answer : answers) {
            if (correct.contains(answer)) {
                cont++;
            } else {
                return false;
            }
        }
        
        if (cont == answers.size() && cont == correct.size()) {
            setIsCorrect(true);
            return true;
        } else if (answers.size() < correct.size()) {
            return true;
        }
        // Never reached
        return false;
    }

    public WidgetType getWidgetType() {
        return WidgetType.CHECKBOX;
    }
}
