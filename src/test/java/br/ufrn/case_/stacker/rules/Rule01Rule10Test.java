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
 * Rule01Test
 * 29/11/19
 */
package br.ufrn.case_.stacker.rules;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test Class just to test the order of application of simplification rules
 *
 * Jadson Santos - jadsonjs@gmail.com
 */
public class Rule01Rule10Test {

    String inputStackTrace =
            "javax.servlet.servletexception: #{ matriculaungraduated.telainstrucoes}: java.lang.nullpointerexception" +
                    "\ncaused by: javax.faces.el.evaluationexception: java.lang.nullpointerexception" +
                    "\n\t... 52 more" +
                    "\ncaused by: java.lang.nullpointerexception" + // top frame is here
                    "\n\tat br.com.system.ensino.tecnico.dao.turmaentradatecnicodao.findturmasentradadisponiveisimdnew(turmaentradatecnicodao.java:237)" +
                    "\n\t... 53 more";

    String outStackTrace =
            "caused by: java.lang.nullpointerexception" + // top frame is here
                    "\n\tat br.com.system.ensino.tecnico.dao.turmaentradatecnicodao.findturmasentradadisponiveisimdnew(turmaentradatecnicodao.java)" +
                    "\ncaused by: javax.faces.el.evaluationexception: java.lang.nullpointerexception" +
                    "\n";

    /**
     * Verify the order of applying rule 1 and rule 0 of simplification
     *
     * This method has a example how to use this library
     */
    @Test
    public void applySimplificationRule1AndRule0() {

        SimplificationChain chainSimplification = new Rule1(true, "br.com.system").setNext(new Rule0());

        Assert.assertEquals(outStackTrace, chainSimplification.execute(inputStackTrace));

    }

    /**
     * Verify the order of applying rule 0 and rule 1 of simplification
     *
     * This method has a example how to use this library
     */
    @Test
    public void applySimplificationRule0AndRule1() {

        SimplificationChain chainSimplification = new Rule0().setNext(new Rule1(true, "br.com.system"));

        Assert.assertEquals(outStackTrace, chainSimplification.execute(inputStackTrace));

    }

}
