package licos.json.element.lobby.server2client

import licos.json.element.lobby.TypeSystem

final case class JsonHumanPlayerSelectionPage(`type`: String, avatar: Seq[JsonSubAvatarInfo])
    extends TypeSystem(`type`) {

  override protected def validType: String = JsonHumanPlayerSelectionPage.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(avatar: Seq[JsonSubAvatarInfo]) = {
    this(JsonHumanPlayerSelectionPage.`type`, avatar)
  }
}

object JsonHumanPlayerSelectionPage {

  val `type`: String = "humanPlayerSelectionPage"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonHumanPlayerSelectionPage] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "avatar").read[Seq[JsonSubAvatarInfo]]
  )(JsonHumanPlayerSelectionPage.apply _)

  implicit val jsonWrites: OWrites[JsonHumanPlayerSelectionPage] = Json.writes[JsonHumanPlayerSelectionPage]

}
