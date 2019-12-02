package licos.knowledge

import licos.protocol.element.village.part.NameProtocol

sealed abstract class Team(id: String, name: NameProtocol) {
  override def toString: String = id
}

case object TeamHumans extends Team("human", NameProtocol().en("Team Humans").ja("人間陣営"))
case object TeamWerewolves extends Team("werewolf", NameProtocol().en("Team werewolves").ja("人狼陣営"))
case object TeamWerehamster extends Team("werehamster", NameProtocol().en("Team werehamster").ja("ハムスター人間陣営"))
