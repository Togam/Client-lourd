import static org.junit.Assert.*;
import org.junit.Test;


public class ProduitTest {

	@Test
	public void testProduit() {
		
	}
	
	@Test
	public void testGetVariete() {
		Produit p1 = new Produit("var1","type1",1);
		Produit p2 = new Produit("var2","type2",2);
		assertEquals("var1",p1.getVariete());
		assertNotEquals("var2",p1.getVariete());
		assertEquals("var2",p2.getVariete());
		assertNotEquals("var1",p2.getVariete());
	}
	
	@Test
	public void testGetType() {
		Produit p1 = new Produit("var1","type1",1);
		Produit p2 = new Produit("var2","type2",2);
		assertEquals("type1",p1.getType());
		assertNotEquals("type2",p1.getType());
		assertEquals("type2",p2.getType());
		assertNotEquals("type1",p2.getType());
	}
	
	@Test
	public void testGetCalibre() {
		Produit p1 = new Produit("var1","type1",1);
		Produit p2 = new Produit("var2","type2",2);
		assertEquals(1,p1.getCalibre());
		assertNotEquals(2,p1.getCalibre());
		assertEquals(2,p2.getCalibre());
		assertNotEquals(3,p2.getCalibre());
	}

}
