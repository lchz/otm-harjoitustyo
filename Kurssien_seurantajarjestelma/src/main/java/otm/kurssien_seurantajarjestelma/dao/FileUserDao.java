package otm.kurssien_seurantajarjestelma.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import otm.kurssien_seurantajarjestelma.domain.User;

public class FileUserDao implements UserDao {

    private List<User> users;
    private String file;

    public FileUserDao(String file) throws Exception {
        users = new ArrayList<>();
        this.file = file;

        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(",");
                User u = new User(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4]);
                users.add(u);
            }

        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }

    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (User user : users) {
                writer.write(user.getId() + ";"
                        + user.getName() + ";"
                        + user.getUsername() + ";"
                        + user.getEmail() + ";"
                        + user.getPassword() + "\n");
            }
        }
    }
    
    private int generateId() {
        return users.size() + 1;
    }
    

    @Override
    public User create(User user) throws Exception {
        user.setId(generateId());
        users.add(user);
        save();
        
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername()
                .equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User findByName(String name) {
        return users.stream()
                .filter(u -> u.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> getAll() {
        return users;
    }
    
    @Override
    public boolean passwordMatches(User user, String password) {
        if(password.equals(user.getPassword())) {
            return true;
        }
        
        return false;
            
    }

}
