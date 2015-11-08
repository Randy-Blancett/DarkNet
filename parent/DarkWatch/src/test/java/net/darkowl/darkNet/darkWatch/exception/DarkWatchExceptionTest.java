/**
 * 
 */
package net.darkowl.darkNet.darkWatch.exception;

import static org.junit.Assert.*;
import net.darkowl.darkNet.darkWatch.exceptions.DarkWatchException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Randy Blancett
 * @since Nov 4, 2015
 * 
 */
public class DarkWatchExceptionTest {

	/**
	 * @since Nov 4, 2015
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @since Nov 4, 2015
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructor() {
		DarkWatchException exception = new DarkWatchException();
		assertTrue(exception instanceof Exception);
		assertTrue(exception instanceof DarkWatchException);

		exception = new DarkWatchException("Test Message!");
		assertTrue(exception instanceof Exception);
		assertTrue(exception instanceof DarkWatchException);
		assertEquals("Test Message!", exception.getMessage());
	}

}
