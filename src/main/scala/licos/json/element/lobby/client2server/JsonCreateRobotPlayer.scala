package licos.json.element.lobby.client2server

import licos.json.element.lobby.TypeSystem
import licos.json.validation.village.{AvatarValidation, VillageValidation}

final case class JsonCreateRobotPlayer(
    `type`:           String,
    name:             String,
    image:            String,
    language:         String,
    isFullyAutomated: Boolean,
    support:          JsonSupport
) extends TypeSystem(`type`) {
  override protected def validType: String = JsonCreateRobotPlayer.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(name: String, image: String, language: String, isFullyAutomated: Boolean, support: JsonSupport) = {
    this(JsonCreateRobotPlayer.`type`, name, image, language, isFullyAutomated, support)
  }
}

object JsonCreateRobotPlayer {

  val `type`: String = "createRobotPlayer"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonCreateRobotPlayer] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "name").read[String](AvatarValidation.name) and
      (JsPath \ "image").read[String](AvatarValidation.image) and
      (JsPath \ "language").read[String](VillageValidation.language) and
      (JsPath \ "isFullyAutomated").read[Boolean] and
      (JsPath \ "support").read[JsonSupport]
  )(JsonCreateRobotPlayer.apply _)

  implicit val jsonWrites: OWrites[JsonCreateRobotPlayer] = Json.writes[JsonCreateRobotPlayer]

}

final case class JsonSupport(
    `4`:  JsonSupportedComposition,
    `5`:  JsonSupportedComposition,
    `6`:  JsonSupportedComposition,
    `7`:  JsonSupportedComposition,
    `8`:  JsonSupportedComposition,
    `9`:  JsonSupportedComposition,
    `10`: JsonSupportedComposition,
    `11`: JsonSupportedComposition,
    `12`: JsonSupportedComposition,
    `13`: JsonSupportedComposition,
    `14`: JsonSupportedComposition,
    `15`: JsonSupportedComposition
)

object JsonSupport {

  import play.api.libs.functional.syntax._
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonSupport] = (
    (JsPath \ "4").read[JsonSupportedComposition] and
      (JsPath \ "5").read[JsonSupportedComposition] and
      (JsPath \ "6").read[JsonSupportedComposition] and
      (JsPath \ "7").read[JsonSupportedComposition] and
      (JsPath \ "8").read[JsonSupportedComposition] and
      (JsPath \ "9").read[JsonSupportedComposition] and
      (JsPath \ "10").read[JsonSupportedComposition] and
      (JsPath \ "11").read[JsonSupportedComposition] and
      (JsPath \ "12").read[JsonSupportedComposition] and
      (JsPath \ "13").read[JsonSupportedComposition] and
      (JsPath \ "14").read[JsonSupportedComposition] and
      (JsPath \ "15").read[JsonSupportedComposition]
  )(JsonSupport.apply _)

  implicit val jsonWrites: OWrites[JsonSupport] = Json.writes[JsonSupport]

}

final case class JsonSupportedComposition(A: Boolean, B: Boolean, C: Boolean)

object JsonSupportedComposition {

  import play.api.libs.functional.syntax._
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonSupportedComposition] = (
    (JsPath \ "A").read[Boolean] and
      (JsPath \ "B").read[Boolean] and
      (JsPath \ "C").read[Boolean]
  )(JsonSupportedComposition.apply _)

  implicit val jsonWrites: OWrites[JsonSupportedComposition] = Json.writes[JsonSupportedComposition]

}
