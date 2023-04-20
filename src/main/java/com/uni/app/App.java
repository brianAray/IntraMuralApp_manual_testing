package com.uni.app;

import com.uni.controllers.*;
import com.uni.daos.*;
import com.uni.datautils.ConnectionUtil;
import com.uni.datautils.Database;
import com.uni.datautils.DatabaseStore;
import com.uni.services.*;
import io.javalin.Javalin;
import org.h2.tools.RunScript;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class App {

    public static void main(String[] args) throws IOException {

//        ConnectionUtil.populateH2Database(ConnectionUtil.getConnection());
        Connection conn = ConnectionUtil.getConnection();

        try {
            RunScript.execute(conn, new FileReader("src/main/resources/startup.sql"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
        });

        // database
        HashMap<String, DatabaseStore> database = Database.getDatabase();

        //DAOs
        GameDAO gameDAO = GameDAO.getSingleton(database);
        SeasonDAO seasonDAO = SeasonDAO.getSingleton(database);
        TeamDAO teamDAO = TeamDAO.getSingleton(database);
        TeamRequestDAO teamRequestDAO = TeamRequestDAO.getSingleton(database);
        UserDAO userDAO = UserDAO.getSingleton(database);
        VenueDAO venueDAO = VenueDAO.getSingleton(database);
        StatBasketballDAO statBasketballDAO = StatBasketballDAO.getSingleton(database);
        GameRequestDAO gameRequestDAO = GameRequestDAO.getSingleton(database);

        //Services
        RegistrationService registrationService = new RegistrationServiceImpl(teamDAO,userDAO,teamRequestDAO);
        SchedulingService schedulingService = new SchedulingServiceImpl(venueDAO,gameDAO,seasonDAO);
        StatisticsService statisticsService = new StatisticsServiceImpl(statBasketballDAO,userDAO);
        GameRequestService gameRequestService = new GameRequestImpl(gameRequestDAO);

        //Controllers
        SchedulingController schedulingController = new SchedulingController(schedulingService);
        TeamController teamController = new TeamController(registrationService);
        UserController userController = new UserController(registrationService);
        TeamRequestController teamRequestController = new TeamRequestController(registrationService);
        StatisticsController statisticsController = new StatisticsController(statisticsService);
        GameRequestController gameRequestController = new GameRequestController(gameRequestService);

        app.get("/", ctx -> {
            String devId = (ctx.queryParam("dev"));
            System.out.println(devId);
            ctx.header("dev", devId);
            ctx.status(200);
        });

        app.before("/login", ctx -> {
           String devId = ctx.header("dev");
            System.out.println(devId);
        });
        app.before("/logout", ctx -> {
            String devId = ctx.header("dev");
            System.out.println(devId);
        });
        app.before("/users", ctx -> {
            String devId = ctx.header("dev");
            System.out.println(devId);
        });
        app.before("/teams", ctx -> {
            String devId = ctx.header("dev");
            System.out.println(devId);
        });
        app.before("/venues", ctx -> {
            String devId = ctx.header("dev");
            System.out.println(devId);
        });
        app.before("/games", ctx -> {
            String devId = ctx.header("dev");
            System.out.println(devId);
        });
        app.before("/seasons", ctx -> {
            String devId = ctx.header("dev");
            System.out.println(devId);
        });
        app.before("/teamrequests", ctx -> {
            String devId = ctx.header("dev");
            System.out.println(devId);
        });
        app.before("/playercards", ctx -> {
            String devId = ctx.header("dev");
            System.out.println(devId);
        });
        app.before("/statbasketball", ctx -> {
            String devId = ctx.header("dev");
            System.out.println(devId);
        });
        app.before("/referee-and-games-lookup", ctx -> {
            String devId = ctx.header("dev");
            System.out.println(devId);
        });
        app.before("/game-requests", ctx -> {
            String devId = ctx.header("dev");
            System.out.println(devId);
        });

        app.post("/login", userController::login);
        app.post("/logout", userController::logout);
        app.post("/users", userController::register);
        app.put("/users", userController::update);
        app.get("/users", userController::retrieveAllUsers);
        app.patch("/users/{id}/role", userController::updateRole);
        app.get("/teams/{teamname}/users", userController::retrievePlayersByTeam);

        app.post("/teams", teamController::registerTeam);
        app.get("/teams", teamController::retrieveAllTeams);
        app.get("/teams/{teamname}", teamController::retrieveTeamByTeamName);

        app.get("/venues", schedulingController::getAllVenues);

        app.post("/games", schedulingController::scheduleGame);
        app.get("/games", schedulingController::getAllGames);

        app.get("/seasons", schedulingController::getAllSeasons);
        app.post("/seasons", schedulingController::scheduleSeason);

        app.get("/teamrequests", teamRequestController::getAllTeamRequests);
        app.post("/teamrequests", teamRequestController::createTeamRequest);
        app.patch("/teamrequests/{id}/approve", teamRequestController::approveRequest);
        app.patch("/teamrequests/{id}/deny", teamRequestController::denyRequest);

        app.get("/playercards/{id}", statisticsController::getPLayerCardById);
        app.get("/games/{gameid}/statbasketball", statisticsController::getAllBasketballStatsByGameId);
        app.post("/statbasketball", statisticsController::addOrUpdateBasketballStat);

        app.get("/referee-and-games-lookup", gameRequestController::retrieveAllRefereeAndGames);
        app.post("/game-requests/apply", gameRequestController::createGameRequest);
        app.delete("/game-requests/delete", gameRequestController::deleteGameRequest);

        app.start(7000);

    }
}
