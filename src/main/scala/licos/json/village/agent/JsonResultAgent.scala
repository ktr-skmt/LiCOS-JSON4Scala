package licos.json.village.agent

import licos.bson.village.{BsonAvatar, BsonName}
import licos.bson.village.agent.BsonResultAgent
import licos.bson.village.role.BsonSimpleRole
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}
import licos.json.village.iri.AgentContext
import licos.json.village.role.JsonSimpleRole
import licos.json.village.{JsonAvatar, JsonName}

/**
  * <pre>
  * Created on 2018/01/09.
  * </pre>
  *
  * @author K.Sakamoto
  */
case class JsonResultAgent(`@context`: String,
                           `@id`: String,
                           id: Long,
                           name: JsonName,
                           image: String,
                           isMine: Boolean,
                           role: JsonSimpleRole,
                           status: String,
                           result: String,
                           avatar: JsonAvatar)
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
           image: String,
           isMine: Boolean,
           role: JsonSimpleRole,
           status: String,
           result: String,
           avatar: JsonAvatar) = {
    this(
      AgentContext.iri: String,
      `@id`: String,
      id: Long,
      name: JsonName,
      image: String,
      isMine: Boolean,
      role: JsonSimpleRole,
      status: String,
      result: String,
      avatar: JsonAvatar
    )
  }

  override def toBson: BsonResultAgent = {
    new BsonResultAgent(
      new ObjectId(),
      `@context`: String,
      `@id`: String,
      id: Long,
      name.toBson: BsonName,
      image: String,
      isMine: Boolean,
      role.toBson: BsonSimpleRole,
      status: String,
      result: String,
      avatar.toBson: BsonAvatar
    )
  }
}

object JsonResultAgent {
  implicit val jsonFormat: OFormat[JsonResultAgent] = Json.format[JsonResultAgent]

  def apply(`@id`: String,
            id: Long,
            name: JsonName,
            image: String,
            isMine: Boolean,
            role: JsonSimpleRole,
            status: String,
            result: String,
            avatar: JsonAvatar): JsonResultAgent = {
    new JsonResultAgent(
      `@id`: String,
      id: Long,
      name: JsonName,
      image: String,
      isMine: Boolean,
      role: JsonSimpleRole,
      status: String,
      result: String,
      avatar: JsonAvatar
    )
  }
}