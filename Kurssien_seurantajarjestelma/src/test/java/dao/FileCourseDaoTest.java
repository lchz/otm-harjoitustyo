package dao;

import domain.FakeUserDao;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import otm.kurssienseurantajarjestelma.dao.CourseDao;
import otm.kurssienseurantajarjestelma.dao.FileCourseDao;
import otm.kurssienseurantajarjestelma.dao.FileUserDao;
import otm.kurssienseurantajarjestelma.dao.UserDao;
import otm.kurssienseurantajarjestelma.domain.Course;
import otm.kurssienseurantajarjestelma.domain.User;

public class FileCourseDaoTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();  
    
    File userFile;  
    CourseDao dao;  
    
    @Before
    public void setUp() throws Exception {
        userFile = testFolder.newFile("testfile_users.txt");  
        UserDao userDao = new FakeUserDao();
        userDao.create(new User(1, "Nikolas", "Nick", "nick@365.com", "nick123"));
        
        try (FileWriter file = new FileWriter(userFile.getAbsolutePath())) {
            file.write("1; matikka; false; Nick; Nikolas\n");
        }
        
        dao = new FileCourseDao(userFile.getAbsolutePath(), userDao);  
    }
    
    @Test
    public void coursesAreReadCorrectlyFromFile() {
        List<Course> courses = dao.getAll();
        assertEquals(1, courses.size());
        
        Course course = courses.get(0);
        assertEquals("matikka", course.getContent());
        assertEquals(1, course.getId());
        assertEquals("Nick", course.getUser().getUsername());
        assertEquals("Nikolas", course.getUser().getName());
    }
    
    @Test
    public void courseCanBeSetFinished() throws Exception {
        dao.setFinished(1);
        
        Course course = dao.getAll().get(0);
        assertTrue(course.isFinished());
    }
    
    @Test
    public void finishedCourseIsntExisted() throws Exception {
        dao.setFinished(2);
        
        List<Course> courses = dao.getAll();
        courses.stream().forEach(c -> assertTrue(!c.isFinished()));
    }
    
    @Test
    public void createCoursesAreListed() throws Exception {
        dao.create(new Course(2, "english", false, new User(2, "Susanna", "susu", "susu@126.com", "susu123")));
        
        List<Course> courses = dao.getAll();
        assertEquals(2, courses.size());
        
        Course course = courses.get(1);
        assertEquals("english", course.getContent());
        assertFalse(course.isFinished());
        assertEquals(2, course.getId());
        assertEquals("Susanna", course.getUser().getName());
        assertEquals("susu", course.getUser().getUsername());
    }
    
    @After
    public void tearDown() {
        userFile.delete();
    }
}
