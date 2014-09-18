package nl.zeeuw.run;

import nl.zeeuw.model.Boek;
import nl.zeeuw.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 * @author Pieter
 * 
 * @date 18 sep. 2014
 */
public class RunMe {

    /**
     * @param args
     */
    public static void main(String[] args) {
	testHib1();
    }
    
    public static void testHib1 () {
	Session session = HibernateUtil.getSessionFactory().openSession();
	
	Transaction tx = session.beginTransaction();
	
	Boek b = new Boek ();
	b.setIsbn(1234567891011l);
	b.setAuteur("Henkie");
	b.setTitel("Boek 1");
	b.setPrijs(21.99);
	
	long bId = (Long) session.save(b);
	
	tx.commit();
	session.close();
	
	System.out.println("ID is " + bId);
	System.out.println("Trying to grab Boek1");
	
	Session session2 = HibernateUtil.getSessionFactory().openSession();
	
	Transaction t2 = session2.beginTransaction();
	Boek b2 = (Boek) session2.get(Boek.class, 1234567891011l);
	
	System.out.println("Titel " + b2.getTitel() + " Auteur " + b2.getAuteur());
	t2.commit();
	session2.close();
    }

}
