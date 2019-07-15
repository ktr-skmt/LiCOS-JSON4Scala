package licos.json.village

import java.util.{List => JList}

import licos.bson.village.{BsonBase, BsonVillage, BsonVotingResultDetail, BsonVotingResultSummary}
import licos.bson.village.agent.BsonStatusAgent
import licos.json.village.agent.JsonStatusAgent
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}

import scala.collection.JavaConverters._

case class JsonBase(`@context`: Seq[String],
                    `@id`: String,
                    village: JsonVillage,
                    token: String,
                    phase: String,
                    date: Int,
                    phaseTimeLimit: Int,
                    phaseStartTime: String,
                    serverTimestamp: String,
                    clientTimestamp: String,
                    directionality: String,
                    intensionalDisclosureRange: String,
                    extensionalDisclosureRange: Seq[JsonStatusAgent],
                    votingResultsSummary: Option[Seq[JsonVotingResultSummary]],
                    votingResultsDetails: Option[Seq[JsonVotingResultDetail]]) extends JsonElement {
  def this(`@context`: JList[String],
           `@id`: String,
           village: JsonVillage,
           token: String,
           phase: String,
           date: Int,
           phaseTimeLimit: Int,
           phaseStartTime: String,
           serverTimestamp: String,
           clientTimestamp: String,
           directionality: String,
           intensionalDisclosureRange: String,
           extensionalDisclosureRange: JList[JsonStatusAgent],
           votingResultsSummary: JList[JsonVotingResultSummary],
           votingResultsDetails: JList[JsonVotingResultDetail]) = {
    this(`@context`.asScala: Seq[String],
         `@id`: String,
         village: JsonVillage,
         token: String,
         phase: String,
         date: Int,
         phaseTimeLimit: Int,
         phaseStartTime: String,
         serverTimestamp: String,
         clientTimestamp: String,
         directionality: String,
         intensionalDisclosureRange: String,
         extensionalDisclosureRange.asScala: Seq[JsonStatusAgent],
         Option(votingResultsSummary.asScala): Option[Seq[JsonVotingResultSummary]],
         Option(votingResultsDetails.asScala): Option[Seq[JsonVotingResultDetail]]
    )
  }

  def otherAvatar(otherAvatarToken: String): JsonBase = {
    if (token == otherAvatarToken) {
      this
    } else {
      JsonBase(
        `@context`: Seq[String],
        `@id`: String,
        village: JsonVillage,
        otherAvatarToken: String,
        phase: String,
        date: Int,
        phaseTimeLimit: Int,
        phaseStartTime: String,
        serverTimestamp: String,
        clientTimestamp: String,
        directionality: String,
        intensionalDisclosureRange: String,
        extensionalDisclosureRange: Seq[JsonStatusAgent],
        votingResultsSummary: Option[Seq[JsonVotingResultSummary]],
        votingResultsDetails: Option[Seq[JsonVotingResultDetail]]
      )
    }
  }

  override def toBson: BsonBase = {
    val bsonVotingResultSummary: JList[BsonVotingResultSummary] = {
      votingResultsSummary match {
        case Some(summaries: Seq[JsonVotingResultSummary]) =>
          summaries.map(_.toBson).asJava
        case None =>
          new java.util.LinkedList[BsonVotingResultSummary]()
      }
    }
    val bsonVotingResultDetail: JList[BsonVotingResultDetail] = {
      votingResultsDetails match {
        case Some(details: Seq[JsonVotingResultDetail]) =>
          details.map(_.toBson).asJava
        case None =>
          new java.util.LinkedList[BsonVotingResultDetail]()
      }
    }
    new BsonBase(
      new ObjectId(),
      `@context`.asJava: JList[String],
      `@id`: String,
      village.toBson: BsonVillage,
      token: String,
      phase: String,
      date: Int,
      phaseTimeLimit: Int,
      phaseStartTime: String,
      serverTimestamp: String,
      clientTimestamp: String,
      directionality: String,
      intensionalDisclosureRange: String,
      extensionalDisclosureRange.map(_.toBson).asJava: JList[BsonStatusAgent],
      bsonVotingResultSummary: JList[BsonVotingResultSummary],
      bsonVotingResultDetail: JList[BsonVotingResultDetail]
    )
  }

  def exceptExtensionalDisclosureRange: JsonBase = {
    JsonBase(
      `@context`: Seq[String],
      `@id`: String,
      village: JsonVillage,
      token: String,
      phase: String,
      date: Int,
      phaseTimeLimit: Int,
      phaseStartTime: String,
      serverTimestamp: String,
      clientTimestamp: String,
      directionality: String,
      intensionalDisclosureRange: String,
      Nil: Seq[JsonStatusAgent],
      votingResultsSummary: Option[Seq[JsonVotingResultSummary]],
      votingResultsDetails: Option[Seq[JsonVotingResultDetail]]
    )
  }
}

object JsonBase {
  implicit val jsonFormat: OFormat[JsonBase] = Json.format[JsonBase]
}