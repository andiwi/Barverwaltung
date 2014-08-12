package daos;


public class ProductDAO extends BaseModelDAO
{
	public static final ProductDAO INSTANCE = new ProductDAO();
	
    private ProductDAO() {}
    /*
    public long findAmountOfProductAtDate(Date date, SalesProduct productOrigin)
    {
    	String queryStr = "SELECT COUNT(p) FROM Product p WHERE p.purchaseDate = :date AND p.productOrigin = :pOrigin";
    	TypedQuery<Long> query = em().createQuery(queryStr, Long.class).setParameter("date", date);
    	query.setParameter("pOrigin", productOrigin);
    
    	return query.getSingleResult();
    }
    
	public List<Product> findAllAvailableProducts()
    {
		String queryStr = "FROM Product WHERE SALE_ID is null ORDER BY purchasedate";
        TypedQuery<Product> query = em().createQuery(queryStr,
                Product.class);
        List<Product> list = query.getResultList();
    	
    	return list;
    }

	public List<Date> findAllPurchaseDates()
	{
		String queryStr = "SELECT p.purchaseDate FROM Product p GROUP BY p.purchaseDate";
		TypedQuery<Date> query = em().createQuery(queryStr, Date.class);
		
		return query.getResultList();
	}

	public List<Product> findProductsByDate(Date purchaseDate)
	{
		String queryStr = "SELECT p FROM Product p WHERE p.purchaseDate = :pDate";
		TypedQuery<Product> query = em().createQuery(queryStr, Product.class);
		query.setParameter("pDate", purchaseDate);
		
		return query.getResultList();
	}	
	*/
}
