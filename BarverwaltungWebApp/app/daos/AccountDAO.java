package daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import models.Account;
import play.db.jpa.JPA;


public class AccountDAO
{
	public static final AccountDAO INSTANCE = new AccountDAO();
	
    private AccountDAO() { }
    
    public List<Account> getAllAccounts()
    {
    	String queryStr = "from Account where id is not 1";
        TypedQuery<Account> query = em().createQuery(queryStr,
                Account.class);
        List<Account> list = query.getResultList();
    	
    	return list;
    }
    
    /**
     * Get a given account based on the id
     * @param id
     * @return
     */
    public Account findAccountById(int id)
    {
    	return em().find(Account.class, id);
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
    
    
    
    
    /**
     * Get the entity manager
     * @return
     */
    private EntityManager em() {
        return JPA.em();
    }

	
}
