package licos.knowledge

import licos.protocol.village.part.Name

sealed abstract class Species(id: String, name: Name) {
  override def toString: String = id
}

case object HumanSpecies extends Species("human", Name().en("Human species").ja("人間種"))
case object WerewolfSpecies extends Species("werewolf", Name().en("Werewolf species").ja("人狼種"))
case object WerehamsterSpecies extends Species("werehamster", Name().en("Werehamster species").ja("ハムスター人間種"))
