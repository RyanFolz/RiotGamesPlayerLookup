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
public class BackgroundStuffGameSeven extends AsyncTask<Data,Integer, Long> {


    private CollectUserData data;
    private SearchPlayerFragment activity;
    private List<SwagOb> swagObList;

    private String gameType;
    private Drawable championPicturePlayed;
    private String kills;
    private String deaths;
    private Drawable[] summonerSpells;
    public Data dataClass;
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
                gameType = data.getGameType(7);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                championPicturePlayed = data.getChampionPicturePlayed(7, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                kills = data.getKills(7) + "";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                deaths = data.getDeaths(7) +"";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                assists = data.getAssists(7) + "";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                gold = data.getGold(7) + " K";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                cs = " " + data.getMinionsKilled(7) + " CS";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            summonerSpells = null;
            try {
                summonerSpells = data.getSummonerSpells(7);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            won = true;
            try {
                won = data.isGameWon(7);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                itemOne =data.getItemFromMatchHistory(7, 1, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemTwo =data.getItemFromMatchHistory(7, 2, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemThree =data.getItemFromMatchHistory(7, 3, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemFour =data.getItemFromMatchHistory(7, 4, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemFive =data.getItemFromMatchHistory(7, 5, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemSix =data.getItemFromMatchHistory(7, 6, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempDrawable = null;
            tempDrawable2 = null;
            try {
                tempDrawable = data.getTeamPlayerChampionIcon(7, 100, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                tempDrawable2 = data.getTeamPlayerChampionIcon(7, 200, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempString = null;
            try {
                tempString = data.getTeamPlayerName(7);
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

            swagObsArray[6] = new SwagOb(gameType, championPicturePlayed, kills, deaths, summonerSpells,
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
           /* activity.gameSevenGameModeTV.setText(gameType);
            activity.gameSevenChampion.setBackground(championPicturePlayed);
            activity.gameSevenSlashOneTV.setText(" / ");
            activity.gameSevenKillsTV.setText(kills);
            activity.gameSevenDeathsTV.setText(deaths);
            activity.gameSevenSlashTwoTV.setText(" / ");
            activity.gameSevenAssistsTV.setText(assists);
            activity.gameSevenGoldTV.setText(gold);
            activity.gameSevenCSTV.setText(cs);

            if(summonerSpells!=null) {
                activity.gameSevenSummonerSpellOneIV.setBackground(summonerSpells[0]);
                activity.gameSevenSummonerSpellTwoIV.setBackground(summonerSpells[1]);
            }
            if (won) {
                activity.gameSevenTitleBar.setBackgroundColor(Color.parseColor("#d604c429"));
                activity.gameSeven.setBackgroundColor(Color.parseColor("#4600FF06"));
            }
            if (!won) {
                activity.gameSevenTitleBar.setBackgroundColor(Color.parseColor("#d6ff0100"));
                activity.gameSeven.setBackgroundColor(Color.parseColor("#4fff0100"));
            }

            activity.gameSevenItemOneIV.setBackground(itemOne);
            activity.gameSevenItemTwoIV.setBackground(itemTwo);
            activity.gameSevenItemThreeIV.setBackground(itemThree);
            activity.gameSevenItemFourIV.setBackground(itemFour);
            activity.gameSevenItemFiveIV.setBackground(itemFive);
            activity.gameSevenItemSixIV.setBackground(itemSix);

            if (tempDrawable != null) {
                activity.gameSevenTeamOnePlayerOne.setBackground(tempDrawable[0]);
                activity.gameSevenTeamOnePlayerTwo.setBackground(tempDrawable[1]);
                activity.gameSevenTeamOnePlayerThree.setBackground(tempDrawable[2]);
                activity.gameSevenTeamOnePlayerFour.setBackground(tempDrawable[3]);
                activity.gameSevenTeamOnePlayerFive.setBackground(tempDrawable[4]);
            }
            if (tempDrawable2 != null) {
                activity.gameSevenTeamTwoPlayerOne.setBackground(tempDrawable2[0]);
                activity.gameSevenTeamTwoPlayerTwo.setBackground(tempDrawable2[1]);
                activity.gameSevenTeamTwoPlayerThree.setBackground(tempDrawable2[2]);
                activity.gameSevenTeamTwoPlayerFour.setBackground(tempDrawable2[3]);
                activity.gameSevenTeamTwoPlayerFive.setBackground(tempDrawable2[4]);
            }

            if (tempString != null) {
                activity.gameSevenTeamOnePlayerOneName.setText(tempString[0]);
                activity.gameSevenTeamOnePlayerTwoName.setText(tempString[1]);
                activity.gameSevenTeamOnePlayerThreeName.setText(tempString[2]);
                activity.gameSevenTeamOnePlayerFourName.setText(tempString[3]);
                activity.gameSevenTeamOnePlayerFiveName.setText(tempString[4]);
                activity.gameSevenTeamTwoPlayerOneName.setText(tempString[5]);
                activity.gameSevenTeamTwoPlayerTwoName.setText(tempString[6]);
                activity.gameSevenTeamTwoPlayerThreeName.setText(tempString[7]);
                activity.gameSevenTeamTwoPlayerFourName.setText(tempString[8]);
                activity.gameSevenTeamTwoPlayerFiveName.setText(tempString[9]);
            }
            if(!data.getIfThereAreRecentGames()){
                activity.gameSevenGameModeTV.setText("NO RECENT GAMES");
                activity.gameSevenTitleBar.setBackgroundColor(Color.parseColor("#FF514B51"));
                activity.gameSeven.setBackgroundColor(Color.parseColor("#FFB8AEB8"));
            }*/


        }
}
