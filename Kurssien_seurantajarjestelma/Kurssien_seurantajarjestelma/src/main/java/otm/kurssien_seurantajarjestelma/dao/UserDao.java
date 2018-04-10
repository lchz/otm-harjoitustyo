package otm.kurssien_seurantajarjestelma.dao;

import otm.kurssien_seurantajarjestelma.domain.User;
import java.util.List;

public interface UserDao {
    
    User create(User user) throws Exception;
    User findByUsername(String username);
    User findByName(String name);
    List<User> getAll();
    boolean passwordMatches(User user, String password);
    
}
