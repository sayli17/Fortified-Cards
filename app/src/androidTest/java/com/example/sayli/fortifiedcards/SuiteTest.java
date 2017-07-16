package com.example.sayli.fortifiedcards;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Created by Aniiket on 2/15/2017.
 */
public class SuiteTest extends TestSuite {
    public static TestSuite suite(){
        TestSuite suite= new TestSuite("TestCScan");
        suite.addTest(TestSuite.createTest(TestCScan.class, "test1"));
        return suite;
    }

}
