/**
 * 
 */
package net.darkowl.darkNet.darkWatch.exception;

import net.darkowl.darkNet.darkWatch.exceptions.DarkWatchException;

import org.junit.After;
import org.junit.Assert;
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
		Assert.assertTrue(exception instanceof Exception);
		Assert.assertTrue(exception instanceof DarkWatchException);

		exception = new DarkWatchException("Test Message!");
		Assert.assertTrue(exception instanceof Exception);
		Assert.assertTrue(exception instanceof DarkWatchException);
		Assert.assertEquals("Test Message!", exception.getMessage());
	}

}
