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
public class BackgroundStuffGameFour extends AsyncTask<Data,Integer, Long> {


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
                gameType = data.getGameType(4);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                championPicturePlayed = data.getChampionPicturePlayed(4, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                kills = data.getKills(4) + "";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                deaths = data.getDeaths(4) +"";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                assists = data.getAssists(4) + "";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                gold = data.getGold(4) + " K";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                cs = " " + data.getMinionsKilled(4) + " CS";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            summonerSpells = null;
            try {
                summonerSpells = data.getSummonerSpells(4);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            won = true;
            try {
                won = data.isGameWon(4);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                itemOne =data.getItemFromMatchHistory(4, 1, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemTwo =data.getItemFromMatchHistory(4, 2, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemThree =data.getItemFromMatchHistory(4, 3, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemFour =data.getItemFromMatchHistory(4, 4, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemFive =data.getItemFromMatchHistory(4, 5, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemSix =data.getItemFromMatchHistory(4, 6, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempDrawable = null;
            tempDrawable2 = null;
            try {
                tempDrawable = data.getTeamPlayerChampionIcon(4, 100, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                tempDrawable2 = data.getTeamPlayerChampionIcon(4, 200, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempString = null;
            try {
                tempString = data.getTeamPlayerName(4);
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

            swagObsArray[3] = new SwagOb(gameType, championPicturePlayed, kills, deaths, summonerSpells,
                    assists, cs, gold, won, itemOne, itemTwo, itemThree, itemFour,
                    itemFive, itemSix, tempDrawable, tempDrawable2, tempString, activity, data);

/*            swagObList.add(new SwagOb(gameType, championPicturePlayed, kills, deaths, summonerSpells,
                    assists, cs, gold, won, itemOne, itemTwo, itemThree, itemFour,
                    itemFive, itemSix, tempDrawable, tempDrawable2, tempString, activity, data));*/

           /* activity.gameFourGameModeTV.setText(gameType);
            activity.gameFourChampion.setBackground(championPicturePlayed);
            activity.gameFourSlashOneTV.setText(" / ");
            activity.gameFourKillsTV.setText(kills);
            activity.gameFourDeathsTV.setText(deaths);
            activity.gameFourSlashTwoTV.setText(" / ");
            activity.gameFourAssistsTV.setText(assists);
            activity.gameFourGoldTV.setText(gold);
            activity.gameFourCSTV.setText(cs);

            if(summonerSpells!=null) {
                activity.gameFourSummonerSpellOneIV.setBackground(summonerSpells[0]);
                activity.gameFourSummonerSpellTwoIV.setBackground(summonerSpells[1]);
            }
            if (won) {
                activity.gameFourTitleBar.setBackgroundColor(Color.parseColor("#d604c429"));
                activity.gameFour.setBackgroundColor(Color.parseColor("#4600FF06"));
            }
            if (!won) {
                activity.gameFourTitleBar.setBackgroundColor(Color.parseColor("#d6ff0100"));
                activity.gameFour.setBackgroundColor(Color.parseColor("#4fff0100"));
            }

            activity.gameFourItemOneIV.setBackground(itemOne);
            activity.gameFourItemTwoIV.setBackground(itemTwo);
            activity.gameFourItemThreeIV.setBackground(itemThree);
            activity.gameFourItemFourIV.setBackground(itemFour);
            activity.gameFourItemFiveIV.setBackground(itemFive);
            activity.gameFourItemSixIV.setBackground(itemSix);

            if (tempDrawable != null) {
                activity.gameFourTeamOnePlayerOne.setBackground(tempDrawable[0]);
                activity.gameFourTeamOnePlayerTwo.setBackground(tempDrawable[1]);
                activity.gameFourTeamOnePlayerThree.setBackground(tempDrawable[2]);
                activity.gameFourTeamOnePlayerFour.setBackground(tempDrawable[3]);
                activity.gameFourTeamOnePlayerFive.setBackground(tempDrawable[4]);
            }
            if (tempDrawable2 != null) {
                activity.gameFourTeamTwoPlayerOne.setBackground(tempDrawable2[0]);
                activity.gameFourTeamTwoPlayerTwo.setBackground(tempDrawable2[1]);
                activity.gameFourTeamTwoPlayerThree.setBackground(tempDrawable2[2]);
                activity.gameFourTeamTwoPlayerFour.setBackground(tempDrawable2[3]);
                activity.gameFourTeamTwoPlayerFive.setBackground(tempDrawable2[4]);
            }

            if (tempString != null) {
                activity.gameFourTeamOnePlayerOneName.setText(tempString[0]);
                activity.gameFourTeamOnePlayerTwoName.setText(tempString[1]);
                activity.gameFourTeamOnePlayerThreeName.setText(tempString[2]);
                activity.gameFourTeamOnePlayerFourName.setText(tempString[3]);
                activity.gameFourTeamOnePlayerFiveName.setText(tempString[4]);
                activity.gameFourTeamTwoPlayerOneName.setText(tempString[5]);
                activity.gameFourTeamTwoPlayerTwoName.setText(tempString[6]);
                activity.gameFourTeamTwoPlayerThreeName.setText(tempString[7]);
                activity.gameFourTeamTwoPlayerFourName.setText(tempString[8]);
                activity.gameFourTeamTwoPlayerFiveName.setText(tempString[9]);
            }
            if(!data.getIfThereAreRecentGames()){
                activity.gameFourGameModeTV.setText("NO RECENT GAMES");
                activity.gameFourTitleBar.setBackgroundColor(Color.parseColor("#FF514B51"));
                activity.gameFour.setBackgroundColor(Color.parseColor("#FFB8AEB8"));
            }*/


        }
}
