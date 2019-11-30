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
 * br.ufrn.case_.stacker.regex
 * Regex
 * 30/11/19
 */
package br.ufrn.case_.stacker.regex;

/**
 * Contains all regex used by th stacker
 *
 * Jadson Santos - jadsonjs@gmail.com
 */
public interface Regex {


    ////////////////  BASIC REGEX   //////////////

    public final static String NEW_LINE = "\n";

    public final static String NEW_LINE_CHARACTER = "\\\\n";

    public final static String TAB = "\t";

    public final static String TAB_CHARACTER = "\\\\t";

    public final static String ONE_MORE_SPACES = "[ ]+";

    public final static String ZERO_MORE_SPACES = "[ ]*";

    public final static String SPACE = "\\s";

    public final static String QUOTATION_MARK = "\"";

    public final static String NUMBER_OF_LINES = ":\\d+";

    public final static String ONE_MORE_DIGIT = "\\d+";

    public final static String ZERO_MORE_DIGIT = "\\d*";

    public final static String ANY_CHARACTER = ".*";

    public final static String STARTS_WITH = "^";

    public final static String EMPTY = "^$";

    ////////////////////////////////////////////////////////////////

    public final static String NEW_LINE_OR_TAB_ZERO_OR_MORE_TIMES = "["+ NEW_LINE + TAB + NEW_LINE_CHARACTER + TAB_CHARACTER+"]*";

    /*
     * find string that starts with: "... 53 more" or "...  more"
     * start with any "character zero or more times" ... space "zero or more digits" space "more" "any character" or "empty"
     */
    public final static String STACK_TRACE_MORE = STARTS_WITH+ANY_CHARACTER+"\\.\\.\\.+"+SPACE+ZERO_MORE_DIGIT+SPACE +"more"+ZERO_MORE_SPACES+".*";

    /**
     * find the string that starts with: \n\tat org.apache.catalina.core.
     *
     * starts with "\n or \t  0 or more times" zero or space "at" space "any character"   "\n or \t  0 or more times"
     */
    public final static String STACK_TRACE_AT = STARTS_WITH + ZERO_MORE_SPACES + NEW_LINE_OR_TAB_ZERO_OR_MORE_TIMES + ZERO_MORE_SPACES +"at"+ ONE_MORE_SPACES
            +ANY_CHARACTER + NEW_LINE_OR_TAB_ZERO_OR_MORE_TIMES;



}
