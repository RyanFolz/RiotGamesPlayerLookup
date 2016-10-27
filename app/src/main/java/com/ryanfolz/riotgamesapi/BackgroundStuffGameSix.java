package com.ryanfolz.riotgamesapi;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by Ryan Folz on 5/28/2015.
 */
public class BackgroundStuffGameSix extends AsyncTask<Data,Integer, Long> {


    private CollectUserData data;
    private SearchPlayerFragment activity;
    private List<SwagOb> swagObList;

    private String gameType;
    private Drawable championPicturePlayed;
    private String kills;
    private String deaths;
    private Drawable[] summonerSpells;
    private String assists;
    private String cs;
    private String gold;
    private boolean won;
    private Drawable itemOne, itemTwo, itemThree, itemFour, itemFive, itemSix;
    private Drawable[] tempDrawable;
    private Drawable[] tempDrawable2;
    private String[] tempString;
    public Data dataClass;
    private SwagOb[] swagObsArray;

    @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Long doInBackground(Data... params) {
            dataClass = params[0];
            data = params[0].data;
            activity = params[0].activity;
            swagObList = params[0].swagObList;
            swagObsArray = params[0].swagObsArray;

            try {
                gameType = data.getGameType(6);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                championPicturePlayed = data.getChampionPicturePlayed(6, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                kills = data.getKills(6) + "";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                deaths = data.getDeaths(6) +"";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                assists = data.getAssists(6) + "";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                gold = data.getGold(6) + " K";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                cs = " " + data.getMinionsKilled(6) + " CS";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            summonerSpells = null;
            try {
                summonerSpells = data.getSummonerSpells(6);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            won = true;
            try {
                won = data.isGameWon(6);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                itemOne =data.getItemFromMatchHistory(6, 1, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemTwo =data.getItemFromMatchHistory(6, 2, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemThree =data.getItemFromMatchHistory(6, 3, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemFour =data.getItemFromMatchHistory(6, 4, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemFive =data.getItemFromMatchHistory(6, 5, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemSix =data.getItemFromMatchHistory(6, 6, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempDrawable = null;
            tempDrawable2 = null;
            try {
                tempDrawable = data.getTeamPlayerChampionIcon(6, 100, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                tempDrawable2 = data.getTeamPlayerChampionIcon(6, 200, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempString = null;
            try {
                tempString = data.getTeamPlayerName(6);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }


        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);

            swagObsArray[5] = new SwagOb(gameType, championPicturePlayed, kills, deaths, summonerSpells,
                    assists, cs, gold, won, itemOne, itemTwo, itemThree, itemFour,
                    itemFive, itemSix, tempDrawable, tempDrawable2, tempString, activity, data);

/*
            swagObList.add(new SwagOb(gameType, championPicturePlayed, kills, deaths, summonerSpells,
                    assists, cs, gold, won, itemOne, itemTwo, itemThree, itemFour,
                    itemFive, itemSix, tempDrawable, tempDrawable2, tempString, activity, data));
*/

            boolean anyNull = false;
            for(int i = 0; i < swagObsArray.length; i++){
                if(swagObsArray[i] == null){
                    anyNull = true;
                }
            }
            if(!anyNull){
                for(int i = 0; i < swagObsArray.length; i++) {
                    swagObList.add(swagObsArray[i]);
                }
                new BackgroundStuffAfterFirstTenRanked().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, dataClass);
            }

           /* activity.gameSixGameModeTV.setText(gameType);
            activity.gameSixChampion.setBackground(championPicturePlayed);
            activity.gameSixSlashOneTV.setText(" / ");
            activity.gameSixKillsTV.setText(kills);
            activity.gameSixDeathsTV.setText(deaths);
            activity.gameSixSlashTwoTV.setText(" / ");
            activity.gameSixAssistsTV.setText(assists);
            activity.gameSixGoldTV.setText(gold);
            activity.gameSixCSTV.setText(cs);

            if(summonerSpells!=null) {
                activity.gameSixSummonerSpellOneIV.setBackground(summonerSpells[0]);
                activity.gameSixSummonerSpellTwoIV.setBackground(summonerSpells[1]);
            }
            if (won) {
                activity.gameSixTitleBar.setBackgroundColor(Color.parseColor("#d604c429"));
                activity.gameSix.setBackgroundColor(Color.parseColor("#4600FF06"));
            }
            if (!won) {
                activity.gameSixTitleBar.setBackgroundColor(Color.parseColor("#d6ff0100"));
                activity.gameSix.setBackgroundColor(Color.parseColor("#4fff0100"));
            }

            activity.gameSixItemOneIV.setBackground(itemOne);
            activity.gameSixItemTwoIV.setBackground(itemTwo);
            activity.gameSixItemThreeIV.setBackground(itemThree);
            activity.gameSixItemFourIV.setBackground(itemFour);
            activity.gameSixItemFiveIV.setBackground(itemFive);
            activity.gameSixItemSixIV.setBackground(itemSix);

            if (tempDrawable != null) {
                activity.gameSixTeamOnePlayerOne.setBackground(tempDrawable[0]);
                activity.gameSixTeamOnePlayerTwo.setBackground(tempDrawable[1]);
                activity.gameSixTeamOnePlayerThree.setBackground(tempDrawable[2]);
                activity.gameSixTeamOnePlayerFour.setBackground(tempDrawable[3]);
                activity.gameSixTeamOnePlayerFive.setBackground(tempDrawable[4]);
            }
            if (tempDrawable2 != null) {
                activity.gameSixTeamTwoPlayerOne.setBackground(tempDrawable2[0]);
                activity.gameSixTeamTwoPlayerTwo.setBackground(tempDrawable2[1]);
                activity.gameSixTeamTwoPlayerThree.setBackground(tempDrawable2[2]);
                activity.gameSixTeamTwoPlayerFour.setBackground(tempDrawable2[3]);
                activity.gameSixTeamTwoPlayerFive.setBackground(tempDrawable2[4]);
            }

            if (tempString != null) {
                activity.gameSixTeamOnePlayerOneName.setText(tempString[0]);
                activity.gameSixTeamOnePlayerTwoName.setText(tempString[1]);
                activity.gameSixTeamOnePlayerThreeName.setText(tempString[2]);
                activity.gameSixTeamOnePlayerFourName.setText(tempString[3]);
                activity.gameSixTeamOnePlayerFiveName.setText(tempString[4]);
                activity.gameSixTeamTwoPlayerOneName.setText(tempString[5]);
                activity.gameSixTeamTwoPlayerTwoName.setText(tempString[6]);
                activity.gameSixTeamTwoPlayerThreeName.setText(tempString[7]);
                activity.gameSixTeamTwoPlayerFourName.setText(tempString[8]);
                activity.gameSixTeamTwoPlayerFiveName.setText(tempString[9]);
            }
            if(!data.getIfThereAreRecentGames()){
                activity.gameSixGameModeTV.setText("NO RECENT GAMES");
                activity.gameSixTitleBar.setBackgroundColor(Color.parseColor("#FF514B51"));
                activity.gameSix.setBackgroundColor(Color.parseColor("#FFB8AEB8"));
            }*/


        }
}
