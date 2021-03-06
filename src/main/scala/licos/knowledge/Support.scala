package licos.knowledge

final case class Support(`for`: Map[Int, SupportedComposition]) extends Serializable {

  def A(numberOfPlayers: Int): Option[Composition] = {
    if (`for`.contains(numberOfPlayers)) {
      Some(`for`(numberOfPlayers).A)
    } else {
      Option.empty[Composition]
    }
  }

  def B(numberOfPlayers: Int): Option[Composition] = {
    if (`for`.contains(numberOfPlayers)) {
      Some(`for`(numberOfPlayers).B)
    } else {
      Option.empty[Composition]
    }
  }

  def C(numberOfPlayers: Int): Option[Composition] = {
    if (`for`.contains(numberOfPlayers)) {
      Some(`for`(numberOfPlayers).C)
    } else {
      Option.empty[Composition]
    }
  }

}

final case class SupportedComposition(A: Composition, B: Composition, C: Composition) extends Serializable
