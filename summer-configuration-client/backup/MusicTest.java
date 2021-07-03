package client;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MusicTest
{
	@Before
	public void setup()
	{
		System.setProperty("client.Music.album", "Revolver");
		System.setProperty("client.Music.year", "1966");
	}

	@Test
	public void test()
	{
		Music music=new Music();
		assertEquals("Revolver", music.getAlbum());
		assertEquals(1966, music.getYear());
	}

}
