import static org.junit.Assert.*;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;


public class CommandeTest {

	@Test
	public void testCommande() throws ParseException {
		
	}

	@Test
	public void testGetId() throws ParseException {
		Produit p1 = new Produit("1","var1","type1",1);
		Produit p2 = new Produit("2","var2","type2",2);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date dE1 = format.parse("01-01-2015");
		Date dE2 = format.parse("02-01-2014");
		Date dC1 = format.parse("01-02-2015");
		Date dC2 = format.parse("02-02-2014");
		Commande c1 = new Commande("1", p1, 10, "cond1",10, dE1,dC1,"1");
		Commande c2 = new Commande("2", p2, 20, "cond2",20, dE2,dC2,"2");
				
		assertEquals("1",c1.getId());
		assertNotEquals("2",c1.getId());
		assertEquals("2",c2.getId());
		assertNotEquals("3",c2.getId());
	}

	@Test
	public void testGetProduit() throws ParseException {
		Produit p1 = new Produit("1","var1","type1",1);
		Produit p2 = new Produit("2","var2","type2",2);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date dE1 = format.parse("01-01-2015");
		Date dE2 = format.parse("02-01-2014");
		Date dC1 = format.parse("01-02-2015");
		Date dC2 = format.parse("02-02-2014");
		Commande c1 = new Commande("1", p1, 10, "cond1",10, dE1,dC1,"1");
		Commande c2 = new Commande("2", p2, 20, "cond2",20, dE2,dC2,"2");
				
		assertEquals(p1,c1.getProduit());
		assertNotEquals(p2,c1.getProduit());
		assertEquals(p2,c2.getProduit());
		assertNotEquals(p1,c2.getProduit());
	}

	@Test
	public void testGetPrixHT() throws ParseException {
		Produit p1 = new Produit("1","var1","type1",1);
		Produit p2 = new Produit("2","var2","type2",2);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date dE1 = format.parse("01-01-2015");
		Date dE2 = format.parse("02-01-2014");
		Date dC1 = format.parse("01-02-2015");
		Date dC2 = format.parse("02-02-2014");
		Commande c1 = new Commande("1", p1, 10, "cond1",10, dE1,dC1,"1");
		Commande c2 = new Commande("2", p2, 20, "cond2",20, dE2,dC2,"2");
		
		assertEquals(10.0,c1.getPrixHT(),0.1);
		assertNotEquals(11.0,c1.getPrixHT(),0.1);
		assertEquals(20.0,c2.getPrixHT(),0.1);
		assertNotEquals(11.0,c2.getPrixHT(),0.1);
	}

	@Test
	public void testGetConditionnement() throws ParseException {
		Produit p1 = new Produit("1","var1","type1",1);
		Produit p2 = new Produit("2","var2","type2",2);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date dE1 = format.parse("01-01-2015");
		Date dE2 = format.parse("02-01-2014");
		Date dC1 = format.parse("01-02-2015");
		Date dC2 = format.parse("02-02-2014");
		Commande c1 = new Commande("1", p1, 10, "cond1",10, dE1,dC1,"1");
		Commande c2 = new Commande("2", p2, 20, "cond2",20, dE2,dC2,"2");
		
		assertEquals("cond1",c1.getConditionnement());
		assertNotEquals("cond2",c1.getConditionnement());
		assertEquals("cond2",c2.getConditionnement());
		assertNotEquals("cond3",c2.getConditionnement());
	}

	@Test
	public void testGetQte() throws ParseException {
		Produit p1 = new Produit("1","var1","type1",1);
		Produit p2 = new Produit("2","var2","type2",2);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date dE1 = format.parse("01-01-2015");
		Date dE2 = format.parse("02-01-2014");
		Date dC1 = format.parse("01-02-2015");
		Date dC2 = format.parse("02-02-2014");
		Commande c1 = new Commande("1", p1, 10, "cond1",10, dE1,dC1,"1");
		Commande c2 = new Commande("2", p2, 20, "cond2",20, dE2,dC2,"2");
		
		assertEquals(10,c1.getQte());
		assertNotEquals(20,c1.getQte());
		assertEquals(20,c2.getQte());
		assertNotEquals(30,c2.getQte());
	}

