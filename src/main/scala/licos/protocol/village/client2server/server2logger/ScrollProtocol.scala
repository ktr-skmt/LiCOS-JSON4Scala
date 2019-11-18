package licos.protocol.village.client2server.server2logger

import licos.entity.Village
import licos.json.element.village.JsonScroll
import licos.json.element.village.iri.{BaseContext, Context, ScrollContext, ScrollMessage}
import licos.knowledge.{ClientToServer, PrivateChannel}
import licos.protocol.village.part.character.{RoleCharacterProtocol, StatusCharacterProtocol}
import licos.protocol.village.part.{BaseProtocol, ChatSettingsProtocol, VillageProtocol}
import licos.util.TimestampGenerator

final case class ScrollProtocol(
    village:                    Village,
    nodeId:                     String,
    scrollTop:                  Int,
    scrollHeight:               Int,
    offsetHeight:               Int,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) {

  val json: Option[JsonScroll] = {
    if (village.isAvailable) {
      Option(
        new JsonScroll(
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
          RoleCharacterProtocol(
            village.myCharacterOpt.get,
            village.id,
            village.language,
            village.myRoleOpt.get
          ).json,
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
