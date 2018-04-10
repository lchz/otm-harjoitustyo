
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otm.kurssien_seurantajarjestelma.domain.User;

public class UserTest {

    User u1;

    @Before
    public void setUp() {
        u1 = new User(1, "Nikolas", "Nick", "nick@365.com", "pswd123");
    }

    @Test
    public void equalWhenSameName() {
        User u2 = new User(2, "Nikolas", "Nicky", "nick@365.un", "pswd1234567");
        assertTrue(u1.equals(u2));
    }

    @Test
    public void equalWhenSameUsername() {
        User u3 = new User(3, "Arto", "Nick", "nicky@365.un", "niki365");
        assertTrue(u1.equals(u3));
    }


    @Test
    public void equalWhenSameEmail() {
        User u4 = new User(4, "Susanna", "Susu", "nick@365.com", "pswd123we");
        assertTrue(u1.equals(u4));
    }

    @Test
    public void equalWhenSamePassword() {
        User u5 = new User(5, "Matti", "Mat", "mat@365.com", "pswd123");
        assertTrue(u1.equals(u5));
    }
    
}

