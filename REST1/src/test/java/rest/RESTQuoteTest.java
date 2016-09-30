/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Emil
 */
public class RESTQuoteTest {

    public RESTQuoteTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }
    RESTQuote rest;

    @Before
    public void setUp() {
        rest = new RESTQuote();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getQoute method, of class RESTQuote.
     */
    @Test
    public void testGetQoute() throws Exception {
        System.out.println("getQoute");
        String result = rest.getQoute();
        assertNotNull(result);
    }

    /**
     * Test of getQuote method, of class RESTQuote.
     */
    @Test
    public void testGetQuote() throws Exception {
        System.out.println("getQuote");
        int id = 1;
        String expResult = rest.getQuote(id + 1, "");
        String result = rest.getQuote(id, "");
        assertNotEquals(expResult, result);
    }

    /**
     * Test of putQuote method, of class RESTQuote.
     */
    @Test
    public void testPutQuote() throws Exception {
        System.out.println("putQuote");
        int id = 1;
        String content = "{\"quote\":\"{Yolo swag 420 smoke weed every day - Internet 2016}\"}";
        RESTQuote instance = new RESTQuote();
        String expResult = "Yolo swag 420 smoke weed every day - Internet 2016";
        instance.putQuote(id, content);
        String result = instance.getQuote(id, "");
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteQuote method, of class RESTQuote.
     */
    @Test
    public void testDeleteQuote() throws Exception {
        System.out.println("deleteQuote");
        int id = 1;
        RESTQuote instance = new RESTQuote();

        String result = instance.deleteQuote(id);
        String expResult = instance.getQuote(id, "");
        assertEquals(expResult, "\"quote\", \"{code: 404, message: Quote with requested id not found}\"");
    }

}
