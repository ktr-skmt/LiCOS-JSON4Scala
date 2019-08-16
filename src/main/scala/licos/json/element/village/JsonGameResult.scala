package licos.json.element.village

import java.util.{List => JList}

import licos.bson.element.village.{BsonBase, BsonGameResult}
import licos.bson.element.village.agent.BsonResultAgent
import licos.bson.element.village.role.BsonResultRole
import licos.json.element.village.agent.JsonResultAgent
import licos.json.element.village.role.JsonResultRole
import org.bson.types.ObjectId
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath, Json, OFormat}

import scala.collection.JavaConverters._

case class JsonGameResult private (base: JsonBase,
                                   sub: JsonSubGameResult) extends JsonElement {
  def this(base: JsonBase,
           agent: Seq[JsonResultAgent],
           role: Seq[JsonResultRole]) = {
    this(
      base: JsonBase,
      JsonSubGameResult(
        agent.sortWith { (a1: JsonResultAgent, a2: JsonResultAgent) => a1.name.en < a2.name.en }.sortBy(!_.isMine): Seq[JsonResultAgent],
        role.sortWith { (r1: JsonResultRole, r2: JsonResultRole) => r1.name.en < r2.name.en }: Seq[JsonResultRole]
      )
    )
  }

  def this(base: JsonBase,
           agent: JList[JsonResultAgent],
           role: JList[JsonResultRole]) = {
    this(base: JsonBase,
         agent.asScala.sortWith { (a1: JsonResultAgent, a2: JsonResultAgent) => a1.name.en < a2.name.en }.sortBy(!_.isMine): Seq[JsonResultAgent],
         role.asScala.sortWith { (r1: JsonResultRole, r2: JsonResultRole) => r1.name.en < r2.name.en }: Seq[JsonResultRole])
  }

  def agent: Seq[JsonResultAgent] = sub.agent.sortWith { (a1: JsonResultAgent, a2: JsonResultAgent) => a1.name.en < a2.name.en }.sortBy(!_.isMine)
  def role: Seq[JsonResultRole] = sub.role.sortWith { (r1: JsonResultRole, r2: JsonResultRole) => r1.name.en < r2.name.en }

  override def toBson: BsonGameResult = {
    new BsonGameResult(
      new ObjectId(),
      base.toBson: BsonBase,
      agent.map(_.toBson).asJava: JList[BsonResultAgent],
      role.map(_.toBson).asJava: JList[BsonResultRole]
    )
  }
}

object JsonGameResult {
  def apply(base: JsonBase,
            agent: Seq[JsonResultAgent],
            role: Seq[JsonResultRole]): JsonGameResult = {
    new JsonGameResult(
      base: JsonBase,
      agent.sortWith { (a1: JsonResultAgent, a2: JsonResultAgent) => a1.name.en < a2.name.en }.sortBy(!_.isMine): Seq[JsonResultAgent],
      role.sortWith { (r1: JsonResultRole, r2: JsonResultRole) => r1.name.en < r2.name.en }: Seq[JsonResultRole]
    )
  }

  implicit val jsonFormat: Format[JsonGameResult] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubGameResult]
    )(JsonGameResult.apply, unlift(JsonGameResult.unapply))
}

case class JsonSubGameResult(agent: Seq[JsonResultAgent],
                             role: Seq[JsonResultRole])

object JsonSubGameResult {
  implicit val jsonFormat: OFormat[JsonSubGameResult] = Json.format[JsonSubGameResult]
}