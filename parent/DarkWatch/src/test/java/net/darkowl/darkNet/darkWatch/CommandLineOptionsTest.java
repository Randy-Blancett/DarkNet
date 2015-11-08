package net.darkowl.darkNet.darkWatch;

import org.junit.Assert;
import org.junit.Test;

public class CommandLineOptionsTest {

	@Test
	public void testHelp() {
		// Help
		Assert.assertEquals("h", CommandLineOptons.HELP.getFlag());
		Assert.assertEquals("help", CommandLineOptons.HELP.getFull());
		Assert.assertEquals(false, CommandLineOptons.HELP.hasArg());
		Assert.assertEquals("-h", CommandLineOptons.HELP.getComandLineFlag());
		Assert.assertEquals("Shows Help Menu",
				CommandLineOptons.HELP.getDescription());
	}

	@Test
	public void testVersion() {
		// Help
		Assert.assertEquals("v", CommandLineOptons.VERSION.getFlag());
		Assert.assertEquals("version", CommandLineOptons.VERSION.getFull());
		Assert.assertEquals(false, CommandLineOptons.VERSION.hasArg());
		Assert.assertEquals("-v", CommandLineOptons.VERSION.getComandLineFlag());
		Assert.assertEquals("Shows Version Information",
				CommandLineOptons.VERSION.getDescription());
	}

}
