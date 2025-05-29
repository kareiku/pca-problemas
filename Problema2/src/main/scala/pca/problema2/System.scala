package pca.problema2

import akka.actor.ActorSystem

import scala.collection.mutable

object System extends App {
    private val map = mutable.HashMap[Int, String]()

    private val clientId1 = 1
    private val clientId2 = 2
    private val clientId3 = 3
    private val clientId4 = 4

    // Clients' map initialization
    for (i <- Seq(clientId1, clientId2, clientId3, clientId4)) {
        map.put(i, null)
    }

    // Print the map once in a while to check its status
    new Thread(() => {
        while (true) {
            val sb = StringBuilder()
            sb.append("========================================================\n")
            map.foreach((k, v) => sb.append(s"Products of client #$k: $v\n"))
            sb.append("========================================================\n")
            println(sb.toString)
            Thread.sleep(3000)
        }
    }).start()

    private val system = ActorSystem()
    private val c2 = system.actorOf(Merchant.props(null), "C2")
    private val f = system.actorOf(Manufacturer.props(c2), "F")
    private val r = system.actorOf(Representative.props(f), "R")
    private val c1 = system.actorOf(Merchant.props(r), "C1")

    // Start clients' orders
    map.foreach((k, v) => c1 ! Merchant.Order(k, map))
}
