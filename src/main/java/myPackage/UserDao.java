package myPackage;

public interface UserDao {
	User get(String username);
	User findByUserName(String username);
}
