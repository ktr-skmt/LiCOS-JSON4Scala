package licos.knowledge

import licos.protocol.village.part.NameProtocol

sealed abstract class Species(id: String, name: NameProtocol) {
  override def toString: String = id
}

case object HumanSpecies extends Species("human", NameProtocol().en("Human species").ja("人間種"))
case object WerewolfSpecies extends Species("werewolf", NameProtocol().en("Werewolf species").ja("人狼種"))
case object WerehamsterSpecies extends Species("werehamster", NameProtocol().en("Werehamster species").ja("ハムスター人間種"))
