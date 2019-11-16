package licos.knowledge

import licos.protocol.village.part.Name

sealed abstract class Team(id: String, name: Name) {
  override def toString: String = id
}

case object TeamHumans extends Team("human", Name().en("Team Humans").ja("人間陣営"))
case object TeamWerewolves extends Team("werewolf", Name().en("Team werewolves").ja("人狼陣営"))
case object TeamWerehamster extends Team("werehamster", Name().en("Team werehamster").ja("ハムスター人間陣営"))
