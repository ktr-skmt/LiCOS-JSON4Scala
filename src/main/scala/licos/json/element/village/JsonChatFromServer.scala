package licos.json.element.village

import licos.bson.element.village.agent.BsonSimpleAgent
import licos.bson.element.village.{BsonBase, BsonChatFromServer, BsonChatText}
import licos.json.element.village.agent.JsonSimpleAgent
import org.bson.types.ObjectId
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath, Json, OFormat}

case class JsonChatFromServer private (base: JsonBase,
                                       sub: JsonSubChatFromServer) extends JsonElement {
  def this(base: JsonBase,
           agent: JsonSimpleAgent,
           isMine: Boolean,
           id: Int,
           counter: Int,
           limit: Int,
           interval: String,
           text: JsonChatText,
           characterLimit: Int,
           isOver: Boolean) = {
    this(
      base,
      JsonSubChatFromServer(
        agent: JsonSimpleAgent,
        isMine: Boolean,
        id: Int,
        counter: Int,
        limit: Int,
        interval: String,
        text: JsonChatText,
        characterLimit: Int,
        isOver: Boolean
      )
    )
  }

  def fromHostPlayerToAvatar(guid: String): JsonChatFromServer = {
    def isAgentMine: Boolean = guid == base.token
    JsonChatFromServer(
      base.otherAvatar(guid): JsonBase,
      JsonSubChatFromServer(
        agent: JsonSimpleAgent,
        isAgentMine: Boolean,
        id: Int,
        counter: Int,
        limit: Int,
        interval: String,
        text: JsonChatText,
        characterLimit: Int,
        isOver: Boolean
      )
    )
  }

  def agent: JsonSimpleAgent = sub.agent
  def isMine: Boolean = sub.isMine
  def id: Int = sub.id
  def counter: Int = sub.counter
  def limit: Int = sub.limit
  def interval: String = sub.interval
  def text: JsonChatText = sub.text
  def characterLimit: Int = sub.characterLimit
  def isOver: Boolean = sub.isOver

  def appendText(t: String): JsonChatFromServer = {
    JsonChatFromServer(
      base: JsonBase,
      JsonSubChatFromServer(
        agent: JsonSimpleAgent,
        isMine: Boolean,
        id: Int,
        counter: Int,
        limit: Int,
        interval: String,
        JsonChatText(
          s"""${text.`@value`}
             |
             |$t""".stripMargin,
          text.`@language`: String
        ),
        characterLimit: Int,
        isOver: Boolean
      )
    )
  }

  override def toBson: BsonChatFromServer = {
    new BsonChatFromServer(
      new ObjectId(),
      base.toBson: BsonBase,
      agent.toBson: BsonSimpleAgent,
      isMine: Boolean,
      id: Int,
      counter: Int,
      limit: Int,
      interval: String,
      text.toBson: BsonChatText,
      characterLimit: Int,
      isOver: Boolean
    )
  }
}

object JsonChatFromServer {
  def apply(base: JsonBase,
            agent: JsonSimpleAgent,
            isMine: Boolean,
            id: Int,
            counter: Int,
            limit: Int,
            interval: String,
            text: JsonChatText,
            characterLimit: Int,
            isOver: Boolean): JsonChatFromServer = {
    new JsonChatFromServer(
      base: JsonBase,
      agent: JsonSimpleAgent,
      isMine: Boolean,
      id: Int,
      counter: Int,
      limit: Int,
      interval: String,
      text: JsonChatText,
      characterLimit: Int,
      isOver: Boolean
    )
  }

  implicit val jsonFormat: Format[JsonChatFromServer] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubChatFromServer]
    )(JsonChatFromServer.apply, unlift(JsonChatFromServer.unapply))
}

case class JsonSubChatFromServer(agent: JsonSimpleAgent,
                                 isMine: Boolean,
                                 id: Int,
                                 counter: Int,
                                 limit: Int,
                                 interval: String,
                                 text: JsonChatText,
                                 characterLimit: Int,
                                 isOver: Boolean)

object JsonSubChatFromServer {
  implicit val jsonFormat: OFormat[JsonSubChatFromServer] = Json.format[JsonSubChatFromServer]
}