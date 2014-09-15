package daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import models.Account;
import models.RawProduct;
import play.db.jpa.JPA;


public class AccountDAO extends BaseModelDAO
{
	public static final AccountDAO INSTANCE = new AccountDAO();
	
    private AccountDAO() { }
    
    public List<Account> getAllAccounts()
    {
    	String queryStr = "from Account where id is not 1 order by lastName, firstName";
        TypedQuery<Account> query = em().createQuery(queryStr,
                Account.class);
        List<Account> list = query.getResultList();
    	
    	return list;
    }
    
    /**
     * If no entity with the given id exists in the DB, a new entity is stored. If there is already
     * an entity with the given id, the entity is updated
     * @param entity
     * @param <T>
     * @return
     */
	public Account updateAccount(Account account)
	{
		return em().merge(account);
	}

	/**
     * Save an entity. Throws an error if an entity with the given id already exists
     * @param entity
     * @return
     */
	public void createAccount(Account account)
	{
		em().persist(account);
	}
    
    
    
  	public List<Account> find(Account account)
	{
		Session session = (Session)JPA.em().getDelegate();
    	
    	Criteria c = session.createCriteria(Account.class);
    	Example example = Example.create(account);
    	//example.excludeZeroes();
    	c.add(example);

    	List<Account> results = c.list();
    	
    	return results;
	}

	
}
