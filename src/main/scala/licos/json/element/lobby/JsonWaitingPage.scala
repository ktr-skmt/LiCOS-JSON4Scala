package licos.json.element.lobby

import play.api.libs.json._

case class JsonWaitingPage(`type`: String,
                           village: JsonVillage,
                           players: Seq[JsonPlayerForWaitingPage],
                           error: String) extends TypeSystem(`type`) {
  override protected def validType: String = JsonWaitingPage.`type`
}

object JsonWaitingPage {
  implicit val jsonFormat: OFormat[JsonWaitingPage] = Json.format[JsonWaitingPage]

  val `type`: String = "waitingPage"

  def generate(village: JsonVillage,
               players: Seq[JsonPlayerForWaitingPage],
               error: String): JsonWaitingPage = {
    JsonWaitingPage(`type`, village, players, error)
  }
}

case class JsonPlayerForWaitingPage(token: String,
                                    name: String,
                                    avatarImage: String,
                                    isAnonymous: Boolean,
                                    isHost: Boolean,
                                    isMe: Boolean)

object JsonPlayerForWaitingPage {
  implicit val jsonFormat: OFormat[JsonPlayerForWaitingPage] = Json.format[JsonPlayerForWaitingPage]
}