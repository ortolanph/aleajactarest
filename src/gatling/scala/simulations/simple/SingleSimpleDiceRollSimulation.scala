package simulations.simple;

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class SingleSimpleDiceRollSimulation extends Simulation {
  val baseUrl: String = "http://localhost:9100/api"

  val httpProtocol: HttpProtocolBuilder = http.baseUrl(baseUrl)

  val scn = scenario("A Single Simple d20 Dice Roll")
    .exec(
      http("Single d20 Dice Roll Run")
        .get("/dices/roll/d20")
    )

  setUp(
    scn.inject(
      rampUsers(100) during (1 minute)
    ).protocols(httpProtocol)
  ).assertions()
}