package otm.kurssien_seurantajarjestelma.ui;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import otm.kurssien_seurantajarjestelma.dao.FileCourseDao;
import otm.kurssien_seurantajarjestelma.dao.FileUserDao;
import otm.kurssien_seurantajarjestelma.domain.Course;
import otm.kurssien_seurantajarjestelma.domain.CourseService;

public class jarjestelmaUi extends Application {
    private CourseService courseService;
    
    private Scene courseScene;
    private Scene newUserScene;
    private Scene loginScene;
    
    private VBox courseNodes;
    private Label menuLabel = new Label();
    
    @Override
    public void init() throws Exception {
        Properties properties = new Properties();
        
        properties.load(new FileInputStream("config.properties"));
        
        String userFile = properties.getProperty("userFile");
        String courseFile = properties.getProperty("courseFile");
        
        FileUserDao userDao = new FileUserDao(userFile);
        FileCourseDao courseDao = new FileCourseDao(courseFile, userDao);
        courseService = new CourseService(courseDao, userDao);
    }
    
    public Node createCourseNode(Course course) {
        HBox box = new HBox(10);
        Label label = new Label(course.getContent());
        label.setMinHeight(30);
        
        Button button = new Button("finished");
        button.setOnAction(e -> {
            courseService.markFinished(course.getId());
            redrawCourselist();
        });
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        box.setPadding(new Insets(0, 5, 0, 5));
        
        box.getChildren().addAll(label, spacer, button);
        return box;
    }
    
    public void redrawCourselist() {
        courseNodes.getChildren().clear();
        
        List<Course> unfinishedCourses = courseService.getUnfinished();
        unfinishedCourses.forEach(c -> {
            courseNodes.getChildren().add(createCourseNode(c));
        });
    }
    
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        //login scene
        
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        loginPane.setPadding(new Insets(10));
        
        Label loginLabel = new Label("username");
        TextField usernameInput = new TextField();
        Label passwordLabel = new Label("password");
        TextField passwordInput = new TextField();
        
        inputPane.getChildren().addAll(loginLabel, usernameInput, passwordLabel, passwordInput);
        Label loginMessage = new Label();
        
        Button loginButton = new Button("login");
        Button createButton = new Button("create new user");
        loginButton.setOnAction(e -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();
            
            menuLabel.setText(username + " logged in...");
            
            if(courseService.login(username, password)) {
                loginMessage.setText("");
                redrawCourselist();
                primaryStage.setScene(courseScene);
                usernameInput.setText("");
                passwordInput.setText("");
                
            } else {
                loginMessage.setText("user does not exist");
                loginMessage.setTextFill(Color.RED);
            }
        });
        
        createButton.setOnAction(e -> {
            usernameInput.setText("");
            primaryStage.setScene(newUserScene);
        });
        
        loginPane.getChildren().addAll(loginMessage, inputPane, loginButton, createButton);
        
        loginScene = new Scene(loginPane, 300, 250);
        
        
    }
    
    @Override
    public void stop() {
        //lopetustoimenpiteet
        System.out.println("sovellus sulkeutuu");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
