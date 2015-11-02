package net.darkowl.darkNet.darkWatch;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DarkWatchTest {
	private PrintStream origOutput;
	private final ByteArrayOutputStream myOut = new ByteArrayOutputStream();

	@Before
	public void setUp() {
		origOutput = System.out;
		System.setOut(new PrintStream(myOut));
	}

	@After
	public void teardown() {
		System.setOut(origOutput);
	}

	@Test
	public void testMainHelp() {
		assertTrue(myOut.toString().length() == 0);
		String[] params = { CommandLineOptons.HELP.getComandLineFlag() };
		DarkWatch.main(params);
		assertTrue(myOut.toString().contains("-h,--help"));
	}

	@Test
	public void testMainVersion() {
		assertTrue(myOut.toString().length() == 0);
		String[] params = { CommandLineOptons.VERSION.getComandLineFlag() };
		DarkWatch.main(params);
		assertTrue(myOut.toString()
				.contains("---------- Dark Watch ----------"));
	}
}
