package daos;

import java.util.List;

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
}
