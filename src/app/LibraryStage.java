package app;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Library stage.
 */
public class LibraryStage extends Application {

    /**
     * class that manages the list of book
     * has a search engine
     * every user can see his username and see his rented books*/

    private User user;
    private Database database;
    private List<Book> bookList;
    private BookStage bookStage;
    private ListView listview;
    private ProfileStage profileStage;

    /**
     * Instantiates a new Library stage.
     *
     * @param user the user
     */
    public LibraryStage(User user){
        this.user = user;
    }

    @Override
    public void start(Stage stage) throws Exception {
        database = new Database();
        database.createBT();
        Label usertext = new Label("Logged in as: " + user.getUsername());
        usertext.setStyle("-fx-background-color: #cef0ff; -fx-font-family: 'Albertus Extra Bold';-fx-font-weight: bold" );
        AnchorPane  topPane = new AnchorPane();
        AnchorPane.setLeftAnchor(usertext,0.0);
        topPane.getChildren().addAll(usertext);

        //Code for bottom

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #feeece");
        root.setTop(topPane);
        Button rentButton = new Button("Rent Book");
        rentButton.setStyle("-fx-background-color: #cef0ff; -fx-font-family: 'Albertus Extra Bold';-fx-font-weight: bold" );
        Button logOut = new Button("Log out");
        logOut.setStyle("-fx-background-color: #cef0ff; -fx-font-family: 'Albertus Extra Bold';-fx-font-weight: bold" );
        logOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    handleLogoutButton(event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Button profile = new Button("Status");
        profile.setStyle("-fx-background-color: #cef0ff; -fx-font-family: 'Albertus Extra Bold';-fx-font-weight: bold" );
        profile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    handleProfileButton();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        HBox topBox = new HBox();
        topBox.setAlignment(Pos.CENTER);
        topBox.setSpacing(20);

        topBox.getChildren().addAll(rentButton,profile,logOut);
        root.setBottom(topBox);




        VBox centerBox = new VBox();
        centerBox.setAlignment(Pos.TOP_CENTER);
        centerBox.setSpacing(10);

        HBox searchBox = new HBox();
        searchBox.setAlignment(Pos.CENTER);
        searchBox.setSpacing(10);

        Button refresh = new Button("Reload");
        refresh.setStyle("-fx-background-color: #cef0ff; -fx-font-family: 'Albertus Extra Bold';-fx-font-weight: bold" );
        TextField tf = new TextField();
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.setValue("Categories");
        choiceBox.setStyle("-fx-background-color: #cef0ff; -fx-font-family: 'Albertus Extra Bold';-fx-font-weight: bold" );
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("Title","Author","Year","Editor");
        choiceBox.setItems(list);
        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-background-color: #cef0ff; -fx-font-family: 'Albertus Extra Bold';-fx-font-weight: bold" );
        searchBox.getChildren().addAll(tf,choiceBox,searchButton,refresh);
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleChoiceBox(choiceBox.getSelectionModel().getSelectedItem(),tf.getText());
            }
        });
        bookList = new ArrayList<>();
        Book b1 = new Book("The Four",
                           "Scott Galloway",
                49,
                             2018,
                           "Publica",
                        "Although much has been written about the Four in the last two decades, no one has surprised their power and astonishing success as well as Scott Galloway. Instead of believing the myths that these companies are spreading, Galloway asks fundamental questions. How did the Four infiltrate our lives so much that it's almost impossible to avoid (or boycott) them? Why does the stock exchange forgive him so easily for the sins that would destroy other companies? And now, when everyone is trying to become the first trillion-dollar company, can another competitor stop them?\n" +
                                "\n" +
                                "Galloway deconstructs the strategies behind the bright facets of the Four. He shows us how he manipulates our fundamental emotional needs, which have animated us since our ancestors lived in caves, with a speed and magnitude that others cannot match. And it shows us how we can apply the lessons learned from their rise in our own businesses or careers.\n" +
                                "\n" +
                                "Whether you want to compete with them, do business with them, or live in a world dominated by them, you need to understand the Four.\n" +
                                "\n" +
                                "Galloway gives the reader a clear perspective on the dominant nature of Amazon, Apple, Facebook and Google. He is interested in how these companies have become more valuable over a lifetime, how they benefit from a low cost of capital and the implications of things that could further strengthen their dominance.\n" +
                                "\n" +
                                "The Four is an essential book, a powerful explosion in which Scott Galloway combines incisiveness, entertainment and attacks. The author says things by name, without sparing from the well-deserved criticism no titan of business and no company-colossus.\n",
                "9786067223323",
                new File("./src/img/book1.jpeg"));
        Book b2 = new Book("Post Corona",
                "Scott Galloway",
                99,
                2020,
                "Portofolio",
                "The COVID-19 outbreak has turned bedrooms into offices, pitted young against old, and widened the gaps between rich and poor, red and blue, the mask wearers and the mask haters. Some businesses--like home exercise company Peloton, video conference software maker Zoom, and Amazon--woke up to find themselves crushed under an avalanche of consumer demand. Others--like the restaurant, travel, hospitality, and live entertainment industries--scrambled to escape obliteration.\n" +
                        "\n" +
                        "But as New York Times bestselling author Scott Galloway argues, the pandemic has not been a change agent so much as an accelerant of trends already well underway. In Post Corona, he outlines the contours of the crisis and the opportunities that lie ahead. Some businesses, like the powerful tech monopolies, will thrive as a result of the disruption. Other industries, like higher education, will struggle to maintain a value proposition that no longer makes sense when we can't stand shoulder to shoulder. And the pandemic has accelerated deeper trends in government and society, exposing a widening gap between our vision of America as a land of opportunity, and the troubling realities of our declining wellbeing.\n" +
                        "\n" +
                        "Combining his signature humor and brash style with sharp business insights and the occasional dose of righteous anger, Galloway offers both warning and hope in equal measure. As he writes, \"Our commonwealth didn't just happen, it was shaped. We chose this path--no trend is permanent and can't be made worse or corrected.\"\n",
                "9780593332214",
                new File("./src/img/book2.jpeg"));
        Book b3 = new Book("The Algebra of Happiness",
                "Scott Galloway",
                87,
                2019,
                "Transworld Publishers Ltd",
                "From the New York Times bestselling author, a provocative book of hard-won wisdom for achieving a fulfilling career and life.\n" +
                        "\n" +
                        "- How can you have a meaningful career, not just a lucrative one?\n" +
                        "- Is a work/life balance really possible?\n" +
                        "- What does it take to make a long-term relationship succeed?\n" +
                        "- What can you do now so there are no regrets aged 40, 50 or 80?\n" +
                        "\n" +
                        "As Scott Galloway puts it, by the time you hit your mid twenties sh*t gets real. Life become stressful. Even the smart, the hard working and the elite can feel lost in a chaotic, noisy and unpredictable world. As a professor at New York University's Stern School of Business, the debate in Galloway's MBA class often veers away from business strategy to the challenging issue of life strategies. Which is why Galloway, in his signature, take-no-prisoners style, has developed a dynamic formula for a life well lived.\n" +
                        "\n" +
                        "In The Algebra of Happiness Galloway tells you how life can be navigated and negotiated better to maximise happiness and minimise the inevitable stress. Delivering practical advice and hard-won wisdom on everything from when to own property to how hard to work, this is self-help for anyone struggling with life's big questions. Through simple equations that measure the relationship between success, resilience and failure or the correlation between happiness and money, Galloway attempts to convert intangible advice to tangible equations.\n",
                "9781787632479",
                new File("./src/img/book3.jpeg"));
        Book b4 = new Book("The Nonlinear Workbook",
                "Willi-Hans Steeb",
                550,
                2008,
                "World Scientific Publishing Co Pte Ltd",
                "The study of nonlinear dynamical systems has advanced tremendously in the last 20 years, making a big impact on science and technology. This book provides all the techniques and methods used in nonlinear dynamics. The concepts and underlying mathematics are discussed in detail.\n" +
                        "\n" +
                        "The numerical and symbolic methods are implemented in C++, SymbolicC++ and Java. Object-oriented techniques are also applied. The book contains more than 150 ready-to-run programs.\n" +
                        "\n" +
                        "The text has also been designed for a one-year course at both the junior and senior levels in nonlinear dynamics. The topics discussed in the book are part of e-learning and distance learning courses conducted by the International School for Scientific Computing, University of Johannesburg.\n",
                "9789812818539",
                new File("./src/img/book4.jpeg"));
        Book b5 = new Book("Computer Algebra With SymbolicC++",
                "Yorick Hardy, Kiat Shi Tan, Willi-Hans Steeb",
                339,
                2008,
                "World Scientific Publishing Co Pte Ltd",
                "Offers an introduction to computer algebra. This book provides coverage of the mathematics of computer algebra as well as guidance to implement a computer algebra system in the object-oriented language C++. It also introduces the used tools from C++.\n",
                "9789812833617",
                new File("./src/img/book5.jpeg"));
        Book b6 = new Book("Mathematical Tools In Computer Graphics With C# Implementations",
                "Alexandre Hardy, Willi-Hans Steeb",
                420,
                2008,
                "World Scientific Publishing Co Pte Ltd",
                "Presents introductory and advanced topics in the field of computer graphics with mathematical descriptions and derivations. This book offers a balance of theory, applications, and code, and derives the underlying numerical methods and algorithms. It contains the classes in C# necessary for computer graphics, and offers an explanation of the code.\n","9789812791030",
                new File("./src/img/book6.jpeg"));
        Book b7 = new Book("Don t Teach Coding: Until You Read This Book",
                "Lindsey Handley, Stephen Foster",
                155,
                2020,
                "John Wiley & Sons Inc",
                "Even though the vast majority of teachers, parents, and students understand the importance of computer science in the 21st century, many struggle to find appropriate educational resources. Don't Teach Coding: Until You Read This Book fills a gap in current knowledge by explaining exactly what coding is and addressing why and how to teach the subject. Providing a historically grounded, philosophically sensitive description of computer coding, this book helps readers understand the best practices for teaching computer science to their students and their children.\n" +
                        "\n" +
                        "Don't Teach Coding: Until You Read This Book is a valuable resource for K-12 educators in computer science education and parents wishing to understand the field to help chart their children’s education path.\n",
                "9781119602620",
                new File("./src/img/book7.jpeg"));
        Book b8 = new Book("C Programming For Dummies",
                "Dan Gookin",
                149,
                2020,
                "John Wiley & Sons Inc",
                "As with any major language, mastery of C can take you to some very interesting new places. Almost 50 years after it first appeared, it's still the world's most popular programming language and is used as the basis of global industry's core systems, including operating systems, high-performance graphics applications, and microcontrollers. This means that fluent C users are in big demand at the sharp end in cutting-edge industries—such as gaming, app development, telecommunications, engineering, and even animation—to translate innovative ideas into a smoothly functioning reality.\n" +
                        "\n" +
                        "To help you get to where you want to go with C, this 2nd edition of C Programming For Dummies covers everything you need to begin writing programs, guiding you logically through the development cycle: from initial design and testing to deployment and live iteration. By the end you'll be au fait with the do's and don'ts of good clean writing and easily able to produce the basic—and not-so-basic—building blocks of an elegant and efficient source code.\n" +
                        "\n" +
                        "-Write and compile source code\n" +
                        "-Link code to create the executable program\n" +
                        "-Debug and optimize your code\n" +
                        "-Avoid common mistakes\n" +
                        "Whatever your destination: tech industry, start-up, or just developing for pleasure at home, this easy-to-follow, informative, and entertaining guide to the C programming language is the fastest and friendliest way to get there!\n",
                "9781119740247",
                new File("./src/img/book8.jpeg"));
        Book b9 = new Book("Secrets and Lies: Digital Security in a Networked World",
                "Bruce Schneier",
                81,
                2004,
                "John Wiley & Sons Ltd",
                "Offers a practical guide to achieving security throughout computer networks. This guide provides readers with a better understanding of why protecting information is harder in the digital world, what they need to know to protect digital information, and, how to assess business and corporate security needs.\n",
                "9780471453802",
                new File("./src/img/book9.jpeg"));
        Book b10 = new Book("Introduction to the iPad with iPadOS 13",
                "Andrew Edney",
                60,
                2020,
                "Bernard Babani Publishing",
                "An Introduction to the iPad with iPadOS 13",
                "9780859347808",
                new File("./src/img/book10.jpeg"));
        addBook(b1,bookList);
        addBook(b2,bookList);
        addBook(b3,bookList);
        addBook(b4,bookList);
        addBook(b5,bookList);
        addBook(b6,bookList);
        addBook(b7,bookList);
        addBook(b8,bookList);
        addBook(b9,bookList);
        addBook(b10,bookList);
        for(Book b: bookList){
            if(!database.checkBook(b.getName())){
                uploadBook(b);
            }
        }
        listview = new ListView();
        ObservableList<String> items = FXCollections.observableArrayList();
        for(Book b: bookList){
            items.add(b.toString());
        }
        listview.setItems(items);
        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listview.setItems(items);
            }
        });
        listview.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {

                    if(mouseEvent.getClickCount() == 2) {
                        Book book = findBook((String) listview.getSelectionModel().getSelectedItem(), bookList);
                        handleListView(book);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        centerBox.getChildren().addAll(searchBox,listview);
        root.setCenter(centerBox);
        //======================================================================

        rentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleRentButton(listview.getSelectionModel().getSelectedItem().toString(),user.getUsername());
            }
        });
        Scene scene = new Scene(root, 600, 900);
        stage.setTitle("Library Application");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Upload book.
     *
     * @param book the book
     */
    public void uploadBook(Book book){
        database.insertBook(book.getName(), book.getAuthor(), book.getEditor(), book.getDescription(),book.getIsbn(), book.getPrice(), book.getYear(), book.makebyte());
    }

    /**
     * Handle list view.
     *
     * @param book the book
     * @throws Exception the exception
     */
    public void handleListView(Object book) throws Exception {
        bookStage = new BookStage((Book) book);
        bookStage.start(new Stage());
    }

    /**
     * Find book book.
     *
     * @param title the title
     * @param books the books
     * @return the book
     */
    public Book findBook(String title,List<Book> books){
        for(Book b: books){
            if(b.getName().equals(title)){
                return b;
            }
        }
        return null;
    }

    /**
     * Handle rent button.
     *
     * @param title the title
     * @param user  the user
     */
    public void handleRentButton(String title,String user){
        if(!database.checkRent(title,user)){
            database.insertRent(user,title);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Perfect!");
            alert.setHeaderText("You have 1 more book!");
            alert.setContentText("You rented the book successfully");
            alert.show();
        }
    }

    /**
     * Handle choice box.
     *
     * @param choice the choice
     * @param value  the value
     */
    public void handleChoiceBox(String choice,String value){
        List<Book> listBook;
        ObservableList<String> items = FXCollections.observableArrayList();

        listBook = database.getBy(choice,value);
        for(Book b: listBook){
            items.add(b.getName());
        }

        listview.setItems(items);
    }

    /**
     * Handle profile button.
     *
     * @throws Exception the exception
     */
    public void handleProfileButton() throws Exception {
        profileStage = new ProfileStage(user);
        profileStage.start(new Stage());
    }

    /**
     * Handle logout button.
     *
     * @param event the event
     * @throws Exception the exception
     */
    public void handleLogoutButton(ActionEvent event) throws Exception {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        new LoginStage().start(new Stage());
    }

    /**
     * Add book.
     *
     * @param book the book
     * @param list the list
     */
    public void addBook(Book book, List<Book> list){
        if(findBook(book.getName(),list) == null) {
            bookList.add(book);
        }
    }
}
