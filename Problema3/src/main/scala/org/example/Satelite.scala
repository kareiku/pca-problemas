package org.example

import akka.actor.*

case class SolicitarSatelite(nave: ActorRef)

case class ConcederSatelite(nave: ActorRef)

case class FinDeManiobraSatelite(nave: ActorRef)

class Satelite(torre: ActorRef) extends Actor {
    private val nombre: String = self.path.name

    override def receive: Receive = {
        case SolicitarSatelite(nave: ActorRef) =>
            println(s"[$nombre] Pido permiso a ${torre.path.name} para ${nave.path.name}")
            torre ! SolicitarTorre(nave, self)
        case ConcederSatelite(nave) =>
            println(s"[$nombre] ${torre.path.name} concede permiso a ${nave.path.name}")
            nave ! ConcederNave
        case FinDeManiobraSatelite(nave: ActorRef) =>
            println(s"[$nombre] La nave ${nave.path.name} ha terminado")
            torre ! FinDeManiobraTorre(nave)
        case _ => ()
    }
}

object Satelite {
    def props(torre: ActorRef): Props = Props(new Satelite(torre))
}
