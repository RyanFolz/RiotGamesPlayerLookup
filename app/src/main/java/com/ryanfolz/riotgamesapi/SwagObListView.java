package com.ryanfolz.riotgamesapi;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Ivan on 6/24/2015.
 */
public class SwagObListView extends ListView implements AdapterView.OnItemClickListener {

    private List<SwagOb> swagList;
    private SwagObClickListener swagClicked;

    public SwagObListView(Context context) {
        super(context);
    }

    public SwagObListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.swagList = swagList;
        this.swagClicked = swagClicked;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (swagClicked != null) {
            swagClicked.onConnectionClicked(swagList.get(i));
        }
    }

    public void setOnSwagObClickListener(SwagObClickListener c){
        this.swagClicked = c;
    }

    public void setSwag(List<SwagOb> swagList) {
        this.swagList = swagList;
        SwagObAdapter swagDapter = new SwagObAdapter(getContext(), swagList);
        setAdapter(swagDapter);
        setOnItemClickListener(this);
    }
}
