package licos.json.village.iri

import licos.WerewolfWorld

/**
  * <pre>
  * Created on 2017/12/15.
  * </pre>
  *
  * @author K.Sakamoto
  */
sealed abstract class Message(label: String) {
  def iri(villageId: Long): String = WerewolfWorld.state(s"#$villageId/${label}Message")
}

case object BoardMessage      extends Message("board")
case object ErrorMessage      extends Message("error")
case object PlayerMessage     extends Message("player")
case object ScrollMessage     extends Message("scroll")
case object SystemMessage     extends Message("system")
case object VoteMessage       extends Message("vote")
case object FlavorTextMessage extends Message("flavorText")
case object DummyMessage      extends Message("dummy")
