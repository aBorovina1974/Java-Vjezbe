package hr.java.vjezbe.baza.podataka;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import hr.java.vjezbe.entitet.Alarm;
import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.KategorijaArtikla;
import hr.java.vjezbe.entitet.Klijent;
import hr.java.vjezbe.entitet.Tvrtka;
import hr.java.vjezbe.entitet.Zaposlenik;

public class BazaPodataka {
	
	private static final String DATABASE_FILE = "resources/bazaPodataka.properties";
	
	/**
	 * Kreira konekciju na bazu podataka. Vraæa referencu
	 * na objekt klase Connection.
	 * @return referenca na objekt klae Connection
	 * @throws SQLException
	 * @throws IOException
	 */
	private static Connection spajanjeNaBazuPodataka() throws SQLException, IOException
	{
		Properties podaciZaKonekciju = new Properties();
		podaciZaKonekciju.load(new FileReader(DATABASE_FILE));
		
		String urlBazePodataka = podaciZaKonekciju.getProperty("bazaPodatakaUrl");
		String korisnickoIme = podaciZaKonekciju.getProperty("korisnickoIme");
		String lozinka = podaciZaKonekciju.getProperty("lozinka");
		
		Connection veza = DriverManager.getConnection(urlBazePodataka, korisnickoIme, lozinka);
		return veza;
	}
	
	/**
	 * Zatvara konekciju prema bazi podataka. Kao argument prima 
	 * referencu na objekt klase Connection.
	 * @param veza referenca na objekt klase Connection.
	 * @throws SQLException
	 */
	private static void zatvaranjeVezeNaBazuPodataka(Connection veza) throws SQLException
	{
		veza.close();
	}
	
	/**
	 * Sprema podatke o objektu klase Klijent u bazu podataka.
	 * Kao argumente prima referencu na objekt klase Klijent,
	 * i referencu na objekt klase Tvrtka.
	 * @param klijent referenca na objekt klase Klijent.
	 * @param tvrtka referenca na objekt klase Tvrtka.
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void spremiKlijenta(Klijent klijent, Tvrtka tvrtka) throws SQLException, IOException
	{
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false);
		try
		{
		PreparedStatement insertKlijent = veza.prepareStatement("INSERT INTO VJEZBE.KLIJENT" +
		"(OIB, PREZIME, IME, TELEFON, EMAIL, DATUM_RODJENJA) VALUES(?, ?, ?, ?, ?, ?)");
		
		insertKlijent.setString(1, klijent.getOib());
		insertKlijent.setString(2, klijent.getPrezime());
		insertKlijent.setString(3, klijent.getIme());
		insertKlijent.setString(4, klijent.getTelefon());
		insertKlijent.setString(5, klijent.getEmail());
		insertKlijent.setDate(6, Date.valueOf(klijent.getDatumRodjenja()));
		
		insertKlijent.executeUpdate();
		
		ResultSet generatedKeys = insertKlijent.getGeneratedKeys();
		if(generatedKeys.next())
		{
			klijent.setId(generatedKeys.getInt(1));
		}
		
		PreparedStatement insertTvrtkaKlijent = veza.prepareStatement(
        "INSERT INTO VJEZBE.TVRTKA_KLIJENT(TVRTKA_ID, KLIJENT_ID) VALUES(?, ?)");
		
		insertTvrtkaKlijent.setInt(1, tvrtka.getId());
		insertTvrtkaKlijent.setInt(2, klijent.getId());
		
		insertTvrtkaKlijent.executeUpdate();
		veza.commit();
		
		}
		catch (Throwable ex)
		{
			veza.rollback();
			throw ex;
		}
		zatvaranjeVezeNaBazuPodataka(veza);
		
	}
	
	/**
	 * Dohvaæa podatke o klijentima iz baze podataka.
	 * Vraæa referencu na listu objekata klase Klijent.
	 * @return referenca na listu klijenata.
	 * @throws SQLException
	 * @throws IOException
	 */
	public static List<Klijent> dohvatiKlijente() throws SQLException, IOException
	{
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false);
		
