package net.darkowl.darkNet.darkWatch;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import net.darkowl.darkNet.darkObjects.config.Configuration;

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
		String[] params = { CommandLineOptons.HELP.getComandLineFlag() };
		DarkWatch.main(params);
		assertTrue(myOut.toString().contains("-h,--help"));
	}

	@Test
	public void testMainVersion() {
		String[] params = { CommandLineOptons.VERSION.getComandLineFlag() };
		DarkWatch.main(params);
		assertTrue(myOut.toString()
				.contains("---------- Dark Watch ----------"));
	}

	@Test
	public void testMainConfig() {
		String[] params = {
				CommandLineOptons.CONFIG.getComandLineFlag(),
				DarkWatchTest.class.getResource(
						"/DarkWatchTestProperties.properties").getPath() };
		DarkWatch.main(params);
		assertEquals("hello", Configuration.getString("hell.o"));
	}
}
