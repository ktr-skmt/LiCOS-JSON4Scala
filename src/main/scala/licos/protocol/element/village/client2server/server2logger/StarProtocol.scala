package licos.protocol.element.village.client2server.server2logger

import java.time.OffsetDateTime

import licos.entity.Village
import licos.json.element.village.JsonStar
import licos.json.element.village.iri.{BaseContext, Context, StarContext, StarMessage}
import licos.knowledge.{ClientToServer, PrivateChannel}
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.part.character.{RoleCharacterProtocol, StatusCharacterProtocol}
import licos.protocol.element.village.part.{BaseProtocol, ChatSettingsProtocol, StarInfoProtocol, VillageProtocol}
import licos.util.TimestampGenerator

final case class StarProtocol(
    village:                    Village,
    serverTimestamp:            OffsetDateTime,
    clientTimestamp:            OffsetDateTime,
    isMarked:                   Boolean,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends VillageMessageProtocol {

  val json: Option[JsonStar] = {
    if (village.isAvailable) {
      Some(
        new JsonStar(
          BaseProtocol(
            Seq[Context](BaseContext, StarContext),
            StarMessage,
            VillageProtocol(
              village.id,
              village.name,
              village.cast.totalNumberOfPlayers,
              village.language,
              ChatSettingsProtocol(
                village.id,
                village.maxNumberOfChatMessages,
                village.maxLengthOfUnicodeCodePoints
              )
            ),
            village.tokenOpt.get,
            village.currentPhase,
            village.currentDay,
            village.currentPhase.timeLimit(village.currentDay, village.numberOfAlivePlayers).get,
            village.phaseStartTimeOpt.get,
            None,
            Option(TimestampGenerator.now),
            ClientToServer,
            PrivateChannel,
            extensionalDisclosureRange,
            Nil,
            Nil
          ).json,
          RoleCharacterProtocol(
            village.myCharacterOpt.get,
            village.id,
            village.language,
            village.myRoleOpt.get
          ).json,
          StarInfoProtocol(
            village.id,
            village.tokenOpt.get,
            serverTimestamp,
            clientTimestamp,
            isMarked
          ).json
        )
      )
    } else {
      None
    }
  }

}

object StarProtocol {

  def read(json: JsonStar): Option[StarProtocol] = {

  }

}