		Statement statementKlijenti = veza.createStatement();
		ResultSet resultSetKlijenti = statementKlijenti.
				  executeQuery("SELECT * FROM VJEZBE.KLIJENT");
		
		List<Klijent> listaKlijenata = new ArrayList<>();
		
		while(resultSetKlijenti.next())
		{
			Integer klijentId = resultSetKlijenti.getInt("ID");
			String oib = resultSetKlijenti.getString("OIB");
			String prezime = resultSetKlijenti.getString("PREZIME");
			String ime = resultSetKlijenti.getString("IME");
			String telefon = resultSetKlijenti.getString("TELEFON");
			String email = resultSetKlijenti.getString("EMAIL");
			LocalDate datumRodjenja= resultSetKlijenti.getDate("DATUM_RODJENJA").toLocalDate();
			
			Klijent klijent = new Klijent(oib, prezime, ime, telefon, email, datumRodjenja);
			klijent.setId(klijentId);
			
			listaKlijenata.add(klijent);
		}
		zatvaranjeVezeNaBazuPodataka(veza);
		return listaKlijenata;
	}
	
	/**
	 * Sprema podatke o objektu klase Zaposlenik u bazu podataka.
	 * Kao argumente prima referencu na objekt klase Zaposlenik,
	 * i referencu na objekt klase Tvrtka.
	 * @param zaposlenik referenca na objekt klase Zaposlenik.
	 * @param tvrtka referenca na objekt klase Tvrtka.
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void spremiZaposlenika(Zaposlenik zaposlenik, Tvrtka tvrtka) throws
	SQLException, IOException
	{
		
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false);
		try
		{
		PreparedStatement insertZaposlenik = veza.prepareStatement(
		"INSERT INTO VJEZBE.ZAPOSLENIK(KORISNICKO_IME, PREZIME, IME, SIFRA) VALUES(?, ?, ?, ?)");
		
		insertZaposlenik.setString(1, zaposlenik.getKorisnickoIme());
		insertZaposlenik.setString(2, zaposlenik.getPrezime());
		insertZaposlenik.setString(3, zaposlenik.getIme());
		insertZaposlenik.setString(4, zaposlenik.getSifraZaposlenika());

		insertZaposlenik.executeUpdate();
		
		ResultSet generatedKeys = insertZaposlenik.getGeneratedKeys();
		if(generatedKeys.next())
		{
			zaposlenik.setId(generatedKeys.getInt(1));
		}
		
		PreparedStatement insertTvrtkaZaposlenik = veza.prepareStatement(
		"INSERT INTO VJEZBE.TVRTKA_ZAPOSLENIK(TVRTKA_ID, ZAPOSLENIK_ID) VALUES(?, ?)");
		insertTvrtkaZaposlenik.setInt(1, tvrtka.getId());
		insertTvrtkaZaposlenik.setInt(2, zaposlenik.getId());
		
		insertTvrtkaZaposlenik.executeUpdate();
		veza.commit();
		}
		catch(Throwable ex)
		{
			veza.rollback();
			throw ex;
		}
		zatvaranjeVezeNaBazuPodataka(veza);
	}
	
	/**
	 * Dohvaæa podatke o zaposlenicima iz baze podataka.
	 * Vraæa referencu na listu objekata klase Zaposlenik.
	 * @return referenca na listu zaposlenika.
	 * @throws SQLException
	 * @throws IOException
	 */
	public static List<Zaposlenik> dohvatiZaposlenike() throws SQLException, IOException
	{
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false);
		
		Statement statementZaposlenici = veza.createStatement();
		ResultSet resultSetZaposlenici = statementZaposlenici.executeQuery(
		"SELECT * FROM VJEZBE.ZAPOSLENIK");
		
		List<Zaposlenik> zaposlenici = new ArrayList<>();
		while(resultSetZaposlenici.next())
		{
			Integer id = resultSetZaposlenici.getInt("ID");
			String korisnickoIme = resultSetZaposlenici.getString("KORISNICKO_IME");
			String prezime = resultSetZaposlenici.getString("PREZIME");
			String ime = resultSetZaposlenici.getString("IME");
			String sifraZaposlenika = resultSetZaposlenici.getString("SIFRA");
			
			Zaposlenik zaposlenik = new Zaposlenik(korisnickoIme, ime, prezime, sifraZaposlenika);
			zaposlenik.setId(id);
			zaposlenici.add(zaposlenik);
		}
		zatvaranjeVezeNaBazuPodataka(veza);
		return zaposlenici;
	}
	
	/**
	 * Sprema podatke o objektu klase Artikl u bazu podataka.
	 * Kao argumente prima referencu na objekt klase Artikl,
	 * i referencu na objekt klase Tvrtka.
	 * @param artikl referenca na objekt klase Artikl.
	 * @param tvrtka referenca na objekt klase Tvrtka.
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void spremiArtikl(Artikl artikl, Tvrtka tvrtka) throws SQLException, IOException
	{
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false);
		try
		{
		PreparedStatement insertArtikl = veza.prepareStatement(
		"INSERT INTO VJEZBE.ARTIKL(NAZIV, KATEGORIJA_ID) VALUES(?, ?)");
		insertArtikl.setString(1, artikl.getNaziv());
		insertArtikl.setInt(2, artikl.getKategorija().getId());
		
		insertArtikl.executeUpdate();
		
		ResultSet generatedKeys = insertArtikl.getGeneratedKeys();
		while(generatedKeys.next())
		{
			artikl.setId(generatedKeys.getInt(1));
		} 
		
		PreparedStatement insertTvrtkaArtikl = veza.prepareStatement(
		"INSERT INTO VJEZBE.TVRTKA_ARTIKL(TVRTKA_ID, ARTIKL_ID) VALUES(?, ?)");
		insertTvrtkaArtikl.setInt(1, tvrtka.getId());
		insertTvrtkaArtikl.setInt(2, artikl.getId());
		
		insertTvrtkaArtikl.executeUpdate();
		veza.commit();
		}
		catch (Throwable ex) 
		{
			veza.rollback();
			throw ex;
		}
		zatvaranjeVezeNaBazuPodataka(veza);
	}
	
	/**
	 * Dohvaæa podatke o artiklima iz baze podataka.
	 * Vraæa referencu na listu objekata klase Artikl.
	 * @return referenca na listu artikala.
	 * @throws SQLException
	 * @throws IOException
	 */
	public static List<Artikl> dohvatiArtikle() throws SQLException, IOException
	{
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false);
		
		Statement statementArtikli = veza.createStatement();
		ResultSet resultSetArtikli = statementArtikli.executeQuery(
		"SELECT * FROM VJEZBE.ARTIKL");
		
		List<Artikl> artikli = new ArrayList<>();
		KategorijaArtikla[] kategorije = KategorijaArtikla.values();
		KategorijaArtikla kategorijaArtikla = null;
		while(resultSetArtikli.next())
		{
			Integer id = resultSetArtikli.getInt("ID");
			String naziv = resultSetArtikli.getString("NAZIV");
			Integer kategorijaId = resultSetArtikli.getInt("KATEGORIJA_ID");
			
			PreparedStatement getKategorija = veza.prepareStatement(
			"SELECT NAZIV FROM VJEZBE.KATEGORIJA_ARTIKLA WHERE ID = ?");
			getKategorija.setInt(1, kategorijaId);
			
			ResultSet resultSetKategorija = getKategorija.executeQuery();
			if(resultSetKategorija.next())
			{
				String nazivKategorije = resultSetKategorija.getString(1);
				for(KategorijaArtikla kategorija : kategorije)
				{
					if(kategorija.toString().equals(nazivKategorije))
					{
						kategorijaArtikla = kategorija;
					}
				}
			}
			
			Artikl artikl = new Artikl(naziv, kategorijaArtikla);
			artikl.setId(id);
			artikli.add(artikl);
		}
		zatvaranjeVezeNaBazuPodataka(veza);
		return artikli;			
	}
	
	/**
	 * Dohvaæa podatke o tvrtci iz baze podataka.
	 * Vraæa referencu na objekt klase Tvrtka.
	 * @return refeenca na objekt klase Tvrtka.
	 * @throws SQLException
	 * @throws IOException
	 */
	public static Tvrtka dohvatiTvrtku() throws SQLException, IOException
	{
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false);
		
		Statement statementTvrtka = veza.createStatement();
		ResultSet resultSetTvrtka = statementTvrtka.executeQuery(
		"SELECT * FROM VJEZBE.TVRTKA");
		String nazivTvrtke = null, oibTvrtke = null;
		Integer id = null;
		if(resultSetTvrtka.next())
		{
			id = resultSetTvrtka.getInt("ID");
			nazivTvrtke = resultSetTvrtka.getString("NAZIV");
			oibTvrtke = resultSetTvrtka.getString("OIB");
		}
		
		Tvrtka tvrtka = new Tvrtka(nazivTvrtke, oibTvrtke);
		tvrtka.setId(id);
		
		zatvaranjeVezeNaBazuPodataka(veza);
		return tvrtka;
	}
	
	/**
	 * Dohvaæa kategorije artikala iz baze podataka.
	 * Vraæa referencu na listu enum objekata kategorijaArtikla.
	 * @return referenca na listu enum objekata.
	 * @throws SQLException
	 * @throws IOException
	 */
	public static List<KategorijaArtikla> dohvatiKategorijeArtikala() throws SQLException, IOException
	{
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false);
		
		Statement statementKategorije = veza.createStatement();
		ResultSet resultSetKategorije = statementKategorije.executeQuery(
		"SELECT * FROM VJEZBE.KATEGORIJA_ARTIKLA");
		
		List<KategorijaArtikla> kategorijeArtikala = new ArrayList<>();
		KategorijaArtikla[] kategorije = KategorijaArtikla.values();
		KategorijaArtikla kategorijaArtikla = null;
		
		while(resultSetKategorije.next())
		{
			Integer id = resultSetKategorije.getInt(1);
			String nazivKategorije = resultSetKategorije.getString(2);
			for(KategorijaArtikla kategorija :kategorije)
			{
				if(kategorija.toString().equals(nazivKategorije))
				{
					kategorijaArtikla = kategorija;
					kategorijaArtikla.setId(id);
					kategorijeArtikala.add(kategorijaArtikla);
				}
			}
		}
		zatvaranjeVezeNaBazuPodataka(veza);
		return kategorijeArtikala;
	}
	
	/**
	 * Dohvaæa podatke o alarmima iz baze podataka.
	 * Vraæa referencu na listu objekata klase Alarm.
	 * @return referenca na listu alarma.
	 * @throws SQLException
	 * @throws IOException
	 */
	public static List<Alarm> dohvatiAlarme() throws SQLException, IOException
	{
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false);
		
		Statement statementAlarmi = veza.createStatement();
		ResultSet resultSetAlarmi = statementAlarmi.executeQuery(
		"SELECT * FROM VJEZBE.ALARM");
		
		List<Alarm> alarmi = new ArrayList<>();
		while(resultSetAlarmi.next())
		{
			Integer idKlijenta = resultSetAlarmi.getInt("KLIJENT_ID");
			Integer id = resultSetAlarmi.getInt("ID");
			String opisAlarma = resultSetAlarmi.getString("OPIS");
			LocalDateTime vrijemeAlarma = resultSetAlarmi.getTimestamp("VRIJEME").toLocalDateTime();
			Boolean statusAlarma = resultSetAlarmi.getBoolean("AKTIVAN");
			
			PreparedStatement getKlijent = veza.prepareStatement(
			"SELECT * FROM VJEZBE.KLIJENT WHERE ID = ?");
			getKlijent.setInt(1, idKlijenta);
			ResultSet resultSetKlijent = getKlijent.executeQuery();
			if(resultSetKlijent.next())
			{
				
				String oib = resultSetKlijent.getString("OIB");
				String prezime = resultSetKlijent.getString("PREZIME");
				String ime = resultSetKlijent.getString("IME");
				String telefon = resultSetKlijent.getString("TELEFON");
				String email = resultSetKlijent.getString("EMAIL");
				LocalDate datumRodjenja = resultSetKlijent.getDate("DATUM_RODJENJA").toLocalDate();
				Klijent klijent = new Klijent(oib, prezime, ime, telefon, email, datumRodjenja);
				klijent.setId(idKlijenta);
				Alarm alarm = new Alarm(klijent, opisAlarma, vrijemeAlarma, statusAlarma);
				alarm.setId(id);
				alarmi.add(alarm);
			}
	
		}
		zatvaranjeVezeNaBazuPodataka(veza);
		return alarmi;
	}
	
	/**
	 * Provjerava da li je greškom unesen OIB koji
	 * pripada drugom klijentu.
	 * Kaoargument prima String koji predstavlja OIB 
	 * unesen od korisnika.
	 * Ako je greškom unesen tuði oib vraæa true a ako 
	 * je OIB ispravan vraæa false.
	 * @param oib oib unesen od korisnika.
	 * @return true ako veæ postoji u bazi ili false ako ne postoji
	 * @throws SQLException
	 * @throws IOException
	 */
	public static Boolean provjeriOib(String oib) throws SQLException, IOException
	{
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false);
		
		PreparedStatement getOib = veza.prepareStatement(
		"SELECT OIB FROM VJEZBE.KLIJENT WHERE OIB = ?");
		getOib.setString(1, oib);
		ResultSet resultSetOib = getOib.executeQuery();
		
		zatvaranjeVezeNaBazuPodataka(veza);
		
		if(resultSetOib.next())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Provjerava da li je greškom unesena šifra ili korisnièko ime
	 * koji pripada drugom zaposleniku.
	 * Kao argument prima referencu na objekt klase zaposlenik.
	 * @param zaposlenik referenca na objekt klase zaposlenik.
	 * Vraæa Integer koji predstavlja šifru ili korisnièko ime 
	 * koje se veæ koristi ili su pak šifra i korisnièko ime ispravni.
	 * @return -1 ako se šifra veæ koristi, 1 ako se korisnièko ime veæ
	 * koristi ili 0 ako su šifra i korisnièko ime ispravni.
	 * @throws SQLException
	 * @throws IOException
	 */
	public static Boolean provjeriSifruIKorisnickoIme(Zaposlenik zaposlenik) throws 
	SQLException, IOException
	{
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false);
		
		String sifra = zaposlenik.getSifraZaposlenika();
		String korisnickoIme = zaposlenik.getKorisnickoIme();
		
		PreparedStatement getSifra = veza.prepareStatement(
		"SELECT SIFRA FROM VJEZBE.ZAPOSLENIK WHERE SIFRA = ?");
		getSifra.setString(1, sifra);
		ResultSet resultSetSifra = getSifra.executeQuery();
		
		PreparedStatement getKorisnickoIme = veza.prepareStatement(
		"SELECT KORISNICKO_IME FROM VJEZBE.ZAPOSLENIK WHERE KORISNICKO_IME = ?");
		getKorisnickoIme.setString(1, korisnickoIme);
		ResultSet resultSetKorisnickoIme = getKorisnickoIme.executeQuery();
		
		if(resultSetSifra.next() || resultSetKorisnickoIme.next())
		{
			zatvaranjeVezeNaBazuPodataka(veza);
			return true;
		}
		else
		{
			zatvaranjeVezeNaBazuPodataka(veza);
			return false;	
		}
	}
}


