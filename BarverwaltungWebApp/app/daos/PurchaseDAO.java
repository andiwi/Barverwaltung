package daos;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import play.db.jpa.JPA;
import models.Purchase;

public class PurchaseDAO extends BaseModelDAO
{
	public static final PurchaseDAO INSTANCE = new PurchaseDAO();
	
    private PurchaseDAO() {}

	public List<Purchase> findPurchasesByDate(Date purchaseDate)
	{
		String queryStr = "SELECT p FROM Purchase p WHERE p.purchaseDate = :pDate";
		TypedQuery<Purchase> query = em().createQuery(queryStr, Purchase.class);
		query.setParameter("pDate", purchaseDate);
		
		return query.getResultList();
	}

	public List<Purchase> findAll()
	{
		String queryStr = "SELECT p FROM Purchase p ORDER BY PURCHASEDATE desc, ID desc";
		TypedQuery<Purchase> query = em().createQuery(queryStr, Purchase.class);
		
		return query.getResultList();
	}

	public void delete(int id)
	{
		Session session = (Session)JPA.em().getDelegate();
		Purchase p = (Purchase)session.load(Purchase.class, id);
		
		session.delete(p);		
	}
}
