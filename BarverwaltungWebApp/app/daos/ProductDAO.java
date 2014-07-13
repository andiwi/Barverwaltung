package daos;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import play.db.jpa.JPA;
import models.Product;

public class ProductDAO
{
	public static final ProductDAO INSTANCE = new ProductDAO();
	
    private ProductDAO() { }
    
	public List<Product> getAllProducts()
    {
    	String queryStr = "FROM Product WHERE SALE_ID is null ORDER BY purchasedate";
        TypedQuery<Product> query = em().createQuery(queryStr,
                Product.class);
        List<Product> list = query.getResultList();
    	
    	return list;
    }
    
    /**
     * Get a given account based on the name
     * @param name
     * @return
     */
    public List<Product> findProductsByName(String productName)
    {
    	String queryStr = "FROM Product WHERE Productname is " + productName;
        TypedQuery<Product> query = em().createQuery(queryStr,
                Product.class);
        List<Product> list = query.getResultList();
    	
    	return list;
	}
    
    /**
     * Get a given account based on the purchaseDate
     * @param purchaseDate
     * @return
     */
    public List<Product> findProductsByDate(java.util.Date purchaseDate)
    {
    	Timestamp time = new Timestamp(purchaseDate.getTime());
    	String queryStr = "FROM Product WHERE purchaseDate = '" + time + "'";
        TypedQuery<Product> query = em().createQuery(queryStr,
                Product.class);
        List<Product> list = query.getResultList();
    	
    	return list;
	}

    /**
     * If no entity with the given id exists in the DB, a new entity is stored. If there is already
     * an entity with the given id, the entity is updated
     * @param entity
     * @param <T>
     * @return
     */
	public Product updateProduct(Product product)
	{
		return em().merge(product);
	}

	/**
     * Save an entity. Throws an error if an entity with the given id already exists
     * @param entity
     * @return
     */
	public void createProduct(Product product)
	{
		em().persist(product);
	}
	
	 /**
     * Get the entity manager
     * @return
     */
    private EntityManager em() {
        return JPA.em();
    }
}
