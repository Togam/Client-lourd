import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;


public class PersistanceSQLTest {

	@Test
	public void testPersistanceSQL() {
		
	}

	@Test
	public void testRangerDansBase() {
		
		
	}

	@Test
	public void testChargerDepuisBase__Produit() {
			PersistanceSQL ps = new PersistanceSQL("localhost", 3306,"vdev");
			
			Object objet = new Produit("var1","fraiche entiere",10);
			Object objet2= ps.ChargerDepuisBase("1", "Produit");

			
			assertEquals(objet.getClass(),objet2.getClass());
			assertEquals(((Produit) objet).getVariete(),((Produit) objet2).getVariete());
			assertEquals(((Produit) objet).getType(),((Produit) objet2).getType());
			assertEquals(((Produit) objet).getCalibre(),((Produit) objet2).getCalibre());
		
	}
	
	public void testChargerDepuisBase__Commande() throws ParseException {
		PersistanceSQL ps = new PersistanceSQL("localhost", 3306,"vdev");
		
		Object objet = new Produit("var1","fraiche entiere",10);
		
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date dE = format.parse("01-01-2015");
		Date dC = format.parse("01-01-2015");
		
		Object objet3 = new Commande(1, (Produit)objet, 0.F, "cond1",10, dE,dC);
		Object objet4 = ps.ChargerDepuisBase("1", "Commande");
		
		assertEquals(objet3.getClass(),objet4.getClass());
		assertEquals(((Commande) objet3).getProduit(),((Commande) objet3).getProduit());
}

}
