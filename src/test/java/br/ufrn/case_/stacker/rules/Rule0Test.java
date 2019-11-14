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
 * Rule1Test
 * 11/11/19
 */
package br.ufrn.case_.stacker.rules;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for Rule1.
 *
 * The remove spaces, lines number and tabs in stack traces
 */
public class Rule0Test {

    String s1 = "Exception in thread \"main\" java.lang.NullPointerException\n" +
            "        at com.example.myproject.Book.getTitle(Book.java:10)\n " +
            "        at com.example.myproject.Author.getBookTitles(Author.java:25)\n";

    String s2 = "Exception in thread \"main\" java.lang.NullPointerException\n" +
            " at com.example.myproject.Book.getTitle(Book.java)\n" +
            " at com.example.myproject.Author.getBookTitles(Author.java)\n";

    @Test
    public void testSimplification() {
        Rule0 r0 = new Rule0();
        Assert.assertEquals(s2, r0.simplify(s1) );
    }

}