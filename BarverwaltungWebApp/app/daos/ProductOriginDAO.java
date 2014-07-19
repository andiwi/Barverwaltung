package daos;

import java.util.List;







import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import models.ProductOrigin;

public class ProductOriginDAO extends BaseModelDAO
{
	public static final ProductOriginDAO INSTANCE = new ProductOriginDAO();
	
    private ProductOriginDAO() {}
    
	public List<ProductOrigin> findAll()
	{
		String queryStr = "FROM ProductOrigin";
		TypedQuery<ProductOrigin> query = em().createQuery(queryStr, ProductOrigin.class);
        List<ProductOrigin> list = query.getResultList();
		return list;
	}
}
