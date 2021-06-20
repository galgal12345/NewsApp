package com.drillgil.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ArticleAdapter extends ArrayAdapter<Article> {


    public ArticleAdapter(@NonNull Context context, @NonNull List<Article> objects) {
        super(context, 0, objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_list_item, parent, false);
        }

        Article currentArticle = getItem(position);

        TextView sectionNameView = (TextView) listItemView.findViewById(R.id.Section_name);
        String sectionName = currentArticle.getmSectionName();
        sectionNameView.setText(sectionName);

        TextView webTitleView = (TextView) listItemView.findViewById(R.id.web_title);
        String webTitle = currentArticle.getmWebTitle();
        webTitleView.setText(webTitle);

        TextView authorView = (TextView) listItemView.findViewById(R.id.author);
        String author = currentArticle.getmAuthor();
        authorView.setText(author);

        TextView webPublicationDateView = (TextView) listItemView.findViewById(R.id.date);
        String webPublicationDate = currentArticle.getmWebPublicationDate();
        webPublicationDateView.setText(webPublicationDate);



        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        parser.setTimeZone(TimeZone.getTimeZone("UTC"));


        return listItemView;
    }
}
