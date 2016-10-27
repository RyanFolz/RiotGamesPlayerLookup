package com.ryanfolz.riotgamesapi;

import android.os.AsyncTask;

import java.util.List;

/**
 * Created by Ryan Folz on 5/28/2015.
 */
public class BackgroundStuffAfterFirstTenRanked extends AsyncTask<Data,Integer, Long> {
    
    public String league, summonerName, tier, leaguePoints, rankOfLastSeason;
    public long postSummonerId;
    public CollectUserData data;
    public SearchPlayerFragment activity;
    public int wins, assists, turretKills, championKills, minionKills, neutralMinionKills;
    public Data dataClass;
    public SwagObListView swagObListView;
    private List<SwagOb> swagObList;


    @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Long doInBackground(Data... params) {
            dataClass = params[0];
            data = params[0].data;
            activity = params[0].activity;
            swagObListView = params[0].swagObListView;
            swagObList = params[0].swagObList;

            return null;
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);


            swagObListView.setSwag(swagObList);

        }
}
