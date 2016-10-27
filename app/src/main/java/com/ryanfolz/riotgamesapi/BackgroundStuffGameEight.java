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
public class BackgroundStuffGameEight extends AsyncTask<Data,Integer, Long> {


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
    public Data dataClass;
    private Drawable itemOne, itemTwo, itemThree, itemFour, itemFive, itemSix;
    private Drawable[] tempDrawable;
    private Drawable[] tempDrawable2;
    private String[] tempString;
    private SwagOb[] swagObsArray;

    @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Long doInBackground(Data... params) {
            data = params[0].data;
            activity = params[0].activity;
            swagObList = params[0].swagObList;
            dataClass = params[0];
            swagObsArray = params[0].swagObsArray;

            try {
                gameType = data.getGameType(8);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                championPicturePlayed = data.getChampionPicturePlayed(8, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                kills = data.getKills(8) + "";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                deaths = data.getDeaths(8) +"";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                assists = data.getAssists(8) + "";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                gold = data.getGold(8) + " K";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                cs = " " + data.getMinionsKilled(8) + " CS";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            summonerSpells = null;
            try {
                summonerSpells = data.getSummonerSpells(8);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            won = true;
            try {
                won = data.isGameWon(8);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                itemOne =data.getItemFromMatchHistory(8, 1, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemTwo =data.getItemFromMatchHistory(8, 2, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemThree =data.getItemFromMatchHistory(8, 3, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemFour =data.getItemFromMatchHistory(8, 4, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemFive =data.getItemFromMatchHistory(8, 5, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemSix =data.getItemFromMatchHistory(8, 6, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempDrawable = null;
            tempDrawable2 = null;
            try {
                tempDrawable = data.getTeamPlayerChampionIcon(8, 100, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                tempDrawable2 = data.getTeamPlayerChampionIcon(8, 200, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempString = null;
            try {
                tempString = data.getTeamPlayerName(8);
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

            swagObsArray[7] = new SwagOb(gameType, championPicturePlayed, kills, deaths, summonerSpells,
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

          /*  activity.gameEightGameModeTV.setText(gameType);
            activity.gameEightChampion.setBackground(championPicturePlayed);
            activity.gameEightSlashOneTV.setText(" / ");
            activity.gameEightKillsTV.setText(kills);
            activity.gameEightDeathsTV.setText(deaths);
            activity.gameEightSlashTwoTV.setText(" / ");
            activity.gameEightAssistsTV.setText(assists);
            activity.gameEightGoldTV.setText(gold);
            activity.gameEightCSTV.setText(cs);

            if(summonerSpells!=null) {
                activity.gameEightSummonerSpellOneIV.setBackground(summonerSpells[0]);
                activity.gameEightSummonerSpellTwoIV.setBackground(summonerSpells[1]);
            }
            if (won) {
                activity.gameEightTitleBar.setBackgroundColor(Color.parseColor("#d604c429"));
                activity.gameEight.setBackgroundColor(Color.parseColor("#4600FF06"));
            }
            if (!won) {
                activity.gameEightTitleBar.setBackgroundColor(Color.parseColor("#d6ff0100"));
                activity.gameEight.setBackgroundColor(Color.parseColor("#4fff0100"));
            }

            activity.gameEightItemOneIV.setBackground(itemOne);
            activity.gameEightItemTwoIV.setBackground(itemTwo);
            activity.gameEightItemThreeIV.setBackground(itemThree);
            activity.gameEightItemFourIV.setBackground(itemFour);
            activity.gameEightItemFiveIV.setBackground(itemFive);
            activity.gameEightItemSixIV.setBackground(itemSix);

            if (tempDrawable != null) {
                activity.gameEightTeamOnePlayerOne.setBackground(tempDrawable[0]);
                activity.gameEightTeamOnePlayerTwo.setBackground(tempDrawable[1]);
                activity.gameEightTeamOnePlayerThree.setBackground(tempDrawable[2]);
                activity.gameEightTeamOnePlayerFour.setBackground(tempDrawable[3]);
                activity.gameEightTeamOnePlayerFive.setBackground(tempDrawable[4]);
            }
            if (tempDrawable2 != null) {
                activity.gameEightTeamTwoPlayerOne.setBackground(tempDrawable2[0]);
                activity.gameEightTeamTwoPlayerTwo.setBackground(tempDrawable2[1]);
                activity.gameEightTeamTwoPlayerThree.setBackground(tempDrawable2[2]);
                activity.gameEightTeamTwoPlayerFour.setBackground(tempDrawable2[3]);
                activity.gameEightTeamTwoPlayerFive.setBackground(tempDrawable2[4]);
            }

            if (tempString != null) {
                activity.gameEightTeamOnePlayerOneName.setText(tempString[0]);
                activity.gameEightTeamOnePlayerTwoName.setText(tempString[1]);
                activity.gameEightTeamOnePlayerThreeName.setText(tempString[2]);
                activity.gameEightTeamOnePlayerFourName.setText(tempString[3]);
                activity.gameEightTeamOnePlayerFiveName.setText(tempString[4]);
                activity.gameEightTeamTwoPlayerOneName.setText(tempString[5]);
                activity.gameEightTeamTwoPlayerTwoName.setText(tempString[6]);
                activity.gameEightTeamTwoPlayerThreeName.setText(tempString[7]);
                activity.gameEightTeamTwoPlayerFourName.setText(tempString[8]);
                activity.gameEightTeamTwoPlayerFiveName.setText(tempString[9]);
            }
            if(!data.getIfThereAreRecentGames()){
                activity.gameEightGameModeTV.setText("NO RECENT GAMES");
                activity.gameEightTitleBar.setBackgroundColor(Color.parseColor("#FF514B51"));
                activity.gameEight.setBackgroundColor(Color.parseColor("#FFB8AEB8"));
            }
*/

        }
}
