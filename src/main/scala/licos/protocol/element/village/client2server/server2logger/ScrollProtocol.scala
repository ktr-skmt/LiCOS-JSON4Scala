package licos.protocol.element.village.client2server.server2logger

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.client2server.JsonScroll
import licos.json.element.village.iri.{Contexts, ScrollMessage}
import licos.knowledge.{Architecture, Character, ClientToServer, Data2Knowledge, PrivateChannel, Role, Status}
import licos.protocol.element.village.part.character.{RoleCharacterProtocol, StatusCharacterProtocol}
import licos.protocol.element.village.part.{BaseProtocol, ChatSettingsProtocol, VillageProtocol}
import licos.util.TimestampGenerator
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer

@SuppressWarnings(Array[String]("org.wartremover.warts.OptionPartial"))
final case class ScrollProtocol(
    village:                    VillageInfo,
    nodeId:                     String,
    scrollTop:                  Int,
    scrollHeight:               Int,
    offsetHeight:               Int,
    myCharacter:                Character,
    myRole:                     Role,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Client2ServerVillageMessageProtocolForLogging {

  val json: Option[JsonScroll] = {
    Some(
      new JsonScroll(
        BaseProtocol(
          Contexts.get(ScrollMessage),
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
        nodeId,
        scrollTop,
        scrollHeight,
        offsetHeight
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonScroll =>
      Json.toJson(j)
    }
  }

}

object ScrollProtocol {

  @SuppressWarnings(
    Array[String](
      "org.wartremover.warts.Any",
      "org.wartremover.warts.MutableDataStructures",
      "org.wartremover.warts.OptionPartial"
    )
  )
  def read(json: JsonScroll, villageInfoFromLobby: VillageInfoFromLobby): Option[ScrollProtocol] = {
    VillageInfoFactory.create(villageInfoFromLobby, json.base) match {
      case Some(village: VillageInfo) =>
        val statusCharacterBuffer = ListBuffer.empty[StatusCharacterProtocol]
        json.base.extensionalDisclosureRange foreach { jsonStatusCharacter: JsonStatusCharacter =>
          val characterOpt: Option[Character] =
            Data2Knowledge.characterOpt(jsonStatusCharacter.name.en, jsonStatusCharacter.id)
          val roleOpt:       Option[Role]         = village.cast.parse(jsonStatusCharacter.role.name.en)
          val statusOpt:     Option[Status]       = Data2Knowledge.statusOpt(jsonStatusCharacter.status)
          val playerTypeOpt: Option[Architecture] = Data2Knowledge.architectureOpt(jsonStatusCharacter.playerType)
          if (characterOpt.nonEmpty && roleOpt.nonEmpty && statusOpt.nonEmpty && playerTypeOpt.nonEmpty) {
            statusCharacterBuffer += StatusCharacterProtocol(
              characterOpt.get,
              roleOpt.get,
              statusOpt.get,
              playerTypeOpt.get,
              village.id,
              village.language
            )
          }
        }

        val myCharacterOpt: Option[Character] =
          Data2Knowledge.characterOpt(json.myCharacter.name.en, json.myCharacter.id)
        val myRoleOpt: Option[Role] = village.cast.parse(json.myCharacter.role.name.en)

        if (myCharacterOpt.nonEmpty && myRoleOpt.nonEmpty) {

          Some(
            ScrollProtocol(
              village,
              json.nodeId,
              json.scrollTop,
              json.scrollHeight,
              json.offsetHeight,
              myCharacterOpt.get,
              myRoleOpt.get,
              statusCharacterBuffer.result
            )
          )
        } else {
          None
        }
      case None => None
    }
  }

}
