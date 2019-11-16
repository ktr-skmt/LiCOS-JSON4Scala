package licos.protocol.village.part

import java.time.OffsetDateTime
import java.util.UUID

import licos.json.element.village.JsonBase
import licos.json.element.village.iri.{Context, Message}
import licos.knowledge.{Channel, Directionality, Phase}
import licos.protocol.village.part.character.StatusCharacterProtocol

final case class BaseProtocol(context:                    Seq[Context],
                              message:                    Message,
                              village:                    VillageProtocol,
                              token:                      UUID,
                              phase:                      Phase,
                              day:                        Int,
                              phaseTimeLimit:             Int,
                              phaseStartTime:             OffsetDateTime,
                              serverTimestamp:            Option[OffsetDateTime],
                              clientTimestamp:            Option[OffsetDateTime],
                              directionality:             Directionality,
                              intensionalDisclosureRange: Channel,
                              extensionalDisclosureRange: Seq[StatusCharacterProtocol],
                              votingResultsSummary:       Seq[VotingResultSummaryProtocol],
                              votingResultsDetails:       Seq[VotingResultDetailProtocol]) {

  val json: JsonBase = JsonBase(
    context.map(_.iri),
    message.iri(village.id),
    village.json,
    token.toString,
    phase.label,
    day,
    phaseTimeLimit,
    phaseStartTime.toString,
    serverTimestamp.map(_.toString),
    clientTimestamp.map(_.toString),
    directionality.label,
    intensionalDisclosureRange.label,
    extensionalDisclosureRange.map(_.json),
    votingResultsSummary.map(_.json),
    votingResultsDetails.map(_.json)
  )

}
