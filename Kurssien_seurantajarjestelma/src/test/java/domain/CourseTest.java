package domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otm.kurssienseurantajarjestelma.domain.Course;
import otm.kurssienseurantajarjestelma.domain.User;

public class CourseTest {
    User u1;
    Course c1;
    
    @Before
    public void setUp() {
        u1 = new User(1, "Nikolas", "Nick", "nick@365.com", "pswd123");
        c1 = new Course(1, "matikka", false, u1);
    }
    
    @Test
    public void courseIsExisted() {
        assertTrue(c1 != null);
    }
    
    @Test
    public void setCorrectId() {
        c1.setId(2);
        assertEquals(2, c1.getId());
    }
    
    @Test
    public void getCorrectId() {
        assertEquals(1, c1.getId());
    }
    
    @Test
    public void getCorrectContent() {
        assertEquals("matikka", c1.getContent());
    }
    
    @Test
    public void getCorrectUser() {
        assertEquals(u1, c1.getUser());
    }
    
    @Test
    public void getCorrectStatusOfCourse() {
        assertEquals(false, c1.isFinished());
    }
    
    @Test
    public void setCorrectStatusOfCourse() {
        c1.setFinished();
        assertEquals(true, c1.isFinished());
    }
    
    @Test
    public void notEqualWhenDifferentType() {
        Object o = new Object();
        assertFalse(c1.equals(o));
    }
    
    @Test
    public void equalWhenSameId() {
        Course c2 = new Course(1, "english", false, u1);
        assertTrue(c1.equals(c2));
    }
    
    @Test
    public void notEqualWhenDifferentId() {
        Course c3 = new Course(3, "jtkt", false, u1);
        assertFalse(c1.equals(c3));
    }
    
    @Test
    public void equalWhenSameContentBySameUser() {
        Course c4 = new Course(4, "matikka", true, u1);
        assertTrue(c1.equals(c4));
    }
    
    @Test
    public void notEqualWhenSameContentByDifferentUser() {
        User u2 = new User(2, "Maria Suomi", "Marry", "marry@helsinki.fi", "maria123");
        Course cMaria = new Course(5, "matikka", false, u2);
        assertFalse(c1.equals(cMaria));
    }
    
    @Test
    public void notEqualWhenDifferentContentBySameUser() {
        Course c6 = new Course(6, "TiTo", false, u1);
        assertFalse(c1.equals(c6));
    }
    
}
