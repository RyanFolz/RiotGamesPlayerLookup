package com.ryanfolz.riotgamesapi;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.List;

/**
 * Created by Ivan on 6/24/2015.
 */
public class SwagObAdapter extends BaseAdapter {

    private List<SwagOb> swagList;
    private LayoutInflater layoutInflater;

    public SwagObAdapter(Context ctx, List<SwagOb> swagList) {
        this.swagList = swagList;
        this.layoutInflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return swagList.size();
    }

    @Override
    public Object getItem(int i) {
        return swagList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = layoutInflater.inflate(R.layout.list_layout, null);
        }

        TextView gameOneGameModeTV;
        ImageView gameOneChampion;
        TextView gameOneKillsTV;
        TextView gameOneDeathsTV;
        TextView gameOneAssistsTV;
        TextView gameOneGoldTV;
        TextView gameOneCSTV;
        LinearLayout gameOneTitleBar;
        LinearLayout gameOne;
        TextView gameOneSlashOneTV;
        TextView gameOneSlashTwoTV;
        ImageView gameOneItemOneIV;
        ImageView gameOneSummonerSpellOneIV;
        ImageView gameOneSummonerSpellTwoIV;
        ImageView gameOneItemTwoIV;
        ImageView gameOneItemThreeIV;
        ImageView gameOneItemFourIV;
        ImageView gameOneItemFiveIV;
        ImageView gameOneItemSixIV;
        ImageView gameOneTeamOnePlayerOne;
        ImageView gameOneTeamOnePlayerTwo;
        ImageView gameOneTeamOnePlayerThree;
        ImageView gameOneTeamOnePlayerFour;
        ImageView gameOneTeamOnePlayerFive;
        ImageView gameOneTeamTwoPlayerOne;
        ImageView gameOneTeamTwoPlayerTwo;
        ImageView gameOneTeamTwoPlayerThree;
        ImageView gameOneTeamTwoPlayerFour;
        ImageView gameOneTeamTwoPlayerFive;
        TextView gameOneTeamOnePlayerOneName;
        TextView gameOneTeamOnePlayerTwoName;
        TextView gameOneTeamOnePlayerThreeName;
        TextView gameOneTeamOnePlayerFourName;
        TextView gameOneTeamOnePlayerFiveName;
        TextView gameOneTeamTwoPlayerOneName;
        TextView gameOneTeamTwoPlayerTwoName;
        TextView gameOneTeamTwoPlayerThreeName;
        TextView gameOneTeamTwoPlayerFourName;
        TextView gameOneTeamTwoPlayerFiveName;

        gameOneGameModeTV = (TextView) view.findViewById(R.id.what_type_of_game_game_one);
        gameOneChampion = (ImageView) view.findViewById(R.id.champion_played_game_one);
        gameOneKillsTV = (TextView) view.findViewById(R.id.kills_game_one);
        gameOneDeathsTV = (TextView) view.findViewById(R.id.deaths_game_one);
        gameOneAssistsTV = (TextView) view.findViewById(R.id.assists_game_one);
        gameOneGoldTV = (TextView) view.findViewById(R.id.gold_game_one);
        gameOneCSTV = (TextView) view.findViewById(R.id.cs_game_one);
        gameOneTitleBar = (LinearLayout) view.findViewById(R.id.game_one_title_bar);
        gameOne = (LinearLayout) view.findViewById(R.id.game_one);
        gameOneSlashOneTV = (TextView) view.findViewById(R.id.game_one_slash_one);
        gameOneSlashTwoTV = (TextView) view.findViewById(R.id.game_one_slash_two);
        gameOneItemOneIV = (ImageView) view.findViewById(R.id.game_one_item_one);
        gameOneItemTwoIV = (ImageView) view.findViewById(R.id.game_one_item_two);
        gameOneItemThreeIV = (ImageView) view.findViewById(R.id.game_one_item_three);
        gameOneItemFourIV = (ImageView) view.findViewById(R.id.game_one_item_four);
        gameOneItemFiveIV = (ImageView) view.findViewById(R.id.game_one_item_five);
        gameOneItemSixIV = (ImageView) view.findViewById(R.id.game_one_item_six);
        gameOneTeamOnePlayerOne = (ImageView) view.findViewById(R.id.game_one_team_one_player_one);
        gameOneTeamOnePlayerTwo = (ImageView) view.findViewById(R.id.game_one_team_one_player_two);
        gameOneTeamOnePlayerThree = (ImageView) view.findViewById(R.id.game_one_team_one_player_three);
        gameOneTeamOnePlayerFour = (ImageView) view.findViewById(R.id.game_one_team_one_player_four);
        gameOneTeamOnePlayerFive = (ImageView) view.findViewById(R.id.game_one_team_one_player_five);
        gameOneTeamTwoPlayerOne = (ImageView) view.findViewById(R.id.game_one_team_two_player_one);
        gameOneTeamTwoPlayerTwo = (ImageView) view.findViewById(R.id.game_one_team_two_player_two);
        gameOneTeamTwoPlayerThree = (ImageView) view.findViewById(R.id.game_one_team_two_player_three);
        gameOneTeamTwoPlayerFour = (ImageView) view.findViewById(R.id.game_one_team_two_player_four);
        gameOneTeamTwoPlayerFive = (ImageView) view.findViewById(R.id.game_one_team_two_player_five);
        gameOneTeamOnePlayerOneName = (TextView) view.findViewById(R.id.game_one_team_one_player_one_name);
        gameOneTeamOnePlayerTwoName = (TextView) view.findViewById(R.id.game_one_team_one_player_two_name);
        gameOneTeamOnePlayerThreeName = (TextView) view.findViewById(R.id.game_one_team_one_player_three_name);
        gameOneTeamOnePlayerFourName = (TextView) view.findViewById(R.id.game_one_team_one_player_four_name);
        gameOneTeamOnePlayerFiveName = (TextView) view.findViewById(R.id.game_one_team_one_player_five_name);
        gameOneTeamTwoPlayerOneName = (TextView) view.findViewById(R.id.game_one_team_two_player_one_name);
        gameOneTeamTwoPlayerTwoName = (TextView) view.findViewById(R.id.game_one_team_two_player_two_name);
        gameOneTeamTwoPlayerThreeName = (TextView) view.findViewById(R.id.game_one_team_two_player_three_name);
        gameOneTeamTwoPlayerFourName = (TextView) view.findViewById(R.id.game_one_team_two_player_four_name);
        gameOneTeamTwoPlayerFiveName = (TextView) view.findViewById(R.id.game_one_team_two_player_five_name);
        gameOneSummonerSpellOneIV = (ImageView)view.findViewById(R.id.game_one_summoner_spell_one);
        gameOneSummonerSpellTwoIV = (ImageView)view.findViewById(R.id.game_one_summoner_spell_two);

