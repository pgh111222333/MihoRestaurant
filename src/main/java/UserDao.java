
public interface UserDao {
	UserModel get(String username);

	UserModel findByUserName(String username);
}
