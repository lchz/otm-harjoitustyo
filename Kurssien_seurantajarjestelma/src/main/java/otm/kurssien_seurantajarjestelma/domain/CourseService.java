package otm.kurssien_seurantajarjestelma.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import otm.kurssien_seurantajarjestelma.dao.CourseDao;
import otm.kurssien_seurantajarjestelma.dao.FileCourseDao;
import otm.kurssien_seurantajarjestelma.dao.FileUserDao;
import otm.kurssien_seurantajarjestelma.dao.UserDao;

/**
 * Sovelluslogiikasta vastaava luokka
 *
 */

public class CourseService {
    private FileCourseDao fileCourseDao;
    private FileUserDao fileUserDao;
    private User loggedIn;

    public CourseService(FileCourseDao fileCourseDao, FileUserDao fileUserDao) {
        this.fileUserDao = fileUserDao;
        this.fileCourseDao = fileCourseDao;
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
        User user = fileUserDao.findByUsername(username);
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
        if (fileUserDao.findByUsername(username) != null) {
            return false;
        }

        if (fileUserDao.findByName(name) != null) {
            return false;
        }

        int id = fileUserDao.getAll().size() + 1;
        User user = new User(id, name, username, email, password);

        try {
            
            fileUserDao.create(user);

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
    
    public boolean createCourse(String content) {
        Course course = new Course(content, loggedIn);

        try {
            fileCourseDao.create(course);
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
        
        if (fileCourseDao.getAll().isEmpty()) {
            return new ArrayList<>();
        }

//        try {
        return fileCourseDao.getAll()
                .stream()
                .filter(c -> c.getUser().getUsername().equals(loggedIn.getUsername()))
                .filter(c -> !c.isFinished())
                .collect(Collectors.toList());
//        } catch (Exception e) {
//            return new ArrayList<>();
//        }
    }

    /**
     * kurssin merkitsminen päätydyksi
     *
     * @param id päätydyksi merkittävän kurssin tunniste
     */
    
    public void markFinished(int id) {
        try {
            fileCourseDao.setFinished(id);

        } catch (Exception e) {

        }
    }

}
