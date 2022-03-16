package Rest_Testing;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class diffunitandtesting {
	
	@Before
	public void BT()
	{
		String Name = "Savi";
		String Sname = "Savi";
				
		Assert.assertEquals(Sname,Name);
		System.out.println("Both the names are same");
		
    }
	
	@Test
	public void testcase1()
	{
		System.out.println("This is the first method");
	}
	
	@After
	public void AT()
	{
		System.out.println("This is after method");
	}
}


