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
import javafx.scene.layout.FlowPane;
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

public class JarjestelmaUi extends Application {
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
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        //login scene
        
        VBox loginPane = new VBox(10);
        HBox inputPane1 = new HBox(10);
        HBox inputPane2 = new HBox(10);
        loginPane.setPadding(new Insets(10));
        
        Label loginLabel = new Label("username");
        TextField usernameInput = new TextField();
        Label passwordLabel = new Label("password");
        TextField passwordInput = new TextField();
        
        inputPane1.getChildren().addAll(loginLabel, usernameInput);
        inputPane2.getChildren().addAll(passwordLabel, passwordInput);
        
        loginPane.getChildren().addAll(inputPane1, inputPane2);
        
        Label loginMessage = new Label();
        
        VBox buttons = new VBox(10);
        
        Button loginButton = new Button("login");
        Button createButton = new Button("create new user");

        buttons.getChildren().addAll(loginButton, createButton);
        
        FlowPane kompo = new FlowPane();
        kompo.getChildren().addAll(loginPane, buttons);
        Scene nakyma1 = new Scene(kompo, 300, 200);

        primaryStage.setScene(nakyma1);
        primaryStage.show();
    }
    
    @Override
    public void stop() {
        //lopetustoimenpiteet
        System.out.println("sovellus sulkeutuu");
    }
    
    public static void main(String[] args) {
        launch(JarjestelmaUi.class);
    }
    
}
