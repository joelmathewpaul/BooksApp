package com.example.newsapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NetworkUtil {
    private URL url;
    private List<Book> books;
    private HttpURLConnection conn;
    private int numOfBooks;
    private JSONObject jObject;
    private JSONArray objects;

    public NetworkUtil() {
        try {
            url=new URL("https://api.nytimes.com/svc/books/v3/lists/current/hardcover-fiction.json?api-key=NsJuIrGmKQQ1vQiAgCoEj7qkbngmAQNt");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            InputStream inputStream = conn.getInputStream();
            final String resp = convertStreamToString(inputStream);
            System.out.println(resp);
            books= new ArrayList<Book>();
            jObject = new JSONObject(resp);
            numOfBooks = Integer.parseInt(jObject.getString("num_results"));
            objects=new JSONArray();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private String convertStreamToString(InputStream inputStream) {
        Scanner s = new Scanner(inputStream);
        return s.hasNextLine()?s.nextLine():"";
    }


    public List<Book> getList() {

        try {
            objects=jObject.getJSONObject("results").getJSONArray("books");
            String desc;
            for (int i = 0; i < numOfBooks;i++){
                desc= objects.getJSONObject(i).getString("description");
                if (desc.length() > 123){
                    desc = desc.substring(0,118)+".....";
                }
                books.add(new Book(objects.getJSONObject(i).getString("title"),
                        "by "+objects.getJSONObject(i).getString("author"),
                        desc,objects.getJSONObject(i).getString("book_image"),
                        objects.getJSONObject(i).getString("amazon_product_url")));
            }
            return books;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }
}
