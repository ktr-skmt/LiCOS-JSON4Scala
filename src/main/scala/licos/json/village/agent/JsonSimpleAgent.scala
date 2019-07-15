package licos.json.village.agent

import licos.bson.village.BsonName
import licos.bson.village.agent.BsonSimpleAgent
import licos.json.village.{JsonName, JsonVotingResultDetail, JsonVotingResultSummary}
import licos.json.village.iri.AgentContext
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}

/**
  * <pre>
  * Created on 2018/01/09.
  * </pre>
  *
  * @author K.Sakamoto
  */
case class JsonSimpleAgent(`@context`: String,
                           `@id`: String,
                           id: Long,
                           name: JsonName,
                           image: String)
  extends JsonAbstractAgent(
    `@context`: String,
    `@id`: String,
    id: Long,
    name: JsonName,
    image: String
  ) {

  def this(`@id`: String,
           id: Long,
           name: JsonName,
           image: String) = {
    this(
      AgentContext.iri: String,
      `@id`: String,
      id: Long,
      name: JsonName,
      image: String
    )
  }

  override def toBson: BsonSimpleAgent = {
    new BsonSimpleAgent(
      new ObjectId(),
      `@context`: String,
      `@id`: String,
      id: Long,
      name.toBson: BsonName,
      image: String
    )
  }

  def jsonVotingResultDetail(`@id`: String, target: JsonSimpleAgent): JsonVotingResultDetail = {
    JsonVotingResultDetail(
      `@id`: String,
      this: JsonSimpleAgent,
      target: JsonSimpleAgent
    )
  }

  def jsonVotingResultSummary(`@id`: String, numberOfVotes: Int, rankOfVotes: Int): JsonVotingResultSummary = {
    JsonVotingResultSummary(
      `@id`: String,
      this: JsonSimpleAgent,
      numberOfVotes: Int,
      rankOfVotes: Int
    )
  }
}

object JsonSimpleAgent {
  implicit val jsonFormat: OFormat[JsonSimpleAgent] = Json.format[JsonSimpleAgent]

  def apply(`@id`: String,
            id: Long,
            name: JsonName,
            image: String): JsonSimpleAgent = {
    new JsonSimpleAgent(
      `@id`: String,
      id: Long,
      name: JsonName,
      image: String)
  }
}