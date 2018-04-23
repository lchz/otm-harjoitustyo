package domain;


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
    public void setCorrectId() {
        u1.setId(2);
        assertEquals(2, u1.getId());
    }
    
    @Test
    public void getCorrectId() {
        assertEquals(1, u1.getId());
    }
    
    @Test
    public void getCorrectName() {
        assertEquals("Nikolas", u1.getName());
    }
    
    @Test
    public void getCorrectUsername() {
        assertEquals("Nick", u1.getUsername());
    }
    
    @Test
    public void getCorrectEmail() {
        assertEquals("nick@365.com", u1.getEmail());
    }
    
    @Test
    public void getCorrectPassword() {
        assertEquals("pswd123", u1.getPassword());
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
    public void notEqualWhenDifferentEmail() {
        User u5 = new User(5, "Susan", "Susuuu", "nicky@365.com", "pswd123we");
        assertFalse(u1.equals(u5));
    }
    
    @Test
    public void notEqualWhenDifferentType() {
        User u6 = new User(6, "Tester", "TestUser", "test@365.com", "test123");
        Object o = new Object();
        assertFalse(u6.equals(o));
    }
    
}

