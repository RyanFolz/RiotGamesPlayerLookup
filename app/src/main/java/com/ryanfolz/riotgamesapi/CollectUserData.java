package com.ryanfolz.riotgamesapi;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by Ryan Folz on 5/17/2015.
 */
public class CollectUserData {

    private JSONObject jsonSummonerInfo, jsonSummonerStats, jsonSummonerRankedStats, jsonSummonerMostRecentGame, jsonSummonerLeagueInfo, jsonSummonerRecentGames;
    private String region, summonerName, summonerNameForURI;
    private long summmonerId = 0;
    private Context context;

    public CollectUserData(){

    }

    public void setRegion(String a) {
        region = a.toLowerCase();
    }

    public void setSummonerName(String a) {
        summonerName = a.toLowerCase();
        summonerName = summonerName.replace(" ", "");
        summonerNameForURI = summonerName.replace(" ", "%20");
    }

    public String getSummonerName() throws JSONException {
        String name = "";
        if (jsonSummonerInfo != null) {
            if (jsonSummonerInfo.getJSONObject(summonerName).has("name")) {
                name = jsonSummonerInfo.getJSONObject(summonerName).getString("name");
            }
            return name;
        }
        return name;
    }

    public String getTier() throws JSONException {
        String tier = "";
        String tempString = "";
        if (jsonSummonerLeagueInfo != null) {
            tempString = jsonSummonerLeagueInfo.toString();
            if (!tempString.equals("{}")) {
                JSONArray array = jsonSummonerLeagueInfo.getJSONArray(summmonerId + "");
                JSONObject thing2 = array.getJSONObject(0);
                tier = thing2.getString("tier");
                tier = tier.toLowerCase();
                if (!tier.equals("")) {
                    tier = tier.substring(0, 1).toUpperCase() + tier.substring(1);
                }
                String division = "";
                JSONArray array2 = thing2.getJSONArray("entries");
                JSONObject thing3 = array2.getJSONObject(0);
                division = thing3.getString("division");
                tier = tier + " " + division;
                return tier;
            }
        }
        return "Unranked";
    }

    public boolean canIContinue() throws JSONException {
        if (jsonSummonerInfo== null) {
            return false;
        }
        return true;
    }

    public String getLeaguePoints() throws JSONException {
        String leaguePoints = "";
        String tempString = "";
        if (jsonSummonerLeagueInfo != null) {
            tempString = jsonSummonerLeagueInfo.toString();
            if (!tempString.equals("{}")) {
                JSONArray array = jsonSummonerLeagueInfo.getJSONArray(summmonerId + "");
                JSONObject thing2 = array.getJSONObject(0);
                JSONArray array2 = thing2.getJSONArray("entries");
                JSONObject thing3 = array2.getJSONObject(0);
                int thing = thing3.getInt("leaguePoints");
                leaguePoints = leaguePoints + thing + "LP";
                return leaguePoints;
            }
        }
        return "";
    }

    public String getMundaneCurrentLeague() throws JSONException {
        String tier = "";
        String tempString = "";
        if (jsonSummonerLeagueInfo != null) {
            tempString = jsonSummonerLeagueInfo.toString();
            if (!tempString.equals("{}")) {
                JSONArray array = jsonSummonerLeagueInfo.getJSONArray(summmonerId + "");
                JSONObject thing2 = array.getJSONObject(0);
                tier = thing2.getString("tier");
                return tier;
            }
        }
        return "UNRANKED";
    }

    public int getWins() throws JSONException {
        int wins = 0;
        if(jsonSummonerStats!= null) {
            JSONArray array = jsonSummonerStats.getJSONArray("playerStatSummaries");
            for (int i = 0; i < array.length(); i++) {
                String playerStatSummary = array.getJSONObject(i).getString("playerStatSummaryType");
                if (playerStatSummary.equals("RankedSolo5x5")) {
                    wins = array.getJSONObject(i).getInt("wins");
                }
            }
            return wins;
        }
        return 0;
    }

    public int getLosses() throws JSONException {
        int losses = 0;
        if(jsonSummonerStats!= null) {
            JSONArray array = jsonSummonerStats.getJSONArray("playerStatSummaries");
            for (int i = 0; i < array.length(); i++) {
                String playerStatSummary = array.getJSONObject(i).getString("playerStatSummaryType");
                if (playerStatSummary.equals("RankedSolo5x5")) {
                    losses = array.getJSONObject(i).getInt("losses");
                }
            }
        }
        return losses;
    }

    public double getRatio() throws JSONException {
        double wins = 0;
        double losses = 0;
        if(jsonSummonerStats!= null) {
            JSONArray array = jsonSummonerStats.getJSONArray("playerStatSummaries");
            for (int i = 0; i < array.length(); i++) {
                String playerStatSummary = array.getJSONObject(i).getString("playerStatSummaryType");
                if (playerStatSummary.equals("RankedSolo5x5")) {
                    wins = array.getJSONObject(i).getInt("wins");
                    losses = array.getJSONObject(i).getInt("losses");
                }
            }
        }
        double total = wins + losses;
        double ratio = (wins / total) * 100;

        return round(ratio, 1);
    }

