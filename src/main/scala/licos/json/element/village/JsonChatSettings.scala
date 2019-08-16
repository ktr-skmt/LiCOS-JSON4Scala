package licos.json.element.village

import licos.WerewolfWorld
import licos.bson.element.village.BsonChatSettings
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}

case class JsonChatSettings(`@context`: Option[String],
                            `@id`: Option[String],
                            limit: Int,
                            characterLimit: Int) extends JsonElement {
  def this(villageId: Long,
           limit: Int,
           characterLimit: Int) = {
    this(
      Option(WerewolfWorld.context("chatSettings")): Option[String],
      Option(WerewolfWorld.state(s"#$villageId/chatSettings")): Option[String],
      limit: Int,
      characterLimit: Int
    )
  }

  override def toBson: BsonChatSettings = {
    new BsonChatSettings(
      new ObjectId(),
      `@context`.getOrElse(""): String,
      `@id`.getOrElse(""): String,
      limit: Int,
      characterLimit: Int
    )
  }
}

object JsonChatSettings {
  implicit val jsonFormat: OFormat[JsonChatSettings] = Json.format[JsonChatSettings]

  def apply(villageId: Long,
            limit: Int,
            characterLimit: Int): JsonChatSettings = {
    new JsonChatSettings(
      villageId: Long,
      limit: Int,
      characterLimit: Int
    )
  }
}
