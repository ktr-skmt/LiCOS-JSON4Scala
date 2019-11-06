package licos.json.element.village

import licos.bson.element.village.BsonVotingResultDetail
import licos.bson.element.village.character.BsonSimpleCharacter
import licos.json.element.village.character.JsonSimpleCharacter
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}

case class JsonVotingResultDetail(`@id`:           String,
                                  sourceCharacter: JsonSimpleCharacter,
                                  targetCharacter: JsonSimpleCharacter)
    extends JsonElement {
  override def toBson: BsonVotingResultDetail = {
    new BsonVotingResultDetail(
      new ObjectId(),
      `@id`:                  String,
      sourceCharacter.toBson: BsonSimpleCharacter,
      targetCharacter.toBson: BsonSimpleCharacter
    )
  }
}

object JsonVotingResultDetail {
  implicit val jsonFormat: OFormat[JsonVotingResultDetail] = Json.format[JsonVotingResultDetail]
}
