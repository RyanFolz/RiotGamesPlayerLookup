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
public class BackgroundStuffGameOne extends AsyncTask<Data,Integer, Long> {


    private CollectUserData data;
    private SearchPlayerFragment activity;
    private List<SwagOb> swagObList;
    private SwagObListView swagObListView;
    public Data dataClass;

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
            swagObListView = params[0].swagObListView;
            swagObsArray = params[0].swagObsArray;
            dataClass = params[0];

            try {
                gameType = data.getGameType(1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                championPicturePlayed = data.getChampionPicturePlayed(1, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                kills = data.getKills(1) + "";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                deaths = data.getDeaths(1) +"";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                assists = data.getAssists(1) + "";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                gold = data.getGold(1) + " K";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                cs = " " + data.getMinionsKilled(1) + " CS";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            summonerSpells = null;
            try {
                summonerSpells = data.getSummonerSpells(1);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            won = true;
            try {
                won = data.isGameWon(1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                itemOne =data.getItemFromMatchHistory(1, 1, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemTwo =data.getItemFromMatchHistory(1, 2, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemThree =data.getItemFromMatchHistory(1, 3, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemFour =data.getItemFromMatchHistory(1, 4, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemFive =data.getItemFromMatchHistory(1, 5, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemSix =data.getItemFromMatchHistory(1, 6, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempDrawable = null;
            tempDrawable2 = null;
            try {
                tempDrawable = data.getTeamPlayerChampionIcon(1, 100, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                tempDrawable2 = data.getTeamPlayerChampionIcon(1, 200, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempString = null;
            try {
                tempString = data.getTeamPlayerName(1);
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

            swagObsArray[0] = new SwagOb(gameType, championPicturePlayed, kills, deaths, summonerSpells,
                    assists, cs, gold, won, itemOne, itemTwo, itemThree, itemFour,
                    itemFive, itemSix, tempDrawable, tempDrawable2, tempString, activity, data);

            swagObList.add(swagObsArray[0]);
            swagObListView.setSwag(swagObList);

            new BackgroundStuffGameTwo().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, dataClass);


/*            swagObList.add(new SwagOb(gameType, championPicturePlayed, kills, deaths, summonerSpells,
                    assists, cs, gold, won, itemOne, itemTwo, itemThree, itemFour,
                    itemFive, itemSix, tempDrawable, tempDrawable2, tempString, activity, data));*/

/*            activity.gameOneGameModeTV.setText(gameType);
            activity.gameOneChampion.setBackground(championPicturePlayed);
            activity.gameOneSlashOneTV.setText(" / ");
            activity.gameOneKillsTV.setText(kills);
            activity.gameOneDeathsTV.setText(deaths);
            activity.gameOneSlashTwoTV.setText(" / ");
            activity.gameOneAssistsTV.setText(assists);
            activity.gameOneGoldTV.setText(gold);
            activity.gameOneCSTV.setText(cs);

            if(summonerSpells!=null) {
                activity.gameOneSummonerSpellOneIV.setBackground(summonerSpells[0]);
                activity.gameOneSummonerSpellTwoIV.setBackground(summonerSpells[1]);
            }
            if (won) {
                activity.gameOneTitleBar.setBackgroundColor(Color.parseColor("#d604c429"));
                activity.gameOne.setBackgroundColor(Color.parseColor("#4600FF06"));
            }
            if (!won) {
                activity.gameOneTitleBar.setBackgroundColor(Color.parseColor("#d6ff0100"));
                activity.gameOne.setBackgroundColor(Color.parseColor("#4fff0100"));
            }

            activity.gameOneItemOneIV.setBackground(itemOne);
            activity.gameOneItemTwoIV.setBackground(itemTwo);
            activity.gameOneItemThreeIV.setBackground(itemThree);
            activity.gameOneItemFourIV.setBackground(itemFour);
            activity.gameOneItemFiveIV.setBackground(itemFive);
            activity.gameOneItemSixIV.setBackground(itemSix);

            if (tempDrawable != null) {
                activity.gameOneTeamOnePlayerOne.setBackground(tempDrawable[0]);
                activity.gameOneTeamOnePlayerTwo.setBackground(tempDrawable[1]);
                activity.gameOneTeamOnePlayerThree.setBackground(tempDrawable[2]);
                activity.gameOneTeamOnePlayerFour.setBackground(tempDrawable[3]);
                activity.gameOneTeamOnePlayerFive.setBackground(tempDrawable[4]);
            }
            if (tempDrawable2 != null) {
                activity.gameOneTeamTwoPlayerOne.setBackground(tempDrawable2[0]);
                activity.gameOneTeamTwoPlayerTwo.setBackground(tempDrawable2[1]);
                activity.gameOneTeamTwoPlayerThree.setBackground(tempDrawable2[2]);
                activity.gameOneTeamTwoPlayerFour.setBackground(tempDrawable2[3]);
                activity.gameOneTeamTwoPlayerFive.setBackground(tempDrawable2[4]);
            }

            if (tempString != null) {
                activity.gameOneTeamOnePlayerOneName.setText(tempString[0]);
                activity.gameOneTeamOnePlayerTwoName.setText(tempString[1]);
                activity.gameOneTeamOnePlayerThreeName.setText(tempString[2]);
                activity.gameOneTeamOnePlayerFourName.setText(tempString[3]);
                activity.gameOneTeamOnePlayerFiveName.setText(tempString[4]);
                activity.gameOneTeamTwoPlayerOneName.setText(tempString[5]);
                activity.gameOneTeamTwoPlayerTwoName.setText(tempString[6]);
                activity.gameOneTeamTwoPlayerThreeName.setText(tempString[7]);
                activity.gameOneTeamTwoPlayerFourName.setText(tempString[8]);
                activity.gameOneTeamTwoPlayerFiveName.setText(tempString[9]);
            }
            if(!data.getIfThereAreRecentGames()){
                activity.gameOneGameModeTV.setText("NO RECENT GAMES");
                activity.gameOneTitleBar.setBackgroundColor(Color.parseColor("#FF514B51"));
                activity.gameOne.setBackgroundColor(Color.parseColor("#FFB8AEB8"));
            }*/
        }
}
