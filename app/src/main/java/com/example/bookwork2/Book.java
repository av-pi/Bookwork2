package com.example.bookwork2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * Entity class for managing each Book added in the application
 */
@Entity(tableName = "book")
public class Book implements Parcelable {

    public static final String BOOK_STATUS_READING = "reading";
    public static final String BOOK_STATUS_INTERESTED = "interested";
    public static final String BOOK_STATUS_FINISHED = "finished";
    public static final String BOOK_STATUS_ABANDONED = "abandoned";

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "author")
    private String author;

    @ColumnInfo(name = "image_url")
    private String imageURL;

    @ColumnInfo(name = "book_url")
    private String bookURL;

    @ColumnInfo(name = "short_description")
    private String shortDesc;

    @ColumnInfo(name = "long_description")
    private String longDesc;

    @ColumnInfo(name = "shelf")
    private String shelf;

    @Ignore
    public Book(int id, String title, String author, String imageURL, String bookURL, String shortDesc, String longDesc, String shelf) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.imageURL = imageURL;
        this.bookURL = bookURL;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.shelf = shelf;
    }

    public Book(String title,
                String author,
                String imageURL,
                String bookURL,
                String shortDesc,
                String longDesc,
                @Nullable String shelf) {

        this.title = title;
        this.author = author;
        this.imageURL = imageURL;
        this.bookURL = bookURL;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;

        this.shelf = (shelf == null) ? BOOK_STATUS_INTERESTED : shelf;
    }

    protected Book(Parcel in) {
        title = in.readString();
        author = in.readString();
        imageURL = in.readString();
        bookURL = in.readString();
        shortDesc = in.readString();
        longDesc = in.readString();
        shelf = in.readString();
        // Read other properties from the Parcel if you have more
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(imageURL);
        dest.writeString(bookURL);
        dest.writeString(shortDesc);
        dest.writeString(longDesc);
        dest.writeString(shelf);
        // Write other properties to the Parcel if you have more
    }

    @Override
    public int describeContents() {
        return 0;
    }


//    public Book() {
//        this.title = "blah";
//        this.author = "blah";
//        this.imageURL = "blah";
//        this.bookURL = "blah";
//        this.shortDesc = "blah";
//        this.longDesc = "blah";
//        this.shelf = "blah";
//    }

    public String getShelf() { return shelf; }

    public void setShelf(String shelf) { this.shelf = shelf; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getBookURL() {
        return bookURL;
    }

    public void setBookURL(String bookURL) {
        this.bookURL = bookURL;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", bookURL='" + bookURL + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", longDesc='" + longDesc + '\'' +
                ", shelf='" + shelf + '\'' +
                '}';
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }
}

