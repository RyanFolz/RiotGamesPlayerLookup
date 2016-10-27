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
public class BackgroundStuffGameThree extends AsyncTask<Data,Integer, Long> {


    private CollectUserData data;
    private SearchPlayerFragment activity;
    private List<SwagOb> swagObList;
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
            swagObsArray = params[0].swagObsArray;
            dataClass = params[0];

            try {
                gameType = data.getGameType(3);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                championPicturePlayed = data.getChampionPicturePlayed(3, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                kills = data.getKills(3) + "";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                deaths = data.getDeaths(3) +"";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                assists = data.getAssists(3) + "";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                gold = data.getGold(3) + " K";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                cs = " " + data.getMinionsKilled(3) + " CS";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            summonerSpells = null;
            try {
                summonerSpells = data.getSummonerSpells(3);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            won = true;
            try {
                won = data.isGameWon(3);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                itemOne =data.getItemFromMatchHistory(3, 1, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemTwo =data.getItemFromMatchHistory(3, 2, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemThree =data.getItemFromMatchHistory(3, 3, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemFour =data.getItemFromMatchHistory(3, 4, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemFive =data.getItemFromMatchHistory(3, 5, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemSix =data.getItemFromMatchHistory(3, 6, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempDrawable = null;
            tempDrawable2 = null;
            try {
                tempDrawable = data.getTeamPlayerChampionIcon(3, 100, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                tempDrawable2 = data.getTeamPlayerChampionIcon(3, 200, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempString = null;
            try {
                tempString = data.getTeamPlayerName(3);
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

            swagObsArray[2] = new SwagOb(gameType, championPicturePlayed, kills, deaths, summonerSpells,
                    assists, cs, gold, won, itemOne, itemTwo, itemThree, itemFour,
                    itemFive, itemSix, tempDrawable, tempDrawable2, tempString, activity, data);

/*            swagObList.add(new SwagOb(gameType, championPicturePlayed, kills, deaths, summonerSpells,
                    assists, cs, gold, won, itemOne, itemTwo, itemThree, itemFour,
                    itemFive, itemSix, tempDrawable, tempDrawable2, tempString, activity, data));*/

           /* activity.gameThreeGameModeTV.setText(gameType);
            activity.gameThreeChampion.setBackground(championPicturePlayed);
            activity.gameThreeSlashOneTV.setText(" / ");
            activity.gameThreeKillsTV.setText(kills);
            activity.gameThreeDeathsTV.setText(deaths);
            activity.gameThreeSlashTwoTV.setText(" / ");
            activity.gameThreeAssistsTV.setText(assists);
            activity.gameThreeGoldTV.setText(gold);
            activity.gameThreeCSTV.setText(cs);

            if(summonerSpells!=null) {
                activity.gameThreeSummonerSpellOneIV.setBackground(summonerSpells[0]);
                activity.gameThreeSummonerSpellTwoIV.setBackground(summonerSpells[1]);
            }
            if (won) {
                activity.gameThreeTitleBar.setBackgroundColor(Color.parseColor("#d604c429"));
                activity.gameThree.setBackgroundColor(Color.parseColor("#4600FF06"));
            }
            if (!won) {
                activity.gameThreeTitleBar.setBackgroundColor(Color.parseColor("#d6ff0100"));
                activity.gameThree.setBackgroundColor(Color.parseColor("#4fff0100"));
            }

            activity.gameThreeItemOneIV.setBackground(itemOne);
            activity.gameThreeItemTwoIV.setBackground(itemTwo);
            activity.gameThreeItemThreeIV.setBackground(itemThree);
            activity.gameThreeItemFourIV.setBackground(itemFour);
            activity.gameThreeItemFiveIV.setBackground(itemFive);
            activity.gameThreeItemSixIV.setBackground(itemSix);

            if (tempDrawable != null) {
                activity.gameThreeTeamOnePlayerOne.setBackground(tempDrawable[0]);
                activity.gameThreeTeamOnePlayerTwo.setBackground(tempDrawable[1]);
                activity.gameThreeTeamOnePlayerThree.setBackground(tempDrawable[2]);
                activity.gameThreeTeamOnePlayerFour.setBackground(tempDrawable[3]);
                activity.gameThreeTeamOnePlayerFive.setBackground(tempDrawable[4]);
            }
            if (tempDrawable2 != null) {
                activity.gameThreeTeamTwoPlayerOne.setBackground(tempDrawable2[0]);
                activity.gameThreeTeamTwoPlayerTwo.setBackground(tempDrawable2[1]);
                activity.gameThreeTeamTwoPlayerThree.setBackground(tempDrawable2[2]);
                activity.gameThreeTeamTwoPlayerFour.setBackground(tempDrawable2[3]);
                activity.gameThreeTeamTwoPlayerFive.setBackground(tempDrawable2[4]);
            }

            if (tempString != null) {
                activity.gameThreeTeamOnePlayerOneName.setText(tempString[0]);
                activity.gameThreeTeamOnePlayerTwoName.setText(tempString[1]);
                activity.gameThreeTeamOnePlayerThreeName.setText(tempString[2]);
                activity.gameThreeTeamOnePlayerFourName.setText(tempString[3]);
                activity.gameThreeTeamOnePlayerFiveName.setText(tempString[4]);
                activity.gameThreeTeamTwoPlayerOneName.setText(tempString[5]);
                activity.gameThreeTeamTwoPlayerTwoName.setText(tempString[6]);
                activity.gameThreeTeamTwoPlayerThreeName.setText(tempString[7]);
                activity.gameThreeTeamTwoPlayerFourName.setText(tempString[8]);
                activity.gameThreeTeamTwoPlayerFiveName.setText(tempString[9]);
            }
            if(!data.getIfThereAreRecentGames()){
                activity.gameThreeGameModeTV.setText("NO RECENT GAMES");
                activity.gameThreeTitleBar.setBackgroundColor(Color.parseColor("#FF514B51"));
                activity.gameThree.setBackgroundColor(Color.parseColor("#FFB8AEB8"));
            }*/


        }
}
