import static org.junit.Assert.*;

import java.sql.SQLException;
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
		Produit p1 = new Produit("1","Mayette","Fraiche Entière",2);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date dE2 = format.parse("14-10-2014");
		Date dC1 = format.parse("12-05-2015");
		Date dC2 = format.parse("12-10-2014");
		Commande c1 = new Commande("00213", p1, 266, "filet 1kg",50, null,dC1,"carr15432");
		Commande c2 = new Commande("00214", p1, 398, "filet 1kg",100, dE2,dC2,"carr15432");
		
		ArrayList<Commande> lesCommandes = new ArrayList<Commande>();
		lesCommandes.add(c1);
		lesCommandes.add(c2);
		
		Distributeur Dist1 = new Distributeur("01","dist1",lesCommandes);
		
		assertEquals("01",Dist1.getId());
		assertNotEquals("02",Dist1.getId());
	}

	@Test
	public void testGetCommandes() throws ParseException, SQLException {
		PersistanceSQL ps = new PersistanceSQL("localhost", 3306,"gestcommande");
		
		Produit p1 = new Produit("1","Mayette","Fraiche Entière",2);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date dE2 = format.parse("14-10-2014");
		Date dC1 = format.parse("12-05-2015");
		Date dC2 = format.parse("12-10-2014");
		Date dC3 = format.parse("08-05-2014");
		Commande c1 = new Commande("00213", p1, 266, "filet 1kg",50, null,dC1,"carr15432");
		Commande c2 = new Commande("00214", p1, 398, "filet 1kg",100, dE2,dC2,"carr15432");
		Commande c3 = new Commande("00215", p1, 2660, "filet 5kg",100, null,dC2,"carr15432");
		
		ArrayList<Commande> lesCommandes = new ArrayList<Commande>();
		lesCommandes.add(c1);
		lesCommandes.add(c2);
		lesCommandes.add(c3);
		
		Object dist1 = new Distributeur("carr15432","carreclerc",lesCommandes);
		Object dist2 = ps.ChargerDepuisBase("carr15432", "Distributeur");
		ArrayList<Commande> lesCommandes2 = new ArrayList<Commande>();
		lesCommandes2 = ((Distributeur)dist2).getCommandes();
		
		assertEquals(lesCommandes.size(),lesCommandes2.size());
		Commande com1 = lesCommandes2.get(0);
		assertEquals(c1.getId(),com1.getId());
		assertNotEquals(c2.getId(),com1.getId());
		
		Produit prod1 = com1.getProduit();
		assertEquals(p1.getId(),prod1.getId());
	}

	@Test
	public void testGetCommandesEnCours() throws ParseException, SQLException {
			PersistanceSQL ps = new PersistanceSQL("localhost", 3306,"gestcommande");
			
			Produit p1 = new Produit("1","Mayette","Fraiche Entière",2);
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date dE2 = format.parse("14-10-2014");
			Date dC1 = format.parse("12-05-2015");
			Date dC2 = format.parse("12-10-2014");
			Date dC3 = format.parse("08-05-2014");
			Commande c1 = new Commande("00213", p1, 266, "filet 1kg",50, null,dC1,"carr15432");
			Commande c2 = new Commande("00214", p1, 398, "filet 1kg",100, dE2,dC2,"carr15432");
			Commande c3 = new Commande("00215", p1, 2660, "filet 5kg",100, null,dC2,"carr15432");
			
			ArrayList<Commande> lesCommandes = new ArrayList<Commande>();
			lesCommandes.add(c1);
			lesCommandes.add(c2);
			lesCommandes.add(c3);
			
			Object dist1 = new Distributeur("carr15432","carreclerc",lesCommandes);
			Object dist2 = ps.ChargerDepuisBase("carr15432", "Distributeur");
			ArrayList<Commande> lesCommandes2EnCours = new ArrayList<Commande>();
			lesCommandes2EnCours = ((Distributeur)dist2).getCommandesEnCours();
		
		ArrayList<Commande> lesCommandesEnvoyees = new ArrayList<Commande>();
		lesCommandesEnvoyees.add(c2);
		
		ArrayList<Commande> lesCommandesEnCours = new ArrayList<Commande>();
		lesCommandesEnCours.add(c1);
		lesCommandesEnCours.add(c3);
		
		assertEquals(lesCommandesEnCours.size(),lesCommandes2EnCours.size());
		assertNotEquals(lesCommandesEnvoyees.size(), lesCommandes2EnCours.size());
		
		Commande com1 = lesCommandes2EnCours.get(0);
		Commande com2 = lesCommandes2EnCours.get(1);
		assertEquals(c1.getId(),com1.getId());
		assertNotEquals(c2.getId(),com2.getId());
	}

}
