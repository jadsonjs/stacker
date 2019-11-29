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
            "javax.servlet.servletexception: #{ matriculaungraduated.telainstrucoes}: java.lang.nullpointerexception" +
            "\ncaused by: javax.faces.el.evaluationexception: java.lang.nullpointerexception" +
            "\n\t... 52 more" +
            "\ncaused by: java.lang.nullpointerexception" + // top frame is here
            "\n\tat br.com.system.ensino.tecnico.dao.turmaentradatecnicodao.findturmasentradadisponiveisimdnew(turmaentradatecnicodao.java:237)" +
            "\n\t... 53 more";

    String outStackTrace =
            "caused by: java.lang.nullpointerexception" + // top frame is here
            "\n\tat br.com.system.ensino.tecnico.dao.turmaentradatecnicodao.findturmasentradadisponiveisimdnew(turmaentradatecnicodao.java:237)" +
            "\ncaused by: javax.faces.el.evaluationexception: java.lang.nullpointerexception" +
            "\njavax.servlet.servletexception: #{ matriculaungraduated.telainstrucoes}: java.lang.nullpointerexception"+
            "\n";

    String outStackTraceOnlyCauseBy =
            "caused by: java.lang.nullpointerexception" + // top frame is here
            "\n\tat br.com.system.ensino.tecnico.dao.turmaentradatecnicodao.findturmasentradadisponiveisimdnew(turmaentradatecnicodao.java:237)" +
            "\ncaused by: javax.faces.el.evaluationexception: java.lang.nullpointerexception" +
            "\n";

    String inputStackTracePackageFilter =
            "javax.servlet.servletexception: #{ matriculaungraduated.telainstrucoes}: java.lang.nullpointerexception" +
            "\n\tat javax.faces.webapp.facesservlet.service(facesservlet.java:277)"+
            "\n\tat org.apache.catalina.core.applicationfilterchain.internaldofilter(applicationfilterchain.java:290)"+
             "\ncaused by: javax.faces.el.evaluationexception: java.lang.nullpointerexception" +
             "\n\t... 52 more" +
             "\ncaused by: java.lang.nullpointerexception" + // top frame is here
             "\n\tat br.com.system.ensino.tecnico.dao.turmaentradatecnicodao.findturmasentradadisponiveisimdnew(turmaentradatecnicodao.java:237)" +
             "\n\tat br.com.system.ensino.ungraduated.jsf.matriculaungraduatedmbean.telainstrucoes(matriculaungraduatedmbean.java:1951)" +
             "\n\tat sun.reflect.generatedmethodaccessor2828.invoke(unknown source)" +
             "\n\tat sun.reflect.delegatingmethodaccessorimpl.invoke(delegatingmethodaccessorimpl.java:43)" +
            "\n\t... 53 more";


    String outStackTracePackageFilter =
            "caused by: java.lang.nullpointerexception" + // top frame is here
                    "\n\tat br.com.system.ensino.tecnico.dao.turmaentradatecnicodao.findturmasentradadisponiveisimdnew(turmaentradatecnicodao.java:237)" +
                    "\n\tat br.com.system.ensino.ungraduated.jsf.matriculaungraduatedmbean.telainstrucoes(matriculaungraduatedmbean.java:1951)" +
                    "\ncaused by: javax.faces.el.evaluationexception: java.lang.nullpointerexception" +
                    "\njavax.servlet.servletexception: #{ matriculaungraduated.telainstrucoes}: java.lang.nullpointerexception"+
                    "\n";


    String outStackTracePackageFilter2 =
            "caused by: java.lang.nullpointerexception" + // top frame is here
                    "\n\tat br.com.system.ensino.tecnico.dao.turmaentradatecnicodao.findturmasentradadisponiveisimdnew(turmaentradatecnicodao.java:237)" +
                    "\n\tat br.com.system.ensino.ungraduated.jsf.matriculaungraduatedmbean.telainstrucoes(matriculaungraduatedmbean.java:1951)" +
                    "\n\tat sun.reflect.generatedmethodaccessor2828.invoke(unknown source)" +
                    "\n\tat sun.reflect.delegatingmethodaccessorimpl.invoke(delegatingmethodaccessorimpl.java:43)" +
                    "\ncaused by: javax.faces.el.evaluationexception: java.lang.nullpointerexception" +
                    "\njavax.servlet.servletexception: #{ matriculaungraduated.telainstrucoes}: java.lang.nullpointerexception"+
                    "\n\tat javax.faces.webapp.facesservlet.service(facesservlet.java:277)"+
                    "\n\tat org.apache.catalina.core.applicationfilterchain.internaldofilter(applicationfilterchain.java:290)"+
                    "\n";



    String inputStackTracePackageFilter3 = "caused by: org.hibernate.hql.ast.querysyntaxexception: unexpected end of subtree [select ccd.componente, ccd.id, ccd.equivalencia, ccd.data, ccd.equivalenciavalidaate, ccd.tipocomponente, ccd.codigo from br.com.system.ensino.dominio.componentedetalhes ccd where ccd.componente in ( ) and ( ccd.equivalenciavalidaate is null or ccd.equivalenciavalidaate >= :datacorrente ) ] \n"+
         "\tat org.hibernate.hql.ast.querysyntaxexception.convert(querysyntaxexception.java)\n"+
            "   \tat org.hibernate.hql.ast.querysyntaxexception.convert(querysyntaxexception.java)\n"+
            "\tat org.hibernate.hql.ast.errorcounter.throwqueryexception(errorcounter.java)\n"+
            "\tat org.hibernate.hql.ast.querytranslatorimpl.analyze(querytranslatorimpl.java)\n"+
            "\tat org.hibernate.hql.ast.querytranslatorimpl.docompile(querytranslatorimpl.java)\n"+
            "\tat org.hibernate.hql.ast.querytranslatorimpl.compile(querytranslatorimpl.java)\n"+
            "\tat org.hibernate.engine.query.hqlqueryplan.<init>(hqlqueryplan.java)\n"+
            "\tat org.hibernate.engine.query.hqlqueryplan.<init>(hqlqueryplan.java)\n"+
            "\tat org.hibernate.engine.query.queryplancache.gethqlqueryplan(queryplancache.java)\n"+
            "\tat org.hibernate.impl.abstractsessionimpl.gethqlqueryplan(abstractsessionimpl.java)\n"+
            "\tat org.hibernate.impl.abstractsessionimpl.createquery(abstractsessionimpl.java)\n"+
            "\tat org.hibernate.impl.sessionimpl.createquery(sessionimpl.java)\n"+
            "\tat br.com.system.arq.dao.ensino.componentecurriculardao.carregarinformacoesdeequivalenciaetipodecomponente(componentecurriculardao.java)\n"+
            "\tat br.com.system.ensino.ungraduated.negocio.calculos.integralizacoeshelper.analisarequivalencias(integralizacoeshelper.java)\n"+
            "\tat br.com.system.ensino.ungraduated.negocio.calculos.integralizacoeshelper.analisarequivalenciasporextenso(integralizacoeshelper.java)\n"+
            "\tat br.com.system.arq.dao.studentydao.findbydisciplinascurricularespendentes(studentydao.java)\n"+
            "\tat br.com.system.arq.dao.studentydao.findbydisciplinascurricularespendentes(studentydao.java)\n"+
            "\tat br.com.system.ensino.negocio.processadorcalculahistorico.verificarcomponentespendentes(processadorcalculahistorico.java)\n"+
            "\tat br.com.system.ensino.negocio.processadorcalculahistorico.calculahistorico(processadorcalculahistorico.java)\n"+
            "\tat br.com.system.ensino.negocio.processadorcalculahistorico.execute(processadorcalculahistorico.java)\n"+
            "\tat br.ufrn.arq.negocio.arqfacadebean$.dointransaction(arqfacadebean.java)\n"+
            "\tat org.springframework.transaction.support.transactiontemplate.execute(transactiontemplate.java)\n"+
            "\tat br.ufrn.arq.negocio.arqfacadebean.executecomtxtemplate(arqfacadebean.java)\n"+
            "\tat br.ufrn.arq.negocio.arqfacadebean.execute(arqfacadebean.java)\n"+
            " ... 27 more ";

    String outputStackTracePackageFilter3 = "caused by: org.hibernate.hql.ast.querysyntaxexception: unexpected end of subtree [select ccd.componente, ccd.id, ccd.equivalencia, ccd.data, ccd.equivalenciavalidaate, ccd.tipocomponente, ccd.codigo from br.com.system.ensino.dominio.componentedetalhes ccd where ccd.componente in ( ) and ( ccd.equivalenciavalidaate is null or ccd.equivalenciavalidaate >= :datacorrente ) ] \n"+
            "\tat br.com.system.arq.dao.ensino.componentecurriculardao.carregarinformacoesdeequivalenciaetipodecomponente(componentecurriculardao.java)\n"+
            "\tat br.com.system.ensino.ungraduated.negocio.calculos.integralizacoeshelper.analisarequivalencias(integralizacoeshelper.java)\n"+
            "\tat br.com.system.ensino.ungraduated.negocio.calculos.integralizacoeshelper.analisarequivalenciasporextenso(integralizacoeshelper.java)\n"+
            "\tat br.com.system.arq.dao.studentydao.findbydisciplinascurricularespendentes(studentydao.java)\n"+
            "\tat br.com.system.arq.dao.studentydao.findbydisciplinascurricularespendentes(studentydao.java)\n"+
            "\tat br.com.system.ensino.negocio.processadorcalculahistorico.verificarcomponentespendentes(processadorcalculahistorico.java)\n"+
            "\tat br.com.system.ensino.negocio.processadorcalculahistorico.calculahistorico(processadorcalculahistorico.java)\n"+
            "\tat br.com.system.ensino.negocio.processadorcalculahistorico.execute(processadorcalculahistorico.java)\n";




    String inputStackTraceWithQuotationMarks =
            "javax.servlet.servletexception: #{ matriculaungraduated.telainstrucoes}: java.lang.nullpointerexception" +
                    "\ncaused by: javax.faces.el.evaluationexception: java.lang.nullpointerexception" +
                    "\n\t... 52 more" +
                    "\ncaused by: java.lang.nullpointerexception" + // top frame is here
                    "\n\t\"\"\"\"at br.com.system.ensino.tecnico.dao.turmaentradatecnicodao.findturmasentradadisponiveisimdnew(turmaentradatecnicodao.java:237)\"\"" +
                    "\n\t... 53 more";

    String outStackTraceWithQuotationMarks =
            "caused by: java.lang.nullpointerexception" + // top frame is here
                    "\n\tat br.com.system.ensino.tecnico.dao.turmaentradatecnicodao.findturmasentradadisponiveisimdnew(turmaentradatecnicodao.java:237)" +
                    "\ncaused by: javax.faces.el.evaluationexception: java.lang.nullpointerexception" +
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
     * Test return only br.com.system packages
     */
    @Test
    public void testPackageFilter() {
        Assert.assertEquals(outStackTracePackageFilter,
                new Rule1(false, "br.com.system").simplify(inputStackTracePackageFilter));
    }

    /**
     * Test return only br.com.system packages
     */
    @Test
    public void testPackageFilter2() {
        Assert.assertEquals(outStackTracePackageFilter2,
                new Rule1(false, "").simplify(inputStackTracePackageFilter));
    }


    /**
     * Test return only br.com.system packages
     */
    @Test
    public void testPackageFilter4() {
        Assert.assertEquals(outputStackTracePackageFilter3,
                new Rule1(true, "br.com.system").simplify(inputStackTracePackageFilter3));
    }


    /**
     * Test return only br.com.system packages
     */
    @Test
    public void testPackageFilterWithQuotationMarks() {
        Assert.assertEquals(outStackTraceWithQuotationMarks,
                new Rule1(true, "br.com.system").simplify(inputStackTraceWithQuotationMarks));
    }
}