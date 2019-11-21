package licos.protocol.element.village.client2server.server2logger

import licos.entity.Village
import licos.json.element.village.JsonOnymousAudienceScroll
import licos.json.element.village.iri.{BaseContext, Context, ScrollContext, ScrollMessage}
import licos.knowledge.{ClientToServer, PrivateChannel}
import licos.protocol.element.village.part.character.StatusCharacterProtocol
import licos.protocol.element.village.part.{AvatarProtocol, BaseProtocol, ChatSettingsProtocol, VillageProtocol}
import licos.util.{LiCOSOnline, TimestampGenerator}

final case class OnymousAudienceScrollProtocol(village:                    Village,
                                               nodeId:                     String,
                                               scrollTop:                  Int,
                                               scrollHeight:               Int,
                                               offsetHeight:               Int,
                                               extensionalDisclosureRange: Seq[StatusCharacterProtocol]) {

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

  }

}