	@Test
	public void testGetDateEnvoi() throws ParseException {
		Produit p1 = new Produit("1","var1","type1",1);
		Produit p2 = new Produit("2","var2","type2",2);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date dE1 = format.parse("01-01-2015");
		Date dE2 = format.parse("02-01-2014");
		Date dC1 = format.parse("01-02-2015");
		Date dC2 = format.parse("02-02-2014");
		Commande c1 = new Commande("1", p1, 10, "cond1",10, dE1,dC1,"1");
		Commande c2 = new Commande("2", p2, 20, "cond2",20, dE2,dC2,"2");
		
		assertEquals(format.parse("01-01-2015"),c1.getDateEnvoi());
		assertNotEquals(format.parse("02-01-2014"),c1.getDateEnvoi());
		assertEquals(format.parse("02-01-2014"),c2.getDateEnvoi());
		assertNotEquals(format.parse("12-12-2014"),c1.getDateEnvoi());
	}

	@Test
	public void testGetDateConditionnement() throws ParseException {
		Produit p1 = new Produit("1","var1","type1",1);
		Produit p2 = new Produit("2","var2","type2",2);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date dE1 = format.parse("01-01-2015");
		Date dE2 = format.parse("02-01-2014");
		Date dC1 = format.parse("01-02-2015");
		Date dC2 = format.parse("02-02-2014");
		Commande c1 = new Commande("1", p1, 10, "cond1",10, dE1,dC1,"1");
		Commande c2 = new Commande("2", p2, 20, "cond2",20, dE2,dC2,"2");
		
		assertEquals(format.parse("01-02-2015"),c1.getDateConditionnement());
		assertNotEquals(format.parse("02-02-2014"),c1.getDateConditionnement());
		assertEquals(format.parse("02-02-2014"),c2.getDateConditionnement());
		assertNotEquals(format.parse("12-12-2014"),c1.getDateConditionnement());
	}

	@Test
	public void testEnCours() throws ParseException {
		Produit p1 = new Produit("1","var1","type1",1);
		Produit p2 = new Produit("2","var2","type2",2);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date dE1 = format.parse("01-01-2015");
		Date dE2 = format.parse("02-01-2014");
		Date dC1 = format.parse("01-02-2015");
		Date dC2 = format.parse("02-02-2014");
		Commande c1 = new Commande("1", p1, 10, "cond1",10, dE1,dC1,"1");
		Commande c2 = new Commande("2", p2, 20, "cond2",20, null,dC2,"2");
		
		assertFalse(c1.EnCours());
		assertTrue(c2.EnCours());
	}

	@Test
	public void testXmlCommande() throws ParseException {
		Produit p1 = new Produit("1","var1","type1",1);
		Produit p2 = new Produit("2","var2","type2",2);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date dE1 = format.parse("01-01-2015");
		Date dE2 = format.parse("02-01-2014");
		Date dC1 = format.parse("01-02-2015");
		Date dC2 = format.parse("02-02-2014");
		Commande c1 = new Commande("1", p1, 10, "cond1",10, dE1,dC1,"1");
		Commande c2 = new Commande("2", p2, 20, "cond2",20, dE2,dC2,"2");
		
		String xml1 = "";
		xml1 += "<commande id=\"1\">";
		xml1 += "<produit variete=\"var1\" ";
		xml1 += "calibre =\"1\"/>";
		xml1 += "<conditionnement type=\"cond1\"/>";
		xml1 += "<quantite>10</quantite>";
		xml1 += "<date_conditionnement>Sun Feb 01 00:00:00 CET 2015</date_conditionnement>";
		xml1 += "<date_envoi>Thu Jan 01 00:00:00 CET 2015</date_envoi>";
		xml1 += "</commande>";
		
		String xml2 = "";
		xml2 += "<commande id=\"1\">";
		xml2 += "<produit variete=\"var1\" ";
		xml2 += "calibre =\"1\"/>";
		xml2 += "<conditionnement type=\"cond1\"/>";
		xml2 += "<quantite>10</quantite>";
		xml2 += "<date_conditionnement> pas la bonne date </date_conditionnement>";
		xml2 += "<date_envoi> pas la bonne date </date_envoi>";
		xml2 += "</commande>";
		
		assertNotEquals(xml2,c2.XmlCommande());
		assertEquals(xml1,c1.XmlCommande());	
	}

}
