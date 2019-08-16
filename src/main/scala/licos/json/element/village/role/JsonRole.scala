package licos.json.element.village.role

import java.util.{List => JList}

import licos.bson.element.village.{BsonBoardPolarity, BsonName}
import licos.bson.element.village.role.BsonRole
import licos.json.element.village.iri.RoleContext
import licos.json.element.village.{JsonBoardPolarity, JsonName}
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
case class JsonRole(`@context`: String,
                    `@id`: String,
                    name: JsonName,
                    image: String,
                    isMine: Boolean,
                    numberOfAgents: Int,
                    board: Seq[JsonBoardPolarity]) extends JsonAbstractRole(
    `@context`: String,
    `@id`: String,
    name: JsonName,
    image: String
  ) {

  def this(`@context`: String,
           `@id`: String,
           name: JsonName,
           image: String,
           isMine: Boolean,
           numberOfAgents: Int,
           board: JList[JsonBoardPolarity]) = {
    this(
      `@context`: String,
      `@id`: String,
      name: JsonName,
      image: String,
      isMine: Boolean,
      numberOfAgents: Int,
      board.asScala: Seq[JsonBoardPolarity]
    )
  }

  def this(`@id`: String,
           name: JsonName,
           image: String,
           isMine: Boolean,
           numberOfAgents: Int,
           board: Seq[JsonBoardPolarity]) = {
    this(
      RoleContext.iri: String,
      `@id`: String,
      name: JsonName,
      image: String,
      isMine: Boolean,
      numberOfAgents: Int,
      board: Seq[JsonBoardPolarity]
    )
  }

  def toBson: BsonRole = {
    new BsonRole(
      new ObjectId(),
      `@context`: String,
      `@id`: String,
      name.toBson: BsonName,
      image: String,
      isMine: Boolean,
      numberOfAgents: Int,
      board.map(_.toBson).asJava: JList[BsonBoardPolarity]
    )
  }
}

object JsonRole {
  implicit val jsonFormat: OFormat[JsonRole] = Json.format[JsonRole]

  def apply(`@id`: String,
            name: JsonName,
            image: String,
            isMine: Boolean,
            numberOfAgents: Int,
            board: Seq[JsonBoardPolarity]): JsonRole = {
    new JsonRole(
      `@id`: String,
      name: JsonName,
      image: String,
      isMine: Boolean,
      numberOfAgents: Int,
      board: Seq[JsonBoardPolarity]
    )
  }
}