        gameOneGameModeTV.setText(swagList.get(i).getGameType());
        gameOneChampion.setBackground(swagList.get(i).getChampionPicturePlayed());
        gameOneSlashOneTV.setText(" / ");
        gameOneKillsTV.setText(swagList.get(i).getKills());
        gameOneDeathsTV.setText(swagList.get(i).getDeaths());
        gameOneSlashTwoTV.setText(" / ");
        gameOneAssistsTV.setText(swagList.get(i).getAssists());
        gameOneGoldTV.setText(swagList.get(i).getGold());
        gameOneCSTV.setText(swagList.get(i).getCs());

        if(swagList.get(i).getSummonerSpells()!=null) {
            gameOneSummonerSpellOneIV.setBackground(swagList.get(i).getSummonerSpells()[0]);
            gameOneSummonerSpellTwoIV.setBackground(swagList.get(i).getSummonerSpells()[1]);
        }
        if (swagList.get(i).isWon()) {
            gameOneTitleBar.setBackgroundColor(Color.parseColor("#d604c429"));
            gameOne.setBackgroundColor(Color.parseColor("#4600FF06"));
        }
        if (!swagList.get(i).isWon()) {
            gameOneTitleBar.setBackgroundColor(Color.parseColor("#d6ff0100"));
            gameOne.setBackgroundColor(Color.parseColor("#4fff0100"));
        }

        gameOneItemOneIV.setBackground(swagList.get(i).getItemOne());
        gameOneItemTwoIV.setBackground(swagList.get(i).getItemTwo());
        gameOneItemThreeIV.setBackground(swagList.get(i).getItemThree());
        gameOneItemFourIV.setBackground(swagList.get(i).getItemFour());
        gameOneItemFiveIV.setBackground(swagList.get(i).getItemFive());
        gameOneItemSixIV.setBackground(swagList.get(i).getItemSix());

        if (swagList.get(i).getTempDrawable() != null) {
            gameOneTeamOnePlayerOne.setBackground(swagList.get(i).getTempDrawable()[0]);
            gameOneTeamOnePlayerTwo.setBackground(swagList.get(i).getTempDrawable()[1]);
            gameOneTeamOnePlayerThree.setBackground(swagList.get(i).getTempDrawable()[2]);
            gameOneTeamOnePlayerFour.setBackground(swagList.get(i).getTempDrawable()[3]);
            gameOneTeamOnePlayerFive.setBackground(swagList.get(i).getTempDrawable()[4]);
        }
        if (swagList.get(i).getTempDrawable2() != null) {
            gameOneTeamTwoPlayerOne.setBackground(swagList.get(i).getTempDrawable2()[0]);
            gameOneTeamTwoPlayerTwo.setBackground(swagList.get(i).getTempDrawable2()[1]);
            gameOneTeamTwoPlayerThree.setBackground(swagList.get(i).getTempDrawable2()[2]);
            gameOneTeamTwoPlayerFour.setBackground(swagList.get(i).getTempDrawable2()[3]);
            gameOneTeamTwoPlayerFive.setBackground(swagList.get(i).getTempDrawable2()[4]);
        }

        if (swagList.get(i).getTempString() != null) {
            gameOneTeamOnePlayerOneName.setText(swagList.get(i).getTempString()[0]);
            gameOneTeamOnePlayerTwoName.setText(swagList.get(i).getTempString()[1]);
            gameOneTeamOnePlayerThreeName.setText(swagList.get(i).getTempString()[2]);
            gameOneTeamOnePlayerFourName.setText(swagList.get(i).getTempString()[3]);
            gameOneTeamOnePlayerFiveName.setText(swagList.get(i).getTempString()[4]);
            gameOneTeamTwoPlayerOneName.setText(swagList.get(i).getTempString()[5]);
            gameOneTeamTwoPlayerTwoName.setText(swagList.get(i).getTempString()[6]);
            gameOneTeamTwoPlayerThreeName.setText(swagList.get(i).getTempString()[7]);
            gameOneTeamTwoPlayerFourName.setText(swagList.get(i).getTempString()[8]);
            gameOneTeamTwoPlayerFiveName.setText(swagList.get(i).getTempString()[9]);
        }
        if(!swagList.get(i).getData().getIfThereAreRecentGames()){
            gameOneGameModeTV.setText("NO RECENT GAMES");
            gameOneTitleBar.setBackgroundColor(Color.parseColor("#FF514B51"));
            gameOne.setBackgroundColor(Color.parseColor("#FFB8AEB8"));
        }

        return view;
    }
}
