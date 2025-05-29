package pca.problema2

import akka.actor.{Actor, ActorRef, Props}

import java.util.random.RandomGenerator
import scala.collection.mutable

class Manufacturer(merchant: ActorRef) extends Actor {
    override def receive: Receive = {
        case Manufacturer.Send(clientId, map) =>
            println(s"[${self.path.name}, for $clientId] Manufacturing products...")
            val products = RandomGenerator.getDefault.nextInt.toBinaryString
            println(s"[${self.path.name}, for $clientId] Delegating product sending...")
            merchant ! Merchant.Send(clientId, map, products)
        case _ =>
    }
}

object Manufacturer {
    def props(merchant: ActorRef): Props = Props(new Manufacturer(merchant))

    case class Send(clientId: Int, map: mutable.HashMap[Int, String])
}
