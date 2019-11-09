package licos.json.element.village

import licos.WerewolfWorld
import licos.bson.element.village.BsonChatSettings
import org.bson.types.ObjectId
import play.api.libs.json.{JsPath, Json, OWrites, Reads}

final case class JsonChatSettings(
    `@context`:                   String,
    `@id`:                        String,
    maxNumberOfChatMessages:      Int,
    maxLengthOfUnicodeCodePoints: Int
) extends JsonElement {
  def this(villageId: Long, maxNumberOfChatMessages: Int, maxLengthOfUnicodeCodePoints: Int) = {
    this(
      WerewolfWorld.context("chatSettings"):            String,
      WerewolfWorld.state(s"#$villageId/chatSettings"): String,
      maxNumberOfChatMessages:                          Int,
      maxLengthOfUnicodeCodePoints:                     Int
    )
  }

  override def toBson: BsonChatSettings = {
    new BsonChatSettings(
      new ObjectId(),
      `@context`:                   String,
      `@id`:                        String,
      maxNumberOfChatMessages:      Int,
      maxLengthOfUnicodeCodePoints: Int
    )
  }
}

object JsonChatSettings {
  import play.api.libs.json._
  import play.api.libs.json.Reads._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonChatSettings] = (
    (JsPath \ "@context").read[String](pattern("""https://werewolf.world/village/context/0.3/chatSettings.jsonld""".r)) and
      (JsPath \ "@id").read[String](pattern("""https://licos.online/state/0.3/village#[0-9]+/chatSettings""".r)) and
      (JsPath \ "maxNumberOfChatMessages").read[Int](min(1)) and
      (JsPath \ "maxLengthOfUnicodeCodePoints").read[Int](min(1))
  )(JsonChatSettings.apply _)

  implicit val jsonWrites: OWrites[JsonChatSettings] = Json.writes[JsonChatSettings]
}
