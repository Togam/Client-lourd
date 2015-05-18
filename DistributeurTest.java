import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;


public class DistributeurTest {

	@Test
	public void testDistributeur() {
		
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
		
		ArrayList<Commande> lesCommandes = new ArrayList<Commande>();
		lesCommandes.add(c1);
		lesCommandes.add(c2);
		
		Distributeur Dist1 = new Distributeur("01","dist1");
		
		assertEquals("01",Dist1.getId());
		assertNotEquals("02",Dist1.getId());
	}

	@Test
	public void testGetCommandes() throws ParseException {
		Produit p1 = new Produit("1","var1","type1",1);
		Produit p2 = new Produit("2","var2","type2",2);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date dE1 = format.parse("01-01-2015");
		Date dE2 = format.parse("02-01-2014");
		Date dC1 = format.parse("01-02-2015");
		Date dC2 = format.parse("02-02-2014");
		Commande c1 = new Commande("1", p1, 10, "cond1",10, dE1,dC1,"1");
		Commande c2 = new Commande("2", p2, 20, "cond2",20, dE2,dC2,"2");
		
		ArrayList<Commande> lesCommandes = new ArrayList<Commande>();
		lesCommandes.add(c1);
		lesCommandes.add(c2);
		
		Distributeur Dist1 = new Distributeur("01","dist1");
		
		//assertEquals(lesCommandes, Dist1.getCommandes());
	}

	@Test
	public void testGetCommandesEnCours() throws ParseException {
		Produit p1 = new Produit("1","var1","type1",1);
		Produit p2 = new Produit("2","var2","type2",2);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date dE1 = format.parse("01-01-2015");
		Date dE2 = format.parse("02-01-2014");
		Date dC1 = format.parse("01-02-2015");
		Date dC2 = format.parse("02-02-2014");
		Commande c1 = new Commande("1", p1, 10, "cond1",10, dE1,dC1,"1");
		Commande c2 = new Commande("2", p2, 20, "cond2",20, dE2,dC2,"2");
		
		ArrayList<Commande> lesCommandes = new ArrayList<Commande>();
		lesCommandes.add(c1);
		lesCommandes.add(c2);
		
		ArrayList<Commande> lesCommandesEnCours = new ArrayList<Commande>();
		lesCommandesEnCours.add(c2);
		
		ArrayList<Commande> lesCommandesEnvoyees = new ArrayList<Commande>();
		lesCommandesEnvoyees.add(c1);
		
		Distributeur Dist1 = new Distributeur("01","dist1");
		
		//assertEquals(lesCommandesEnCours, Dist1.getCommandesEnCours());
		assertNotEquals(lesCommandesEnvoyees, Dist1.getCommandesEnCours());
		
	}

}
