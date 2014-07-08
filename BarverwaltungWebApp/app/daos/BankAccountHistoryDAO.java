package daos;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import models.BankAccountHistory;
import play.db.jpa.JPA;

public class BankAccountHistoryDAO {

	public static final BankAccountHistoryDAO INSTANCE = new BankAccountHistoryDAO();
		
	private BankAccountHistoryDAO() { }
	    
    public List<BankAccountHistory> getAllBankAccountHistory()
    {
    	String queryStr = "FROM BankAccountHistory";
        TypedQuery<BankAccountHistory> query = em().createQuery(queryStr,
        		BankAccountHistory.class);
        List<BankAccountHistory> list = query.getResultList();
    	
    	return list;
    }
    
    /**
     * Get a given account based on the id
     * @param id
     * @return
     */
    public BankAccountHistory findBankAccountHistoryByDate(Date from, Date to)
    {
    	//return em().find(BankAccountHistory.class, id);
    	//TODO
    	return null;
	}

	/**
     * Save an entity. Throws an error if an entity with the given id already exists
     * @param entity
     * @return
     */
	public void createEntry(BankAccountHistory entry)
	{
		em().persist(entry);
	}
    
    /**
     * Get the entity manager
     * @return
     */
    private EntityManager em() {
        return JPA.em();
    }

}
