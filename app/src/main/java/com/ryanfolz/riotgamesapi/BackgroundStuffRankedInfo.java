package com.ryanfolz.riotgamesapi;

import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by Ryan Folz on 5/28/2015.
 */
public class BackgroundStuffRankedInfo extends AsyncTask<Data,Integer, Long> {
    
    public String league, summonerName, tier, leaguePoints, rankOfLastSeason;
    public long postSummonerId;
    public CollectUserData data;
    public SearchPlayerFragment activity;
    public int wins, losses;
    public boolean canIContinue;
    public Data dataClass;
    
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Long doInBackground(Data... params) {
            dataClass = params[0];
            data = params[0].data;
            activity = params[0].activity;

            try {
                data.setUpTheJSONs();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            league = "UNRANKED";
            canIContinue = true;
            try {
                canIContinue = data.canIContinue();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if(canIContinue) {
                try {
                    league = data.getMundaneCurrentLeague();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    summonerName = data.getSummonerName();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    tier = data.getTier();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    leaguePoints = data.getLeaguePoints();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    wins = data.getWins();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    losses = data.getLosses();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    rankOfLastSeason = data.getRankOfLastSeason();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            if(canIContinue) {
                activity.setLeague(activity.tierIV, league);
                activity.summonerNameTV.setText(summonerName);
                activity.tierTV.setText(tier);
                activity.leaguePointsTV.setText(leaguePoints + "");
                activity.winsTV.setText("W: " + wins + "");
                activity.slashTV.setText("/");
                activity.lossesTV.setText("L: " + losses);
                activity.lastSeasonRankTV.setText("Season 4: " + rankOfLastSeason);
                activity.d.data = data;

                new BackgroundStuffNormalInfo().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, dataClass);
                new BackgroundStuffGameOne().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, dataClass);
/*                new BackgroundStuffGameTwo().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, dataClass);
                new BackgroundStuffGameThree().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, dataClass);
                new BackgroundStuffGameFour().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, dataClass);
                new BackgroundStuffGameFive().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, dataClass);
                new BackgroundStuffGameSix().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, dataClass);
                new BackgroundStuffGameSeven().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, dataClass);
                new BackgroundStuffGameEight().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, dataClass);
                new BackgroundStuffGameNine().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, dataClass);
                new BackgroundStuffGameTen().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, dataClass);*/


            }
        }
}
