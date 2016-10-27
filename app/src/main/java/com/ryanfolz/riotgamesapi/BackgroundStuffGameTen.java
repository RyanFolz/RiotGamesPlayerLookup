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
public class BackgroundStuffGameTen extends AsyncTask<Data,Integer, Long> {


    private CollectUserData data;
    private SearchPlayerFragment activity;
    private List<SwagOb> swagObList;

    private String gameType;
    private Drawable championPicturePlayed;
    private String kills;
    private String deaths;
    public Data dataClass;
    private Drawable[] summonerSpells;
    private String assists;
    private String cs;
    private String gold;
    private boolean won;
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
                gameType = data.getGameType(10);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                championPicturePlayed = data.getChampionPicturePlayed(10, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                kills = data.getKills(10) + "";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                deaths = data.getDeaths(10) +"";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                assists = data.getAssists(10) + "";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                gold = data.getGold(10) + " K";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                cs = " " + data.getMinionsKilled(10) + " CS";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            summonerSpells = null;
            try {
                summonerSpells = data.getSummonerSpells(10);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            won = true;
            try {
                won = data.isGameWon(10);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                itemOne =data.getItemFromMatchHistory(10, 1, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemTwo =data.getItemFromMatchHistory(10, 2, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemThree =data.getItemFromMatchHistory(10, 3, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemFour =data.getItemFromMatchHistory(10, 4, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemFive =data.getItemFromMatchHistory(10, 5, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemSix =data.getItemFromMatchHistory(10, 6, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempDrawable = null;
            tempDrawable2 = null;
            try {
                tempDrawable = data.getTeamPlayerChampionIcon(10, 100, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                tempDrawable2 = data.getTeamPlayerChampionIcon(10, 200, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempString = null;
            try {
                tempString = data.getTeamPlayerName(10);
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

            swagObsArray[9] = new SwagOb(gameType, championPicturePlayed, kills, deaths, summonerSpells,
                    assists, cs, gold, won, itemOne, itemTwo, itemThree, itemFour,
                    itemFive, itemSix, tempDrawable, tempDrawable2, tempString, activity, data);

/*            swagObList.add(new SwagOb(gameType, championPicturePlayed, kills, deaths, summonerSpells,
                    assists, cs, gold, won, itemOne, itemTwo, itemThree, itemFour,
                    itemFive, itemSix, tempDrawable, tempDrawable2, tempString, activity, data));*/
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

          /*  activity.gameTenGameModeTV.setText(gameType);
            activity.gameTenChampion.setBackground(championPicturePlayed);
            activity.gameTenSlashOneTV.setText(" / ");
            activity.gameTenKillsTV.setText(kills);
            activity.gameTenDeathsTV.setText(deaths);
            activity.gameTenSlashTwoTV.setText(" / ");
            activity.gameTenAssistsTV.setText(assists);
            activity.gameTenGoldTV.setText(gold);
            activity.gameTenCSTV.setText(cs);

            if(summonerSpells!=null) {
                activity.gameTenSummonerSpellOneIV.setBackground(summonerSpells[0]);
                activity.gameTenSummonerSpellTwoIV.setBackground(summonerSpells[1]);
            }
            if (won) {
                activity.gameTenTitleBar.setBackgroundColor(Color.parseColor("#d604c429"));
                activity.gameTen.setBackgroundColor(Color.parseColor("#4600FF06"));
            }
            if (!won) {
                activity.gameTenTitleBar.setBackgroundColor(Color.parseColor("#d6ff0100"));
                activity.gameTen.setBackgroundColor(Color.parseColor("#4fff0100"));
            }

            activity.gameTenItemOneIV.setBackground(itemOne);
            activity.gameTenItemTwoIV.setBackground(itemTwo);
            activity.gameTenItemThreeIV.setBackground(itemThree);
            activity.gameTenItemFourIV.setBackground(itemFour);
            activity.gameTenItemFiveIV.setBackground(itemFive);
            activity.gameTenItemSixIV.setBackground(itemSix);

            if (tempDrawable != null) {
                activity.gameTenTeamOnePlayerOne.setBackground(tempDrawable[0]);
                activity.gameTenTeamOnePlayerTwo.setBackground(tempDrawable[1]);
                activity.gameTenTeamOnePlayerThree.setBackground(tempDrawable[2]);
                activity.gameTenTeamOnePlayerFour.setBackground(tempDrawable[3]);
                activity.gameTenTeamOnePlayerFive.setBackground(tempDrawable[4]);
            }
            if (tempDrawable2 != null) {
                activity.gameTenTeamTwoPlayerOne.setBackground(tempDrawable2[0]);
                activity.gameTenTeamTwoPlayerTwo.setBackground(tempDrawable2[1]);
                activity.gameTenTeamTwoPlayerThree.setBackground(tempDrawable2[2]);
                activity.gameTenTeamTwoPlayerFour.setBackground(tempDrawable2[3]);
                activity.gameTenTeamTwoPlayerFive.setBackground(tempDrawable2[4]);
            }

            if (tempString != null) {
                activity.gameTenTeamOnePlayerOneName.setText(tempString[0]);
                activity.gameTenTeamOnePlayerTwoName.setText(tempString[1]);
                activity.gameTenTeamOnePlayerThreeName.setText(tempString[2]);
                activity.gameTenTeamOnePlayerFourName.setText(tempString[3]);
                activity.gameTenTeamOnePlayerFiveName.setText(tempString[4]);
                activity.gameTenTeamTwoPlayerOneName.setText(tempString[5]);
                activity.gameTenTeamTwoPlayerTwoName.setText(tempString[6]);
                activity.gameTenTeamTwoPlayerThreeName.setText(tempString[7]);
                activity.gameTenTeamTwoPlayerFourName.setText(tempString[8]);
                activity.gameTenTeamTwoPlayerFiveName.setText(tempString[9]);
            }
            if(!data.getIfThereAreRecentGames()){
                activity.gameTenGameModeTV.setText("NO RECENT GAMES");
                activity.gameTenTitleBar.setBackgroundColor(Color.parseColor("#FF514B51"));
                activity.gameTen.setBackgroundColor(Color.parseColor("#FFB8AEB8"));
            }*/


        }
}
