package com.example.apoorva.newspart2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Context context, ArrayList<News> newsArrayList) {
        super(context, 0, newsArrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        News currentNews =getItem(position);

        TextView titleTextView = (TextView)listItemView.findViewById(R.id.title_textview);
        titleTextView.setText(currentNews.getWebTitle());

        TextView sectionTextView = (TextView)listItemView.findViewById(R.id.section_textview);
        sectionTextView.setText("Section: " + currentNews.getSectionName());

        TextView authorTextView = (TextView)listItemView.findViewById(R.id.author_textview);
        if (currentNews.getAuthor()!="") {
            authorTextView.setText("by " + currentNews.getAuthor());
        } else {
            authorTextView.setVisibility(View.GONE);
        }

        TextView dateTextView = (TextView)listItemView.findViewById(R.id.date_textview);
        TextView timeTextView = (TextView)listItemView.findViewById(R.id.time_textview);

        if (currentNews.getDateAndTime() != "") {

            int indexT = currentNews.getDateAndTime().indexOf('T');
            int indexZ = currentNews.getDateAndTime().indexOf('Z');

            dateTextView.setText(currentNews.getDateAndTime().substring(0, indexT));
            timeTextView.setText(currentNews.getDateAndTime().substring(indexT + 1, indexZ));
        } else {
            dateTextView.setVisibility(View.GONE);
            timeTextView.setVisibility(View.GONE);
        }

        return listItemView;

    }
}

