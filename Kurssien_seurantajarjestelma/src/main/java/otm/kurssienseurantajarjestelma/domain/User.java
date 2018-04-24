package otm.kurssien_seurantajarjestelma.domain;

public class User {
    public int id;
    private String name;
    private String username;
    private String email;
    private String password;
    
    public User(int id, String name, String username, String email, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        
        User u = (User) obj;
        return this.username.equals(u.username) || this.name.equals(u.name) || 
                this.email.equals(u.email);
        
    }
}
