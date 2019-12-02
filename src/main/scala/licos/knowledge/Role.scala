package licos.knowledge

import licos.protocol.element.village.part.NameProtocol
import licos.util.WerewolfWorld

import scala.collection.mutable.ListBuffer

@SuppressWarnings(Array[String]("org.wartremover.warts.MutableDataStructures"))
sealed abstract class Role(val species: Species, val team: Team, val numberOfPlayers: Int, val name: NameProtocol) {
  override def toString: String                = name.en
  val characters:        ListBuffer[Character] = ListBuffer.empty[Character]

  def icon: String = WerewolfWorld.roleIcon(name.en.toLowerCase)

  override def equals(o: Any): Boolean = {
    o match {
      case role: Role =>
        import cats.implicits._
        this.name.en === role.name.en
      case _ => false
    }
  }
}

final case class MasterRole(override val numberOfPlayers: Int)
    extends Role(HumanSpecies, TeamHumans, numberOfPlayers, NameProtocol().en("Master").ja("マスター"))
final case class VillagerRole(override val numberOfPlayers: Int)
    extends Role(HumanSpecies, TeamHumans, numberOfPlayers, NameProtocol().en("Villager").ja("村人"))
final case class SeerRole(override val numberOfPlayers: Int)
    extends Role(HumanSpecies, TeamHumans, numberOfPlayers, NameProtocol().en("Seer").ja("預言者"))
final case class MediumRole(override val numberOfPlayers: Int)
    extends Role(HumanSpecies, TeamHumans, numberOfPlayers, NameProtocol().en("Medium").ja("霊媒師"))
final case class HunterRole(override val numberOfPlayers: Int)
    extends Role(HumanSpecies, TeamHumans, numberOfPlayers, NameProtocol().en("Hunter").ja("狩人"))
final case class MasonRole(override val numberOfPlayers: Int)
    extends Role(HumanSpecies, TeamHumans, numberOfPlayers, NameProtocol().en("Mason").ja("共有者"))
final case class MadmanRole(override val numberOfPlayers: Int)
    extends Role(HumanSpecies, TeamWerewolves, numberOfPlayers, NameProtocol().en("Madman").ja("狂人"))
final case class WerewolfRole(override val numberOfPlayers: Int)
    extends Role(WerewolfSpecies, TeamWerewolves, numberOfPlayers, NameProtocol().en("Werewolf").ja("人狼"))
final case class WerehamsterRole(override val numberOfPlayers: Int)
    extends Role(WerehamsterSpecies, TeamWerehamster, numberOfPlayers, NameProtocol().en("Werehamster").ja("ハムスター人間"))
