package licos.knowledge

final class Cast(
    val master:      MasterRole,
    val villager:    VillagerRole,
    val seer:        SeerRole,
    val medium:      MediumRole,
    val hunter:      HunterRole,
    val mason:       MasonRole,
    val madman:      MadmanRole,
    val werewolf:    WerewolfRole,
    val werehamster: WerehamsterRole
) extends Serializable {

  def totalNumberOfPlayers: Int = {
    villager.numberOfPlayers +
      seer.numberOfPlayers +
      medium.numberOfPlayers +
      hunter.numberOfPlayers +
      mason.numberOfPlayers +
      madman.numberOfPlayers +
      werewolf.numberOfPlayers +
      werehamster.numberOfPlayers
  }

  def allRoles: Seq[Role] = Seq[Role](master, villager, seer, medium, hunter, mason, madman, werewolf, werehamster)

  def parse(roleLabel: String): Option[Role] = allRoles.find(_.name.en.equalsIgnoreCase(roleLabel))
}

object Cast {
  def apply(
      master:      MasterRole,
      villager:    VillagerRole,
      seer:        SeerRole,
      medium:      MediumRole,
      hunter:      HunterRole,
      mason:       MasonRole,
      madman:      MadmanRole,
      werewolf:    WerewolfRole,
      werehamster: WerehamsterRole
  ): Cast = {
    new Cast(master, villager, seer, medium, hunter, mason, madman, werewolf, werehamster)
  }
  val playerNumRoleNumMap: Map[Int, Map[String, Cast]] = Map[Int, Map[String, Cast]](
    0 -> Map[String, Cast](
      "A" -> Cast(
        MasterRole(1),
        VillagerRole(0),
        SeerRole(0),
        MediumRole(0),
        HunterRole(0),
        MasonRole(0),
        MadmanRole(0),
        WerewolfRole(0),
        WerehamsterRole(0)
      )
    ),
    1 -> Map[String, Cast](
      "villager" -> Cast(
        MasterRole(1),
        VillagerRole(1),
        SeerRole(0),
        MediumRole(0),
        HunterRole(0),
        MasonRole(0),
        MadmanRole(0),
        WerewolfRole(0),
        WerehamsterRole(0)
      ),
      "seer" -> Cast(
        MasterRole(1),
        VillagerRole(0),
        SeerRole(1),
        MediumRole(0),
        HunterRole(0),
        MasonRole(0),
        MadmanRole(0),
        WerewolfRole(0),
        WerehamsterRole(0)
      ),
      "medium" -> Cast(
        MasterRole(1),
        VillagerRole(0),
        SeerRole(0),
        MediumRole(1),
        HunterRole(0),
        MasonRole(0),
        MadmanRole(0),
        WerewolfRole(0),
        WerehamsterRole(0)
      ),
      "hunter" -> Cast(
        MasterRole(1),
        VillagerRole(0),
        SeerRole(0),
        MediumRole(0),
        HunterRole(1),
        MasonRole(0),
        MadmanRole(0),
        WerewolfRole(0),
        WerehamsterRole(0)
      ),
      "mason" -> Cast(
        MasterRole(1),
        VillagerRole(0),
        SeerRole(0),
        MediumRole(0),
        HunterRole(0),
        MasonRole(1),
        MadmanRole(0),
        WerewolfRole(0),
        WerehamsterRole(0)
      ),
      "madman" -> Cast(
        MasterRole(1),
        VillagerRole(0),
        SeerRole(0),
        MediumRole(0),
        HunterRole(0),
        MasonRole(0),
        MadmanRole(1),
        WerewolfRole(0),
        WerehamsterRole(0)
      ),
      "werewolf" -> Cast(
        MasterRole(1),
        VillagerRole(0),
        SeerRole(0),
        MediumRole(0),
        HunterRole(0),
        MasonRole(0),
        MadmanRole(0),
        WerewolfRole(1),
        WerehamsterRole(0)
      ),
      "werehamster" -> Cast(
        MasterRole(1),
        VillagerRole(0),
        SeerRole(0),
        MediumRole(0),
        HunterRole(0),
        MasonRole(0),
        MadmanRole(0),
        WerewolfRole(0),
        WerehamsterRole(0)
      )
    ),
    2 -> Map[String, Cast](
      "A" -> Cast(
        MasterRole(1),
        VillagerRole(2),
        SeerRole(0),
        MediumRole(0),
        HunterRole(0),
        MasonRole(0),
        MadmanRole(0),
        WerewolfRole(0),
        WerehamsterRole(0)
      ),
      "B" -> Cast(
        MasterRole(1),
        VillagerRole(1),
        SeerRole(0),
        MediumRole(0),
        HunterRole(0),
        MasonRole(0),
        MadmanRole(0),
        WerewolfRole(1),
        WerehamsterRole(0)
      ),
      "C" -> Cast(
        MasterRole(1),
        VillagerRole(0),
        SeerRole(0),
        MediumRole(0),
        HunterRole(0),
        MasonRole(0),
        MadmanRole(0),
        WerewolfRole(2),
        WerehamsterRole(0)
      )
    ),
    3 -> Map[String, Cast](
      "A" -> Cast(
        MasterRole(1),
        VillagerRole(2),
        SeerRole(0),
        MediumRole(0),
        HunterRole(0),
        MasonRole(0),
        MadmanRole(0),
        WerewolfRole(1),
        WerehamsterRole(0)
      ),
      "B" -> Cast(
        MasterRole(1),
        VillagerRole(1),
        SeerRole(1),
        MediumRole(0),
        HunterRole(0),
        MasonRole(0),
        MadmanRole(0),
        WerewolfRole(1),
        WerehamsterRole(0)
      ),
      "C" -> Cast(
        MasterRole(1),
        VillagerRole(0),
        SeerRole(1),
        MediumRole(0),
        HunterRole(1),
        MasonRole(0),
        MadmanRole(0),
        WerewolfRole(1),
        WerehamsterRole(0)
      )
    ),
    4 -> Map[String, Cast](
      "A" -> Cast(
        MasterRole(1),
        VillagerRole(3),
        SeerRole(0),
        MediumRole(0),
        HunterRole(0),
        MasonRole(0),
        MadmanRole(0),
        WerewolfRole(1),
        WerehamsterRole(0)
      ),
      "B" -> Cast(
        MasterRole(1),
        VillagerRole(2),
        SeerRole(1),
        MediumRole(0),
        HunterRole(0),
        MasonRole(0),
        MadmanRole(0),
        WerewolfRole(1),
        WerehamsterRole(0)
      ),
      "C" -> Cast(
        MasterRole(1),
        VillagerRole(1),
        SeerRole(1),
        MediumRole(1),
        HunterRole(0),
        MasonRole(0),
        MadmanRole(0),
        WerewolfRole(1),
        WerehamsterRole(0)
      )
    ),
    5 -> Map[String, Cast](
      "A" -> Cast(
        MasterRole(1),
        VillagerRole(3),
        SeerRole(1),
        MediumRole(0),
        HunterRole(0),
        MasonRole(0),
        MadmanRole(0),
        WerewolfRole(1),
        WerehamsterRole(0)
      ),
      "B" -> Cast(
        MasterRole(1),
        VillagerRole(2),
        SeerRole(1),
        MediumRole(0),
        HunterRole(0),
        MasonRole(0),
        MadmanRole(0),
        WerewolfRole(1),
        WerehamsterRole(1)
      ),
      "C" -> Cast(
        MasterRole(1),
        VillagerRole(1),
        SeerRole(1),
        MediumRole(0),
        HunterRole(1),
        MasonRole(0),
        MadmanRole(1),
        WerewolfRole(1),
        WerehamsterRole(0)
      )
    ),
    6 -> Map[String, Cast](
      "A" -> Cast(
        MasterRole(1),
        VillagerRole(4),
        SeerRole(1),
        MediumRole(0),
        HunterRole(0),
        MasonRole(0),
        MadmanRole(0),
        WerewolfRole(1),
        WerehamsterRole(0)
      ),
      "B" -> Cast(
        MasterRole(1),
        VillagerRole(3),
        SeerRole(1),
        MediumRole(0),
        HunterRole(0),
        MasonRole(0),
        MadmanRole(0),
        WerewolfRole(1),
        WerehamsterRole(1)
      ),
      "C" -> Cast(
        MasterRole(1),
        VillagerRole(0),
        SeerRole(1),
        MediumRole(0),
        HunterRole(1),
        MasonRole(0),
        MadmanRole(3),
        WerewolfRole(1),
        WerehamsterRole(0)
      )
    ),
    7 -> Map[String, Cast](
      "A" -> Cast(
        MasterRole(1),
        VillagerRole(5),
        SeerRole(1),
        MediumRole(0),
        HunterRole(0),
        MasonRole(0),
        MadmanRole(0),
        WerewolfRole(1),
        WerehamsterRole(0)
      ),
      "B" -> Cast(
        MasterRole(1),
        VillagerRole(4),
        SeerRole(1),
        MediumRole(0),
        HunterRole(0),
        MasonRole(0),
        MadmanRole(0),
        WerewolfRole(1),
        WerehamsterRole(1)
      ),
      "C" -> Cast(
        MasterRole(1),
        VillagerRole(2),
        SeerRole(1),
        MediumRole(1),
        HunterRole(1),
        MasonRole(0),
        MadmanRole(1),
        WerewolfRole(1),
        WerehamsterRole(0)
      )
    ),
    8 -> Map[String, Cast](
      "A" -> Cast(
        MasterRole(1),
        VillagerRole(6),
        SeerRole(1),
        MediumRole(0),
        HunterRole(0),
        MasonRole(0),
        MadmanRole(0),
        WerewolfRole(1),
        WerehamsterRole(0)
      ),
      "B" -> Cast(
        MasterRole(1),
        VillagerRole(4),
        SeerRole(1),
        MediumRole(0),
        HunterRole(0),
        MasonRole(0),
        MadmanRole(1),
        WerewolfRole(1),
        WerehamsterRole(1)
      ),
      "C" -> Cast(
        MasterRole(1),
        VillagerRole(1),
        SeerRole(1),
        MediumRole(1),
        HunterRole(0),
        MasonRole(2),
        MadmanRole(1),
        WerewolfRole(2),
        WerehamsterRole(0)
      )
    ),
    9 -> Map[String, Cast](
      "A" -> Cast(
        MasterRole(1),
        VillagerRole(5),
        SeerRole(1),
        MediumRole(1),
        HunterRole(0),
        MasonRole(0),
        MadmanRole(0),
        WerewolfRole(2),
        WerehamsterRole(0)
      ),
      "B" -> Cast(
        MasterRole(1),
        VillagerRole(3),
        SeerRole(1),
        MediumRole(1),
        HunterRole(1),
        MasonRole(0),
        MadmanRole(0),
        WerewolfRole(2),
        WerehamsterRole(1)
      ),
      "C" -> Cast(
        MasterRole(1),
        VillagerRole(3),
        SeerRole(1),
        MediumRole(1),
        HunterRole(1),
        MasonRole(0),
        MadmanRole(1),
        WerewolfRole(1),
        WerehamsterRole(1)
      )
    ),
    10 -> Map[String, Cast](
      "A" -> Cast(
        MasterRole(1),
        VillagerRole(5),
        SeerRole(1),
        MediumRole(1),
        HunterRole(0),
        MasonRole(0),
        MadmanRole(1),
        WerewolfRole(2),
        WerehamsterRole(0)
      ),
      "B" -> Cast(
        MasterRole(1),
        VillagerRole(4),
        SeerRole(1),
        MediumRole(1),
        HunterRole(1),
        MasonRole(0),
        MadmanRole(0),
        WerewolfRole(2),
        WerehamsterRole(1)
      ),
      "C" -> Cast(
        MasterRole(1),
        VillagerRole(2),
        SeerRole(2),
        MediumRole(1),
        HunterRole(1),
        MasonRole(0),
        MadmanRole(1),
        WerewolfRole(2),
        WerehamsterRole(1)
      )
    ),
    11 -> Map[String, Cast](
      "A" -> Cast(
        MasterRole(1),
        VillagerRole(5),
        SeerRole(1),
        MediumRole(1),
        HunterRole(1),
        MasonRole(0),
        MadmanRole(1),
        WerewolfRole(2),
        WerehamsterRole(0)
      ),
      "B" -> Cast(
        MasterRole(1),
        VillagerRole(4),
        SeerRole(1),
        MediumRole(1),
        HunterRole(1),
        MasonRole(0),
        MadmanRole(1),
        WerewolfRole(2),
        WerehamsterRole(1)
      ),
      "C" -> Cast(
        MasterRole(1),
        VillagerRole(3),
        SeerRole(2),
        MediumRole(1),
        HunterRole(1),
        MasonRole(0),
        MadmanRole(1),
        WerewolfRole(2),
        WerehamsterRole(1)
      )
    ),
    12 -> Map[String, Cast](
      "A" -> Cast(
        MasterRole(1),
        VillagerRole(6),
        SeerRole(1),
        MediumRole(1),
        HunterRole(1),
        MasonRole(0),
        MadmanRole(1),
        WerewolfRole(2),
        WerehamsterRole(0)
      ),
      "B" -> Cast(
        MasterRole(1),
        VillagerRole(5),
        SeerRole(1),
        MediumRole(1),
        HunterRole(1),
        MasonRole(0),
        MadmanRole(1),
        WerewolfRole(2),
        WerehamsterRole(1)
      ),
      "C" -> Cast(
        MasterRole(1),
        VillagerRole(3),
        SeerRole(1),
        MediumRole(1),
        HunterRole(1),
        MasonRole(2),
        MadmanRole(1),
        WerewolfRole(2),
        WerehamsterRole(1)
      )
    ),
    13 -> Map[String, Cast](
      "A" -> Cast(
        MasterRole(1),
        VillagerRole(5),
        SeerRole(1),
        MediumRole(1),
        HunterRole(1),
        MasonRole(2),
        MadmanRole(1),
        WerewolfRole(2),
        WerehamsterRole(0)
      ),
      "B" -> Cast(
        MasterRole(1),
        VillagerRole(4),
        SeerRole(1),
        MediumRole(1),
        HunterRole(1),
        MasonRole(2),
        MadmanRole(1),
        WerewolfRole(2),
        WerehamsterRole(1)
      ),
      "C" -> Cast(
        MasterRole(1),
        VillagerRole(3),
        SeerRole(2),
        MediumRole(1),
        HunterRole(1),
        MasonRole(2),
        MadmanRole(1),
        WerewolfRole(2),
        WerehamsterRole(1)
      )
    ),
    14 -> Map[String, Cast](
      "A" -> Cast(
        MasterRole(1),
        VillagerRole(5),
        SeerRole(1),
        MediumRole(1),
        HunterRole(1),
        MasonRole(2),
        MadmanRole(1),
        WerewolfRole(2),
        WerehamsterRole(1)
      ),
      "B" -> Cast(
        MasterRole(1),
        VillagerRole(5),
        SeerRole(1),
        MediumRole(1),
        HunterRole(1),
        MasonRole(2),
        MadmanRole(1),
        WerewolfRole(2),
        WerehamsterRole(1)
      ),
      "C" -> Cast(
        MasterRole(1),
        VillagerRole(3),
        SeerRole(2),
        MediumRole(1),
        HunterRole(1),
        MasonRole(2),
        MadmanRole(2),
        WerewolfRole(2),
        WerehamsterRole(1)
      )
    ),
    15 -> Map[String, Cast](
      "A" -> Cast(
        MasterRole(1),
        VillagerRole(6),
        SeerRole(1),
        MediumRole(1),
        HunterRole(1),
        MasonRole(2),
        MadmanRole(1),
        WerewolfRole(2),
        WerehamsterRole(1)
      ),
      "B" -> Cast(
        MasterRole(1),
        VillagerRole(6),
        SeerRole(1),
        MediumRole(1),
        HunterRole(1),
        MasonRole(2),
        MadmanRole(1),
        WerewolfRole(2),
        WerehamsterRole(1)
      ),
      "C" -> Cast(
        MasterRole(1),
        VillagerRole(3),
        SeerRole(2),
        MediumRole(1),
        HunterRole(1),
        MasonRole(2),
        MadmanRole(2),
        WerewolfRole(3),
        WerehamsterRole(1)
      )
    )
  )
}
