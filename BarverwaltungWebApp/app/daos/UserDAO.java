package daos;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import models.User;

public class UserDAO extends BaseModelDAO
{

	public static final UserDAO INSTANCE = new UserDAO();
	
    private UserDAO() {}
    
    public User findUserByUsername(String username)
	{
		String queryStr = "SELECT u FROM User u WHERE u.username = :uUsername";
    	TypedQuery<User> query = em().createQuery(queryStr, User.class);
    	query.setParameter("uUsername", username);
   
    	List<User> list = query.getResultList();
    	if(list.isEmpty())
    	{
    		return null;
    	}else return list.get(0);
	}

	public Integer countUsers()
	{
		String queryStr = "SELECT COUNT(u) FROM User u";
		Query query = em().createQuery(queryStr);
		Number num = (Number)query.getSingleResult();
		
		return num.intValue();
	}
}
