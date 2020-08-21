package licos.json.element.village

import licos.json.validation.village.{ChatSettingsValidation, ChatValidation}
import licos.util.{LiCOSOnline, WerewolfWorld}

final case class JsonChatSettings(
    `@context`:                   String,
    `@id`:                        String,
    maxNumberOfChatMessages:      Int,
    maxLengthOfUnicodeCodePoints: Int
) extends JsonElement {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(villageId: Long, maxNumberOfChatMessages: Int, maxLengthOfUnicodeCodePoints: Int) = {
    this(
      WerewolfWorld.context("chatSettings"):        String,
      LiCOSOnline.state(villageId, "chatSettings"): String,
      maxNumberOfChatMessages:                      Int,
      maxLengthOfUnicodeCodePoints:                 Int
    )
  }

}

object JsonChatSettings {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonChatSettings] = (
    (JsPath \ "@context").read[String](ChatSettingsValidation.`@context`) and
      (JsPath \ "@id").read[String](ChatSettingsValidation.`@id`) and
      (JsPath \ "maxNumberOfChatMessages").read[Int](ChatValidation.maxNumberOfChatMessages) and
      (JsPath \ "maxLengthOfUnicodeCodePoints").read[Int](ChatValidation.maxLengthOfUnicodeCodePoints)
  )(JsonChatSettings.apply _)

  implicit val jsonWrites: OWrites[JsonChatSettings] = Json.writes[JsonChatSettings]
}
