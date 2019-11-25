package licos.protocol.element.village.client2server.server2logger

import licos.entity.Village
import licos.json.element.village.JsonVote
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.iri.{BaseContext, Context, VoteContext, VoteMessage}
import licos.knowledge.{Character, ClientToServer, Data2Knowledge, PrivateChannel, Role, Status}
import licos.protocol.element.village.part.character.{
  RoleCharacterProtocol,
  SimpleCharacterProtocol,
  StatusCharacterProtocol
}
import licos.protocol.element.village.part.{BaseProtocol, ChatSettingsProtocol, VillageProtocol}
import licos.util.{LiCOSOnline, TimestampGenerator}
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer

final case class VoteProtocol(
    village:                    Village,
    character:                  Character,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Client2ServerVillageMessageProtocolForLogging {

  val json: Option[JsonVote] = {
    if (village.isAvailable) {
      Some(
        new JsonVote(
          BaseProtocol(
            Seq[Context](BaseContext, VoteContext),
            VoteMessage,
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
            village.myRoleOpt.get,
            village.id,
            village.language
          ).json,
          SimpleCharacterProtocol(
            character,
            village.id,
            village.language
          ).json(LiCOSOnline.stateVillage(village.id))
        )
      )
    } else {
      None
    }
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonVote =>
      Json.toJson(j)
    }
  }

}

object VoteProtocol {

  def read(json: JsonVote, village: Village): Option[VoteProtocol] = {
    val characterOpt: Option[Character] = Data2Knowledge.characterOpt(json.character.name.en, json.character.id)
    if (characterOpt.nonEmpty) {

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
        VoteProtocol(
          village,
          characterOpt.get,
          statusCharacterBuffer.result
        )
      )
    } else {
      None
    }
  }

}
