package org.heroesunlimited

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class SimpleDiceRollSimulation extends Simulation {
  var baseUrl = "http://localhost:8100"
  val sessionHeaders = Map("Content-Type" -> "application/json")

  val httpProtocol = http
    .baseUrl(baseUrl)
    .headers(sessionHeaders)

  val csvPath = getClass.getResource("/dicedata/simpledice.csv")
  val csvFeeder = csv(csvPath.getPath)

/*  val scn = scenario("Hero Simulation Scenario")
    .feed(csvFeeder.random)
    .exec(
      http("Simple Build a Hero")
        .get("/api/players/name/${player_name}"))
    .pause(10)
    .exec(
      http("Building with Character Class")
        .get("/api/players/name/${player_name}/class/${player_class}")
    )
    .pause(10)
    .exec(
      http("Building a Complete Character")
        .get("/api/players/name/${player_name}/class/${player_class}/race/${player_race}/gender/${player_gender}")
    )


  setUp(
    scn.inject(
      rampUsersPerSec(50) to 100 during (2 minutes)
    ).protocols(httpProtocol))*/
}
