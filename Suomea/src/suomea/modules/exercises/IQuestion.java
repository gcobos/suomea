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

import java.util.List;

/**
 *
 * @author drone
 */
public interface IQuestion {

    public String getWord();

    public void setWord(String word);

    public List<String> getOptions();

    public void addOption(String option);

    public void addCorrectAnswer(int index);

    public void addFail();

    public int getNumberOfFails();

    public void setIsCorrect(boolean answer);

    public boolean isCorrect();

    public boolean checkAnswer(List<Integer> answers);

    public WidgetType getWidgetType();


}
