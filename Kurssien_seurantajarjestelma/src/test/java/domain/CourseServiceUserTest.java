package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import otm.kurssienseurantajarjestelma.domain.CourseService;
import otm.kurssienseurantajarjestelma.domain.User;

public class CourseServiceUserTest {
    
    FakeCourseDao courseDao;
    FakeUserDao userDao;
    CourseService service;
    
    @Before
    public void setUp() {
        courseDao = new FakeCourseDao();
        userDao = new FakeUserDao();
        service = new CourseService(courseDao, userDao);
    }
    
    @Test
    public void nonExistingUserCanLogIn() {
        boolean result = service.login("nonexisting", "non123");
        assertFalse(result);
        
        assertEquals(null, service.getLoggedUser());
    }
    
    @Test
    public void existingUserCanLogIn() {
        boolean result = service.login("Nick", "nick123");
        assertTrue(result);
        
        User loggedIn = service.getLoggedUser();
        assertEquals("Nikolas", loggedIn.getName());
    }
    
    @Test
    public void wrongPasswordCannotLogIn() {
        boolean result = service.login("Nick", "nick");
        assertFalse(result);
        
        assertEquals(null, service.getLoggedUser());
    }
    
    @Test
    public void loggedInUserCanLogout() {
        service.login("Nick", "nick123");
        service.logout();
        
        assertEquals(null, service.getLoggedUser());
    }
}
