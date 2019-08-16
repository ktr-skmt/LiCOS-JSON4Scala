package licos.json.element.village

import licos.bson.element.village.role.BsonSimpleRole
import licos.bson.element.village.{BsonBase, BsonBoard}
import licos.bson.element.village.agent.{BsonRoleAgent, BsonSimpleAgent}
import licos.json.element.village.agent.{JsonRoleAgent, JsonSimpleAgent}
import licos.json.element.village.role.JsonSimpleRole
import org.bson.types.ObjectId
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath, Json, OFormat}

case class JsonBoard private (base: JsonBase,
                              sub: JsonSubBoard) extends JsonElement {
  def this(base: JsonBase,
           myAgent: JsonRoleAgent,
           agent: JsonSimpleAgent,
           role: JsonSimpleRole,
           prediction: String) = {
    this(
      base,
      JsonSubBoard(
        myAgent: JsonRoleAgent,
        agent: JsonSimpleAgent,
        role: JsonSimpleRole,
        prediction: String
      )
    )
  }

  def myAgent: JsonRoleAgent = sub.myAgent
  def agent: JsonSimpleAgent = sub.agent
  def role: JsonSimpleRole = sub.role
  def prediction: String = sub.prediction

  override def toBson: BsonBoard = {
    new BsonBoard(
      new ObjectId(),
      base.toBson: BsonBase,
      myAgent.toBson: BsonRoleAgent,
      agent.toBson: BsonSimpleAgent,
      role.toBson: BsonSimpleRole,
      prediction: String
    )
  }
}

object JsonBoard {
  def apply(base: JsonBase,
            myAgent: JsonRoleAgent,
            agent: JsonSimpleAgent,
            role: JsonSimpleRole,
            prediction: String): JsonBoard = {
    new JsonBoard(
      base: JsonBase,
      myAgent: JsonRoleAgent,
      agent: JsonSimpleAgent,
      role: JsonSimpleRole,
      prediction: String
    )
  }

  implicit val jsonFormat: Format[JsonBoard] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubBoard]
    )(JsonBoard.apply, unlift(JsonBoard.unapply))
}

case class JsonSubBoard(myAgent: JsonRoleAgent,
                        agent: JsonSimpleAgent,
                        role: JsonSimpleRole,
                        prediction: String)

object JsonSubBoard {
  implicit val jsonFormat: OFormat[JsonSubBoard] = Json.format[JsonSubBoard]
}