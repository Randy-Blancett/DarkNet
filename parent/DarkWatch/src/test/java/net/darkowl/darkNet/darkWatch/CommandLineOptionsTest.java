package net.darkowl.darkNet.darkWatch;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CommandLineOptionsTest {

	@Test
	public void testHelp() {
		// Help
		assertEquals("h", CommandLineOptons.HELP.getFlag());
		assertEquals("help", CommandLineOptons.HELP.getFull());
		assertEquals(false, CommandLineOptons.HELP.hasArg());
		assertEquals("-h", CommandLineOptons.HELP.getComandLineFlag());
		assertEquals("Shows Help Menu", CommandLineOptons.HELP.getDescription());
	}

	@Test
	public void testVersion() {
		// Help
		assertEquals("v", CommandLineOptons.VERSION.getFlag());
		assertEquals("version", CommandLineOptons.VERSION.getFull());
		assertEquals(false, CommandLineOptons.VERSION.hasArg());
		assertEquals("-v", CommandLineOptons.VERSION.getComandLineFlag());
		assertEquals("Shows Version Information",
				CommandLineOptons.VERSION.getDescription());
	}

}
