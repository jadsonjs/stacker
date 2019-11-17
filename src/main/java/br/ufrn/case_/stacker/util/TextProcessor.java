/*
 * Federal University of Rio Grande do Norte
 * Department of Informatics and Applied Mathematics
 * Collaborative & Automated Software Engineering (CASE) Research Group
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 *
 * stacker
 * br.ufrn.case_.stacker.rules
 * StringUtils
 * 17/11/19
 */
package br.ufrn.case_.stacker.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Split a text by END LINE character
 *
 * Jadson Santos - jadsonjs@gmail.com
 */
public class TextProcessor {

    /**
     * Split a text by '\n' character
     *
     * @param inputStackTrace
     * @return
     */
    public  static List<String> slipByEndLine(String inputStackTrace) {
        List<String> lines = new ArrayList<>();
        if(inputStackTrace == null || inputStackTrace.trim().isEmpty())
            return lines;

        if(inputStackTrace.contains("\\n"))
            lines = Arrays.asList(inputStackTrace.split("\\n"));
        else {
            if (inputStackTrace.contains("\n"))
                lines = Arrays.asList(inputStackTrace.split("\n"));
            else
                lines = Arrays.asList(inputStackTrace.split("\r"));
        }
        return lines;
    }

}
