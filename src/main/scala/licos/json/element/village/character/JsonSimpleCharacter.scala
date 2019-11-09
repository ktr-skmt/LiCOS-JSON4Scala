package licos.json.element.village.character

import licos.bson.element.village.BsonName
import licos.bson.element.village.character.BsonSimpleCharacter
import licos.json.element.village.{JsonName, JsonVotingResultDetail, JsonVotingResultSummary}
import licos.json.element.village.iri.CharacterContext
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}

/**
  * <pre>
  * Created on 2018/01/09.
  * </pre>
  *
  * @author K.Sakamoto
  */
final case class JsonSimpleCharacter(`@context`: String, `@id`: String, id: Long, name: JsonName, image: String)
    extends JsonAbstractCharacter(
      `@context`: String,
      `@id`:      String,
      id:         Long,
      name:       JsonName,
      image:      String
    ) {

  def this(`@id`: String, id: Long, name: JsonName, image: String) = {
    this(
      CharacterContext.iri: String,
      `@id`:                String,
      id:                   Long,
      name:                 JsonName,
      image:                String
    )
  }

  override def toBson: BsonSimpleCharacter = {
    new BsonSimpleCharacter(
      new ObjectId(),
      `@context`:  String,
      `@id`:       String,
      id:          Long,
      name.toBson: BsonName,
      image:       String
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
  implicit val jsonFormat: OFormat[JsonSimpleCharacter] = Json.format[JsonSimpleCharacter]

  def apply(`@id`: String, id: Long, name: JsonName, image: String): JsonSimpleCharacter = {
    new JsonSimpleCharacter(`@id`: String, id: Long, name: JsonName, image: String)
  }
}
