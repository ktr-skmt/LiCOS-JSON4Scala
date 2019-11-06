package licos.json.element.lobby

import play.api.libs.json._

case class JsonWaitingPage(`type`:  String,
                           village: JsonVillage,
                           players: Seq[JsonPlayerInWaitingPage],
                           error:   Option[String])
    extends TypeSystem(`type`) {
  override protected def validType: String = JsonWaitingPage.`type`
}

object JsonWaitingPage {
  implicit val jsonFormat: OFormat[JsonWaitingPage] = Json.format[JsonWaitingPage]

  val `type`: String = "waitingPage"

  def generate(village: JsonVillage, players: Seq[JsonPlayerInWaitingPage], error: Option[String]): JsonWaitingPage = {
    JsonWaitingPage(`type`, village, players, error)
  }
}

case class JsonPlayerInWaitingPage(token:       String,
                                   name:        String,
                                   avatarImage: String,
                                   isAnonymous: Boolean,
                                   isHost:      Boolean,
                                   isMe:        Boolean)

object JsonPlayerInWaitingPage {
  implicit val jsonFormat: OFormat[JsonPlayerInWaitingPage] = Json.format[JsonPlayerInWaitingPage]
}
