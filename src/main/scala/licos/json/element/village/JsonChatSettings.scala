package licos.json.element.village

import licos.WerewolfWorld
import licos.bson.element.village.BsonChatSettings
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}


case class JsonChatSettings(`@context`: String,
                            `@id`: String,
                            maxNumberOfChatMessages: Int,
                            maxLengthOfUnicodeCodePoints: Int) extends JsonElement {
  def this(villageId: Long,
           maxNumberOfChatMessages: Int,
           maxLengthOfUnicodeCodePoints: Int) = {
    this(
      WerewolfWorld.context("chatSettings"): String,
      WerewolfWorld.state(s"#$villageId/chatSettings"): String,
      maxNumberOfChatMessages: Int,
      maxLengthOfUnicodeCodePoints: Int
    )
  }

  override def toBson: BsonChatSettings = {
    new BsonChatSettings(
      new ObjectId(),
      `@context`: String,
      `@id`: String,
      maxNumberOfChatMessages: Int,
      maxLengthOfUnicodeCodePoints: Int
    )
  }
}

object JsonChatSettings {
  implicit val jsonFormat: OFormat[JsonChatSettings] = Json.format[JsonChatSettings]
}