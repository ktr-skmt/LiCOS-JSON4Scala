package licos.json.village

import java.util.{List => JList}

import licos.bson.village.{BsonBase, BsonPhase}
import licos.bson.village.agent.BsonAgent
import licos.bson.village.role.BsonRole
import licos.json.village.agent.JsonAgent
import licos.json.village.role.JsonRole
import org.bson.types.ObjectId
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath, Json, OFormat}

import scala.collection.JavaConverters._

case class JsonPhase private (base: JsonBase,
                              sub: JsonSubPhase) extends JsonElement {
  def this(base: JsonBase,
           agent: Seq[JsonAgent],
           role: Seq[JsonRole]) = {
    this(
      base: JsonBase,
      JsonSubPhase(
        agent.sortWith { (a1: JsonAgent, a2: JsonAgent) => a1.name.en < a2.name.en }.sortBy(!_.isMine): Seq[JsonAgent],
        role.sortWith { (r1: JsonRole, r2: JsonRole) => r1.name.en < r2.name.en }: Seq[JsonRole]
      )
    )
  }

  def this(base: JsonBase,
           agent: JList[JsonAgent],
           role: JList[JsonRole]) = {
    this(
      base: JsonBase,
      agent.asScala.sortWith { (a1: JsonAgent, a2: JsonAgent) => a1.name.en < a2.name.en }.sortBy(!_.isMine): Seq[JsonAgent],
      role.asScala.sortWith { (r1: JsonRole, r2: JsonRole) => r1.name.en < r2.name.en }: Seq[JsonRole]
    )
  }

  def agent: Seq[JsonAgent] = sub.agent.sortWith { (a1: JsonAgent, a2: JsonAgent) => a1.name.en < a2.name.en }.sortBy(!_.isMine)
  def role: Seq[JsonRole] = sub.role.sortWith { (r1: JsonRole, r2: JsonRole) => r1.name.en < r2.name.en }

  override def toBson: BsonPhase = {
    new BsonPhase(
      new ObjectId(),
      base.toBson: BsonBase,
      agent.map(_.toBson).asJava: JList[BsonAgent],
      role.map(_.toBson).asJava: JList[BsonRole]
    )
  }

  def exceptExtensionalDisclosureRange: JsonPhase = {
    JsonPhase(
      base.exceptExtensionalDisclosureRange: JsonBase,
      sub: JsonSubPhase
    )
  }
}

object JsonPhase {
  def apply(base: JsonBase,
            agent: Seq[JsonAgent],
            role: Seq[JsonRole]): JsonPhase = {
    new JsonPhase(
      base: JsonBase,
      agent.sortWith { (a1: JsonAgent, a2: JsonAgent) => a1.name.en < a2.name.en }.sortBy(!_.isMine): Seq[JsonAgent],
      role.sortWith { (r1: JsonRole, r2: JsonRole) => r1.name.en < r2.name.en }: Seq[JsonRole]
    )
  }

  implicit val jsonFormat: Format[JsonPhase] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubPhase]
    )(JsonPhase.apply, unlift(JsonPhase.unapply))
}

case class JsonSubPhase(agent: Seq[JsonAgent],
                        role: Seq[JsonRole])

object JsonSubPhase {
  implicit val jsonFormat: OFormat[JsonSubPhase] = Json.format[JsonSubPhase]
}