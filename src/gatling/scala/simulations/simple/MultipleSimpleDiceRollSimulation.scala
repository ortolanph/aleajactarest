package simulations.simple

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class MultipleSimpleDiceRollSimulation extends Simulation {
  val baseUrl: String = "http://localhost:9100/api"

  val httpProtocol: HttpProtocolBuilder = http.baseUrl(baseUrl)

  val feeder = csv("data/simple_data.csv").random

  val scn = scenario("A Single Simple d20 Dice Roll")
    .feed(feeder)
    .exec(
      http("Single ${dice} Dice Roll Run")
        .get("/dices/roll/${dice}")
    )

  setUp(
    scn.inject(
      rampUsers(100) during(60 seconds)
    ).protocols(httpProtocol)
  ).assertions()
}
