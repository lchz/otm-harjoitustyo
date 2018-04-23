package domain;

import java.util.ArrayList;
import java.util.List;
import otm.kurssien_seurantajarjestelma.dao.UserDao;
import otm.kurssien_seurantajarjestelma.domain.User;

public class FakeUserDao implements UserDao {
    List<User> users = new ArrayList<>();
    
    public FakeUserDao() {
        users.add(new User(1, "Nikolas", "Nick", "nick@365.com", "nick123"));
    }

    @Override
    public User create(User user) throws Exception {
        users.add(user);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
    }

    @Override
    public User findByName(String name) {
        return users.stream().filter(u -> u.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public boolean passwordMatches(User user, String password) {
        if(user.getPassword().equals(password)) {
            return true;
        }
        
        return false;
    }
    
}
