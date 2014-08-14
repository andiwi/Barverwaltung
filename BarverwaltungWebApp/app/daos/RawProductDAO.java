package daos;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;

import play.db.jpa.JPA;
import models.Purchase;
import models.RawProduct;

public class RawProductDAO extends BaseModelDAO
{
	public static final RawProductDAO INSTANCE = new RawProductDAO();
	
    private RawProductDAO() {}
    
	public List<RawProduct> findAll()
	{
		String queryStr = "SELECT p FROM RawProduct p";
		TypedQuery<RawProduct> query = em().createQuery(queryStr, RawProduct.class);
		
		return query.getResultList();
	}

	public List<RawProduct> find(RawProduct rawproduct)
	{
		Session session = (Session)JPA.em().getDelegate();
    	
    	Criteria c = session.createCriteria(RawProduct.class);
    	Example example = Example.create(rawproduct);
    	example.excludeZeroes();
    	c.add(example);

    	List<RawProduct> results = c.list();
    	
    	return results;

	}
}
