package daos;

import java.util.List;

import javax.persistence.TypedQuery;

import models.SalesProduct;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import play.db.jpa.JPA;


public class SalesProductDAO extends BaseModelDAO
{
	public static final SalesProductDAO INSTANCE = new SalesProductDAO();
	
    private SalesProductDAO() {}
    
    public List<SalesProduct> findAll()
	{
		String queryStr = "SELECT p FROM SalesProduct p";
		TypedQuery<SalesProduct> query = em().createQuery(queryStr, SalesProduct.class);
		
		return query.getResultList();
	}

	public List<SalesProduct> find(SalesProduct salesproduct) {
		Session session = (Session)JPA.em().getDelegate();
    	
    	Criteria c = session.createCriteria(SalesProduct.class);
    	Example example = Example.create(salesproduct);
    	//example.excludeZeroes();
    	c.add(example);
    	List<SalesProduct> results = c.list();
    	
    	return results;
	}
}
