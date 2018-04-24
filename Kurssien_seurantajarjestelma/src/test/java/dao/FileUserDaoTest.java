package dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import otm.kurssienseurantajarjestelma.dao.FileUserDao;
import otm.kurssienseurantajarjestelma.dao.UserDao;
import otm.kurssienseurantajarjestelma.domain.User;

public class FileUserDaoTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    File fakeUserFile;
    UserDao dao;
    
    @Before
    public void setUp() throws IOException, Exception {
        fakeUserFile = testFolder.newFile("testfile_users.txt");
        
        try (FileWriter file = new FileWriter(fakeUserFile.getAbsolutePath())) {
            file.write("1; Nikolas; Nick; nick@365.com; nick123\n");
        } catch (Exception e) {
//            FileWriter file = new FileWriter(new File(filee));
            Throwable cause = e.getCause();
        }
        
        dao = new FileUserDao(fakeUserFile.getAbsolutePath());
    }
    
    @Test
    public void usersAreReadCorrectlyFromFile() {
        List<User> users = dao.getAll();
        assertEquals(1, users.size());
        
        User user = users.get(0);
        assertEquals("Nikolas", user.getName());
        assertEquals("Nikolas", user.getName());
        assertEquals("Nick", user.getUsername());
        assertEquals("nick@365.com", user.getEmail());
        assertEquals("nick123", user.getPassword());
    }
    
    @Test
    public void existingUserIsFoundByUsername() {
        User user = dao.findByUsername("Nick");
        assertEquals("Nikolas", user.getName());
        assertEquals("Nick", user.getUsername());
        assertEquals("nick@365.com", user.getEmail());
        assertEquals("nick123", user.getPassword());
    }
    
    @Test
    public void existingUserIsFoundByName() {
        User user = dao.findByName("Nikolas");
        assertEquals("Nikolas", user.getName());
        assertEquals("Nick", user.getUsername());
        assertEquals("nick@365.com", user.getEmail());
        assertEquals("nick123", user.getPassword());
    }
    
    @Test
    public void nonExistingUserIsFound() {
        User user = dao.findByUsername("hellas");
        assertEquals(null, user);
    }
    
    @Test
    public void passwordMatchesUser() {
        User user = dao.findByUsername("Nick");
        assertTrue("nick123", dao.passwordMatches(user, user.getPassword()));
    }
    
    @Test
    public void savedUserIsFound() throws Exception {
        User newUser = new User(2, "Arto Hellas", "hellas", "hellas@365.com", "hellas123");
        dao.create(newUser);
        
        User user = dao.findByName("Arto Hellas");
        assertEquals("Arto Hellas", user.getName());
        assertEquals("hellas", user.getUsername());
        assertEquals("hellas@365.com", user.getEmail());
    }
    
//    @Test
//    public void catchedExceptionInConstructor() {
//        when()
//    }
    
    @After
    public void tearDown() {
        fakeUserFile.delete();
    }
    
}
