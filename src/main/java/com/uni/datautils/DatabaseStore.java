package com.uni.datautils;

import com.uni.entities.*;
import org.h2.engine.User;

import javax.xml.crypto.Data;
import java.lang.reflect.AnnotatedArrayType;
import java.util.ArrayList;

public class DatabaseStore {

    private ArrayList<Venue> venues = new ArrayList<>();
    private ArrayList<Season> seasons = new ArrayList<>();
    private ArrayList<ImUser> users = new ArrayList<>();
    private ArrayList<Team> teams = new ArrayList<>();
    private ArrayList<Game> games = new ArrayList<>();
    private ArrayList<StatBasketball> statBasketballs = new ArrayList<>();
    private ArrayList<TeamRequest> teamRequests = new ArrayList<>();

    public DatabaseStore(){
        populateUsers();
        populateTeams();
        populateVenues();
        populateSeasons();
        populateGames();
    }

    public ArrayList<Venue> getVenues() {
        return venues;
    }

    public void setVenues(ArrayList<Venue> venues) {
        this.venues = venues;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

    public ArrayList<ImUser> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<ImUser> users) {
        this.users = users;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public ArrayList<StatBasketball> getStatBasketballs() {
        return statBasketballs;
    }

    public void setStatBasketballs(ArrayList<StatBasketball> statBasketballs) {
        this.statBasketballs = statBasketballs;
    }

    public ArrayList<TeamRequest> getTeamRequests() {
        return teamRequests;
    }

    public void setTeamRequests(ArrayList<TeamRequest> teamRequests) {
        this.teamRequests = teamRequests;
    }



    private void populateUsers(){
        // admins
        this.users.add(new ImUser(1, "gatorFan99", "chomp!!", "admin", 0,0,"none",false));
        this.users.add(new ImUser(2, "TopDawg", "sicem", "admin", 0,0,"none",true));

        // referees
        this.users.add(new ImUser(3, "cindy101", "pass123", "referee", 0,0,"none",true));
        this.users.add(new ImUser(4, "mandy101", "pass123", "referee", 0,0,"none",true));

        // captains
        this.users.add(new ImUser(5, "Bobby202", "pass123", "player", 72,195,"none",true));
        this.users.add(new ImUser(6, "Candice202", "pass123", "player", 61,125,"none",true));
        this.users.add(new ImUser(7, "Jessika202", "pass123", "player", 64,130,"none",true));

        // players
        this.users.add(new ImUser(8, "eegdell0", "DyAU3y5hLA", "player", 57, 61, "https://robohash.org/sitnoneos.png?size=150x150&set=set1", true));
        this.users.add(new ImUser(9, "slafoy1", "3hOS1nh", "player", 54, 67, "https://robohash.org/utvitaeofficia.png?size=150x150&set=set1", true));
        this.users.add(new ImUser(10, "mgoodhay2", "FHsI2MXUb", "player", 80, 50, "https://robohash.org/quiaatquevero.png?size=150x150&set=set1", true));
        this.users.add(new ImUser(11, "phanrott3", "rVufrSxG", "player", 49, 73, "https://robohash.org/nemoetdignissimos.png?size=150x150&set=set1", false));
        this.users.add(new ImUser(12, "kskottle4", "fY8zGjrTbczw", "player", 78, 52, "https://robohash.org/vitaereiciendisqui.png?size=150x150&set=set1", false));
        this.users.add(new ImUser(13, "margo23", "eCItXyih", "player", 40, 64, "https://robohash.org/occaecatiautlaborum.png?size=150x150&set=set1", false));
        this.users.add(new ImUser(14, "cmeekings6", "Ft2RahrB", "player", 49, 46, "https://robohash.org/uttemporibuspariatur.png?size=150x150&set=set1", true));
        this.users.add(new ImUser(15, "dmcwilliam7", "XrcsKgdCUtRv", "player", 76, 50, "https://robohash.org/etconsequatursit.png?size=150x150&set=set1", true));
        this.users.add(new ImUser(16, "rscreas8", "gMhQ8W5ZObv", "player", 73, 47, "https://robohash.org/earumnamrepellat.png?size=150x150&set=set1", false));
        this.users.add(new ImUser(17, "cwippermann9", "AFUp4lI0lOMS", "player", 63, 83, "https://robohash.org/etaliquidet.png?size=150x150&set=set1", true));
        this.users.add(new ImUser(18, "mreddicka", "EOG8Mb", "player", 69, 48, "https://robohash.org/illoerrorharum.png?size=150x150&set=set1", false));
        this.users.add(new ImUser(19, "backermannb", "dDk0hto", "player", 62, 86, "https://robohash.org/autipsamcumque.png?size=150x150&set=set1", true));
        this.users.add(new ImUser(20, "chutablec", "swB1brkS2", "player", 71, 79, "https://robohash.org/suntdeseruntcum.png?size=150x150&set=set1", false));
        this.users.add(new ImUser(21, "rlegerwoodd", "uAs0Kf", "player", 61, 43, "https://robohash.org/consequunturvoluptatemfugit.png?size=150x150&set=set1", false));
        this.users.add(new ImUser(22, "bbettensone", "b2fAvPuM", "player", 43, 59, "https://robohash.org/nameteos.png?size=150x150&set=set1", false));
        this.users.add(new ImUser(23, "odowellf", "MOXvFNcR", "player", 46, 80, "https://robohash.org/temporamolestiaesed.png?size=150x150&set=set1", false));
        this.users.add(new ImUser(24, "acolcloughg", "jxvh2PvoO5y", "player", 59, 63, "https://robohash.org/asperioresipsadebitis.png?size=150x150&set=set1", false));
        this.users.add(new ImUser(25, "wpaumierh", "a9myRNBCGR", "player", 77, 75, "https://robohash.org/nequeestaccusantium.png?size=150x150&set=set1", false));
    }

    private void populateTeams(){
        this.teams.add(new Team("Grand Dunk Railroad", 5, "active", "basketball"));
        this.teams.add(new Team("The Ballers", 6, "active", "basketball"));
        this.teams.add(new Team("The Splash", 7, "active", "basketball"));
    }

    private void populateVenues(){
        this.venues.add(new Venue("Main Campus Gym: Court 1"));
        this.venues.add(new Venue("Main Campus Gym: Court 2"));
        this.venues.add(new Venue("Main Campus Gym: Court 3"));
        this.venues.add(new Venue("Satellite Campus Gym: Smith Field"));
        this.venues.add(new Venue("Satellite Campus Gym: Lee Field"));
    }

    private void populateSeasons(){
        this.seasons.add(new Season("Fall 2022 Regular Season Basketball"));
        this.seasons.add(new Season("Fall 2022 Regular Season Softball"));
        this.seasons.add(new Season("Hopping for a Cure Charity Tournament"));
    }

    private void populateGames(){
        this.games.add(new Game(1, "Main Campus Gym: Court 1", "Fall 2022 Regular Season Basketball", "Grand Dunk Railroad", "The Ballers", 0, 0, 1669122000,"scheduled"));
        this.games.add(new Game(2, "Main Campus Gym: Court 2", "Fall 2022 Regular Season Basketball", "The Ballers", "Grand Dunk Railroad", 0, 0, 1669122000,"scheduled"));
        this.games.add(new Game(3, "Main Campus Gym: Court 1", "Fall 2022 Regular Season Basketball", "The Splash", "Grand Dunk Railroad", 0, 0, 1669122000,"scheduled"));
        this.games.add(new Game(4, "Satellite Campus Gym: Smith Field", "Fall 2022 Regular Season Basketball", "The Splash", "The Ballers", 0, 0, 1669215600,"scheduled"));
    }
    // missing StatSoftball
    // missing referee lookup
}
