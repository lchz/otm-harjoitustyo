package otm.kurssienseurantajarjestelma.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import otm.kurssienseurantajarjestelma.domain.Course;
import otm.kurssienseurantajarjestelma.domain.User;

public class FileCourseDao implements CourseDao {

    public List<Course> courses;
    private String file;

    public FileCourseDao(String file, UserDao users) throws Exception {
        this.courses = new ArrayList<>();
        this.file = file;

        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split("; ");
                int id = Integer.parseInt(parts[0]);
                boolean finished = Boolean.parseBoolean(parts[2]);

                User user = users.getAll()
                        .stream()
                        .filter(u -> u.getUsername().equals(parts[3])).findFirst().orElse(null);

                Course course = new Course(id, parts[1], finished, user);
                this.courses.add(course);
            }

        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }

    private void save() throws Exception {
        
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Course course : courses) {
                writer.write(course.getId() + "; "
                        + course.getContent() + "; "
                        + course.isFinished() + "; "
                        + course.getUser().getUsername() + "; "
                        + course.getUser().getName() + "\n");
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }

    private int generateId() {
        return courses.size() + 1;
    }

    @Override
    public Course create(Course course) throws Exception {
        course.setId(generateId());
        this.courses.add(course);
        save();

        return course;
    }

    @Override
    public List<Course> getAll() {
        return this.courses;
    }

    @Override
    public void setFinished(int id) throws Exception {
        courses.stream()
                .filter((c) -> (c.getId() == id))
                .forEachOrdered((c) -> {
                    c.setFinished();
                });

        save();
    }

}