    public String getRankOfLastSeason() throws JSONException {
        int participantID = 1;
        String rankLastSeason = "Unranked";
        if (jsonSummonerMostRecentGame != null) {
            JSONArray array = jsonSummonerMostRecentGame.getJSONArray("participantIdentities");
            for (int i = 0; i < array.length(); i++) {
                if (array.getJSONObject(i).getJSONObject("player").getInt("summonerId") == summmonerId) {
                    participantID = array.getJSONObject(i).getInt("participantId");
                }
            }

            rankLastSeason = jsonSummonerMostRecentGame.getJSONArray("participants").getJSONObject(participantID - 1).getString("highestAchievedSeasonTier");
        }
        rankLastSeason = rankLastSeason.toLowerCase();
        rankLastSeason = rankLastSeason.substring(0, 1).toUpperCase() + rankLastSeason.substring(1);
        return rankLastSeason;
    }

    public String getGameType(int a) throws JSONException {
        String type = "Normal";
        if (jsonSummonerRecentGames != null) {
            JSONArray array = jsonSummonerRecentGames.getJSONArray("games");
            if ((a - 1) < array.length()) {
                JSONObject object = array.getJSONObject(a - 1);
                if(object.has("subType")) {
                    type = object.getString("subType");
                    if (type.equals("NORMAL")){
                        type = "Normal";
                    }
                    if (type.equals("RANKED_SOLO_5x5")){
                        type = "Ranked Solo 5v5";
                    }
                    if (type.equals("RANKED_PREMADE_3x3")){
                        type = "Ranked Premade 3v3";
                    }
                    if (type.equals("RANKED_PREMADE_5x5")){
                        type = "Ranked Premade 5v5";
                    }
                    if (type.equals("ODIN_UNRANKED")){
                        type = "Crystal Star";
                    }
                    if (type.equals("RANKED_TEAM_3x3")){
                        type = "Ranked Team 3v3";
                    }
                    if (type.equals("RANKED_TEAM_5x5")){
                        type = "Ranked Team 5v5";
                    }
                    if (type.equals("NORMAL_3x3")){
                        type = "Normal 3v3";
                    }
                    if (type.equals("BOT_3x3")){
                        type = "Bot 3v3";
                    }
                    if (type.equals("CAP_5x5")){
                        type = "Team Builder 5v5";
                    }
                    if (type.equals("ARAM_UNRANKED_5x5")){
                        type = "ARAM";
                    }
                    if (type.equals("ONEFORALL_5x5")){
                        type = "One For All";
                    }
                    if (type.equals("FIRSTBLOOD_1x1")){
                        type = "1v1";
                    }
                    if (type.equals("SR_6x6")){
                        type = "Hexakill";
                    }
                    if (type.equals("URF")){
                        type = "Ultra Rapid Fire";
                    }
                    if (type.equals("URF_BOT")){
                        type = "Bot Ultra Rapid Fire";
                    }
                    if (type.equals("NIGHTMARE_BOT")){
                        type = "Doom Bots";
                    }
                    if (type.equals("ASCENSION")){
                        type = "Ascension";
                    }
                    if (type.equals("HEXAKILL")){
                        type = "Hexakill";
                    }
                    if (type.equals("KING_PORO")){
                        type = "Poro King";
                    }
                    if (type.equals("COUNTER_PICK")){
                        type = "Counter Pick";
                    }
                }
            }
        }
        return type;
    }

    public Drawable getChampionPicturePlayed(int a, Resources res) throws JSONException, IOException {
        Drawable drawable = null;
        Bitmap bd = null;
        int champId = 1;
        if (jsonSummonerRecentGames != null) {
            JSONArray array = jsonSummonerRecentGames.getJSONArray("games");
            if ((a) <= array.length()) {
                JSONObject object = array.getJSONObject(a - 1);

                champId = object.getInt("championId");
            }
        }
        if(getChampionIconFromNumberKey(champId, res)!= null){
            return getChampionIconFromNumberKey(champId, res);
        }
        StringBuilder url = new StringBuilder("https://global.api.pvp.net/api/lol/static-data/" + region + "/v1.2/champion/" + champId + "?api_key=9ed10e48-7ac1-422e-b3d0-fd75dedcc3b2");
        HttpGet get = new HttpGet(url.toString());
        HttpResponse r = new DefaultHttpClient().execute(get);
        HttpEntity e = r.getEntity();
        String data = EntityUtils.toString(e);
        JSONObject jsonObject = new JSONObject(data);
        String champ = jsonObject.getString("key");
        try {
            URL url2 = new URL("http://ddragon.leagueoflegends.com/cdn/5.9.1/img/champion/" + champ + ".png");
            InputStream is = new BufferedInputStream(url2.openStream());
            bd = BitmapFactory.decodeStream(is);
        } catch (Exception e2) {
        }
        if (bd != null) {
            drawable = new BitmapDrawable(bd);
        }
        return drawable;
    }

    public Drawable getItemFromMatchHistory(int game, int slot, Resources res) throws JSONException, IOException {
        Drawable drawable = null;
        Bitmap bd = null;
        int itemId = 0;
        int realSlot = slot - 1;
        if (jsonSummonerRecentGames != null) {
            JSONArray array = jsonSummonerRecentGames.getJSONArray("games");
            if ((game) <= array.length()) {
                JSONObject object = array.getJSONObject(game - 1);
                JSONObject object2 = object.getJSONObject("stats");
                if (object2.has("item" + realSlot)) {
                    itemId = object2.getInt("item" + realSlot);
                } else {
                    itemId = 0;
                }
            }
        }
        if(itemId == 3751){
            return res.getDrawable(R.drawable.bamiitem);
        }
        if(itemId == 3285 || itemId == 3286){
            return res.getDrawable(R.drawable.ludensquare);
        }
        if (itemId != 0) {
            try {
                URL url2 = new URL("http://ddragon.leagueoflegends.com/cdn/5.2.1/img/item/" + itemId + ".png");
                InputStream is = new BufferedInputStream(url2.openStream());
                bd = BitmapFactory.decodeStream(is);
            } catch (Exception e2) {
            }
            if (bd != null) {
                drawable = new BitmapDrawable(bd);
                return drawable;
            }
        }
        Drawable d = res.getDrawable(R.drawable.noitem);
        return d;

    }

