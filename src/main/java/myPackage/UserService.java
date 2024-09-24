package myPackage;

public interface UserService {
	User login(String username, String password);
	User get(String username);
}
