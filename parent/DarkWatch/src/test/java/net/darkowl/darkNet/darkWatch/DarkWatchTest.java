package net.darkowl.darkNet.darkWatch;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class DarkWatchTest {

	@Test
	public void testMainHelp() {
		PrintStream orig = System.out;
		final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(myOut));

		assertTrue(myOut.toString().length() == 0);
		String[] params = { CommandLineOptons.HELP.getComandLineFlag() };
		DarkWatch.main(params);
		assertTrue(myOut.toString().contains("-h,--help   Shows Help Menu"));
		System.setOut(orig);
	}
}
