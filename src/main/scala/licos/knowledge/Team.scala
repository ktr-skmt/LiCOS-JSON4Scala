package licos.knowledge

import licos.protocol.element.village.part.NameProtocol

sealed abstract class Team(val id: String, val name: NameProtocol) extends Product with Serializable {
  override def toString: String = id
}

case object TeamVillagers extends Team("villager", NameProtocol().en("Team Villagers").ja("村人陣営"))
case object TeamWerewolves extends Team("werewolf", NameProtocol().en("Team werewolves").ja("人狼陣営"))
case object TeamWerehamster extends Team("werehamster", NameProtocol().en("Team werehamster").ja("ハムスター人間陣営"))
