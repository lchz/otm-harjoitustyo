package otm.kurssienseurantajarjestelma.ui;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import otm.kurssienseurantajarjestelma.dao.FileCourseDao;
import otm.kurssienseurantajarjestelma.dao.FileUserDao;
import otm.kurssienseurantajarjestelma.domain.Course;
import otm.kurssienseurantajarjestelma.domain.CourseService;

public class JarjestelmaUi extends Application {

    private CourseService courseService;

    private Scene courseScene;
    private Scene newUserScene;
    private Scene loginScene;

    private VBox courseNodes;
    private Label menuLabel = new Label();

    /**
     * tarvittavien dokumentien lisääminen
     * @throws Exception 
     */
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

    /**
     * näkymien asettaminen
     * 
     * @param primaryStage päänäkymä
     * @throws Exception 
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        //login scene

        VBox loginPane = new VBox(10);
        HBox inputPane1 = new HBox(10);
        HBox inputPane2 = new HBox(10);
        loginPane.setPadding(new Insets(10));

        Label loginLabel = new Label("Username");
        TextField usernameInput = new TextField();
        Label passwordLabel = new Label("Password");
        TextField passwordInput = new TextField();

        inputPane1.getChildren().addAll(loginLabel, usernameInput);
        inputPane2.getChildren().addAll(passwordLabel, passwordInput);

        loginPane.getChildren().addAll(inputPane1, inputPane2);

        Label loginMessage = new Label();

        VBox buttons = new VBox(10);

        Button loginButton = new Button("Login");
        Button createButton = new Button("Create new user");

        buttons.getChildren().addAll(loginButton, createButton);

        FlowPane kompo = new FlowPane();
        kompo.getChildren().addAll(loginMessage, loginPane, buttons);
        loginScene = new Scene(kompo, 300, 200);

        loginButton.setOnAction(e -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();

            menuLabel.setText("Welcome " + username);

            if (courseService.login(username, password)) {
                loginMessage.setText("");
                usernameInput.setText("");
                passwordInput.setText("");
                primaryStage.setScene(courseScene);
                redrawCourselist();

            } else {
                loginMessage.setText("Wrong username or password.");
                usernameInput.setText("");
                passwordInput.setText("");
                loginMessage.setTextFill(Color.RED);
            }
        });

        createButton.setOnAction(e -> {
            usernameInput.setText("");
            primaryStage.setScene(newUserScene);
        });

        //Create new user
        VBox newUserPane = new VBox(10);

        HBox newUsernamePane = new HBox(10);
        newUsernamePane.setPadding(new Insets(10));
        TextField newUsernameInput = new TextField();
        Label newUsernameLabel = new Label("Username:");
        newUsernamePane.getChildren().addAll(newUsernameLabel, newUsernameInput);

        HBox newNamePane = new HBox(10);
        newNamePane.setPadding(new Insets(10));
        TextField newNameInput = new TextField();
        Label newNameLabel = new Label("Name:");
        newNamePane.getChildren().addAll(newNameLabel, newNameInput);

        HBox newPasswordPane = new HBox(10);
        newPasswordPane.setPadding(new Insets(10));
        TextField newPasswordInput = new TextField();
        Label newPasswordLabel = new Label("Password:");
        newPasswordPane.getChildren().addAll(newPasswordLabel, newPasswordInput);

        HBox newEmailPane = new HBox(10);
        newEmailPane.setPadding(new Insets(10));
        TextField newEmailInput = new TextField();
        Label newEmailLabel = new Label("E-mail:");
        newEmailPane.getChildren().addAll(newEmailLabel, newEmailInput);

        Label userCreationMessage = new Label();

        Button createNewUserButton = new Button("create");
        Button goBackToLoginButton = new Button("Go to login page");
        createNewUserButton.setPadding(new Insets(10));
        goBackToLoginButton.setPadding(new Insets(10));

        goBackToLoginButton.setOnAction(e -> {
            newUsernameInput.setText("");
            newNameInput.setText("");
            newPasswordInput.setText("");
            newEmailInput.setText("");
            primaryStage.setScene(loginScene);
        });

        createNewUserButton.setOnAction(e -> {
            String username = newUsernameInput.getText();
            String name = newNameInput.getText();
            String password = newPasswordInput.getText();
            String email = newEmailInput.getText();

            if (username.length() < 3) {
                userCreationMessage.setText("Username has to be at least 3 marks");
                userCreationMessage.setTextFill(Color.RED);
            } else if (password.length() < 6) {
                userCreationMessage.setText("Password has to be at least 6 marks");
                userCreationMessage.setTextFill(Color.RED);
            } else if (password.toLowerCase().contains("ä") || password.toLowerCase().contains("ö") || password.toLowerCase().contains("å")) {
                userCreationMessage.setText("Password cannot contain ä, ö or å");
                userCreationMessage.setTextFill(Color.RED);
            } else if (username.toLowerCase().contains("ä") || username.toLowerCase().contains("ö") || username.toLowerCase().contains("å")) {
                userCreationMessage.setText("Username cannot contain ä, ö or å");
                userCreationMessage.setTextFill(Color.RED);
            } else if (!email.contains("@")) {
                userCreationMessage.setText("E-mail is not correct.");
                userCreationMessage.setTextFill(Color.RED);
            } else if (courseService.createUser(username, name, email, password)) {
                userCreationMessage.setText("");
                newUsernameInput.setText("");
                newNameInput.setText("");
                newPasswordInput.setText("");
                newEmailInput.setText("");
                loginMessage.setText("new user created");
                loginMessage.setTextFill(Color.GREEN);
                primaryStage.setScene(loginScene);
            } else {
                userCreationMessage.setText("User has already existed or username has to be unique.");
                userCreationMessage.setTextFill(Color.RED);
            }
        });

        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, newNamePane, newEmailPane, newPasswordPane, createNewUserButton, goBackToLoginButton);

        newUserScene = new Scene(newUserPane, 300, 350);

        //main scene
        ScrollPane courseScrollbar = new ScrollPane();
        BorderPane mainPane = new BorderPane(courseScrollbar);
        courseScene = new Scene(mainPane, 350, 250);

        HBox menuPane = new HBox(10);
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        Button logoutButton = new Button("log out");
        menuPane.getChildren().addAll(menuLabel, menuSpacer, logoutButton);

        logoutButton.setOnAction(e -> {
            courseService.logout();
            primaryStage.setScene(loginScene);
        });

        HBox createForm = new HBox(25);
        Button createCourse = new Button("create");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        TextField newCourseInput = new TextField();
        createForm.getChildren().addAll(spacer, newCourseInput, createCourse);

        courseNodes = new VBox(10);
        courseNodes.setMaxWidth(280);
        courseNodes.setMinWidth(280);
        redrawCourselist();

        courseScrollbar.setContent(courseNodes);
        mainPane.setBottom(createForm);
        mainPane.setTop(menuPane);

        createCourse.setOnAction(e -> {
            courseService.createCourse(newCourseInput.getText());
            newCourseInput.setText("");
            redrawCourselist();
        });
        
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    /**
     * kurssin solmun luominen
     * 
     * @param course kurssi
     * 
     * @return luodun kurssin solmu
     */
    public Node createCourseNode(Course course) {
        HBox box = new HBox(10);
        Label label = new Label(course.getContent());
        label.setMinHeight(28);
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
    
    /**
     * kurssien listaaminen
     */
    public void redrawCourselist() {
        courseNodes.getChildren().clear();

        List<Course> undoneCourses = courseService.getUnfinished();

        try {
            undoneCourses.forEach(course -> {
                courseNodes.getChildren().add(createCourseNode(course));
            });
        } catch (Exception e) {
            e.getCause();
        }

    }
   
    /**
     * viesti kun sovellus sulkeutuu
     */
    @Override
    public void stop() {
        //lopetustoimenpiteet
        System.out.println("sovellus sulkeutuu");
    }

    public static void main(String[] args) {
        launch(JarjestelmaUi.class);
    }

}
