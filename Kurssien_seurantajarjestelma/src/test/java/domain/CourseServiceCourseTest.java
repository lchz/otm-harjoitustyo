package domain;

import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import otm.kurssienseurantajarjestelma.domain.Course;
import otm.kurssienseurantajarjestelma.domain.CourseService;
import otm.kurssienseurantajarjestelma.domain.User;

public class CourseServiceCourseTest {
    
    FakeCourseDao courseDao;
    FakeUserDao userDao;
    CourseService service;

    @Before
    public void setUp() throws Exception {
        courseDao = new FakeCourseDao();
        userDao = new FakeUserDao();
        User u1 = new User(2, "tester1", "tester1", "tester1@126.com", "tester1");
        User u2 = new User(3, "tester2", "tester2", "tester2@126.com", "tester2");
        userDao.create(u1);
        userDao.create(u2);
        courseDao.create(new Course(1, "jtkt", false, u1));
        service = new CourseService(courseDao, userDao);
        service.login("tester1", "tester1");
    }
    
    @Test
    public void atStarListContainsInitializedCourses() {
        List<Course> courses = service.getUnfinished();
        
        assertEquals(1, courses.size());
        Course course = courses.get(0);
        assertEquals("jtkt", course.getContent());
        assertEquals("tester1", course.getUser().getUsername());
    }
    
    @Test
    public void listEmptyIfNogLoggedIn() {
        service.logout();
        List<Course> courses = service.getUnfinished();
        
        assertEquals(0, courses.size());
    }
    
    @Test
    public void loggedUserListContainsAddedCourse() {
        service.createCourse("tikape");
        
        List<Course> courses = service.getUnfinished();
        assertEquals(2, courses.size());
        
        Course course = courses.get(1);
        assertEquals("tikape", course.getContent());
        assertEquals("tester1", course.getUser().getUsername());
    }
    
    @Test
    public void loggedUsersListDoesNotContainCoursesOfOther() {
        service.createCourse("tikape");
        service.logout();
        service.login("tester2", "tester2");
        
        List<Course> courses = service.getUnfinished();
        assertEquals(0, courses.size());
    }
    
    @Test
    public void whenMarkedFinishedIsNotListed() {
        service.markFinished(1);
        
        List<Course> courses = service.getUnfinished();
        assertEquals(0, courses.size());
    }
    
    @Test
    public void loggedUsersListOfFinishedCourses() {
        service.markFinished(1);
        service.createCourse("otm");
        
        List<Course> courses = service.getFinished();
        assertEquals(1, courses.size());
        
        service.createCourse("123");
        service.markFinished(2);
        List<Course> courses2 = service.getFinished();
        
        assertEquals(2, courses2.size());
    }

}
