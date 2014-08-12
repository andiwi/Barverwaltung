package daos;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

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
		String queryStr = "SELECT p FROM Purchase p";
		TypedQuery<Purchase> query = em().createQuery(queryStr, Purchase.class);
		
		return query.getResultList();
	}
}
