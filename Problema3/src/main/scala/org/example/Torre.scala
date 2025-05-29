package org.example

import akka.actor.*

case class SolicitarTorre(nave: ActorRef, satelite: ActorRef)

case class FinDeManiobraTorre(nave: ActorRef)

class Torre extends Actor with Stash {
    private val nombre: String = self.path.name

    override def receive: Receive = disponible

    private def disponible: Receive = {
        case SolicitarTorre(nave: ActorRef, satelite: ActorRef) =>
            println(s"[$nombre] Permiso concedido a ${nave.path.name}")
            satelite ! ConcederSatelite(nave)
            context.become(ocupada)
        case _ => ()
    }

    private def ocupada: Receive = {
        case SolicitarTorre(_: ActorRef, _: ActorRef) =>
            stash()
        case FinDeManiobraTorre(nave: ActorRef) =>
            println(s"[$nombre] Maniobra finalizada de la ${nave.path.name}. Pista libre.")
            unstashAll()
            context.become(disponible)
        case _ => ()
    }
}

object Torre {
    def props: Props = Props(new Torre)
}
