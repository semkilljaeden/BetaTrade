package tradeservice;

import javax.ejb.Local;

import entity.UserAccount;

@Local
public interface DBManagerLocal {

	public UserAccount createUserAccount(String username, String password, String email, String phone);
	public UserAccount createUserAccount(String username, String password, String email, String phone, String cashBalance);
	public UserAccount updateUserAccount(String username, String password, String email, String phone, String cashBalance);
	public UserAccount getUserAccount(String username);
	public String deleteUserAccount(String username);
}