    public Drawable[] getTeamPlayerChampionIcon(int game, int side, Resources res) throws JSONException, IOException {
        Drawable[] drawable = new Drawable[]{null, null, null, null, null};
        Bitmap bd = null;
        int i = 0;
        if (jsonSummonerRecentGames != null) {
            JSONArray array = jsonSummonerRecentGames.getJSONArray("games");
            JSONObject object = array.getJSONObject(game - 1);
            int teamId = object.getInt("teamId");
            if (teamId == side) {
                int championId = object.getInt("championId");
                Drawable tempDrawable = getChampionIcon(championId, res);
                if (tempDrawable != null) {
                    drawable[i] = tempDrawable;
                    i++;
                }
            }
            JSONArray array2 = object.getJSONArray("fellowPlayers");
            for (int j = 0; j < array2.length(); j++) {
                JSONObject object2 = array2.getJSONObject(j);
                if (object2.getInt("teamId") == side) {
                    int championId = object2.getInt("championId");
                    Drawable tempDrawable = getChampionIcon(championId, res);
                    if (tempDrawable != null) {
                        drawable[i] = tempDrawable;
                        i++;
                    }
                }
            }
        }

        return drawable;
    }

    public String[] getTeamPlayerName(int game) throws JSONException, IOException {
        Bundle bundle;
        long[] summonerIds = new long[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int i = 0;
        int k = 5;
        if (jsonSummonerRecentGames != null) {
            JSONArray array = jsonSummonerRecentGames.getJSONArray("games");
            JSONObject object = array.getJSONObject(game - 1);
            int teamId = object.getInt("teamId");
            if (teamId == 100) {
                summonerIds[i] = summmonerId;
                i++;
            }
            if (teamId == 200) {
                summonerIds[k] = summmonerId;
                k++;
            }
            JSONArray array2 = object.getJSONArray("fellowPlayers");
            for (int j = 0; j < array2.length(); j++) {
                JSONObject object2 = array2.getJSONObject(j);
                if (object2.getInt("teamId") == 100) {
                    int summonerId2 = object2.getInt("summonerId");
                    summonerIds[i] = summonerId2;
                    i++;
                }
                if (object2.getInt("teamId") == 200) {
                    int summonerId2 = object2.getInt("summonerId");
                    summonerIds[k] = summonerId2;
                    k++;
                }
            }
            JSONObject namesObject = getJsonNamesFromIds(summonerIds);
            String[] names = new String[]{"", "", "", "", "", "", "", "", "", ""};
            int j = 0;
            JSONObject tempObject;
            for (int h = 0; h < 10; h++) {
                if (summonerIds[j] != 0) {
                    if (namesObject.getJSONObject(summonerIds[j] + "").getString("name") != null) {
                        names[j] = namesObject.getJSONObject(summonerIds[j] + "").getString("name");
                        j++;
                    }
                } else {
                    names[j] = "";
                    j++;
                }
            }
            return names;
        }
        String[] names = new String[]{"", "", "", "", "", "", "", "", "", ""};
        return names;
    }

    public int getKills(int a) throws JSONException {
        int kills = 0;
        if (jsonSummonerRecentGames != null) {
            JSONArray array = jsonSummonerRecentGames.getJSONArray("games");
            JSONObject object = array.getJSONObject(a - 1);
            JSONObject object2 = object.getJSONObject("stats");
            if (object2.has("championsKilled")) {
                kills = object2.getInt("championsKilled");
            } else {
                kills = 0;
            }
        }
        return kills;
    }

    public int getDeaths(int a) throws JSONException {
        int deaths = 0;
        if (jsonSummonerRecentGames != null) {
            JSONArray array = jsonSummonerRecentGames.getJSONArray("games");
            JSONObject object = array.getJSONObject(a - 1);
            JSONObject object2 = object.getJSONObject("stats");
            if (object2.has("numDeaths")) {
                deaths = object2.getInt("numDeaths");
            } else {
                deaths = 0;
            }
        }
        return deaths;

    }

    public int getAssists(int a) throws JSONException {
        int assists = 0;
        if (jsonSummonerRecentGames != null) {
            JSONArray array = jsonSummonerRecentGames.getJSONArray("games");
            JSONObject object = array.getJSONObject(a - 1);
            JSONObject object2 = object.getJSONObject("stats");
            if (object2.has("assists")) {
                assists = object2.getInt("assists");
            } else {
                assists = 0;
            }
        }
        return assists;
    }

    public double getGold(int a) throws JSONException {
        int goldEarned = 0;
        if (jsonSummonerRecentGames != null) {
            JSONArray array = jsonSummonerRecentGames.getJSONArray("games");
            JSONObject object = array.getJSONObject(a - 1);
            JSONObject object2 = object.getJSONObject("stats");
            if (object2.has("goldEarned")) {
                goldEarned = object2.getInt("goldEarned");
            } else {
                goldEarned = 0;
            }
        }
        double temp = (double) goldEarned;
        temp = temp / 1000;
        temp = round(temp, 1);
        return temp;
    }

    public int getMinionsKilled(int a) throws JSONException {
        int minionsKilled = 0;
        if (jsonSummonerRecentGames != null) {
            JSONArray array = jsonSummonerRecentGames.getJSONArray("games");
            JSONObject object = array.getJSONObject(a - 1);
            JSONObject object2 = object.getJSONObject("stats");
            if (object2.has("minionsKilled")) {
                minionsKilled = object2.getInt("minionsKilled");
            } else {
                minionsKilled = 0;
            }
        }
        return minionsKilled;
    }

    public boolean isGameWon(int a) throws JSONException {
        boolean gameWon = true;
        if (jsonSummonerRecentGames != null) {
            JSONArray array = jsonSummonerRecentGames.getJSONArray("games");
            JSONObject object = array.getJSONObject(a - 1);
            JSONObject object2 = object.getJSONObject("stats");
            if (object2.has("win")) {
                gameWon = object2.getBoolean("win");
            } else {
                gameWon = false;
            }
        }
        return gameWon;
    }

    public Drawable[] getSummonerSpells(int game) throws JSONException, IOException {
        Drawable drawable[] = new Drawable[]{null, null};
        Bitmap bd = null;
        int spell1 = 0, spell2 = 0;
        if (jsonSummonerRecentGames != null) {
            JSONArray array = jsonSummonerRecentGames.getJSONArray("games");
            if ((game) <= array.length()) {
                JSONObject object = array.getJSONObject(game - 1);
                if (object.has("spell1")) {
                    spell1 = object.getInt("spell1");
                }
                if (object.has("spell2")) {
                    spell2 = object.getInt("spell2");
                }
            }
        }
        StringBuilder url = new StringBuilder("https://global.api.pvp.net/api/lol/static-data/" + region + "/v1.2/summoner-spell/" + spell1 + "?api_key=9ed10e48-7ac1-422e-b3d0-fd75dedcc3b2");
        HttpGet get = new HttpGet(url.toString());
        HttpResponse r = new DefaultHttpClient().execute(get);
        HttpEntity e = r.getEntity();
        String data = EntityUtils.toString(e);
        JSONObject jsonObject = new JSONObject(data);
        String key = jsonObject.getString("key");
        try {
            URL url2 = new URL("http://ddragon.leagueoflegends.com/cdn/5.2.1/img/spell/" + key + ".png");
            InputStream is = new BufferedInputStream(url2.openStream());
            bd = BitmapFactory.decodeStream(is);
        } catch (Exception e2) {
        }
        if (bd != null) {
            drawable[0] = new BitmapDrawable(bd);
        }
        url = new StringBuilder("https://global.api.pvp.net/api/lol/static-data/" + region + "/v1.2/summoner-spell/" + spell2 + "?api_key=9ed10e48-7ac1-422e-b3d0-fd75dedcc3b2");
        get = new HttpGet(url.toString());
        r = new DefaultHttpClient().execute(get);
        e = r.getEntity();
        data = EntityUtils.toString(e);
        jsonObject = new JSONObject(data);
        key = jsonObject.getString("key");
        try {
            URL url2 = new URL("http://ddragon.leagueoflegends.com/cdn/5.2.1/img/spell/" + key + ".png");
            InputStream is = new BufferedInputStream(url2.openStream());
            bd = BitmapFactory.decodeStream(is);
        } catch (Exception e2) {
        }
        if (bd != null) {
            drawable[1] = new BitmapDrawable(bd);
        }
        return drawable;
    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public Drawable getChampionIcon(int champId, Resources res) throws IOException, JSONException {
        Drawable d = null;
        Bitmap bd = null;
        StringBuilder url = new StringBuilder("https://global.api.pvp.net/api/lol/static-data/" + region + "/v1.2/champion/" + champId + "?api_key=9ed10e48-7ac1-422e-b3d0-fd75dedcc3b2");
        HttpGet get = new HttpGet(url.toString());
        HttpResponse r = new DefaultHttpClient().execute(get);
        HttpEntity e = r.getEntity();
        String data = EntityUtils.toString(e);
        JSONObject jsonObject = new JSONObject(data);
        String champ = jsonObject.getString("key");
        if(champ.equals("Ekko")){
            return res.getDrawable(R.drawable.ekkosquare);
        } else if(champ.equals("TahmKench")){
            return res.getDrawable(R.drawable.tahmkenchsquare);
        }else{
            try {
                URL url2 = new URL("http://ddragon.leagueoflegends.com/cdn/5.9.1/img/champion/" + champ + ".png");
                InputStream is = new BufferedInputStream(url2.openStream());
                bd = BitmapFactory.decodeStream(is);
            } catch (Exception ignored) {
            }
            if (bd != null) {
                d = new BitmapDrawable(bd);
            }
            return d;
        }
    }

    public int getNormalWins() throws JSONException {
        int wins = 0;
        if(jsonSummonerStats!= null) {
            JSONArray array = jsonSummonerStats.getJSONArray("playerStatSummaries");
            for (int i = 0; i < array.length(); i++) {
                String playerStatSummary = array.getJSONObject(i).getString("playerStatSummaryType");
                if (playerStatSummary.equals("Unranked")) {
                    wins = array.getJSONObject(i).getInt("wins");
                }
            }
            return wins;
        }
        return wins;
    }

    public int getNormalNeutralMonterKills() throws JSONException {
        int neutralMonsterKills = 0;
        if(jsonSummonerStats!= null) {
            JSONArray array = jsonSummonerStats.getJSONArray("playerStatSummaries");
            for (int i = 0; i < array.length(); i++) {
                String playerStatSummary = array.getJSONObject(i).getString("playerStatSummaryType");
                if (playerStatSummary.equals("Unranked")) {
                    neutralMonsterKills = array.getJSONObject(i).getJSONObject("aggregatedStats").getInt("totalNeutralMinionsKilled");
                }
            }
            return neutralMonsterKills;
        }
        return neutralMonsterKills;
    }

    public int getNormalMinionKills() throws JSONException {
        int minionKills = 0;
        if(jsonSummonerStats!= null) {
            JSONArray array = jsonSummonerStats.getJSONArray("playerStatSummaries");
            for (int i = 0; i < array.length(); i++) {
                String playerStatSummary = array.getJSONObject(i).getString("playerStatSummaryType");
                if (playerStatSummary.equals("Unranked")) {
                    minionKills = array.getJSONObject(i).getJSONObject("aggregatedStats").getInt("totalMinionKills");
                }
            }
            return minionKills;
        }
        return minionKills;
    }

    public int getNormalChampionKills() throws JSONException {
        int championKills = 0;
        if(jsonSummonerStats!= null) {
            JSONArray array = jsonSummonerStats.getJSONArray("playerStatSummaries");
            for (int i = 0; i < array.length(); i++) {
                String playerStatSummary = array.getJSONObject(i).getString("playerStatSummaryType");
                if (playerStatSummary.equals("Unranked")) {
                    championKills = array.getJSONObject(i).getJSONObject("aggregatedStats").getInt("totalChampionKills");
                }
            }
            return championKills;
        }
        return championKills;
    }

    public int getNormalAssists() throws JSONException {
        int assists = 0;
        if(jsonSummonerStats!= null) {
            JSONArray array = jsonSummonerStats.getJSONArray("playerStatSummaries");
            for (int i = 0; i < array.length(); i++) {
                String playerStatSummary = array.getJSONObject(i).getString("playerStatSummaryType");
                if (playerStatSummary.equals("Unranked")) {
                    assists = array.getJSONObject(i).getJSONObject("aggregatedStats").getInt("totalAssists");
                }
            }
            return assists;
        }
        return assists;
    }

    public int getNormalTurretKills() throws JSONException {
        int turretKills = 0;
        if(jsonSummonerStats!= null) {
            JSONArray array = jsonSummonerStats.getJSONArray("playerStatSummaries");
            for (int i = 0; i < array.length(); i++) {
                String playerStatSummary = array.getJSONObject(i).getString("playerStatSummaryType");
                if (playerStatSummary.equals("Unranked")) {
                    turretKills = array.getJSONObject(i).getJSONObject("aggregatedStats").getInt("totalTurretsKilled");
                }
            }
            return turretKills;
        }
        return turretKills;
    }

    public boolean getIfThereAreRecentGames() {
        if (jsonSummonerRecentGames == null) {
            return false;
        }
        return true;
    }

    public void setUpTheJSONs() throws IOException, JSONException, URISyntaxException {
        try {
            jsonSummonerInfo = getJsonSummonerInfo();
        }catch (Exception e){

        }
        if (canIContinue()) {
            jsonSummonerStats = getJsonSummaryStats();
            jsonSummonerLeagueInfo = getJsonSummonerLeagueInfo();
            jsonSummonerRecentGames = getJsonSummonerRecentGames();
            //jsonSummonerRankedStats = getJsonRankedStats();
            jsonSummonerMostRecentGame = getJsonSummonerMostRecentGame();
        }
    }

    public JSONObject getJsonNamesFromIds(long[] ids) throws IOException, JSONException {
        if(ids.length == 12){
            long[] tempArray = new long[]{ids[0], ids[1], ids[2], ids[3], ids[4], ids[6], ids[7], ids[8], ids[9],ids[10]};
            StringBuilder url = new StringBuilder("https://" + region + ".api.pvp.net/api/lol/" + region + "/v1.4/summoner/" + tempArray[0] + ",%20" + tempArray[1] + ",%20" + tempArray[2] + ",%20" + tempArray[3] + ",%20" + tempArray[4] + ",%20" + tempArray[5] + ",%20" + tempArray[6] + ",%20" + tempArray[7] + ",%20" + tempArray[8] + ",%20" + tempArray[9] + "?api_key=9ed10e48-7ac1-422e-b3d0-fd75dedcc3b2");
            HttpGet get = new HttpGet(url.toString());
            HttpResponse r = new DefaultHttpClient().execute(get);
            HttpEntity e = r.getEntity();
            String data = EntityUtils.toString(e);
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject;
        }else {
            StringBuilder url = new StringBuilder("https://" + region + ".api.pvp.net/api/lol/" + region + "/v1.4/summoner/" + ids[0] + ",%20" + ids[1] + ",%20" + ids[2] + ",%20" + ids[3] + ",%20" + ids[4] + ",%20" + ids[5] + ",%20" + ids[6] + ",%20" + ids[7] + ",%20" + ids[8] + ",%20" + ids[9] + "?api_key=9ed10e48-7ac1-422e-b3d0-fd75dedcc3b2");
            HttpGet get = new HttpGet(url.toString());
            HttpResponse r = new DefaultHttpClient().execute(get);
            HttpEntity e = r.getEntity();
            String data = EntityUtils.toString(e);
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject;
        }
    }

    public JSONObject getJsonSummonerInfo() throws IOException, JSONException, URISyntaxException, IllegalArgumentException {
        jsonSummonerInfo = null;
        if (!summonerName.contains("\n")) {
            StringBuilder url = new StringBuilder("https://" + region + ".api.pvp.net/api/lol/" + region + "/v1.4/summoner/by-name/" + summonerNameForURI + "?api_key=9ed10e48-7ac1-422e-b3d0-fd75dedcc3b2");
            HttpGet get = new HttpGet(url.toString());
            HttpResponse r = new DefaultHttpClient().execute(get);
            HttpEntity e = r.getEntity();
            String data = EntityUtils.toString(e);
            JSONObject jsonObject = new JSONObject(data);
            summmonerId = jsonObject.getJSONObject(summonerName).getLong("id");
            return jsonObject;
        }
        return null;
    }

    public JSONObject getJsonSummaryStats() throws URISyntaxException, IOException, JSONException {
        StringBuilder url = new StringBuilder("https://" + region + ".api.pvp.net/api/lol/" + region + "/v1.3/stats/by-summoner/" + summmonerId + "/summary?season=SEASON2015&api_key=9ed10e48-7ac1-422e-b3d0-fd75dedcc3b2");
        HttpGet get = new HttpGet(url.toString());
        HttpResponse r = new DefaultHttpClient().execute(get);
        HttpEntity e = r.getEntity();
        String data = EntityUtils.toString(e);
        JSONObject jsonObject = new JSONObject(data);
        return jsonObject;
    }

    public JSONObject getJsonRankedStats() throws URISyntaxException, IOException, JSONException {
        StringBuilder url = new StringBuilder("https://" + region + ".api.pvp.net/api/lol/" + region + "/v1.3/stats/by-summoner/" + summmonerId + "/ranked?season=SEASON2015&api_key=9ed10e48-7ac1-422e-b3d0-fd75dedcc3b2");
        HttpGet get = new HttpGet(url.toString());
        HttpResponse r = new DefaultHttpClient().execute(get);
        HttpEntity e = r.getEntity();
        String data = EntityUtils.toString(e);
        JSONObject jsonObject = new JSONObject(data);
        return jsonObject;
    }


    public JSONObject getJsonSummonerMostRecentGame() throws IOException, JSONException {
        long matchId;
        if(jsonSummonerRecentGames != null){
            matchId = jsonSummonerRecentGames.getJSONArray("games").getJSONObject(0).getLong("gameId");
        }else{
            matchId = 0;
        }

        StringBuilder url = new StringBuilder("https://" + region + ".api.pvp.net/api/lol/" + region + "/v2.2/match/" + matchId + "?api_key=9ed10e48-7ac1-422e-b3d0-fd75dedcc3b2");
        HttpGet get = new HttpGet(url.toString());
        HttpResponse r = new DefaultHttpClient().execute(get);
        HttpEntity e = r.getEntity();
        String data = EntityUtils.toString(e);
        JSONObject jsonObject = new JSONObject(data);
        return jsonObject;
    }

    public JSONObject getJsonSummonerLeagueInfo() throws IOException, JSONException {
        StringBuilder url = new StringBuilder("https://" + region + ".api.pvp.net/api/lol/" + region + "/v2.5/league/by-summoner/" + summmonerId + "/entry?api_key=9ed10e48-7ac1-422e-b3d0-fd75dedcc3b2");
        HttpGet get = new HttpGet(url.toString());
        HttpResponse r = new DefaultHttpClient().execute(get);
        HttpEntity e = r.getEntity();
        String data = EntityUtils.toString(e);
        if (!data.equals("")) {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject;
        }
        return new JSONObject("{}");
    }

    public JSONObject getJsonSummonerRecentGames() throws IOException, JSONException {
        StringBuilder url = new StringBuilder("https://" + region + ".api.pvp.net/api/lol/" + region + "/v1.3/game/by-summoner/" + summmonerId + "/recent?api_key=9ed10e48-7ac1-422e-b3d0-fd75dedcc3b2");
        HttpGet get = new HttpGet(url.toString());
        HttpResponse r = new DefaultHttpClient().execute(get);
        HttpEntity e = r.getEntity();
        String data = EntityUtils.toString(e);
        JSONObject jsonObject = new JSONObject(data);
        return jsonObject;
    }


    public long getSummonerId() {
        return summmonerId;
    }

    public Drawable getChampionIconFromNumberKey(int key, Resources res) {
        if(key==223){
            return res.getDrawable(R.drawable.tahmkenchsquare);
        }
        if(key==266){
            return res.getDrawable(R.drawable.aatroxsquare);
        }
        else if(key==412){
            return res.getDrawable(R.drawable.threshsquare);
        }
        else if(key==23){
            return res.getDrawable(R.drawable.tryndameresquare);
        }
        else if(key==79){
            return res.getDrawable(R.drawable.gragassquare);
        }
        else if(key==69){
            return res.getDrawable(R.drawable.cassiopeiasquare);
        }
        else if(key==13){
            return res.getDrawable(R.drawable.ryzesquare);
        }
        else if(key==78){
            return res.getDrawable(R.drawable.poppysquare);
        }
        else if(key==14){
            return res.getDrawable(R.drawable.sionsquare);
        }
        else if(key==1){
            return res.getDrawable(R.drawable.anniesquare);
        }
        else if(key==111){
            return res.getDrawable(R.drawable.nautilussquare);
        }
        else if(key==43){
            return res.getDrawable(R.drawable.karmasquare);
        }
        else if(key==99){
            return res.getDrawable(R.drawable.luxsquare);
        }
        else if(key==103){
            return res.getDrawable(R.drawable.ahrisquare);
        }
        else if(key==2){
            return res.getDrawable(R.drawable.olafsquare);
        }
        else if(key==112){
            return res.getDrawable(R.drawable.viktorsquare);
        }
        else if(key==34){
            return res.getDrawable(R.drawable.aniviasquare);
        }
        else if(key==86){
            return res.getDrawable(R.drawable.garensquare);
        }
        else if(key==27){
            return res.getDrawable(R.drawable.singedsquare);
        }
        else if(key==127){
            return res.getDrawable(R.drawable.lissandrasquare);
        }
        else if(key==57){
            return res.getDrawable(R.drawable.maokaisquare);
        }
        else if(key==25){
            return res.getDrawable(R.drawable.morganasquare);
        }
        else if(key==28){
            return res.getDrawable(R.drawable.evelynnsquare);
        }
        else if(key==105){
            return res.getDrawable(R.drawable.fizzsquare);
        }
        else if(key==74){
            return res.getDrawable(R.drawable.heimerdingersquare);
        }
        else if(key==238){
            return res.getDrawable(R.drawable.zedsquare);
        }
        else if(key==68){
            return res.getDrawable(R.drawable.rumblesquare);
        }
        else if(key==37){
            return res.getDrawable(R.drawable.sonasquare);
        }
        else if(key==82){
            return res.getDrawable(R.drawable.mordekaisersquare);
        }
        else if(key==96){
            return res.getDrawable(R.drawable.kogmawsquare);
        }
        else if(key==55){
            return res.getDrawable(R.drawable.katarinasquare);
        }
        else if(key==117){
            return res.getDrawable(R.drawable.lulusquare);
        }
        else if(key==22){
            return res.getDrawable(R.drawable.ashesquare);
        }
        else if(key==30){
            return res.getDrawable(R.drawable.karthussquare);
        }
        else if(key==12){
            return res.getDrawable(R.drawable.alistarsquare);
        }
        else if(key==122){
            return res.getDrawable(R.drawable.dariussquare);
        }
        else if(key==67){
            return res.getDrawable(R.drawable.vaynesquare);
        }
        else if(key==77){
            return res.getDrawable(R.drawable.udyrsquare);
        }
        else if(key==110){
            return res.getDrawable(R.drawable.varussquare);
        }
        else if(key==89){
            return res.getDrawable(R.drawable.leonasquare);
        }
        else if(key==126){
            return res.getDrawable(R.drawable.jaycesquare);
        }
        else if(key==134){
            return res.getDrawable(R.drawable.syndrasquare);
        }
        else if(key==80){
            return res.getDrawable(R.drawable.pantheonsquare);
        }
        else if(key==92){
            return res.getDrawable(R.drawable.rivensquare);
        }
        else if(key==121){
            return res.getDrawable(R.drawable.khazixsquare);
        }
        else if(key==42){
            return res.getDrawable(R.drawable.corkisquare);
        }
        else if(key==51){
            return res.getDrawable(R.drawable.caitlynsquare);
        }
        else if(key==268){
            return res.getDrawable(R.drawable.azirsquare);
        }
        else if(key==76){
            return res.getDrawable(R.drawable.nidaleesquare);
        }
        else if(key==3){
            return res.getDrawable(R.drawable.galiosquare);
        }
        else if(key==85){
            return res.getDrawable(R.drawable.kennensquare);
        }
        else if(key==45){
            return res.getDrawable(R.drawable.veigarsquare);
        }
        else if(key==432){
            return res.getDrawable(R.drawable.bardsquare);
        }
        else if(key==150){
            return res.getDrawable(R.drawable.gnarsquare);
        }
        else if(key==104){
            return res.getDrawable(R.drawable.gravessquare);
        }
        else if(key==90){
            return res.getDrawable(R.drawable.malzaharsquare);
        }
        else if(key==254){
            return res.getDrawable(R.drawable.visquare);
        }
        else if(key==10){
            return res.getDrawable(R.drawable.kaylesquare);
        }
        else if(key==39){
            return res.getDrawable(R.drawable.ireliasquare);
        }
        else if(key==64){
            return res.getDrawable(R.drawable.leesinsquare);
        }
        else if(key==60){
            return res.getDrawable(R.drawable.elisesquare);
        }
        else if(key==106){
            return res.getDrawable(R.drawable.volibearsquare);
        }
        else if(key==20){
            return res.getDrawable(R.drawable.nunusquare);
        }
        else if(key==4){
            return res.getDrawable(R.drawable.twistedfatesquare);
        }
        else if(key==24){
            return res.getDrawable(R.drawable.jaxsquare);
        }
        else if(key==102){
            return res.getDrawable(R.drawable.shyvanasquare);
        }
        else if(key==429){
            return res.getDrawable(R.drawable.kalistasquare);
        }
        else if(key==36){
            return res.getDrawable(R.drawable.drmundosquare);
        }
        else if(key==63){
            return res.getDrawable(R.drawable.brandsquare);
        }
        else if(key==131){
            return res.getDrawable(R.drawable.dianasquare);
        }
        else if(key==113){
            return res.getDrawable(R.drawable.sejuanisquare);
        }
        else if(key==8){
            return res.getDrawable(R.drawable.vladimirsquare);
        }
        else if(key==154){
            return res.getDrawable(R.drawable.zacsquare);
        }
        else if(key==421){
            return res.getDrawable(R.drawable.relsaisquare);
        }
        else if(key==133){
            return res.getDrawable(R.drawable.quinnsquare);
        }
        else if(key==84){
            return res.getDrawable(R.drawable.akalisquare);
        }
        else if(key==18){
            return res.getDrawable(R.drawable.tristanasquare);
        }
        else if(key==120){
            return res.getDrawable(R.drawable.hecarimsquare);
        }
        else if(key==15){
            return res.getDrawable(R.drawable.sivirsquare);
        }
        else if(key==236){
            return res.getDrawable(R.drawable.luciansquare);
        }
        else if(key==107){
            return res.getDrawable(R.drawable.rengarsquare);
        }
        else if(key==19){
            return res.getDrawable(R.drawable.warwicksquare);
        }
        else if(key==72){
            return res.getDrawable(R.drawable.skarnersquare);
        }
        else if(key==54){
            return res.getDrawable(R.drawable.malphitesquare);
        }
        else if(key==157){
            return res.getDrawable(R.drawable.yasuosquare);
        }
        else if(key==101){
            return res.getDrawable(R.drawable.xerathsquare);
        }
        else if(key==17){
            return res.getDrawable(R.drawable.teemosquare);
        }
        else if(key==75){
            return res.getDrawable(R.drawable.nasussquare);
        }
        else if(key==58){
            return res.getDrawable(R.drawable.renektonsquare);
        }
        else if(key==119){
            return res.getDrawable(R.drawable.dravensquare);
        }
        else if(key==35){
            return res.getDrawable(R.drawable.shacosquare);
        }
        else if(key==50){
            return res.getDrawable(R.drawable.swainsquare);
        }
        else if(key==115){
            return res.getDrawable(R.drawable.ziggssquare);
        }
        else if(key==91){
            return res.getDrawable(R.drawable.talonsquare);
        }
        else if(key==40){
            return res.getDrawable(R.drawable.jannasquare);
        }
        else if(key==245){
            return res.getDrawable(R.drawable.ekkosquare);
        }
        else if(key==61){
            return res.getDrawable(R.drawable.oriannasquare);
        }
        else if(key==114){
            return res.getDrawable(R.drawable.fiorasquare);
        }
        else if(key==9){
            return res.getDrawable(R.drawable.fiddlestickssquare);
        }
        else if(key==33){
            return res.getDrawable(R.drawable.rammussquare);
        }
        else if(key==31){
            return res.getDrawable(R.drawable.chogathsquare);
        }
        else if(key==7){
            return res.getDrawable(R.drawable.leblancsquare);
        }
        else if(key==16){
            return res.getDrawable(R.drawable.sorakasquare);
        }
        else if(key==26){
            return res.getDrawable(R.drawable.zileansquare);
        }
        else if(key==56){
            return res.getDrawable(R.drawable.nocturnesquare);
        }
        else if(key==222){
            return res.getDrawable(R.drawable.jinxsquare);
        }
        else if(key==83){
            return res.getDrawable(R.drawable.yoricksquare);
        }
        else if(key==6){
            return res.getDrawable(R.drawable.urgotsquare);
        }
        else if(key==21){
            return res.getDrawable(R.drawable.missfortunesquare);
        }
        else if(key==62){
            return res.getDrawable(R.drawable.wukongsquare);
        }
        else if(key==53){
            return res.getDrawable(R.drawable.blitzcranksquare);
        }
        else if(key==98){
            return res.getDrawable(R.drawable.shensquare);
        }
        else if(key==201){
            return res.getDrawable(R.drawable.braumsquare);
        }
        else if(key==5){
            return res.getDrawable(R.drawable.xinzhaosquare);
        }
        else if(key==29){
            return res.getDrawable(R.drawable.twitchsquare);
        }
        else if(key==11){
            return res.getDrawable(R.drawable.masteryisquare);
        }
        else if(key==44){
            return res.getDrawable(R.drawable.taricsquare);
        }
        else if(key==32){
            return res.getDrawable(R.drawable.amumusquare);
        }
        else if(key==41){
            return res.getDrawable(R.drawable.gangplanksquare);
        }
        else if(key==48){
            return res.getDrawable(R.drawable.trundlesquare);
        }
        else if(key==38){
            return res.getDrawable(R.drawable.kassadinsquare);
        }
        else if(key==161){
            return res.getDrawable(R.drawable.velkozsquare);
        }
        else if(key==143){
            return res.getDrawable(R.drawable.zyrasquare);
        }
        else if(key==267){
            return res.getDrawable(R.drawable.namisquare);
        }
        else if(key==59){
            return res.getDrawable(R.drawable.jarvansquare);
        }
        else if(key==81){
            return res.getDrawable(R.drawable.ezrealsquare);
        }
        else if(key ==203){
            return res.getDrawable(R.drawable.kindredsquare);
        }
        else if(key ==420){
            return res.getDrawable(R.drawable.illaoisquare);
        }
        else if(key ==202){
            return res.getDrawable(R.drawable.jhinsquare);
        }
        else if(key ==136){
            return res.getDrawable(R.drawable.solsquare);
        }
        else if(key ==163){
            return res.getDrawable(R.drawable.taliyahsquare);
        }
        else if(key ==240){
            return res.getDrawable(R.drawable.kledsquare);
        }
        else {
            return res.getDrawable(R.drawable.unknownsquare);
        }
    }

}
