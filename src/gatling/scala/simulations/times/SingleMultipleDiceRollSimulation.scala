package simulations.times

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.language.postfixOps
import scala.util.Random

class SingleMultipleDiceRollSimulation extends Simulation {
  var dices:  Array[String] = Array("d6", "d8", "d10", "d12", "d20")

  val baseUrl: String = "http://localhost:9100/api"
  val httpProtocol: HttpProtocolBuilder = http.baseUrl(baseUrl)
  val rolls: Int = Random.nextInt(100)
  val chosenDice: String = dices(Random.nextInt(6))

  val scn = scenario("A Single Simple d20 Dice Roll")
    .exec(
      http("Single Multiple Dice Roll")
        .get(s"/dices/roll/${rolls}/${chosenDice}")
    )

  setUp(
    scn.inject(
      atOnceUsers(1)
    ).protocols(httpProtocol)
  ).assertions()
}