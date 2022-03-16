package Rest_Testing;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class NG1 {
	@BeforeTest
	public void BT()
	{
		String Name = "Savi";
		String SName = "Balu";
		Assert.assertEquals(SName, Name);
	}
	
	@Test
	public void testcase1()
	{
		System.out.println("This is my first teestcase1");
	}
	
	@AfterTest
	public void AT()
	{
		System.out.println("This is After test");
	}

	}