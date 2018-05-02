package otm.kurssienseurantajarjestelma.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import otm.kurssienseurantajarjestelma.domain.User;

public class FileUserDao implements UserDao {

    private List<User> users;
    private String file;

    public FileUserDao(String file) throws Exception {
        users = new ArrayList<>();
        this.file = file;

        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split("; ");
                User u = new User(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4]);
                users.add(u);
            }

        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }
    
    /**
     * käyttäjien tallentaminen
     * 
     * @throws Exception 
     */
    private void save() throws Exception {

        try (FileWriter writer = new FileWriter(new File(file))) {
            for (User user : users) {
                writer.write(user.getId() + "; "
                        + user.getName() + "; "
                        + user.getUsername() + "; "
                        + user.getEmail() + "; "
                        + user.getPassword() + "\n");
            }

        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }
    
    /**
     * käyttäjän id:n generoiminen
     * 
     * @return käyttäjän id
     */
    private int generateId() {
        return users.size() + 1;
    }
    
    /**
     * uuden käyttäjän luominen
     * 
     * @param user käyttäjä
     * 
     * @return uusi käyttäjä jos on luotu onnistuneesti
     * 
     * @throws Exception 
     */
    @Override
    public User create(User user) throws Exception {
        user.setId(generateId());
        users.add(user);
        save();

        return user;
    }

    /**
     * käyttäjien löytäminen käyttäjätunnuksen perusteella
     * 
     * @param username käyttäjätunnus
     * 
     * @return löytynyt käyttäjä jos on olemassa, muuten null
     */
    @Override
    public User findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    /**
     * käyttäjien löytäminen nimen perusteella
     * 
     * @param name käyttäjän nimi
     * 
     * @return löytynyt käyttäjä jos on olemassa, muuten null
     */
    @Override
    public User findByName(String name) {
        return users.stream()
                .filter(u -> u.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * kaikien käyttäjien listaaminen
     * 
     * @return kaikki käyttäjät listana
     */
    @Override
    public List<User> getAll() {
        return users;
    }
    
    /**
     * salasanan oikeaallisuus
     * 
     * @param user käyttäjä
     * @param password salasana
     * 
     * @return true jos salasana oikein, muuten false
     */

    @Override
    public boolean passwordMatches(User user, String password) {
        return password.equals(user.getPassword());
    }
}
