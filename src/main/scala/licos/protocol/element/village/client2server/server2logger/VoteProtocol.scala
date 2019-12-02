package licos.protocol.element.village.client2server.server2logger

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.client2server.JsonVote
import licos.json.element.village.iri.{Contexts, VoteMessage}
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

@SuppressWarnings(Array[String]("org.wartremover.warts.OptionPartial"))
final case class VoteProtocol(
    village:                    VillageInfo,
    character:                  Character,
    myCharacter:                Character,
    myRole:                     Role,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Client2ServerVillageMessageProtocolForLogging {

  val json: Option[JsonVote] = {
    Some(
      new JsonVote(
        BaseProtocol(
          Contexts.get(VoteMessage),
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
          village.token,
          village.phase,
          village.day,
          village.phaseTimeLimit,
          village.phaseStartTime,
          None,
          Option(TimestampGenerator.now),
          ClientToServer,
          PrivateChannel,
          extensionalDisclosureRange,
          None,
          None
        ).json,
        RoleCharacterProtocol(
          myCharacter,
          myRole,
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
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonVote =>
      Json.toJson(j)
    }
  }

}

object VoteProtocol {

  @SuppressWarnings(
    Array[String](
      "org.wartremover.warts.Any",
      "org.wartremover.warts.MutableDataStructures",
      "org.wartremover.warts.OptionPartial"
    )
  )
  def read(json: JsonVote, villageInfoFromLobby: VillageInfoFromLobby): Option[VoteProtocol] = {
    VillageInfoFactory.create(villageInfoFromLobby, json.base) match {
      case Some(village: VillageInfo) =>
        val characterOpt: Option[Character] = Data2Knowledge.characterOpt(json.character.name.en, json.character.id)
        val myCharacterOpt: Option[Character] =
          Data2Knowledge.characterOpt(json.myCharacter.name.en, json.myCharacter.id)
        val myRoleOpt: Option[Role] = village.cast.parse(json.myCharacter.role.name.en)
        if (characterOpt.nonEmpty && myCharacterOpt.nonEmpty && myRoleOpt.nonEmpty) {

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
              myCharacterOpt.get,
              myRoleOpt.get,
              statusCharacterBuffer.result
            )
          )
        } else {
          System.err.println(s"""character ${characterOpt.nonEmpty}""")
          System.err.println(s"""myCharacter ${myCharacterOpt.nonEmpty}""")
          System.err.println(s"""myRole ${myRoleOpt.nonEmpty}""")
          None
        }
      case None =>
        System.err.println("test2")
        None
    }
  }

}
