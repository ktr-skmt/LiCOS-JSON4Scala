package licos.json.element.village

import java.util.{List => JList}

import licos.bson.element.village.character.BsonStatusCharacter
import licos.bson.element.village.{BsonBase, BsonVillage, BsonVotingResultDetail, BsonVotingResultSummary}
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.validation.village.{AvatarValidation, BaseValidation, TimeValidation}
import org.bson.types.ObjectId

import scala.collection.JavaConverters._

final case class JsonBase(
    `@context`:                 Seq[String],
    `@id`:                      String,
    village:                    JsonVillage,
    token:                      String,
    phase:                      String,
    day:                        Int,
    phaseTimeLimit:             Int,
    phaseStartTime:             String,
    serverTimestamp:            String,
    clientTimestamp:            String,
    directionality:             String,
    intensionalDisclosureRange: String,
    extensionalDisclosureRange: Seq[JsonStatusCharacter],
    votingResultsSummary:       Option[Seq[JsonVotingResultSummary]],
    votingResultsDetails:       Option[Seq[JsonVotingResultDetail]]
) extends JsonElement {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(
      `@context`:                 JList[String],
      `@id`:                      String,
      village:                    JsonVillage,
      token:                      String,
      phase:                      String,
      day:                        Int,
      phaseTimeLimit:             Int,
      phaseStartTime:             String,
      serverTimestamp:            String,
      clientTimestamp:            String,
      directionality:             String,
      intensionalDisclosureRange: String,
      extensionalDisclosureRange: JList[JsonStatusCharacter],
      votingResultsSummary:       JList[JsonVotingResultSummary],
      votingResultsDetails:       JList[JsonVotingResultDetail]
  ) = {

    this(
      `@context`.asScala:                   Seq[String],
      `@id`:                                String,
      village:                              JsonVillage,
      token:                                String,
      phase:                                String,
      day:                                  Int,
      phaseTimeLimit:                       Int,
      phaseStartTime:                       String,
      serverTimestamp:                      String,
      clientTimestamp:                      String,
      directionality:                       String,
      intensionalDisclosureRange:           String,
      extensionalDisclosureRange.asScala:   Seq[JsonStatusCharacter],
      Option(votingResultsSummary.asScala): Option[Seq[JsonVotingResultSummary]],
      Option(votingResultsDetails.asScala): Option[Seq[JsonVotingResultDetail]]
    )
  }

  def otherAvatar(otherAvatarToken: String): JsonBase = {
    import cats.implicits._
    if (token === otherAvatarToken) {
      this
    } else {
      JsonBase(
        `@context`:                 Seq[String],
        `@id`:                      String,
        village:                    JsonVillage,
        otherAvatarToken:           String,
        phase:                      String,
        day:                        Int,
        phaseTimeLimit:             Int,
        phaseStartTime:             String,
        serverTimestamp:            String,
        clientTimestamp:            String,
        directionality:             String,
        intensionalDisclosureRange: String,
        extensionalDisclosureRange: Seq[JsonStatusCharacter],
        votingResultsSummary:       Option[Seq[JsonVotingResultSummary]],
        votingResultsDetails:       Option[Seq[JsonVotingResultDetail]]
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
      `@context`.asJava:                               JList[String],
      `@id`:                                           String,
      village.toBson:                                  BsonVillage,
      token:                                           String,
      phase:                                           String,
      day:                                             Int,
      phaseTimeLimit:                                  Int,
      phaseStartTime:                                  String,
      serverTimestamp:                                 String,
      clientTimestamp:                                 String,
      directionality:                                  String,
      intensionalDisclosureRange:                      String,
      extensionalDisclosureRange.map(_.toBson).asJava: JList[BsonStatusCharacter],
      bsonVotingResultSummary:                         JList[BsonVotingResultSummary],
      bsonVotingResultDetail:                          JList[BsonVotingResultDetail]
    )
  }

  def exceptExtensionalDisclosureRange: JsonBase = {
    JsonBase(
      `@context`:                 Seq[String],
      `@id`:                      String,
      village:                    JsonVillage,
      token:                      String,
      phase:                      String,
      day:                        Int,
      phaseTimeLimit:             Int,
      phaseStartTime:             String,
      serverTimestamp:            String,
      clientTimestamp:            String,
      directionality:             String,
      intensionalDisclosureRange: String,
      Nil:                        Seq[JsonStatusCharacter],
      votingResultsSummary:       Option[Seq[JsonVotingResultSummary]],
      votingResultsDetails:       Option[Seq[JsonVotingResultDetail]]
    )
  }
}

object JsonBase {

  import play.api.libs.json._
  import play.api.libs.json.Reads._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonBase] = (
    (JsPath \ "@context").read[Seq[String]](Reads.seq[String](BaseValidation.`@context`.item)) and
      (JsPath \ "@id").read[String](BaseValidation.`@id`) and
      (JsPath \ "village").read[JsonVillage] and
      (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "phase").read[String](TimeValidation.phase) and
      (JsPath \ "day").read[Int](TimeValidation.day) and
      (JsPath \ "phaseTimeLimit").read[Int](BaseValidation.phaseTimeLimit) and
      (JsPath \ "phaseStartTime").read[String](BaseValidation.phaseStartTime) and
      (JsPath \ "serverTimestamp").read[String](BaseValidation.serverTimestamp) and
      (JsPath \ "clientTimestamp").read[String](BaseValidation.clientTimestamp) and
      (JsPath \ "directionality").read[String](BaseValidation.directionality) and
      (JsPath \ "intensionalDisclosureRange").read[String](BaseValidation.intensionalDisclosureRange) and
      (JsPath \ "extensionalDisclosureRange").read[Seq[JsonStatusCharacter]] and
      (JsPath \ "votingResultsSummary").readNullable[Seq[JsonVotingResultSummary]] and
      (JsPath \ "votingResultsDetails").readNullable[Seq[JsonVotingResultDetail]]
  )(JsonBase.apply _)

  implicit val jsonWrites: OWrites[JsonBase] = Json.writes[JsonBase]
}
