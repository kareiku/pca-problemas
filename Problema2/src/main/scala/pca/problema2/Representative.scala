package pca.problema2

import akka.actor.{Actor, ActorRef, Props}

import scala.collection.mutable

class Representative(manufacturer: ActorRef) extends Actor {
    override def receive: Receive = {
        case Representative.Delegate(clientId, map) =>
            println(s"[${self.path.name}, for $clientId] Carrying out procedures...")
            println(s"[${self.path.name}, for $clientId] Sending paperwork...")
            manufacturer ! Manufacturer.Send(clientId, map)
        case _ =>
    }
}

object Representative {
    def props(manufacturer: ActorRef): Props = Props(new Representative(manufacturer))

    case class Delegate(clientId: Int, map: mutable.HashMap[Int, String])
}
