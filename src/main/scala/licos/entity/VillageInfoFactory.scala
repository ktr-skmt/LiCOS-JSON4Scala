package licos.entity

import java.time.OffsetDateTime
import java.util.{Locale, UUID}

import licos.json.element.village.JsonBase
import licos.knowledge.{Data2Knowledge, Phase}
import licos.protocol.element.village.part.{ChatSettingsProtocol, VillageProtocol}

import scala.concurrent.duration._

object VillageInfoFactory {

  def create(
      villageInfoFromLobby:         VillageInfoFromLobby,
      id:                           Long,
      name:                         String,
      totalNumberOfPlayers:         Int,
      language:                     Locale,
      maxNumberOfChatMessages:      Int,
      maxLengthOfUnicodeCodePoints: Int,
      token:                        UUID,
      phase:                        Phase,
      day:                          Int,
      phaseTimeLimit:               Int,
      phaseStartTime:               OffsetDateTime,
      clientTimestampOpt:           Option[OffsetDateTime],
      serverTimestampOpt:           Option[OffsetDateTime]
  ): VillageInfo = {
    VillageInfo(
      villageInfoFromLobby,
      VillageProtocol(
        id,
        name,
        totalNumberOfPlayers,
        language,
        ChatSettingsProtocol(
          id,
          maxNumberOfChatMessages,
          maxLengthOfUnicodeCodePoints
        )
      ),
      token,
      phase,
      day,
      phaseTimeLimit.seconds,
      phaseStartTime,
      clientTimestampOpt,
      serverTimestampOpt
    )
  }

  def createOpt(villageInfoFromLobby: VillageInfoFromLobby, jsonBase: JsonBase): Option[VillageInfo] = {
    import cats.implicits._
    Data2Knowledge.phaseOpt(jsonBase.phase).map { phase: Phase =>
      create(
        villageInfoFromLobby,
        jsonBase.village.id,
        jsonBase.village.name,
        jsonBase.village.totalNumberOfPlayers,
        new Locale(jsonBase.village.language),
        jsonBase.village.chatSettings.maxNumberOfChatMessages,
        jsonBase.village.chatSettings.maxLengthOfUnicodeCodePoints,
        UUID.fromString(jsonBase.token),
        phase,
        jsonBase.day,
        jsonBase.phaseTimeLimit,
        OffsetDateTime.parse(jsonBase.phaseStartTime),
        jsonBase.clientTimestamp
          .filterNot(_ === "")
          .map(OffsetDateTime.parse),
        jsonBase.serverTimestamp
          .filterNot(_ === "")
          .map(OffsetDateTime.parse)
      )
    }
  }
}
