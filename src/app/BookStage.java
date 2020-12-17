package app;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;

/**
 * The type Book stage.
 */
public class BookStage extends Application {
    /**
     * class that shows the properties of a book*/
    private Book book;

    /**
     * Instantiates a new Book stage.
     *
     * @param book the book
     */
    public BookStage(Book book){
        this.book = book;
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        Image image = new Image(new FileInputStream(book.read(book.makebyte()).getPath()));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.getChildren().addAll(imageView);
        root.setCenter(vbox);
        TextArea ta = new TextArea();
        ta.setText(book.getDescription());
        Label author = new Label("The author of the book is: " + book.getAuthor());
        author.setStyle("-fx-background-color: #cef0ff; -fx-font-family: 'Albertus Extra Bold';-fx-font-weight: bold" );
        Label publication = new Label("The publication Year is: " + book.getYear());
        publication.setStyle("-fx-background-color: #cef0ff; -fx-font-family: 'Albertus Extra Bold';-fx-font-weight: bold" );
        Label price = new Label("Renting the book will cost: " + book.getPrice());
        price.setStyle("-fx-background-color: #cef0ff; -fx-font-family: 'Albertus Extra Bold';-fx-font-weight: bold" );
        Label editor = new Label("This book is provided by: " + book.getEditor());
        editor.setStyle("-fx-background-color: #cef0ff; -fx-font-family: 'Albertus Extra Bold';-fx-font-weight: bold" );
        Label isbn = new Label("The isbn is: " + book.getIsbn());
        isbn.setStyle("-fx-background-color: #cef0ff; -fx-font-family: 'Albertus Extra Bold';-fx-font-weight: bold" );
        TextArea description = new TextArea(book.getDescription());
        description.setEditable(false);
        VBox rightBox = new VBox();
        rightBox.setAlignment(Pos.CENTER);
        rightBox.setSpacing(20);
        rightBox.getChildren().addAll(author,publication,price,editor,isbn,description);
        root.setStyle("-fx-background-color: #feeece");
        root.setRight(rightBox);
        Scene scene = new Scene(root);
        stage.setTitle(book.getName());
        stage.setScene(scene);
        stage.show();
    }
}
