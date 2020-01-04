package licos.knowledge

final class Composition(
    val master:      MasterRole,
    val villager:    VillagerRole,
    val seer:        SeerRole,
    val medium:      MediumRole,
    val hunter:      HunterRole,
    val mason:       MasonRole,
    val madman:      MadmanRole,
    val werewolf:    WerewolfRole,
    val werehamster: WerehamsterRole
) {

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

object Composition {
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
  ): Composition = {
    new Composition(master, villager, seer, medium, hunter, mason, madman, werewolf, werehamster)
  }
  val support: Support = Support(
    Map[Int, SupportedComposition](
      2 -> SupportedComposition(
        A = Composition(
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
        B = Composition(
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
        C = Composition(
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
      3 -> SupportedComposition(
        A = Composition(
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
        B = Composition(
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
        C = Composition(
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
      4 -> SupportedComposition(
        A = Composition(
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
        B = Composition(
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
        C = Composition(
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
      5 -> SupportedComposition(
        A = Composition(
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
        B = Composition(
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
        C = Composition(
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
      6 -> SupportedComposition(
        A = Composition(
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
        B = Composition(
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
        C = Composition(
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
      7 -> SupportedComposition(
        A = Composition(
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
        B = Composition(
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
        C = Composition(
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
      8 -> SupportedComposition(
        A = Composition(
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
        B = Composition(
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
        C = Composition(
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
      9 -> SupportedComposition(
        A = Composition(
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
        B = Composition(
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
        C = Composition(
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
      10 -> SupportedComposition(
        A = Composition(
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
        B = Composition(
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
        C = Composition(
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
      11 -> SupportedComposition(
        A = Composition(
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
        B = Composition(
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
        C = Composition(
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
      12 -> SupportedComposition(
        A = Composition(
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
        B = Composition(
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
        C = Composition(
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
      13 -> SupportedComposition(
        A = Composition(
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
        B = Composition(
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
        C = Composition(
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
      14 -> SupportedComposition(
        A = Composition(
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
        B = Composition(
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
        C = Composition(
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
      15 -> SupportedComposition(
        A = Composition(
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
        B = Composition(
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
        C = Composition(
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
  )
}
