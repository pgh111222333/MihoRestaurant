public class UserServiceImpl implements UserService {
    UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public UserModel login(String username, String password) {
        UserModel user = this.findByUserName(username);
        if (user != null && password.equals(user.getPassWord())) {
            return user;
        }
        return null;
    }

    private UserModel findByUserName(String username) {
        return userDao.findByUserName(username); // Sửa ở đây
    }

    @Override
    public UserModel get(String username) {
        return userDao.get(username); // Cần hiện thực hóa phương thức này
    }
}
