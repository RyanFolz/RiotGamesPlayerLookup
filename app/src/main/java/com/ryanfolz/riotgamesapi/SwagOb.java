package com.ryanfolz.riotgamesapi;

import android.graphics.drawable.Drawable;

/**
 * Created by Ivan on 6/24/2015.
 */
public class SwagOb {

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
    private SearchPlayerFragment activty;
    private CollectUserData data;

    public SwagOb(String gameType, Drawable championPicturePlayed, String kills, String deaths, Drawable[] summonerSpells,
                  String assists, String cs, String gold, boolean won, Drawable itemOne, Drawable itemTwo, Drawable itemThree, Drawable itemFour,
                  Drawable itemFive, Drawable itemSix, Drawable[] tempDrawable, Drawable[] tempDrawable2, String[] tempString, SearchPlayerFragment activity, CollectUserData data){
        this.gameType = gameType;
        this.championPicturePlayed = championPicturePlayed;
        this.kills = kills;
        this.deaths = deaths;
        this.summonerSpells = summonerSpells;
        this.itemFive = itemFive;
        this.itemTwo = itemTwo;
        this.itemOne = itemOne;
        this.itemThree = itemThree;
        this.itemFour = itemFour;
        this.itemSix = itemSix;
        this.assists = assists;
        this.cs = cs;
        this.gold = gold;
        this.won = won;
        this.tempDrawable = tempDrawable;
        this.tempDrawable2 = tempDrawable2;
        this.tempString = tempString;
        this.activty = activity;
        this.data = data;
    }

    public CollectUserData getData(){
        return data;
    }

    public SearchPlayerFragment getActivty(){
        return activty;
    }
    public void setActivty(SearchPlayerFragment activty){
        this.activty = activty;
    }
    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public Drawable getChampionPicturePlayed() {
        return championPicturePlayed;
    }

    public void setChampionPicturePlayed(Drawable championPicturePlayed) {
        this.championPicturePlayed = championPicturePlayed;
    }

    public String getKills() {
        return kills;
    }

    public void setKills(String kills) {
        this.kills = kills;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public Drawable[] getSummonerSpells() {
        return summonerSpells;
    }

    public void setSummonerSpells(Drawable[] summonerSpells) {
        this.summonerSpells = summonerSpells;
    }

    public String getAssists() {
        return assists;
    }

    public void setAssists(String assists) {
        this.assists = assists;
    }

    public String getCs() {
        return cs;
    }

    public void setCs(String cs) {
        this.cs = cs;
    }

    public String getGold() {
        return gold;
    }

    public void setGold(String gold) {
        this.gold = gold;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public Drawable getItemOne() {
        return itemOne;
    }

    public void setItemOne(Drawable itemOne) {
        this.itemOne = itemOne;
    }

    public Drawable getItemTwo() {
        return itemTwo;
    }

    public void setItemTwo(Drawable itemTwo) {
        this.itemTwo = itemTwo;
    }

    public Drawable getItemThree() {
        return itemThree;
    }

    public void setItemThree(Drawable itemThree) {
        this.itemThree = itemThree;
    }

    public Drawable getItemFour() {
        return itemFour;
    }

    public void setItemFour(Drawable itemFour) {
        this.itemFour = itemFour;
    }

    public Drawable getItemFive() {
        return itemFive;
    }

    public void setItemFive(Drawable itemFive) {
        this.itemFive = itemFive;
    }

    public Drawable getItemSix() {
        return itemSix;
    }

    public void setItemSix(Drawable itemSix) {
        this.itemSix = itemSix;
    }

    public Drawable[] getTempDrawable() {
        return tempDrawable;
    }

    public void setTempDrawable(Drawable[] tempDrawable) {
        this.tempDrawable = tempDrawable;
    }

    public Drawable[] getTempDrawable2() {
        return tempDrawable2;
    }

    public void setTempDrawable2(Drawable[] tempDrawable2) {
        this.tempDrawable2 = tempDrawable2;
    }

    public String[] getTempString() {
        return tempString;
    }

    public void setTempString(String[] tempString) {
        this.tempString = tempString;
    }
}
