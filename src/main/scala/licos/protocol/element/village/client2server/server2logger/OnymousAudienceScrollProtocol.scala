package licos.protocol.element.village.client2server.server2logger

import licos.entity.Village
import licos.json.element.village.JsonOnymousAudienceScroll
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.iri.{BaseContext, Context, ScrollContext, ScrollMessage}
import licos.knowledge.{Character, ClientToServer, Data2Knowledge, PrivateChannel, Role, Status}
import licos.protocol.element.village.part.character.StatusCharacterProtocol
import licos.protocol.element.village.part.{AvatarProtocol, BaseProtocol, ChatSettingsProtocol, VillageProtocol}
import licos.util.{LiCOSOnline, TimestampGenerator}

import scala.collection.mutable.ListBuffer

final case class OnymousAudienceScrollProtocol(village:                    Village,
                                               nodeId:                     String,
                                               scrollTop:                  Int,
                                               scrollHeight:               Int,
                                               offsetHeight:               Int,
                                               extensionalDisclosureRange: Seq[StatusCharacterProtocol])
    extends Client2ServerVillageMessageProtocolForLogging {

  val json: Option[JsonOnymousAudienceScroll] = {
    if (village.isAvailable) {
      Some(
        new JsonOnymousAudienceScroll(
          BaseProtocol(
            Seq[Context](BaseContext, ScrollContext),
            ScrollMessage,
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
          AvatarProtocol(
            village.tokenOpt.get,
            village.avatarNameOpt.get,
            village.avatarImageOpt.get
          ).json(LiCOSOnline.stateVillage(village.id)),
          nodeId,
          scrollTop,
          scrollHeight,
          offsetHeight
        )
      )
    } else {
      None
    }
  }
}

object OnymousAudienceScrollProtocol {

  def read(json: JsonOnymousAudienceScroll, village: Village): Option[OnymousAudienceScrollProtocol] = {

    val statusCharacterBuffer = ListBuffer.empty[StatusCharacterProtocol]
    json.base.extensionalDisclosureRange foreach { jsonStatusCharacter: JsonStatusCharacter =>
      val characterOpt: Option[Character] =
        Data2Knowledge.characterOpt(jsonStatusCharacter.name.en, jsonStatusCharacter.id)
      val roleOpt:   Option[Role]   = village.cast.parse(jsonStatusCharacter.role.name.en)
      val statusOpt: Option[Status] = Data2Knowledge.statusOpt(jsonStatusCharacter.status)
      if (characterOpt.nonEmpty && roleOpt.nonEmpty && statusOpt.nonEmpty) {
        statusCharacterBuffer += StatusCharacterProtocol(
          characterOpt.get,
          roleOpt.get,
          statusOpt.get,
          jsonStatusCharacter.isHumanPlayer,
          village.id,
          village.language
        )
      }
    }

    Some(
      OnymousAudienceScrollProtocol(
        village,
        json.nodeId,
        json.scrollTop,
        json.scrollHeight,
        json.offsetHeight,
        statusCharacterBuffer.result
      )
    )
  }

}
