package licos.json.element.lobby.client2server

import licos.json.element.lobby.TypeSystem
import licos.json.validation.village.{AvatarValidation, VillageValidation}

final case class JsonCreateHumanPlayer(`type`: String, name: String, image: String, lang: String)
    extends TypeSystem(`type`) {

  override protected def validType: String = JsonCreateHumanPlayer.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(name: String, image: String, lang: String) = {
    this(
      JsonCreateHumanPlayer.`type`,
      name,
      image,
      lang
    )
  }

}

object JsonCreateHumanPlayer {

  val `type`: String = "createHumanPlayer"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonCreateHumanPlayer] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "name").read[String](AvatarValidation.name) and
      (JsPath \ "image").read[String](AvatarValidation.image) and
      (JsPath \ "lang").read[String](VillageValidation.language)
  )(JsonCreateHumanPlayer.apply _)

  implicit val jsonWrites: OWrites[JsonCreateHumanPlayer] = Json.writes[JsonCreateHumanPlayer]

}
