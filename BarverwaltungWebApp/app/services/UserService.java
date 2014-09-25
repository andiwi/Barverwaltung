package services;

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
}