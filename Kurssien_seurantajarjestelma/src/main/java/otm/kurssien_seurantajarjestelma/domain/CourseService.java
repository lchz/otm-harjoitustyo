package otm.kurssien_seurantajarjestelma.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import otm.kurssien_seurantajarjestelma.dao.CourseDao;
import otm.kurssien_seurantajarjestelma.dao.UserDao;

/**
 * Sovelluslogiikasta vastaava luokka
 *
 */

public class CourseService {
    private CourseDao courseDao;
    private UserDao userDao;
    private User loggedIn;

    public CourseService(CourseDao courseDao, UserDao userDao) {
        this.userDao = userDao;
        this.courseDao = courseDao;
    }

    /**
     * sisäänkirjautuminen
     *
     * @param username käyttäjätunnus
     * @param password salasana
     *
     * @return true jos käyttäjätunnus on olemassa ja salasana on oikein, muuten
     * false
     */
    
    public boolean login(String username, String password) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return false;
        }

        if (!(user.getPassword().equals(password))) {
            return false;
        }

        loggedIn = user;

        return true;
    }

    /**
     * kirjautuneena oleva käyttäjä
     *
     * @return kirjautuneena oleva käyttäjä
     */
    
    public User getLoggedUser() {
        return loggedIn;
    }

    /**
     * uloskirjautuminen
     */
    
    public void logout() {
        loggedIn = null;
    }

    /**
     * uuden käyttäjän luominen
     *
     * @param username käyttäjätunnus
     * @param name käyttäjän nimi
     * @param email käyttäjän sähköposti
     * @param password käyttäjän salasana
     *
     * @return true jos käyttäjätunnus on luotu onnistuneesti, muuten false
     */
    
    public boolean createUser(String username, String name, String email, String password) {
        if (userDao.findByUsername(username) != null) {
            return false;
        }

        if (userDao.findByName(name) != null) {
            return false;
        }

        int id = userDao.getAll().size() + 1;
        User user = new User(id, name, username, email, password);

        try {
            
            userDao.create(user);

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    
    /**
     * Uuden kurssin lisääminen kirjautuneena olevalle käyttäjälle
     *
     * @param content luotavan kurssin sisältö
     * @return true jos kurssien lisääminen onnistunut
     */
    
    public boolean createCourse(String content, User user) {
        Course course = new Course(content, loggedIn);

        try {
            courseDao.create(course);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * kirjautuneen käyttäjän tekemättömät kurssit
     *
     * @return kirjautuneen käyttäjän tekemättömät kurssit
     */
    
    public List<Course> getUnfinished() {
        if (loggedIn == null) {
            return new ArrayList<>();
        }

        return courseDao.getAll()
                .stream()
                .filter(c -> c.getUser().equals(loggedIn))
                .filter(c -> !c.isFinished())
                .collect(Collectors.toList());
    }

    /**
     * kurssin merkitsminen päätydyksi
     *
     * @param id päätydyksi merkittävän kurssin tunniste
     */
    
    public void markFinished(int id) {
        try {
            courseDao.setFinished(id);

        } catch (Exception e) {

        }
    }

}
