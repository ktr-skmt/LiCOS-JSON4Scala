package licos.json.village

import licos.WerewolfWorld
import licos.bson.village.BsonVillage
import licos.json.village.iri.VillageContext
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}

case class JsonVillage(`@context`: String,
                       `@id`: String,
                       id: Long,
                       name: String,
                       totalNumberOfAgents: Int,
                       lang: String,
                       chatSettings: JsonChatSettings) extends JsonElement {
  def this(id: Long,
           name: String,
           totalNumberOfAgents: Int,
           lang: String,
           chatSettings: JsonChatSettings) = {
    this(
      VillageContext.iri: String,
      WerewolfWorld.stateVillage: String,
      id: Long,
      name: String,
      totalNumberOfAgents: Int,
      lang: String,
      chatSettings: JsonChatSettings
    )
  }

  override def toBson: BsonVillage = {
    new BsonVillage(
      new ObjectId(),
      `@context`: String,
      `@id`: String,
      id: Long,
      name: String,
      totalNumberOfAgents: Int,
      lang: String,
      chatSettings.toBson
    )
  }
}

object JsonVillage {
  implicit val jsonFormat: OFormat[JsonVillage] = Json.format[JsonVillage]

  def apply(id: Long,
            name: String,
            totalNumberOfAgents: Int,
            lang: String,
            chatSettings: JsonChatSettings): JsonVillage = {
    new JsonVillage(
      id: Long,
      name: String,
      totalNumberOfAgents: Int,
      lang: String,
      chatSettings: JsonChatSettings
    )
  }
}