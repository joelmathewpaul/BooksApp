package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Book> books;
    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private NetworkUtil util;
    private Thread bookThread;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        books=new ArrayList<Book>();
        handler= new Handler();
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        bookThread = new Thread(new bookThread());
        bookThread.start();
    }

    private void set(){
        bookAdapter= new BookAdapter(this,books);
        recyclerView.setAdapter(bookAdapter);
    }
    class bookThread  implements Runnable{

        @Override
        public void run() {
            util= new NetworkUtil();
            books=util.getList();
            handler.post(()->{
                set();
            });
        }
    }
}