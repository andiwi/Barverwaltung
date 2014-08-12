package daos;

import java.util.List;







import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import models.SalesProduct;

public class ProductOriginDAO extends BaseModelDAO
{
	public static final ProductOriginDAO INSTANCE = new ProductOriginDAO();
	
    private ProductOriginDAO() {}
    
	public List<SalesProduct> findAll()
	{
		String queryStr = "FROM ProductOrigin";
		TypedQuery<SalesProduct> query = em().createQuery(queryStr, SalesProduct.class);
        List<SalesProduct> list = query.getResultList();
		return list;
	}
}
