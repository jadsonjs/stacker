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
 * RegexTest
 * 30/11/19
 */
package br.ufrn.case_.stacker.regex;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Test class for Regex
 *
 * Jadson Santos - jadsonjs@gmail.com
 */
public class RegexTest {

    /**
     * Test when the text of stack trace comes with a real \n caracter
     */
    @Test
    public  void textRegexNewLine() {
        String lines1 = "line1 \n line2 \n line3";

        Assert.assertEquals(3, lines1.split(Regex.NEW_LINE).length);
        Assert.assertEquals(1, lines1.split(Regex.NEW_LINE_CHARACTER).length); // here is the problem
    }

    /**
     * Test when the text of stack traces comes with a character \n''as a text not a real new line.
     */
    @Test
    public  void textRegexNewLineCharacter() {
        String lines2= "line1 \\n line2 \\n line3";

        Assert.assertEquals(1, lines2.split(Regex.NEW_LINE).length); // here is the problem is comes a text, split by \n not work
        Assert.assertEquals(3, lines2.split(Regex.NEW_LINE_CHARACTER).length);
    }


    @Test
    public  void textRegexAtLine1() {
        String line = "\tat javax.faces.webapp.facesservlet.service(facesservlet.java:671)";
        Assert.assertTrue(line.matches(Regex.STACK_TRACE_AT));
    }

    @Test
    public  void textRegexAtLine2() {
        String line = "\n\tat javax.faces.webapp.facesservlet.service(facesservlet.java:671)";
        Assert.assertTrue(line.matches(Regex.STACK_TRACE_AT));
    }

    @Test
    public  void textRegexAtLine3() {
        String line = "\\tat javax.faces.webapp.facesservlet.service(facesservlet.java:671)";
        Assert.assertTrue(line.matches(Regex.STACK_TRACE_AT));
    }


    @Test
    public  void textRegexAtLine4() {
        String line = "       \\tat       javax.faces.webapp.facesservlet.service(facesservlet.java:671)";
        Assert.assertTrue(line.matches(Regex.STACK_TRACE_AT));
    }


    @Test
    public  void textRegexAtLine5() {
        String line = "       \\n\\tat       javax.faces.webapp.facesservlet.service(facesservlet.java:671)";
        Assert.assertTrue(line.matches(Regex.STACK_TRACE_AT));
    }

    @Test
    public  void textRegexAtLine6() {
        String line = "tatjavax.faces.webapp.facesservlet.service(facesservlet.java:671)";
        Assert.assertFalse(line.matches(Regex.STACK_TRACE_AT));
    }

    @Test
    public  void textRegexMoreLines1() {
        String line = " ... 27 more ";
        Assert.assertTrue(line.matches(Regex.STACK_TRACE_MORE));
    }

    @Test
    public  void textRegexMoreLines2() {
        String line = " ...  more ";
        Assert.assertTrue(line.matches(Regex.STACK_TRACE_MORE));
    }

    @Test
    public  void textRegexMoreLines3() {
        String line = "       ...  more ";
        Assert.assertTrue(line.matches(Regex.STACK_TRACE_MORE));
    }


    @Test
    public  void textRegexMoreLines4() {
        String line = " ...  more";
        Assert.assertTrue(line.matches(Regex.STACK_TRACE_MORE));
    }

    @Test
    public  void textRegexMoreLines5() {
        String line = " ...  otherword";
        Assert.assertFalse(line.matches(Regex.STACK_TRACE_MORE));
    }

    @Test
    public  void textRegexMoreLines6() {
        String line = " ..  more ";
        Assert.assertFalse(line.matches(Regex.STACK_TRACE_MORE));
    }


}
