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
public class BackgroundStuffGameNine extends AsyncTask<Data,Integer, Long> {


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
            dataClass = params[0];
            swagObsArray = params[0].swagObsArray;

            try {
                gameType = data.getGameType(9);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                championPicturePlayed = data.getChampionPicturePlayed(9, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                kills = data.getKills(9) + "";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                deaths = data.getDeaths(9) +"";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                assists = data.getAssists(9) + "";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                gold = data.getGold(9) + " K";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                cs = " " + data.getMinionsKilled(9) + " CS";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            summonerSpells = null;
            try {
                summonerSpells = data.getSummonerSpells(9);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            won = true;
            try {
                won = data.isGameWon(9);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                itemOne =data.getItemFromMatchHistory(9, 1, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemTwo =data.getItemFromMatchHistory(9, 2, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemThree =data.getItemFromMatchHistory(9, 3, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemFour =data.getItemFromMatchHistory(9, 4, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemFive =data.getItemFromMatchHistory(9, 5, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                itemSix =data.getItemFromMatchHistory(9, 6, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempDrawable = null;
            tempDrawable2 = null;
            try {
                tempDrawable = data.getTeamPlayerChampionIcon(9, 100, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                tempDrawable2 = data.getTeamPlayerChampionIcon(9, 200, activity.getResources());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempString = null;
            try {
                tempString = data.getTeamPlayerName(9);
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

            swagObsArray[8] = new SwagOb(gameType, championPicturePlayed, kills, deaths, summonerSpells,
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
            /*activity.gameNineGameModeTV.setText(gameType);
            activity.gameNineChampion.setBackground(championPicturePlayed);
            activity.gameNineSlashOneTV.setText(" / ");
            activity.gameNineKillsTV.setText(kills);
            activity.gameNineDeathsTV.setText(deaths);
            activity.gameNineSlashTwoTV.setText(" / ");
            activity.gameNineAssistsTV.setText(assists);
            activity.gameNineGoldTV.setText(gold);
            activity.gameNineCSTV.setText(cs);

            if(summonerSpells!=null) {
                activity.gameNineSummonerSpellOneIV.setBackground(summonerSpells[0]);
                activity.gameNineSummonerSpellTwoIV.setBackground(summonerSpells[1]);
            }
            if (won) {
                activity.gameNineTitleBar.setBackgroundColor(Color.parseColor("#d604c429"));
                activity.gameNine.setBackgroundColor(Color.parseColor("#4600FF06"));
            }
            if (!won) {
                activity.gameNineTitleBar.setBackgroundColor(Color.parseColor("#d6ff0100"));
                activity.gameNine.setBackgroundColor(Color.parseColor("#4fff0100"));
            }

            activity.gameNineItemOneIV.setBackground(itemOne);
            activity.gameNineItemTwoIV.setBackground(itemTwo);
            activity.gameNineItemThreeIV.setBackground(itemThree);
            activity.gameNineItemFourIV.setBackground(itemFour);
            activity.gameNineItemFiveIV.setBackground(itemFive);
            activity.gameNineItemSixIV.setBackground(itemSix);

            if (tempDrawable != null) {
                activity.gameNineTeamOnePlayerOne.setBackground(tempDrawable[0]);
                activity.gameNineTeamOnePlayerTwo.setBackground(tempDrawable[1]);
                activity.gameNineTeamOnePlayerThree.setBackground(tempDrawable[2]);
                activity.gameNineTeamOnePlayerFour.setBackground(tempDrawable[3]);
                activity.gameNineTeamOnePlayerFive.setBackground(tempDrawable[4]);
            }
            if (tempDrawable2 != null) {
                activity.gameNineTeamTwoPlayerOne.setBackground(tempDrawable2[0]);
                activity.gameNineTeamTwoPlayerTwo.setBackground(tempDrawable2[1]);
                activity.gameNineTeamTwoPlayerThree.setBackground(tempDrawable2[2]);
                activity.gameNineTeamTwoPlayerFour.setBackground(tempDrawable2[3]);
                activity.gameNineTeamTwoPlayerFive.setBackground(tempDrawable2[4]);
            }

            if (tempString != null) {
                activity.gameNineTeamOnePlayerOneName.setText(tempString[0]);
                activity.gameNineTeamOnePlayerTwoName.setText(tempString[1]);
                activity.gameNineTeamOnePlayerThreeName.setText(tempString[2]);
                activity.gameNineTeamOnePlayerFourName.setText(tempString[3]);
                activity.gameNineTeamOnePlayerFiveName.setText(tempString[4]);
                activity.gameNineTeamTwoPlayerOneName.setText(tempString[5]);
                activity.gameNineTeamTwoPlayerTwoName.setText(tempString[6]);
                activity.gameNineTeamTwoPlayerThreeName.setText(tempString[7]);
                activity.gameNineTeamTwoPlayerFourName.setText(tempString[8]);
                activity.gameNineTeamTwoPlayerFiveName.setText(tempString[9]);
            }
            if(!data.getIfThereAreRecentGames()){
                activity.gameNineGameModeTV.setText("NO RECENT GAMES");
                activity.gameNineTitleBar.setBackgroundColor(Color.parseColor("#FF514B51"));
                activity.gameNine.setBackgroundColor(Color.parseColor("#FFB8AEB8"));
            }
*/

        }
}
