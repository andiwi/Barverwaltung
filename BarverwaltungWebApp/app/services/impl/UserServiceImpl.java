package services.impl;

import daos.UserDAO;
import models.User;
import services.UserService;

public class UserServiceImpl implements UserService {

	private UserDAO userDao;
	
	public UserServiceImpl()
	{
		userDao = UserDAO.INSTANCE;
	}
	
	@Override
	public User findUserByUsername(String username)
	{
		if(username != null)
		{
			return userDao.findUserByUsername(username);
		}
		return null;
	}

	@Override
	public void login(User user)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logout(User user)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkUser(User candidate)
	{
		User userFromDb = this.findUserByUsername(candidate.getUsername());
		if(userFromDb != null)
		{
			return HashHelper.checkPassword(candidate.getPassword(), userFromDb.getPassword());
		}else return false;
	}

	@Override
	public boolean createUser(User user)
	{
		if(this.findUserByUsername(user.getUsername()) == null)
		{
			userDao.persist(user);
			if(this.findUserByUsername(user.getUsername()) != null)
			{
				return true;
			}
		}
		return false;
	}
}