package com.example.newsapp;



import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class BookViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private TextView authorName;
    private TextView description;
    private ImageView image;
    private Button buyButton;
    private Context context;
    private String buyUrl;
    public BookViewHolder(@NonNull View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.title);
        authorName=itemView.findViewById(R.id.authorName);
        description=itemView.findViewById(R.id.description);
        description.setMovementMethod(new ScrollingMovementMethod());
        image=itemView.findViewById(R.id.bookImage);
        buyButton=itemView.findViewById(R.id.buyButton);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(buyUrl));
                context.startActivity(buyIntent);
            }
        });
    }


    public void bindData(Book book, Context context){
        buyUrl=book.getBuyLink();
        title.setText(book.getTitle());
        authorName.setText(book.getAuthorName());
        description.setText(book.getDescription());
        Picasso.with(context).load(book.getImage()).into(image);
        this.context=context;


    }


}
