package domain;

import java.util.ArrayList;
import java.util.List;
import otm.kurssienseurantajarjestelma.dao.CourseDao;
import otm.kurssienseurantajarjestelma.domain.Course;

public class FakeCourseDao implements CourseDao{
    List<Course> courses;
    
    public FakeCourseDao() {
        courses = new ArrayList<>();
    }

    @Override
    public Course create(Course course) throws Exception {
        course.setId(courses.size() + 1);
        courses.add(course);
        return course;
    }

    @Override
    public List<Course> getAll() {
        return courses;
    }

    @Override
    public void setFinished(int id) throws Exception {
        for (Course c: courses) {
            if (c.getId() == id) {
                c.setFinished();
            }
        }
    }
    
    
}
