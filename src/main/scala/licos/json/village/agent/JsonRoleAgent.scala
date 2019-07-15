package licos.json.village.agent

import licos.bson.village.BsonName
import licos.bson.village.agent.BsonRoleAgent
import licos.bson.village.role.BsonSimpleRole
import licos.json.village.JsonName
import licos.json.village.iri.AgentContext
import licos.json.village.role.JsonSimpleRole
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}

/**
  * <pre>
  * Created on 2018/01/10.
  * </pre>
  *
  * @author K.Sakamoto
  */
case class JsonRoleAgent(`@context`: String,
                         `@id`: String,
                         id: Long,
                         name: JsonName,
                         image: String,
                         role: JsonSimpleRole)
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
           role: JsonSimpleRole) = {
    this(
      AgentContext.iri: String,
      `@id`: String,
      id: Long,
      name: JsonName,
      image: String,
      role: JsonSimpleRole
    )
  }

  override def toBson: BsonRoleAgent = {
    new BsonRoleAgent(
      new ObjectId(),
      `@context`: String,
      `@id`: String,
      id: Long,
      name.toBson: BsonName,
      image: String,
      role.toBson: BsonSimpleRole
    )
  }
}

object JsonRoleAgent {
  implicit val jsonFormat: OFormat[JsonRoleAgent] = Json.format[JsonRoleAgent]

  def apply(`@id`: String,
            id: Long,
            name: JsonName,
            image: String,
            role: JsonSimpleRole): JsonRoleAgent = {
    new JsonRoleAgent(
      `@id`: String,
      id: Long,
      name: JsonName,
      image: String,
      role: JsonSimpleRole
    )
  }
}
