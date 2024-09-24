package myPackage;

public class UserServiceImpl {
	UserDaoImpl userDao = new UserDaoImpl();
	
	public User login(String username, String password) {
		User user = this.findByUserName(username);
		if (user != null && password.equals(user.getPassWord())) {
			return user;
		}
	return null;
	}
	
	public User findByUserName(String username) {
	return userDao.findByUserName(username);
	}
}
