package licos.entity

import java.time.OffsetDateTime
import java.util.{Locale, UUID}

import licos.json.element.village.JsonBase
import licos.knowledge.{Data2Knowledge, Phase}
import licos.protocol.element.village.part.{ChatSettingsProtocol, VillageProtocol}

import scala.concurrent.duration._

object VillageInfoFactory {
  def create(villageInfoFromLobby: VillageInfoFromLobby, jsonBase: JsonBase): Option[VillageInfo] = {

    Data2Knowledge.phaseOpt(jsonBase.phase).map { phase: Phase =>
      VillageInfo(
        villageInfoFromLobby,
        VillageProtocol(
          jsonBase.village.id,
          jsonBase.village.name,
          jsonBase.village.totalNumberOfPlayers,
          new Locale(jsonBase.village.language),
          ChatSettingsProtocol(
            jsonBase.village.id,
            jsonBase.village.chatSettings.maxNumberOfChatMessages,
            jsonBase.village.chatSettings.maxLengthOfUnicodeCodePoints
          )
        ),
        UUID.fromString(jsonBase.token),
        phase,
        jsonBase.day,
        jsonBase.phaseTimeLimit.seconds,
        OffsetDateTime.parse(jsonBase.phaseStartTime)
      )
    }

  }
}
