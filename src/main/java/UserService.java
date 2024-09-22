
public interface UserService {
	UserModel login(String username, String password);
	UserModel get(String username);
}
