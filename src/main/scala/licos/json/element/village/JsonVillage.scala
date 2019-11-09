package licos.json.element.village

import licos.WerewolfWorld
import licos.bson.element.village.BsonVillage
import licos.json.element.village.iri.VillageContext
import org.bson.types.ObjectId

final case class JsonVillage(
    `@context`:              String,
    `@id`:                   String,
    id:                      Long,
    name:                    String,
    totalNumberOfCharacters: Int,
    lang:                    String,
    chatSettings:            JsonChatSettings
) extends JsonElement {
  def this(id: Long, name: String, totalNumberOfCharacters: Int, lang: String, chatSettings: JsonChatSettings) = {
    this(
      VillageContext.iri:         String,
      WerewolfWorld.stateVillage: String,
      id:                         Long,
      name:                       String,
      totalNumberOfCharacters:    Int,
      lang:                       String,
      chatSettings:               JsonChatSettings
    )
  }

  override def toBson: BsonVillage = {
    new BsonVillage(
      new ObjectId(),
      `@context`:              String,
      `@id`:                   String,
      id:                      Long,
      name:                    String,
      totalNumberOfCharacters: Int,
      lang:                    String,
      chatSettings.toBson
    )
  }
}

object JsonVillage {
  import play.api.libs.json._
  import play.api.libs.json.Reads._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonVillage] = (
    (JsPath \ "@context").read[String](pattern("""https://werewolf.world/village/context/0.3/village.jsonld""".r)) and
      (JsPath \ "@id").read[String](pattern("""https://licos.online/state/0.3/village""".r)) and
      (JsPath \ "id").read[Long](min(0L)) and
      (JsPath \ "name").read[String](pattern("""[A-Za-z0-9]{5,15}""".r)) and
      (JsPath \ "totalNumberOfCharacters").read[Int](min(4) keepAnd max(15)) and
      (JsPath \ "lang").read[String](pattern("""[a-z]{2}""".r)) and
      (JsPath \ "chatSettings").read[JsonChatSettings]
  )(JsonVillage.apply _)
  implicit val jsonWrites: OWrites[JsonVillage] = Json.writes[JsonVillage]
}
