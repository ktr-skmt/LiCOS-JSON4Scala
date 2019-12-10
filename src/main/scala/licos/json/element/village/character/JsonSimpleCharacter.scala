package licos.json.element.village.character

import licos.json.element.village.{JsonName, JsonVotingResultDetail, JsonVotingResultSummary}
import licos.json.element.village.iri.CharacterContext
import licos.json.validation.village.CharacterValidation

final case class JsonSimpleCharacter(`@context`: String, `@id`: String, id: Int, name: JsonName, image: String)
    extends JsonAbstractCharacter(
      `@context`: String,
      `@id`:      String,
      id:         Int,
      name:       JsonName,
      image:      String
    ) {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(`@id`: String, id: Int, name: JsonName, image: String) = {
    this(
      CharacterContext.iri: String,
      `@id`:                String,
      id:                   Int,
      name:                 JsonName,
      image:                String
    )
  }

  def jsonVotingResultDetail(`@id`: String, target: JsonSimpleCharacter): JsonVotingResultDetail = {
    JsonVotingResultDetail(
      `@id`:  String,
      this:   JsonSimpleCharacter,
      target: JsonSimpleCharacter
    )
  }

  def jsonVotingResultSummary(`@id`: String, numberOfVotes: Int, rankOfVotes: Int): JsonVotingResultSummary = {
    JsonVotingResultSummary(
      `@id`:         String,
      this:          JsonSimpleCharacter,
      numberOfVotes: Int,
      rankOfVotes:   Int
    )
  }
}

object JsonSimpleCharacter {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonSimpleCharacter] = (
    (JsPath \ "@context").read[String](CharacterValidation.`@context`) and
      (JsPath \ "@id").read[String](CharacterValidation.`@id`) and
      (JsPath \ "id").read[Int](CharacterValidation.id) and
      (JsPath \ "name").read[JsonName] and
      (JsPath \ "image").read[String](CharacterValidation.image)
  )(JsonSimpleCharacter.apply _)

  implicit val jsonWrites: OWrites[JsonSimpleCharacter] = Json.writes[JsonSimpleCharacter]

}
