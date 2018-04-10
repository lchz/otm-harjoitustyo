package otm.kurssien_seurantajarjestelma.dao;

import java.util.List;
import otm.kurssien_seurantajarjestelma.domain.Course;

public interface CourseDao {
    
    Course create(Course course) throws Exception;
    List<Course> getAll();
    void setFinished(int id) throws Exception;
    
}
