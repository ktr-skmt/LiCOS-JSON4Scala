package licos.json.village.agent

import licos.bson.village.{BsonName, BsonUpdate}
import licos.bson.village.agent.BsonAgent
import licos.json.village.iri.AgentContext
import licos.json.village.{JsonName, JsonUpdate}
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}

/**
  * <pre>
  * Created on 2018/01/09.
  * </pre>
  *
  * @author K.Sakamoto
  */
case class JsonAgent(`@context`: String,
                     `@id`: String,
                     id: Long,
                     name: JsonName,
                     image: String,
                     isMine: Boolean,
                     status: String,
                     update: JsonUpdate,
                     isAChoice: Boolean)
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
           status: String,
           update: JsonUpdate,
           isAChoice: Boolean) = {
    this(
      AgentContext.iri: String,
      `@id`: String,
      id: Long,
      name: JsonName,
      image: String,
      isMine: Boolean,
      status: String,
      update: JsonUpdate,
      isAChoice: Boolean
    )
  }

  override def toBson: BsonAgent = {
    new BsonAgent(
      new ObjectId(),
      `@context`: String,
      `@id`: String,
      id: Long,
      name.toBson: BsonName,
      image: String,
      isMine: Boolean,
      status: String,
      update.toBson: BsonUpdate,
      isAChoice: Boolean
    )
  }
}

object JsonAgent {
  implicit val jsonFormat: OFormat[JsonAgent] = Json.format[JsonAgent]

  def apply(`@id`: String,
            id: Long,
            name: JsonName,
            image: String,
            isMine: Boolean,
            status: String,
            update: JsonUpdate,
            isAChoice: Boolean): JsonAgent = {
    new JsonAgent(
      `@id`: String,
      id: Long,
      name: JsonName,
      image: String,
      isMine: Boolean,
      status: String,
      update: JsonUpdate,
      isAChoice: Boolean)
  }
}