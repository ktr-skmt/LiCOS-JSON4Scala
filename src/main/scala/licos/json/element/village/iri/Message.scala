package licos.json.element.village.iri

import licos.util.LiCOSOnline

sealed abstract class Message(label: String) {
  def iri(villageId: Long): String = LiCOSOnline.state(villageId, s"${label}Message")
}

case object BoardMessage extends Message("board")
case object ChatMessage extends Message("chat")
case object ErrorMessage extends Message("error")
case object ScrollMessage extends Message("scroll")
case object SystemMessage extends Message("system")
case object VoteMessage extends Message("vote")
case object FlavorTextMessage extends Message("flavorText")
case object DummyMessage extends Message("dummy")
case object StarMessage extends Message("star")
