package otm.kurssien_seurantajarjestelma.domain;

/*
    Yksittäistä kurssia kuvaava luokka
*/

public class Course {
    private int id;
    private String content;
    private boolean finished;
    private User user;
    
    public Course(int id, String content, boolean finished, User user) {
        this.id = id;
        this.content = content;
        this.finished = finished;
        this.user = user;
    }
    
    public Course(String content, User user) {
        this.content = content;
        this.user = user;
        this.finished = false;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getContent() {
        return this.content;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public int getId() {
        return this.id;
    }
    
    public boolean isFinished() {
        return this.finished;
    }
    
    public void setFinished() {
        this.finished = true;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Course)) {
            return false;
        }
        
        Course k = (Course) obj;
        return id == k.id;
    }
    
}
