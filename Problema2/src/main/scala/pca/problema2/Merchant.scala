package pca.problema2

import akka.actor.{Actor, ActorRef, Props}

import scala.collection.mutable

class Merchant(representative: ActorRef) extends Actor {
    override def receive: Receive = {
        case Merchant.Order(clientId, map) =>
            println(s"[${self.path.name}, for $clientId] Delegating order...")
            representative ! Representative.Delegate(clientId, map)
        case Merchant.Send(clientId, map, products) =>
            println(s"[${self.path.name}, for $clientId] Sending products...")
            map.put(clientId, products)
        case _ =>
    }
}

object Merchant {
    def props(representative: ActorRef): Props = Props(new Merchant(representative))

    case class Order(clientId: Int, map: mutable.HashMap[Int, String])

    case class Send(clientId: Int, map: mutable.HashMap[Int, String], products: String)
}
