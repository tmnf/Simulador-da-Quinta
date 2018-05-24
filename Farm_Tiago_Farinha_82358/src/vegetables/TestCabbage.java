package vegetables;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import pt.iul.ista.poo.utils.Point2D;

public class TestCabbage {
	
	 Cabbage c1, c2, c3, c4;

	@Before
	public void setUp() {
		c1 = new Cabbage(new Point2D(1,2));
		c2 = new Cabbage(new Point2D(1,2));
		c3 = new Cabbage(new Point2D(5,3));
	}

	@Test
	public void testNullAndNotNull() {
		assertNotNull(c1);
		assertNotNull(c2);
		assertNotNull(c3);
		assertNull(c4);
	}
	
	@Test
	public void testSameAndNotSame() {
		assertSame(c1,c1);
		assertNotSame(c1, c2);
		assertNotSame(c1,c3);
		
	}
	
	@Test
	public void testToString() {
		assertEquals(c1.toString(),"Cabbage- Pos: (1, 2)");
		assertEquals(c2.toString(),"Cabbage- Pos: (1, 2)");
		assertEquals(c3.toString(),"Cabbage- Pos: (5, 3)");
	}
	

}
