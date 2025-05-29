package org.example

import akka.actor.*

case object ConcederNave

class Nave(mi_satelite: ActorRef) extends Actor {
    private val nombre: String = self.path.name

    override def receive: Receive = {
        case "iniciar" =>
            println(s"[$nombre] está en el espacio profundo")
            solicitar_aterrizaje()
        case ConcederNave =>
            println(s"[$nombre] inicia maniobra")
            mi_satelite ! FinDeManiobraSatelite(self)
        case _ => ()
    }

    private def solicitar_aterrizaje(): Unit = {
        println(s"[$nombre] pide permiso a ${mi_satelite.path.name}")
        mi_satelite ! SolicitarSatelite(self)
    }
}

object Nave {
    def props(s: ActorRef): Props = Props(new Nave(s))
}
