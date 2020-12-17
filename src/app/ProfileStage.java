package app;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The type Profile stage.
 */
public class ProfileStage extends Application {

    /**
     * class that displays the books rent by every user
     * it differs from a user to the other*/

    private User user;
    private Database database;

    /**
     * Instantiates a new Profile stage.
     *
     * @param user the user
     */
    public ProfileStage(User user){
        this.user = user;
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        database = new Database();
        Label listOfBooks = new Label("The books you rented are: ");
        listOfBooks.setStyle("-fx-background-color: #cef0ff; -fx-font-family: 'Albertus Extra Bold';-fx-font-weight: bold" );
        root.setTop(listOfBooks);

        ListView listView = new ListView();
        ObservableList<String> list = FXCollections.observableArrayList();
        for(String s: database.getBook(user.getUsername())){
            list.add(s);
        }

        listView.setItems(list);
        root.setStyle("-fx-background-color: #feeece");
        root.setCenter(listView);
        Scene scene = new Scene(root);
        stage.setTitle(user.getUsername() + "'s Profile");
        stage.setScene(scene);
        stage.show();
    }
}
