package licos.json.element.village.role

import java.util.{List => JList}

import licos.bson.element.village.BsonName
import licos.bson.element.village.agent.BsonSimpleAgent
import licos.bson.element.village.role.BsonResultRole
import licos.json.element.village.JsonName
import licos.json.element.village.agent.JsonSimpleAgent
import licos.json.element.village.iri.RoleContext
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}

import scala.collection.JavaConverters._

/**
  * <pre>
  * Created on 2018/01/09.
  * </pre>
  *
  * @author K.Sakamoto
  */
case class JsonResultRole(`@context`: String,
                          `@id`: String,
                          isMine: Boolean,
                          name: JsonName,
                          image: String,
                          numberOfAgents: Int,
                          agent: Seq[JsonSimpleAgent])
  extends JsonAbstractRole(
    `@context`: String,
    `@id`: String,
    name: JsonName,
    image: String
  ) {

  def this(`@id`: String,
           isMine: Boolean,
           name: JsonName,
           image: String,
           numberOfAgents: Int,
           agent: Seq[JsonSimpleAgent]) = {
    this(
      RoleContext.iri: String,
      `@id`: String,
      isMine: Boolean,
      name: JsonName,
      image: String,
      numberOfAgents: Int,
      agent: Seq[JsonSimpleAgent]
    )
  }

  def this(`@context`: String,
           `@id`: String,
           isMine: Boolean,
           name: JsonName,
           image: String,
           numberOfAgents: Int,
           agent: JList[JsonSimpleAgent]) = {
    this(
      `@context`: String,
      `@id`: String,
      isMine: Boolean,
      name: JsonName,
      image: String,
      numberOfAgents: Int,
      agent.asScala: Seq[JsonSimpleAgent]
    )
  }

  def toBson: BsonResultRole = {
    new BsonResultRole(
      new ObjectId(),
      `@context`: String,
      `@id`: String,
      isMine: Boolean,
      name.toBson: BsonName,
      image: String,
      numberOfAgents: Int,
      agent.map(_.toBson).asJava: JList[BsonSimpleAgent]
    )
  }
}

object JsonResultRole {
  implicit val jsonFormat: OFormat[JsonResultRole] = Json.format[JsonResultRole]

  def apply(`@id`: String,
            isMine: Boolean,
            name: JsonName,
            image: String,
            numberOfAgents: Int,
            agent: Seq[JsonSimpleAgent]): JsonResultRole = {
    new JsonResultRole(
      `@id`: String,
      isMine: Boolean,
      name: JsonName,
      image: String,
      numberOfAgents: Int,
      agent: Seq[JsonSimpleAgent]
    )
  }
}