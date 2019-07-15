package licos.json.village

import licos.bson.village.{BsonBase, BsonChatFromClient, BsonChatText}
import licos.bson.village.agent.{BsonRoleAgent, BsonSimpleAgent}
import licos.json.village.agent.{JsonRoleAgent, JsonSimpleAgent}
import org.bson.types.ObjectId
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath, Json, OFormat}

case class JsonChatFromClient private (base: JsonBase,
                                       sub: JsonSubChatFromClient) extends JsonElement {
  def this(base: JsonBase,
           myAgent: JsonRoleAgent,
           agent: JsonSimpleAgent,
           isMine: Boolean,
           text: JsonChatText,
           characterLimit: Int,
           isOver: Boolean) = {
    this(
      base: JsonBase,
      JsonSubChatFromClient(
        myAgent: JsonRoleAgent,
        agent: JsonSimpleAgent,
        isMine: Boolean,
        text: JsonChatText,
        characterLimit: Int,
        isOver: Boolean
      )
    )
  }

  def myAgent: JsonRoleAgent = sub.myAgent
  def agent: JsonSimpleAgent = sub.agent
  def isMine: Boolean = sub.isMine
  def text: JsonChatText = sub.text
  def characterLimit: Int = sub.characterLimit
  def isOver: Boolean = sub.isOver

  override def toBson: BsonChatFromClient = {
    new BsonChatFromClient(
      new ObjectId(),
      base.toBson: BsonBase,
      myAgent.toBson: BsonRoleAgent,
      agent.toBson: BsonSimpleAgent,
      isMine: Boolean,
      text.toBson: BsonChatText,
      characterLimit: Int,
      isOver: Boolean
    )
  }
}

object JsonChatFromClient {
  def apply(base: JsonBase,
            myAgent: JsonRoleAgent,
            agent: JsonSimpleAgent,
            isMine: Boolean,
            text: JsonChatText,
            characterLimit: Int,
            isOver: Boolean): JsonChatFromClient = {
    new JsonChatFromClient(
      base: JsonBase,
      myAgent: JsonRoleAgent,
      agent: JsonSimpleAgent,
      isMine: Boolean,
      text: JsonChatText,
      characterLimit: Int,
      isOver: Boolean)
  }

  implicit val jsonFormat: Format[JsonChatFromClient] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubChatFromClient]
    )(JsonChatFromClient.apply, unlift(JsonChatFromClient.unapply))
}

case class JsonSubChatFromClient(myAgent: JsonRoleAgent,
                                 agent: JsonSimpleAgent,
                                 isMine: Boolean,
                                 text: JsonChatText,
                                 characterLimit: Int,
                                 isOver: Boolean)

object JsonSubChatFromClient {
  implicit val jsonFormat: OFormat[JsonSubChatFromClient] = Json.format[JsonSubChatFromClient]
}