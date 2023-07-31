package com.example.bookwork2;

import static com.example.bookwork2.Book.BOOK_STATUS_INTERESTED;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookRepository {
    private BookDao bookDao;
    private LiveData<List<Book>> allBooks;
    private MutableLiveData<List<Book>> searchedBooks;

    private static final String LOG_TAG = "BookRepository";

    public BookRepository(Application application) {
        BookDatabase db = BookDatabase.getInstance(application);

        bookDao = db.bookDao();
        allBooks = bookDao.getAllBooks();

        searchedBooks = new MutableLiveData<>();
    }

    public void insert(Book book) {
        new dbOperationAsyncTask(this.bookDao, dbOperationAsyncTask.INSERT_TASK).execute(book);
    }

    public void delete(Book book) {
        new dbOperationAsyncTask(this.bookDao, dbOperationAsyncTask.DELETE_TASK).execute(book);
    }

    public void update(Book book) {
        new dbOperationAsyncTask(this.bookDao, dbOperationAsyncTask.UPDATE_TASK).execute(book);
    }

    //TODO: Check if iterating through allBooks member
    // variable and retrieving specified shelf is better
    public LiveData<List<Book>> getShelf(String shelf) {
        return bookDao.getShelf(shelf);
    }

    public LiveData<List<Book>> getAllBooks() {
        return allBooks;
    }

    public LiveData<List<Book>> getSearchedBooksLiveData() {
        return this.searchedBooks;
    }

    public void searchByQuery(String query) {

        Call<ResponseBody> call = ApiClient.getInstance().getApi().searchByQuery(query, "40");
        List<Book> list = new ArrayList<>();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                String responseBody = null;
                JSONObject json = null;
                try {
                    responseBody = response.body().string();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    json = new JSONObject(responseBody);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                JSONArray array = null;

                try {
                    array = (JSONArray) json.getJSONArray("items");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                for (int i = 0; i < array.length(); i++) {
                    try {
                        String title, author, imageURL, bookURL, shortDesc, longDesc, shelf;

                        title = array.getJSONObject(i).getJSONObject("volumeInfo").get("title").toString();
                        author = array.getJSONObject(i).getJSONObject("volumeInfo").getJSONArray("authors").get(0).toString();
                        imageURL = parseImageURL(array.getJSONObject(i).getJSONObject("volumeInfo").getJSONObject("imageLinks").getString("thumbnail"));
                        bookURL = array.getJSONObject(i).getJSONObject("volumeInfo").getString("previewLink");
                        shortDesc = "blah";
                        longDesc = array.getJSONObject(i).getJSONObject("volumeInfo").getString("description");
                        shelf = "random";

                        list.add(new Book(i, title, author, imageURL, bookURL, shortDesc, longDesc, shelf));

                    } catch (JSONException e) {
                        //throw new RuntimeException(e);
                        continue;
                    }
                }

                searchedBooks.postValue(list);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(LOG_TAG, t.getMessage());
                searchedBooks.postValue(null);

            }
        });

    }

    private static String parseImageURL(String rawURL) {

        String newUrl = rawURL.substring(0, 4) + "s" + rawURL.substring(4);
        return newUrl;

    }

    public void advancedSearch(@Nullable String title, @Nullable String author, @Nullable String genre, @Nullable String publisher) {

        Call<ResponseBody> call = ApiClient.getInstance().getApi().advancedSearch(title, title, author, genre, publisher);
        List<Book> list = new ArrayList<>();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                String responseBody = null;
                JSONObject json = null;
                try {
                    responseBody = response.body().string();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    json = new JSONObject(responseBody);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                JSONArray array = null;

                try {
                    array = (JSONArray) json.getJSONArray("items");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                for (int i = 0; i < array.length(); i++) {
                    try {
                        String title, author, imageURL, bookURL, shortDesc, longDesc, shelf;

                        title = array.getJSONObject(i).getJSONObject("volumeInfo").get("title").toString();
                        author = array.getJSONObject(i).getJSONObject("volumeInfo").getJSONArray("authors").get(0).toString();
                        imageURL = parseImageURL(array.getJSONObject(i).getJSONObject("volumeInfo").getJSONObject("imageLinks").getString("thumbnail"));
                        bookURL = array.getJSONObject(i).getJSONObject("volumeInfo").getString("previewLink");
                        shortDesc = "blah";
                        longDesc = array.getJSONObject(i).getJSONObject("volumeInfo").getString("description");
                        shelf = "random";

                        list.add(new Book(i, title, author, imageURL, bookURL, shortDesc, longDesc, shelf));

                    } catch (JSONException e) {
                        //throw new RuntimeException(e);
                        continue;
                    }
                }

                searchedBooks.postValue(list);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                searchedBooks.postValue(null);
            }
        });

    }

    private static class dbOperationAsyncTask extends AsyncTask<Book, Void, Void> {
        private BookDao bookDao;
        private String task;

        public static final String INSERT_TASK = "insert";
        public static final String DELETE_TASK = "delete";
        public static final String UPDATE_TASK = "update";


        private dbOperationAsyncTask(BookDao bookDao, String task) {
            this.bookDao = bookDao;
            this.task = task;
        }

        @Override
        protected Void doInBackground(Book... books) {

            if (this.task.equals(INSERT_TASK)) {
                this.bookDao.insert(books[0]);
            } else if (this.task.equals(DELETE_TASK)) {
                this.bookDao.delete(books[0]);
            } else if (this.task.equals(UPDATE_TASK)) {
                this.bookDao.update(books[0]);
            }

            return null;
        }
    }
}
