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
public class BackgroundStuffGameFive extends AsyncTask<Data,Integer, Long> {


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
                gameType = data.getGameType(5);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                championPicturePlayed = data.getChampionPicturePlayed(5, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                kills = data.getKills(5) + "";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                deaths = data.getDeaths(5) +"";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                assists = data.getAssists(5) + "";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                gold = data.getGold(5) + " K";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                cs = " " + data.getMinionsKilled(5) + " CS";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            summonerSpells = null;
            try {
                summonerSpells = data.getSummonerSpells(5);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            won = true;
            try {
                won = data.isGameWon(5);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                itemOne =data.getItemFromMatchHistory(5, 1, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemTwo =data.getItemFromMatchHistory(5, 2, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemThree =data.getItemFromMatchHistory(5, 3, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemFour =data.getItemFromMatchHistory(5, 4, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemFive =data.getItemFromMatchHistory(5, 5, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemSix =data.getItemFromMatchHistory(5, 6, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempDrawable = null;
            tempDrawable2 = null;
            try {
                tempDrawable = data.getTeamPlayerChampionIcon(5, 100,activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                tempDrawable2 = data.getTeamPlayerChampionIcon(5, 200, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempString = null;
            try {
                tempString = data.getTeamPlayerName(5);
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

            swagObsArray[4] = new SwagOb(gameType, championPicturePlayed, kills, deaths, summonerSpells,
                    assists, cs, gold, won, itemOne, itemTwo, itemThree, itemFour,
                    itemFive, itemSix, tempDrawable, tempDrawable2, tempString, activity, data);

/*            swagObList.add(new SwagOb(gameType, championPicturePlayed, kills, deaths, summonerSpells,
                    assists, cs, gold, won, itemOne, itemTwo, itemThree, itemFour,
                    itemFive, itemSix, tempDrawable, tempDrawable2, tempString, activity, data));*/
            /*activity.gameFiveGameModeTV.setText(gameType);
            activity.gameFiveChampion.setBackground(championPicturePlayed);
            activity.gameFiveSlashOneTV.setText(" / ");
            activity.gameFiveKillsTV.setText(kills);
            activity.gameFiveDeathsTV.setText(deaths);
            activity.gameFiveSlashTwoTV.setText(" / ");
            activity.gameFiveAssistsTV.setText(assists);
            activity.gameFiveGoldTV.setText(gold);
            activity.gameFiveCSTV.setText(cs);

            if(summonerSpells!=null) {
                activity.gameFiveSummonerSpellOneIV.setBackground(summonerSpells[0]);
                activity.gameFiveSummonerSpellTwoIV.setBackground(summonerSpells[1]);
            }
            if (won) {
                activity.gameFiveTitleBar.setBackgroundColor(Color.parseColor("#d604c429"));
                activity.gameFive.setBackgroundColor(Color.parseColor("#4600FF06"));
            }
            if (!won) {
                activity.gameFiveTitleBar.setBackgroundColor(Color.parseColor("#d6ff0100"));
                activity.gameFive.setBackgroundColor(Color.parseColor("#4fff0100"));
            }

            activity.gameFiveItemOneIV.setBackground(itemOne);
            activity.gameFiveItemTwoIV.setBackground(itemTwo);
            activity.gameFiveItemThreeIV.setBackground(itemThree);
            activity.gameFiveItemFourIV.setBackground(itemFour);
            activity.gameFiveItemFiveIV.setBackground(itemFive);
            activity.gameFiveItemSixIV.setBackground(itemSix);

            if (tempDrawable != null) {
                activity.gameFiveTeamOnePlayerOne.setBackground(tempDrawable[0]);
                activity.gameFiveTeamOnePlayerTwo.setBackground(tempDrawable[1]);
                activity.gameFiveTeamOnePlayerThree.setBackground(tempDrawable[2]);
                activity.gameFiveTeamOnePlayerFour.setBackground(tempDrawable[3]);
                activity.gameFiveTeamOnePlayerFive.setBackground(tempDrawable[4]);
            }
            if (tempDrawable2 != null) {
                activity.gameFiveTeamTwoPlayerOne.setBackground(tempDrawable2[0]);
                activity.gameFiveTeamTwoPlayerTwo.setBackground(tempDrawable2[1]);
                activity.gameFiveTeamTwoPlayerThree.setBackground(tempDrawable2[2]);
                activity.gameFiveTeamTwoPlayerFour.setBackground(tempDrawable2[3]);
                activity.gameFiveTeamTwoPlayerFive.setBackground(tempDrawable2[4]);
            }

            if (tempString != null) {
                activity.gameFiveTeamOnePlayerOneName.setText(tempString[0]);
                activity.gameFiveTeamOnePlayerTwoName.setText(tempString[1]);
                activity.gameFiveTeamOnePlayerThreeName.setText(tempString[2]);
                activity.gameFiveTeamOnePlayerFourName.setText(tempString[3]);
                activity.gameFiveTeamOnePlayerFiveName.setText(tempString[4]);
                activity.gameFiveTeamTwoPlayerOneName.setText(tempString[5]);
                activity.gameFiveTeamTwoPlayerTwoName.setText(tempString[6]);
                activity.gameFiveTeamTwoPlayerThreeName.setText(tempString[7]);
                activity.gameFiveTeamTwoPlayerFourName.setText(tempString[8]);
                activity.gameFiveTeamTwoPlayerFiveName.setText(tempString[9]);
            }
            if(!data.getIfThereAreRecentGames()){
                activity.gameFiveGameModeTV.setText("NO RECENT GAMES");
                activity.gameFiveTitleBar.setBackgroundColor(Color.parseColor("#FF514B51"));
                activity.gameFive.setBackgroundColor(Color.parseColor("#FFB8AEB8"));
            }*/


        }
}
