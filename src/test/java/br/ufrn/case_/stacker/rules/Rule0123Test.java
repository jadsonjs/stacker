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
 * Rule012Test
 * 08/11/19
 */
package br.ufrn.case_.stacker.rules;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for the rules 0 1, 2 and 3 together
 *
 * Jadson Santos - jadsonjs@gmail.com
 */
public class Rule0123Test {


    String input =
            "javax.servlet.servletexception: #{ matriculagraduacao.telainstrucoes}: java.lang.nullpointerexception" +
                    "\ncaused by: javax.faces.el.evaluationexception: java.lang.nullpointerexception" +
                    "\n\t... 52 more" +
                    "\ncaused by: java.lang.nullpointerexception" + // top frame is here
                    "\n\tat br.com.system.ensino.tecnico.dao.turmaentradatecnicodao.findturmasentradadisponiveisimdnew(turmaentradatecnicodao.java:237)" +
                    "\n\t... 53 more";

    String output =
            "javax.servlet.servletexception: #{ matriculagraduacao.telainstrucoes}: java.lang.nullpointerexception" +
                    "\n\tat javax.faces.webapp.facesservlet.service(facesservlet.java:277)" +
                    "\n\tat org.apache.catalina.core.applicationfilterchain.internaldofilter(applicationfilterchain.java:290) " +
                    "\n\tat org.apache.catalina.core.applicationfilterchain.dofilter(applicationfilterchain.java:206)" +
                    "\n\tat org.ajax4jsf.webapp.basexmlfilter.doxmlfilter(basexmlfilter.java:206)" +
                    "\n\tat org.ajax4jsf.webapp.basefilter.handlerequest(basefilter.java:290)" +
                    "\n\tat org.ajax4jsf.webapp.basefilter.processuploadsandhandlerequest(basefilter.java:388)" +
                    "\n\tat org.ajax4jsf.webapp.basefilter.dofilter(basefilter.java:515)" +

            "\ncaused by: javax.faces.el.evaluationexception: java.lang.nullpointerexception"+
                          "\n\tat com.sun.faces.application.methodbindingmethodexpressionadapter.invoke(methodbindingmethodexpressionadapter.java:102)"+
                          "\n\tat com.sun.faces.application.actionlistenerimpl.processaction(actionlistenerimpl.java:102)"+
                          "\n\t... 52 more"+

            "\ncaused by: javax.faces.el.evaluationexception: java.lang.nullpointerexception"+
                    "\n\t... 52 more" +
                    "\ncaused by: java.lang.nullpointerexception" + // top frame is here
                    "\n\tat br.com.system.ensino.tecnico.dao.turmaentradatecnicodao.findturmasentradadisponiveisimdnew(turmaentradatecnicodao.java:2037)" +
                    "\n\tat br.com.system.ensino.graduacao.jsf.matriculagraduacaombean.telainstrucoes(matriculagraduacaombean.java:1982)" +
                    "\n\tat sun.reflect.generatedmethodaccessor2828.invoke(unknown source)" +
                    "\n\tat sun.reflect.delegatingmethodaccessorimpl.invoke(delegatingmethodaccessorimpl.java:43)" +
                    "\n\t... 53 more";


    /**
     * Verify if two stack traces are correlated. Represents the same error.
     *
     * This method has a example how to use this library
     */
    @Test
    public void isRealStackTracesCorrelated() {

        SimplificationChain chainSimplification = new Rule1(true, "br.com.system").setNext(new Rule0());

        CorrelationChain chainCorrelation = new Rule2().setNext(new Rule3());

        Assert.assertTrue( chainCorrelation.execute( chainSimplification.execute(output) , chainSimplification.execute(input)  ) );

    }


    /**
     * Verify if two stack traces are correlated. Represents the same error.
     *
     * This method has a example how to use this library
     */
    @Test
    public void isRealStackTracesCorrelatedAllPackages() {

        SimplificationChain chainSimplification = new Rule1().setNext(new Rule0());

        CorrelationChain chainCorrelation = new Rule2().setNext(new Rule3());

        Assert.assertTrue( chainCorrelation.execute( chainSimplification.execute(output) , chainSimplification.execute(input)  ) );

    }
}