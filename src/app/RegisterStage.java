package app;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The type Register stage.
 */
public class RegisterStage extends Application {

    /**
     * contains the registration form for the library app
     * has data validation*/

    private Database database;
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        database = new Database();
        VBox registerBox = new VBox();
        registerBox.setAlignment(Pos.CENTER);
        registerBox.setSpacing(10);
        root.setStyle("-fx-background-color: #feeece");
        HBox userBox = new HBox();
        userBox.setSpacing(10);
        userBox.setAlignment(Pos.CENTER);
        Label username = new Label("Username");
        username.setStyle("-fx-background-color: #cef0ff; -fx-font-family: 'Albertus Extra Bold';-fx-font-weight: bold" );
        TextField userField = new TextField();
        userBox.getChildren().addAll(username,userField);

        HBox pwdBox = new HBox();
        pwdBox.setSpacing(10);
        pwdBox.setAlignment(Pos.CENTER);
        Label password = new Label("Password");
        password.setStyle("-fx-background-color: #cef0ff; -fx-font-family: 'Albertus Extra Bold';-fx-font-weight: bold" );
        TextField pwdField = new TextField();

        pwdBox.getChildren().addAll(password,pwdField);
        Button registerButton = new Button("Register");
        registerButton.setStyle("-fx-background-color: #cef0ff; -fx-font-family: 'Albertus Extra Bold';-fx-font-weight: bold" );
        registerBox.getChildren().addAll(userBox,pwdBox,registerButton);
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleRegisterButton(userField.getText(),pwdField.getText(),userField,pwdField,actionEvent);
            }
        });
        root.setCenter(registerBox);

        Scene scene = new Scene(root,300,400);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Handle register button.
     *
     * @param username the username
     * @param password the password
     * @param usn      the usn
     * @param pwd      the pwd
     * @param event    the event
     */
    public void handleRegisterButton(String username, String password,TextField usn, TextField pwd,ActionEvent event){
        if(database.checkUsn(username) || password.length() == 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Try using other values");
            alert.setContentText("User already exists");
            alert.show();
            usn.setText("");
            pwd.setText("");
        }else{
            database.insertUsr(username,password);
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
    }
}
