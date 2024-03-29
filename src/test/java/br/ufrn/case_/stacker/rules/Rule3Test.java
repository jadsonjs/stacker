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
 * Rule2Test
 * 08/11/19
 */
package br.ufrn.case_.stacker.rules;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for the top frame rule
 * Jadson Santos - jadsonjs@gmail.com
 */
public class Rule3Test {

    String s1 = "Exception in thread \"main\" java.lang.NullPointerException\n" +
            "        at com.example.myproject.Book.getTitle(Book.java:170)\n " + // top frame
            "        at com.example.myproject.Author.getBookTitles(Author.java:25)\n" +
            "        at com.example.myproject.Bootstrap.main(Bootstrap.java:14)";

    String s2 = "Exception in thread \"main\" java.lang.NullPointerException\n" +
            "        at com.example.myproject.Book.getTitle(Book.java:170)\n" +   // top frame
            "        at com.example.myproject.Bootstrap.main(Bootstrap.java:14)";

    String r1 ="\ncaused by: javax.faces.el.evaluationexception: java.lang.nullpointerexception"+
            "\n\t... 52 more" +
            "\ncaused by: java.lang.nullpointerexception" + // top frame is here
            "\n\tat br.com.system.ensino.tecnico.dao.turmaentradatecnicodao.findturmasentradadisponiveisimdnew(turmaentradatecnicodao.java:2037)" +
            "\n\tat br.com.system.ensino.graduacao.jsf.matriculagraduacaombean.telainstrucoes(matriculagraduacaombean.java:1982)" +
            "\n\tat sun.reflect.generatedmethodaccessor2828.invoke(unknown source)" +
            "\n\tat sun.reflect.delegatingmethodaccessorimpl.invoke(delegatingmethodaccessorimpl.java:43)" +
            "\n\t... 53 more";

    String r2 ="\ncaused by: javax.faces.el.evaluationexception: java.lang.nullpointerexception"+
            "\n\t... 52 more" +
            "\ncaused by: java.lang.nullpointerexception" + // top frame is here
            "\n\tat br.com.system.ensino.tecnico.dao.turmaentradatecnicodao.findturmasentradadisponiveisimdnew(turmaentradatecnicodao.java:2037)" +
            "\n\tat br.com.system.ensino.graduacao.jsf.matriculagraduacaombean.telaCadastroAluno(matriculagraduacaombean.java:1982)" +
            "\n\t... 53 more";

    String e1 ="\ncaused by: javax.faces.el.evaluationexception: java.lang.nullpointerexception"+
            "\n\t... 52 more" +
            "\ncaused by: java.lang.nullpointerexception" + // top frame is here
            "\n\tat br.com.system.ensino.tecnico.dao.turmaentradatecnicodao.findturmasentradadisponiveisimdnew(turmaentradatecnicodao.java:2037)" +
            "\n\tat br.com.system.ensino.graduacao.jsf.matriculagraduacaombean.telainstrucoes(matriculagraduacaombean.java:1982)" +
            "\n\tat sun.reflect.generatedmethodaccessor2828.invoke(unknown source)" +
            "\n\tat sun.reflect.delegatingmethodaccessorimpl.invoke(delegatingmethodaccessorimpl.java:43)" +
            "\n\t... 53 more";

    String e2 ="\ncaused by: javax.faces.el.evaluationexception: java.lang.illegalargumentexception"+
            "\n\t... 52 more" +
            "\ncaused by: java.lang.nullpointerexception" + // top frame is here
            "\n\tat br.com.system.ensino.tecnico.dao.turmaentradatecnicodao.findturmasentradadisponiveisimdnew(turmaentradatecnicodao.java:2037)" +
            "\n\tat br.com.system.ensino.graduacao.jsf.matriculagraduacaombean.telaCadastroAluno(matriculagraduacaombean.java:1982)" +
            "\n\t... 53 more";

    String e3 ="\ncaused by: javax.faces.el.evaluationexception: java.lang.nullpointerexception"+
            "\n\t... 52 more" +
            "\ncaused by: java.lang.nullpointerexception" + // top frame is here
            "\n\tat br.com.system.ensino.tecnico.dao.turmaentradatecnicodao.findturmasentradadisponiveisimdnew(turmaentradatecnicodao.java:2037)" +
            "\n\tat br.com.system.ensino.graduacao.jsf.matriculagraduacaombean.telainstrucoes(matriculagraduacaombean.java:1982)" +
            "\n\tat sun.reflect.generatedmethodaccessor2828.invoke(unknown source)" +
            "\n\tat sun.reflect.delegatingmethodaccessorimpl.invoke(delegatingmethodaccessorimpl.java:43)" +
            "\n\t... 53 more";

    String e4 ="\ncaused by: javax.faces.el.evaluationexception: java.lang.nullpointerexception"+
            "\n\t... 52 more" +
            "\ncaused by: java.lang.nullpointerexception" + // top frame is here
            "\n\tat br.com.system.ensino.tecnico.dao.turmaentradatecnicodao.findgeral(turmaentradatecnicodao.java:2037)" +
            "\n\tat br.com.system.ensino.graduacao.jsf.matriculagraduacaombean.telaCadastroAluno(matriculagraduacaombean.java:1982)" +
            "\n\t... 53 more";

    String s5 ="\ncaused by: javax.faces.el.evaluationexception: java.lang.nullpointerexception"+
            "\ncaused by: java.lang.nullpointerexception" + // top frame is here
            "\n\tat br.com.system.ensino.tecnico.dao.turmaentradatecnicodao.findturmasentradadisponiveisimdnew(turmaentradatecnicodao.java:2037)" +
            "\n\tat br.com.system.ensino.graduacao.jsf.matriculagraduacaombean.telainstrucoes(matriculagraduacaombean.java:1982)" +
            "\n\tat sun.reflect.generatedmethodaccessor2828.invoke(unknown source)" +
            "\n\tat sun.reflect.delegatingmethodaccessorimpl.invoke(delegatingmethodaccessorimpl.java:43)";

    String s6 ="\ncaused by: javax.faces.el.evaluationexception: java.lang.nullpointerexception";



    @Test
    public void testVerifyTopFrame() {
        Rule3 r3 = new Rule3();
        Assert.assertTrue(r3.isCorrelated(s1, s2));
    }


    @Test
    public void testVerifyTopFrameRealStackTrace() {
        Rule3 r3 = new Rule3();
        Assert.assertTrue(r3.isCorrelated(r1, r2));
    }


    @Test
    public void testVerifyTopFrameDifferentExceptions() {
        Rule3 r3 = new Rule3();
        Assert.assertFalse(r3.isCorrelated(e1, e2));
    }

    @Test
    public void testVerifyTopFrameDifferentFileName() {
        Rule3 r3 = new Rule3();
        Assert.assertFalse(r3.isCorrelated(e3, e4));
    }

    /**
     * Verify when there is no top frame.
     */
    @Test
    public void testVerifyNoTopFrame() {
        Rule3 r3 = new Rule3();
        Assert.assertFalse(r3.isCorrelated(s5, s6));
    }

}
