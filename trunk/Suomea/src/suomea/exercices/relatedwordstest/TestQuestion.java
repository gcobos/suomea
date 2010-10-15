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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bicha
 */
public class TestQuestion {

    private String word;
    
    private List<String> options;

    private List<Integer> correct;

    private int fails = 0;

    private boolean isCorrect = false;


    public TestQuestion(){
        this.options = new ArrayList<String>();
        this.correct = new ArrayList<Integer>();
    }

    public String getWord(){
        return this.word;
    }

    public void setWord(String word){
        this.word = word;
    }

    public List<String> getOptions(){
        return this.options;
    }

    public void addOption(String option){
        this.options.add(option);
    }

    public List<Integer> getCorrectAnswers(){
        return this.correct;
    }

    public void addCorrectAnswer(int index){
        this.correct.add(index);
    }

    public void addFail(){
        this.fails++;
    }

    public int getNumberOfFails(){
        return this.fails;
    }

    public void SetIsCorrect(boolean answer){
        this.isCorrect = answer;
    }

    public boolean isCorrect(){
        return this.isCorrect;
    }

    public boolean answersContains(int index) {
        for(Integer i : this.getCorrectAnswers()){
            if(i == index){
                return true;
            }
        }
        return false;
    }
}
