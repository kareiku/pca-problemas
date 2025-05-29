package org.example

import akka.actor.*

object Satelites extends App {
    val system = ActorSystem("Astropuerto")
    val torre = system.actorOf(Torre.props, "Torre")
    val satelite_1 = system.actorOf(Satelite.props(torre), "satelite_1")
    val satelite_2 = system.actorOf(Satelite.props(torre), "satelite_2")
    val nave_1 = system.actorOf(Nave.props(satelite_1), "nave_1")
    val nave_2 = system.actorOf(Nave.props(satelite_1), "nave_2")
    val nave_3 = system.actorOf(Nave.props(satelite_2), "nave_3")
    val nave_4 = system.actorOf(Nave.props(satelite_2), "nave_4")
    nave_1 ! "iniciar"
    nave_2 ! "iniciar"
    nave_3 ! "iniciar"
    nave_4 ! "iniciar"
}
