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
public class BackgroundStuffGameTwo extends AsyncTask<Data,Integer, Long> {


    private CollectUserData data;
    private SearchPlayerFragment activity;
    public Data dataClass;
    private List<SwagOb> swagObList;

    private String gameType;
    private Drawable championPicturePlayed;
    private String kills;
    private String deaths;
    private Drawable[] summonerSpell;
    private String assists;
    private String cs;
    private String gold;
    private boolean won;
    private Drawable itemOne, itemTwo, itemThree, itemFour, itemFive, itemSix;
    private Drawable[] tempDrawable;
    private Drawable[] tempDrawable2;
    private String[] tempString;
    private SwagOb[] swagObsArray;
    private SwagObListView swagObListView;

    @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Long doInBackground(Data... params) {
            data = params[0].data;
            activity = params[0].activity;
            swagObList = params[0].swagObList;
            swagObsArray = params[0].swagObsArray;
            swagObListView = params[0].swagObListView;
            dataClass = params[0];

            try {
                gameType = data.getGameType(2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                championPicturePlayed = data.getChampionPicturePlayed(2, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                kills = data.getKills(2) + "";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                deaths = data.getDeaths(2) +"";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                assists = data.getAssists(2) + "";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                gold = data.getGold(2) + " K";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                cs = " " + data.getMinionsKilled(2) + " CS";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            summonerSpell = null;
            try {
                summonerSpell = data.getSummonerSpells(2);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            won = true;
            try {
                won = data.isGameWon(2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                itemOne =data.getItemFromMatchHistory(2, 1, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemTwo =data.getItemFromMatchHistory(2, 2, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemThree =data.getItemFromMatchHistory(2, 3, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemFour =data.getItemFromMatchHistory(2, 4, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemFive =data.getItemFromMatchHistory(2, 5, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemSix =data.getItemFromMatchHistory(2, 6, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempDrawable = null;
            tempDrawable2 = null;
            try {
                tempDrawable = data.getTeamPlayerChampionIcon(2, 100, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                tempDrawable2 = data.getTeamPlayerChampionIcon(2, 200, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempString = null;
            try {
                tempString = data.getTeamPlayerName(2);
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

            swagObsArray[1] = new SwagOb(gameType, championPicturePlayed, kills, deaths, summonerSpell,
                    assists, cs, gold, won, itemOne, itemTwo, itemThree, itemFour,
                    itemFive, itemSix, tempDrawable, tempDrawable2, tempString, activity, data);

            swagObList.add(swagObsArray[1]);
            swagObListView.setSwag(swagObList);

            new BackgroundStuffGameThree().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, dataClass);
            new BackgroundStuffGameFour().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, dataClass);
            new BackgroundStuffGameFive().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, dataClass);
            new BackgroundStuffGameSix().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, dataClass);
            new BackgroundStuffGameSeven().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, dataClass);
            new BackgroundStuffGameEight().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, dataClass);
            new BackgroundStuffGameNine().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, dataClass);
            new BackgroundStuffGameTen().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, dataClass);

/*            swagObList.add(new SwagOb(gameType, championPicturePlayed, kills, deaths, summonerSpell,
                    assists, cs, gold, won, itemOne, itemTwo, itemThree, itemFour,
                    itemFive, itemSix, tempDrawable, tempDrawable2, tempString, activity, data));*/

           /* activity.gameTwoGameModeTV.setText(gameType);
            activity.gameTwoChampion.setBackground(championPicturePlayed);
            activity.gameTwoSlashOneTV.setText(" / ");
            activity.gameTwoKillsTV.setText(kills);
            activity.gameTwoDeathsTV.setText(deaths);
            activity.gameTwoSlashTwoTV.setText(" / ");
            activity.gameTwoAssistsTV.setText(assists);
            activity.gameTwoGoldTV.setText(gold);
            activity.gameTwoCSTV.setText(cs);

            if(summonerSpell!=null) {
                activity.gameTwoSummonerSpellOneIV.setBackground(summonerSpell[0]);
                activity.gameTwoSummonerSpellTwoIV.setBackground(summonerSpell[1]);
            }
            if (won) {
                activity.gameTwoTitleBar.setBackgroundColor(Color.parseColor("#d604c429"));
                activity.gameTwo.setBackgroundColor(Color.parseColor("#4600FF06"));
            }
            if (!won) {
                activity.gameTwoTitleBar.setBackgroundColor(Color.parseColor("#d6ff0100"));
                activity.gameTwo.setBackgroundColor(Color.parseColor("#4fff0100"));
            }

            activity.gameTwoItemOneIV.setBackground(itemOne);
            activity.gameTwoItemTwoIV.setBackground(itemTwo);
            activity.gameTwoItemThreeIV.setBackground(itemThree);
            activity.gameTwoItemFourIV.setBackground(itemFour);
            activity.gameTwoItemFiveIV.setBackground(itemFive);
            activity.gameTwoItemSixIV.setBackground(itemSix);

            if (tempDrawable != null) {
                activity.gameTwoTeamOnePlayerOne.setBackground(tempDrawable[0]);
                activity.gameTwoTeamOnePlayerTwo.setBackground(tempDrawable[1]);
                activity.gameTwoTeamOnePlayerThree.setBackground(tempDrawable[2]);
                activity.gameTwoTeamOnePlayerFour.setBackground(tempDrawable[3]);
                activity.gameTwoTeamOnePlayerFive.setBackground(tempDrawable[4]);
            }
            if (tempDrawable2 != null) {
                activity.gameTwoTeamTwoPlayerOne.setBackground(tempDrawable2[0]);
                activity.gameTwoTeamTwoPlayerTwo.setBackground(tempDrawable2[1]);
                activity.gameTwoTeamTwoPlayerThree.setBackground(tempDrawable2[2]);
                activity.gameTwoTeamTwoPlayerFour.setBackground(tempDrawable2[3]);
                activity.gameTwoTeamTwoPlayerFive.setBackground(tempDrawable2[4]);
            }

            if (tempString != null) {
                activity.gameTwoTeamOnePlayerOneName.setText(tempString[0]);
                activity.gameTwoTeamOnePlayerTwoName.setText(tempString[1]);
                activity.gameTwoTeamOnePlayerThreeName.setText(tempString[2]);
                activity.gameTwoTeamOnePlayerFourName.setText(tempString[3]);
                activity.gameTwoTeamOnePlayerFiveName.setText(tempString[4]);
                activity.gameTwoTeamTwoPlayerOneName.setText(tempString[5]);
                activity.gameTwoTeamTwoPlayerTwoName.setText(tempString[6]);
                activity.gameTwoTeamTwoPlayerThreeName.setText(tempString[7]);
                activity.gameTwoTeamTwoPlayerFourName.setText(tempString[8]);
                activity.gameTwoTeamTwoPlayerFiveName.setText(tempString[9]);
            }
            if(!data.getIfThereAreRecentGames()){
                activity.gameTwoGameModeTV.setText("NO RECENT GAMES");
                activity.gameTwoTitleBar.setBackgroundColor(Color.parseColor("#FF514B51"));
                activity.gameTwo.setBackgroundColor(Color.parseColor("#FFB8AEB8"));
            }
*/

        }
}
