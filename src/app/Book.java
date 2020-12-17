package app;

import java.io.*;

/**
 * The type Book.
 */
public class Book {

    /**
     * class that maintains the proprieties of a book*/

    private String name;
    private String author;
    private int price;
    private int year;
    private String editor;
    private String description;

    /**
     * Gets isbn.
     *
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    private String isbn;
    private File image;

    /**
     * Instantiates a new Book.
     *
     * @param name        the name
     * @param author      the author
     * @param price       the price
     * @param year        the year
     * @param editura     the editura
     * @param description the description
     * @param isbn        the isbn
     * @param image       the image
     */
    public Book(String name, String author, int price, int year, String editura, String description, String isbn, File image) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.year = year;
        this.editor = editura;
        this.description = description;
        this.image = image;
        this.isbn = isbn;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets author.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets author.
     *
     * @param author the author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets year.
     *
     * @param year the year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Gets editor.
     *
     * @return the editor
     */
    public String getEditor() {
        return editor;
    }

    /**
     * Sets editura.
     *
     * @param editura the editura
     */
    public void setEditura(String editura) {
        this.editor = editura;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public File getImage() {
        return image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(File image) {
        this.image = image;
    }

    /**
     * Makebyte byte [ ].
     *
     * @return the byte [ ]
     */
    public byte[] makebyte() {
        try {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this.getImage());
            byte[] employeeAsBytes = baos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(employeeAsBytes);
            return employeeAsBytes;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Read file.
     *
     * @param data the data
     * @return the file
     */
    public File read(byte[] data) {
        try {


            ByteArrayInputStream baip = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(baip);
            File dataobj = (File ) ois.readObject();
            return dataobj ;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
