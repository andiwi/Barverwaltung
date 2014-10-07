package services;

import java.util.List;

import models.User;

public interface UserService {
	
	public User findUserByUsername(String username);
	
	public void login(User user);
	
	public void logout(User user);
	
	/**
	 * Ueberprueft ob user in db vorhanden und passwort richtig eingegeben(für Login)
	 * candidate hat das Passwort unverschluesselt abgespeichert
	 * @param candidate
	 * @return
	 */
	public boolean checkUser(User candidate);
	
	public boolean createUser(User user);
	
	/*
	 * Zaehlt die in der Datenbank vorhandenen User
	 */
	public Integer countUsers();

	public List<User> findUser(User user);
}