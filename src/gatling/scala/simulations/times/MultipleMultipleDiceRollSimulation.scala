package simulations.times

import io.gatling.core.Predef._
import io.gatling.core.feeder.BatchableFeederBuilder
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class MultipleMultipleDiceRollSimulation extends Simulation {
  var dices:  Array[String] = Array("d6", "d8", "d10", "d12", "d20")

  val baseUrl: String = "http://localhost:9100/api"
  val httpProtocol: HttpProtocolBuilder = http.baseUrl(baseUrl)

  val feeder: BatchableFeederBuilder[String] = csv("data/multiple_data.csv").random

  val scn: ScenarioBuilder = scenario("Multiple Dice Roll")
    .feed(feeder)
    .exec(
      http("Multiple ${dice} Dice Roll")
        .get("/dices/roll/${times_roll}/${dice}")
    )

  setUp(
    scn.inject(
      rampUsers(100) during(60 seconds)
    ).protocols(httpProtocol)
  ).assertions()
}
