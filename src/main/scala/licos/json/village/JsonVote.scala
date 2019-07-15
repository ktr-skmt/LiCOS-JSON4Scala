package licos.json.village

import licos.bson.village.{BsonBase, BsonVote}
import licos.bson.village.agent.{BsonRoleAgent, BsonSimpleAgent}
import licos.json.village.agent.{JsonRoleAgent, JsonSimpleAgent}
import org.bson.types.ObjectId
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath, Json, OFormat}

case class JsonVote private (base: JsonBase, sub: JsonSubVote) extends JsonElement {
  def this(base: JsonBase,
           agent: JsonSimpleAgent,
           myAgent: JsonRoleAgent) = this(
    base: JsonBase,
    JsonSubVote(
      agent: JsonSimpleAgent,
      myAgent: JsonRoleAgent
    )
  )
  def agent: JsonSimpleAgent = sub.agent
  def myAgent: JsonRoleAgent = sub.myAgent
  override def toBson: BsonVote = {
    new BsonVote(
      new ObjectId(),
      base.toBson: BsonBase,
      agent.toBson: BsonSimpleAgent,
      myAgent.toBson: BsonRoleAgent
    )
  }
}

object JsonVote {
  def apply(base: JsonBase,
            agent: JsonSimpleAgent,
            myAgent: JsonRoleAgent): JsonVote = new JsonVote(
    base: JsonBase,
    agent: JsonSimpleAgent,
    myAgent: JsonRoleAgent
  )

  implicit val jsonFormat: Format[JsonVote] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubVote]
    )(JsonVote.apply, unlift(JsonVote.unapply))
}

case class JsonSubVote(agent: JsonSimpleAgent, myAgent: JsonRoleAgent)

object JsonSubVote {
  implicit val jsonFormat: OFormat[JsonSubVote] = Json.format[JsonSubVote]
}