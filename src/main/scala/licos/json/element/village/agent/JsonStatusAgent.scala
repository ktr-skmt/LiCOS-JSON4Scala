package licos.json.element.village.agent

import licos.bson.element.village.{BsonAvatar, BsonName}
import licos.bson.element.village.agent.BsonStatusAgent
import licos.bson.element.village.role.BsonSimpleRole
import licos.json.element.village.iri.AgentContext
import licos.json.element.village.role.JsonSimpleRole
import licos.json.element.village.{JsonAvatar, JsonName}
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}

case class JsonStatusAgent(`@context`: String,
                           `@id`: String,
                           id: Long,
                           name: JsonName,
                           image: String,
                           role: JsonSimpleRole,
                           status: String,
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
           role: JsonSimpleRole,
           status: String,
           avatar: JsonAvatar) = {
    this(
      AgentContext.iri: String,
      `@id`: String,
      id: Long,
      name: JsonName,
      image: String,
      role: JsonSimpleRole,
      status: String,
      avatar: JsonAvatar
    )
  }
  override def toBson: BsonStatusAgent = {
    new BsonStatusAgent(
      new ObjectId(),
      `@context`: String,
      `@id`: String,
      id: Long,
      name.toBson: BsonName,
      image: String,
      role.toBson: BsonSimpleRole,
      status: String,
      avatar.toBson: BsonAvatar
    )
  }
}

object JsonStatusAgent {
  implicit val jsonFormat: OFormat[JsonStatusAgent] = Json.format[JsonStatusAgent]

  def apply(`@id`: String,
            id: Long,
            name: JsonName,
            image: String,
            role: JsonSimpleRole,
            status: String,
            avatar: JsonAvatar): JsonStatusAgent = {
    new JsonStatusAgent(
      `@id`: String,
      id: Long,
      name: JsonName,
      image: String,
      role: JsonSimpleRole,
      status: String,
      avatar: JsonAvatar
    )
  }
}
