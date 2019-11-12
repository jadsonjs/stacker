package br.ufrn.case_.stacker.rules;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class Rule0Test {

    String s1 = "Exception in thread \"main\" java.lang.NullPointerException\n" +
            "       at com.example.myproject.Book.getTitle(Book.java:1)\n " +
            "        at com.example.myproject.Author.getBookTitles(Author.java:25)\n " +
            "        at com.example.myproject.Bootstrap.main(Bootstrap.java:14)";

    String s2 = "Exception in thread \"main\" java.lang.NullPointerException\n" +
            "        at com.example.myproject.Book.getTitle(Book.java:170)\n" +
            "        at com.example.myproject.Author.getBookTitles(Author.java:25)\n" +
            "        at com.example.myproject.Bootstrap.main(Bootstrap.java:14)";

    @Test
    public void testVerifyRule0() {

        Rule0 r0 = new Rule0();
        Assert.assertTrue(r0.verifyRule(s1, s2));

        System.out.println(r0.getStackTrace());
    }
}