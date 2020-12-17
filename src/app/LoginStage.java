package app;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The type Login stage.
 */
public class LoginStage extends Application {

    /**
     * class that manages the login step
     * communicates with the database to see if credentials are matching
     * login portal*/

    private RegisterStage registerStageForm;
    private Database database;
    private User user;
    private LibraryStage libraryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        database = new Database();
        database.createDb();
        database.createUT();
       BorderPane root = new BorderPane();
        root.setId("root");
        HBox usnBox = new HBox();
        Label username = new Label("Username: ");
        username.setStyle("-fx-background-color: #cef0ff; -fx-font-family: 'Albertus Extra Bold';-fx-font-weight: bold" );

        TextField usnText = new TextField();
        usnBox.setAlignment(Pos.CENTER);
        usnBox.setSpacing(10);
        usnBox.getChildren().addAll(username,usnText);

        HBox pwdBox = new HBox();
        Label password = new Label("Password: ");
        password.setStyle("-fx-background-color: #cef0ff; -fx-font-family: 'Albertus Extra Bold';-fx-font-weight: bold" );

        PasswordField pwdText = new PasswordField();
        pwdBox.setAlignment(Pos.CENTER);
        pwdBox.setSpacing(10);
        pwdBox.getChildren().addAll(password,pwdText);

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #cef0ff; -fx-font-family: 'Albertus Extra Bold';-fx-font-weight: bold" );
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleLoginButton(usnText.getText(),pwdText.getText(),usnText,pwdText,actionEvent);
            }
        });

        VBox loginBox = new VBox();
        loginBox.setSpacing(10);
        loginBox.setPadding(new Insets(70,10,10,10));
        loginBox.setAlignment(Pos.CENTER);
        loginBox.getChildren().addAll(usnBox,pwdBox,loginButton);



        VBox registerBox = new VBox();
        Label register = new Label("Don't have an account yet? Register");
        register.setTextFill(Color.web("#ffffff"));
        Button registerButton = new Button("Register!");
       registerButton.setStyle("-fx-background-color: #cef0ff; -fx-font-family: 'Albertus Extra Bold';-fx-font-weight: bold" );
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    handleRegisterButton(actionEvent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        registerBox.setAlignment(Pos.CENTER);
        registerBox.getChildren().addAll(register,registerButton);
        registerBox.setPadding(new Insets(20,20,20,20));
        root.setCenter(loginBox);
        root.setBottom(registerBox);

        Scene scene = new Scene(root, 300,275);
        scene.getStylesheets().addAll(this.getClass().getResource("../style/style.css").toExternalForm());
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Handle register button.
     *
     * @param event the event
     * @throws Exception the exception
     */
    public void handleRegisterButton(ActionEvent event) throws Exception {
        registerStageForm = new RegisterStage();
        Stage stage = new Stage();
        stage.setTitle("Register");
        registerStageForm.start(stage);

    }


    /**
     * Handle login button.
     *
     * @param username      the username
     * @param password      the password
     * @param tf            the tf
     * @param passwordField the password field
     * @param event         the event
     */
    public void handleLoginButton(String username, String password,TextField tf, PasswordField passwordField, ActionEvent event){
        if(!database.checkCred(username,password)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login failed!");
            alert.setHeaderText("Try again!");
            alert.setContentText("Username or password do not match the db!");
            tf.setText("");
            passwordField.setText("");
            alert.show();
        }else{
            user = new User(username,password);
            ((Node)(event.getSource())).getScene().getWindow().hide();
            try{
                database.createRT();
                libraryStage = new LibraryStage(user);
                Stage stage = new Stage();
                libraryStage.start(stage);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}