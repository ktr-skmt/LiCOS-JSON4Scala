package licos.protocol.element.lobby.part

import licos.json.element.lobby.client2server.JsonSupportedComposition
import licos.knowledge.Composition

final case class SupportedCompositionProtocol(
    numberOfPlayers: Int,
    isSupportedForA: Boolean,
    isSupportedForB: Boolean,
    isSupportedForC: Boolean
) {

  val json: Option[JsonSupportedComposition] = {
    Some(
      JsonSupportedComposition(
        isSupportedForA,
        isSupportedForB,
        isSupportedForC
      )
    )
  }

  def isSupportedForTheNumberOfPlayers: Boolean = Composition.support.`for`.contains(numberOfPlayers)

  def AOpt: Option[Composition] = {
    if (isSupportedForTheNumberOfPlayers) {
      Some(Composition.support.`for`(numberOfPlayers).A)
    } else {
      None
    }
  }

  def BOpt: Option[Composition] = {
    if (isSupportedForTheNumberOfPlayers) {
      Some(Composition.support.`for`(numberOfPlayers).B)
    } else {
      None
    }
  }

  def COpt: Option[Composition] = {
    if (isSupportedForTheNumberOfPlayers) {
      Some(Composition.support.`for`(numberOfPlayers).C)
    } else {
      None
    }
  }

}

object SupportedCompositionProtocol {

  def read(json: JsonSupportedComposition, numberOfPlayers: Int): Option[SupportedCompositionProtocol] = {
    Some(
      SupportedCompositionProtocol(
        numberOfPlayers,
        json.A,
        json.B,
        json.C
      )
    )
  }
}
