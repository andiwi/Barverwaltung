package daos;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import models.Account;
import models.Sale;

public class SaleDAO extends BaseModelDAO
{
	public static final SaleDAO INSTANCE = new SaleDAO();
	
    private SaleDAO() {}
    
    public List<Sale> findAllWhereConsumer(Account consumer)
	{
		String queryStr = "SELECT s FROM Sale s WHERE s.consumer = :sConsumer ORDER BY s.sellDate desc";
		TypedQuery<Sale> query = em().createQuery(queryStr, Sale.class);
		query.setParameter("sConsumer", consumer);
		
		return query.getResultList();
	}

	public List<Sale> findAllWhereConsumerAndDate(Account consumer, Date date)
	{
		String queryStr = "SELECT s FROM Sale s WHERE s.consumer = :sConsumer AND s.sellDate = :sSellDate ORDER BY s.sellDate desc";
		TypedQuery<Sale> query = em().createQuery(queryStr, Sale.class);
		query.setParameter("sConsumer", consumer);
		query.setParameter("sSellDate", date);
		
		return query.getResultList();
	}
}
