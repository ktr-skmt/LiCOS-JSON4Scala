package licos.json.element.village

import licos.WerewolfWorld
import licos.bson.element.village.BsonChatSettings
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}

case class JsonChatSettings(`@context`: Option[String],
                            `@id`: Option[String],
                            maxNumberOfChatMessages: Int,
                            maxLengthOfUnicodeCodePoints: Int) extends JsonElement {
  def this(villageId: Long,
           maxNumberOfChatMessages: Int,
           maxLengthOfUnicodeCodePoints: Int) = {
    this(
      Option(WerewolfWorld.context("chatSettings")): Option[String],
      Option(WerewolfWorld.state(s"#$villageId/chatSettings")): Option[String],
      maxNumberOfChatMessages: Int,
      maxLengthOfUnicodeCodePoints: Int
    )
  }

  override def toBson: BsonChatSettings = {
    new BsonChatSettings(
      new ObjectId(),
      `@context`.getOrElse(""): String,
      `@id`.getOrElse(""): String,
      maxNumberOfChatMessages: Int,
      maxLengthOfUnicodeCodePoints: Int
    )
  }
}

object JsonChatSettings {
  implicit val jsonFormat: OFormat[JsonChatSettings] = Json.format[JsonChatSettings]

  def apply(villageId: Long,
            maxNumberOfChatMessages: Int,
            maxLengthOfUnicodeCodePoints: Int): JsonChatSettings = {
    new JsonChatSettings(
      villageId: Long,
      maxNumberOfChatMessages: Int,
      maxLengthOfUnicodeCodePoints: Int
    )
  }
}
