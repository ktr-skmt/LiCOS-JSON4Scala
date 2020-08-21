package licos.json.element.village

import licos.json.element.village.iri.VillageContext
import licos.json.validation.village.VillageValidation
import licos.util.LiCOSOnline

final case class JsonVillage(
    `@context`:           String,
    `@id`:                String,
    id:                   Long,
    name:                 String,
    totalNumberOfPlayers: Int,
    language:             String,
    chatSettings:         JsonChatSettings
) extends JsonElement {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(id: Long, name: String, totalNumberOfPlayers: Int, language: String, chatSettings: JsonChatSettings) = {
    this(
      VillageContext.iri:       String,
      LiCOSOnline.stateVillage: String,
      id:                       Long,
      name:                     String,
      totalNumberOfPlayers:     Int,
      language:                 String,
      chatSettings:             JsonChatSettings
    )
  }

}

object JsonVillage {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonVillage] = (
    (JsPath \ "@context").read[String](VillageValidation.`@context`) and
      (JsPath \ "@id").read[String](VillageValidation.`@id`) and
      (JsPath \ "id").read[Long](VillageValidation.id) and
      (JsPath \ "name").read[String](VillageValidation.name) and
      (JsPath \ "totalNumberOfPlayers").read[Int](VillageValidation.totalNumberOfPlayers) and
      (JsPath \ "language").read[String](VillageValidation.language) and
      (JsPath \ "chatSettings").read[JsonChatSettings]
  )(JsonVillage.apply _)

  implicit val jsonWrites: OWrites[JsonVillage] = Json.writes[JsonVillage]
}
