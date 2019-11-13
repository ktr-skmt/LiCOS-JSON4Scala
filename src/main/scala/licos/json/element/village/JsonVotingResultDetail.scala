package licos.json.element.village

import licos.bson.element.village.BsonVotingResultDetail
import licos.bson.element.village.character.BsonSimpleCharacter
import licos.json.element.village.character.JsonSimpleCharacter
import licos.json.validation.village.VotingResultValidation
import org.bson.types.ObjectId

final case class JsonVotingResultDetail(
    `@id`:           String,
    sourceCharacter: JsonSimpleCharacter,
    targetCharacter: JsonSimpleCharacter
) extends JsonElement {

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

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonVotingResultDetail] = (
    (JsPath \ "@id").read[String](VotingResultValidation.votingResultsDetails.item.`@id`) and
      (JsPath \ "sourceCharacter").read[JsonSimpleCharacter] and
      (JsPath \ "targetCharacter").read[JsonSimpleCharacter]
  )(JsonVotingResultDetail.apply _)

  implicit val jsonWrites: OWrites[JsonVotingResultDetail] = Json.writes[JsonVotingResultDetail]
}
