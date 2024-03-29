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
 * 08/11/19
 */
package br.ufrn.case_.stacker.rules;

import org.junit.Assert;
import org.junit.Test;

public class Rule2Test {

    String s1 = "Exception in thread \"main\" java.lang.NullPointerException\n" +
            "        at com.example.myproject.Book.getTitle(Book.java:170)\n " +
            "        at com.example.myproject.Author.getBookTitles(Author.java:25)\n" +
            "        at com.example.myproject.Bootstrap.main(Bootstrap.java:14)";

    String s2 = "Exception in thread \"main\" java.lang.NullPointerException\n" +
            "        at com.example.myproject.Book.getTitle(Book.java:170)\n" +
            "        at com.example.myproject.Bootstrap.main(Bootstrap.java:14)";

    String s3 = "Exception in thread \"main\" java.lang.NullPointerException\n" +
            "        at com.example.myproject.Book.getTitle(Book.java:170)\n " +
            "        at com.example.myproject.Author.getBookTitles(Author.java:25)\n " +
            "        at com.example.myproject.Bootstrap.main(Bootstrap.java:14)";

    String s4 = "Exception in thread \"main\" java.lang.NullPointerException\n" +
            "        at com.example.myproject.Book.getTitle2(Book.java:170)\n" +
            "        at com.example.myproject.Author.getBookPages(Author.java:25)\n " +
            "        at com.example.myproject.Bootstrap.main(Bootstrap.java:14)";

    @Test
    public void testVerifyCorrelation() {
        Rule2 r2 = new Rule2();
        Assert.assertTrue(r2.isCorrelated(s1, s2));
    }

    @Test
    public void testVerifyNotCorrelation() {
        Rule2 r2 = new Rule2();
        Assert.assertFalse(r2.isCorrelated(s3, s4));
    }
}