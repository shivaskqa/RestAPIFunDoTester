package com.org.ssbms.basetest;

import org.testng.annotations.Test;

import com.org.ssbms.utils.FileAndEnv;

public class BaseTest {
	
	@Test
	public void test()
	{
		
		System.out.println(FileAndEnv.readProperties());
	}

}
