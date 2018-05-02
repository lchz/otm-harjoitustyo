package otm.kurssienseurantajarjestelma.dao;

import java.util.List;
import otm.kurssienseurantajarjestelma.domain.Course;

public interface CourseDao {
    
    Course create(Course course) throws Exception;
    List<Course> getAll();
    void setFinished(int id) throws Exception;
   
}
