package licos.protocol.element.village.part

import java.time.OffsetDateTime
import java.util.UUID

import licos.json.element.village.JsonBase
import licos.json.element.village.iri.{Context, Message}
import licos.knowledge.{Channel, Directionality, Phase}
import licos.protocol.element.village.part.character.StatusCharacterProtocol

import scala.concurrent.duration.FiniteDuration

final case class BaseProtocol(
    context:                    Seq[Context],
    message:                    Message,
    village:                    VillageProtocol,
    token:                      UUID,
    phase:                      Phase,
    day:                        Int,
    phaseTimeLimit:             FiniteDuration,
    phaseStartTime:             OffsetDateTime,
    serverTimestamp:            Option[OffsetDateTime],
    clientTimestamp:            Option[OffsetDateTime],
    directionality:             Directionality,
    intensionalDisclosureRange: Channel,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol],
    votingResultsSummary:       Option[Seq[VotingResultSummaryProtocol]],
    votingResultsDetails:       Option[Seq[VotingResultDetailProtocol]]
) {

  lazy val json: JsonBase = JsonBase(
    context.map(_.iri),
    message.iri(village.id),
    village.json,
    token.toString,
    phase.label,
    day,
    phaseTimeLimit.toSeconds.toInt,
    phaseStartTime.toString,
    serverTimestamp.map(_.toString),
    clientTimestamp.map(_.toString),
    directionality.label,
    intensionalDisclosureRange.label,
    extensionalDisclosureRange.map(_.json),
    votingResultsSummary.map(_.map(_.json)),
    votingResultsDetails.map(_.map(_.json))
  )

}
