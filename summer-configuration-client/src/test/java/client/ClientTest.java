package client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import com.samajackun.summer.conf.annotations.Property;

public class ClientTest
{
	@Property(name="test.myString")
	private static final String myString="enero";

	@Property(name="test.myInt")
	private final int myInt=120;

	@Property(name="test.myLong")
	private long myLong;

	@Property(name="test.myDouble")
	private double myDouble;

	@Property(name="test.myDir")
	private static File DIR;

	@Property(name="test.myDate", format="dd-MM-yyyy")
	private Date myDate;

	@Property(name="test.myDate2")
	private Date myDate2;

	@Test
	public void testTester()
	{
		assertEquals("enero", ClientTest.myString);
		assertEquals(120, this.myInt);
		assertEquals(1200L, this.myLong);
		assertEquals(1.2d, this.myDouble, 0.01d);
		assertNotNull(DIR);
		assertTrue(DIR.exists());
		assertEquals(new GregorianCalendar(2014, 5 - 1, 2).getTime(), this.myDate);
		assertEquals(new GregorianCalendar(2014, 5 - 1, 1).getTime(), this.myDate2);
	}

	@Test
	public void testClient()
	{
		Client client=new Client();
		assertEquals("febrero", client.getMyString());
		assertEquals(130, client.getMyInt());
		assertEquals(1300L, client.getMyLong());
		assertEquals(1.3d, client.getMyDouble(), 0.01d);
		assertEquals(new GregorianCalendar(2014, 5 - 1, 3, 10, 11, 12).getTime(), client.getMyDate());
	}

	public ClientTest()
	{

	}

	@Test
	public void test()
	{
	}
}
