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
 * Rule0Test
 * 11/11/19
 */
package br.ufrn.case_.stacker.rules;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for Rule0.  that organizer the "caused by" and remove lines not necessary.
 */
public class Rule1Test {

    String inputStackTrace =
            "javax.servlet.servletexception: #{ matriculagraduacao.telainstrucoes}: java.lang.nullpointerexception" +
            "\ncaused by: javax.faces.el.evaluationexception: java.lang.nullpointerexception" +
            "\n\t... 52 more" +
            "\ncaused by: java.lang.nullpointerexception" + // top frame is here
            "\n\tat br.ufrn.sigaa.ensino.tecnico.dao.turmaentradatecnicodao.findturmasentradadisponiveisimdnew(turmaentradatecnicodao.java:237)" +
            "\n\t... 53 more";

    String outStackTrace =
            "caused by: java.lang.nullpointerexception" + // top frame is here
            "\n\tat br.ufrn.sigaa.ensino.tecnico.dao.turmaentradatecnicodao.findturmasentradadisponiveisimdnew(turmaentradatecnicodao.java:237)" +
            "\ncaused by: javax.faces.el.evaluationexception: java.lang.nullpointerexception" +
            "\njavax.servlet.servletexception: #{ matriculagraduacao.telainstrucoes}: java.lang.nullpointerexception"+
            "\n";

    String outStackTraceOnlyCauseBy =
            "caused by: java.lang.nullpointerexception" + // top frame is here
            "\n\tat br.ufrn.sigaa.ensino.tecnico.dao.turmaentradatecnicodao.findturmasentradadisponiveisimdnew(turmaentradatecnicodao.java:237)" +
            "\ncaused by: javax.faces.el.evaluationexception: java.lang.nullpointerexception" +
            "\n";

    String inputStackTracePackageFilter =
            "javax.servlet.servletexception: #{ matriculagraduacao.telainstrucoes}: java.lang.nullpointerexception" +
            "\n\tat javax.faces.webapp.facesservlet.service(facesservlet.java:277)"+
            "\n\tat org.apache.catalina.core.applicationfilterchain.internaldofilter(applicationfilterchain.java:290)"+
             "\ncaused by: javax.faces.el.evaluationexception: java.lang.nullpointerexception" +
             "\n\t... 52 more" +
             "\ncaused by: java.lang.nullpointerexception" + // top frame is here
             "\n\tat br.ufrn.sigaa.ensino.tecnico.dao.turmaentradatecnicodao.findturmasentradadisponiveisimdnew(turmaentradatecnicodao.java:237)" +
             "\n\tat br.ufrn.sigaa.ensino.graduacao.jsf.matriculagraduacaombean.telainstrucoes(matriculagraduacaombean.java:1951)" +
             "\n\tat sun.reflect.generatedmethodaccessor2828.invoke(unknown source)" +
             "\n\tat sun.reflect.delegatingmethodaccessorimpl.invoke(delegatingmethodaccessorimpl.java:43)" +
            "\n\t... 53 more";


    String outStackTracePackageFilter =
            "caused by: java.lang.nullpointerexception" + // top frame is here
                    "\n\tat br.ufrn.sigaa.ensino.tecnico.dao.turmaentradatecnicodao.findturmasentradadisponiveisimdnew(turmaentradatecnicodao.java:237)" +
                    "\n\tat br.ufrn.sigaa.ensino.graduacao.jsf.matriculagraduacaombean.telainstrucoes(matriculagraduacaombean.java:1951)" +
                    "\ncaused by: javax.faces.el.evaluationexception: java.lang.nullpointerexception" +
                    "\njavax.servlet.servletexception: #{ matriculagraduacao.telainstrucoes}: java.lang.nullpointerexception"+
                    "\n";


    String outStackTracePackageFilter2 =
            "caused by: java.lang.nullpointerexception" + // top frame is here
                    "\n\tat br.ufrn.sigaa.ensino.tecnico.dao.turmaentradatecnicodao.findturmasentradadisponiveisimdnew(turmaentradatecnicodao.java:237)" +
                    "\n\tat br.ufrn.sigaa.ensino.graduacao.jsf.matriculagraduacaombean.telainstrucoes(matriculagraduacaombean.java:1951)" +
                    "\n\tat sun.reflect.generatedmethodaccessor2828.invoke(unknown source)" +
                    "\n\tat sun.reflect.delegatingmethodaccessorimpl.invoke(delegatingmethodaccessorimpl.java:43)" +
                    "\ncaused by: javax.faces.el.evaluationexception: java.lang.nullpointerexception" +
                    "\njavax.servlet.servletexception: #{ matriculagraduacao.telainstrucoes}: java.lang.nullpointerexception"+
                    "\n\tat javax.faces.webapp.facesservlet.service(facesservlet.java:277)"+
                    "\n\tat org.apache.catalina.core.applicationfilterchain.internaldofilter(applicationfilterchain.java:290)"+
                    "\n";

    /**
     * Test organization of stack trace eliminating not important information
     */
    @Test
    public void testPutCauseByDirectOrder() {
        Assert.assertEquals(outStackTrace, new Rule1(false, "").simplify(inputStackTrace));
    }

    /**
     * Test organization of stack trace eliminating information not present in caused by.
     */
    @Test
    public void testReturnOnlyCauseByDirectOrder() {
        Assert.assertEquals(outStackTraceOnlyCauseBy, new Rule1(true, "").simplify(inputStackTrace));
    }

    /**
     * Test return only br.ufrn.sigaa packages
     */
    @Test
    public void testPackageFilter() {
        Assert.assertEquals(outStackTracePackageFilter,
                new Rule1(false, "br.ufrn.sigaa").simplify(inputStackTracePackageFilter));
    }

    /**
     * Test return only br.ufrn.sigaa packages
     */
    @Test
    public void testPackageFilter2() {
        Assert.assertEquals(outStackTracePackageFilter2,
                new Rule1(false, "").simplify(inputStackTracePackageFilter));
    }
}