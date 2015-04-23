package scala.berlin.spray

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import spray.routing.SimpleRoutingApp

object Main extends App with SimpleRoutingApp {
  implicit val system = ActorSystem("my-system")

  val config = ConfigFactory.load()
  val route = config.getString("spark.route")
  val port = config.getInt("spark.port")

  startServer(interface = "0.0.0.0", port = port) {
    path(route) {
      get {
        complete {
          <h1>Welcome at webtrekk</h1>
        }
      }
    }
  }
}
