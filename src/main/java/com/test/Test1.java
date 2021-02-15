package com.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class Test1 {
	
	@Test
	public void example1() {
		Assert.assertEquals(2, 3, "This test should be failed");
	}
	
	@Test
	public void example2() {
		Assert.assertEquals(2, 2);
	}
	
	@Test
	public void example3() {
		Assert.assertTrue(true, "This test should be failed");
	}
	
	@Test
    public void example4() {
        Assert.fail("This test should be failed");
    }

    @Test(dependsOnMethods = "example1")
    public void skippedByDependencyTest() {
    	
    }
  

}