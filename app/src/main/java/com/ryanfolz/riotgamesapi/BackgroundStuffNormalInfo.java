package com.ryanfolz.riotgamesapi;

import android.graphics.Color;
import android.os.AsyncTask;

import org.json.JSONException;

/**
 * Created by Ryan Folz on 5/28/2015.
 */
public class BackgroundStuffNormalInfo extends AsyncTask<Data,Integer, Long> {
    
    public String league, summonerName, tier, leaguePoints, rankOfLastSeason;
    public long postSummonerId;
    public CollectUserData data;
    public SearchPlayerFragment activity;
    public int wins, assists, turretKills, championKills, minionKills, neutralMinionKills;
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
                wins = data.getNormalWins();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                assists = data.getNormalAssists();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                turretKills = data.getNormalTurretKills();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                minionKills = data.getNormalMinionKills();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                neutralMinionKills = data.getNormalNeutralMonterKills();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                championKills = data.getNormalChampionKills();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);

            activity.normalTitle.setText("Normal 5v5 Games");
            activity.normalMinionsValue.setText(minionKills + "");
            activity.normalTurretsValue.setText(turretKills + "");
            activity.normalWinsValue.setText(wins + "");
            activity.normalKillsValue.setText(championKills + "");
            activity.normalAssistsValue.setText(assists + "");
            activity.normalNeutralMonstersValue.setText(neutralMinionKills + "");
            activity.normalSpaceOne.setBackgroundColor(Color.parseColor("#ff989898"));
            activity.normalSpaceTwo.setBackgroundColor(Color.parseColor("#ff989898"));
            activity.normalSpaceThree.setBackgroundColor(Color.parseColor("#ff989898"));
            activity.normalSpaceFour.setBackgroundColor(Color.parseColor("#ff989898"));
            activity.normalSpaceFive.setBackgroundColor(Color.parseColor("#ff989898"));
            activity.normalSpaceSix.setBackgroundColor(Color.parseColor("#ff989898"));
            activity.normalSpaceSeven.setBackgroundColor(Color.parseColor("#ff989898"));
            activity.normalSpaceEight.setBackgroundColor(Color.parseColor("#ff989898"));
            activity.normalKills.setBackgroundColor(Color.parseColor("#FF3CCBD0"));
            activity.normalWins.setBackgroundColor(Color.parseColor("#FF3CCBD0"));
            activity.normalAssists.setBackgroundColor(Color.parseColor("#FF3CCBD0"));
            activity.normalNeutralMonsters.setBackgroundColor(Color.parseColor("#FF3CCBD0"));
            activity.normalMinions.setBackgroundColor(Color.parseColor("#FF3CCBD0"));
            activity.normalTurrets.setBackgroundColor(Color.parseColor("#FF3CCBD0"));
            activity.normalTurretsValue.setBackgroundColor(Color.parseColor("#FF239BC5"));
            activity.normalMinionsValue.setBackgroundColor(Color.parseColor("#FF239BC5"));
            activity.normalNeutralMonstersValue.setBackgroundColor(Color.parseColor("#FF239BC5"));
            activity.normalKillsValue.setBackgroundColor(Color.parseColor("#FF239BC5"));
            activity.normalWinsValue.setBackgroundColor(Color.parseColor("#FF239BC5"));
            activity.normalAssistsValue.setBackgroundColor(Color.parseColor("#FF239BC5"));
            activity.normalWins.setText("Wins");
            activity.normalAssists.setText("Assists");
            activity.normalKills.setText("Kills");
            activity.normalTurrets.setText("Turrets Destroyed");
            activity.normalMinions.setText("Minion Kills");
            activity.normalNeutralMonsters.setText("Neutral Minion Kills");
            activity.normalTitle.setBackgroundColor(Color.parseColor("#FF0065AA"));
        }
}
