package licos.protocol.element.village.client2server.server2logger

import java.time.OffsetDateTime

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.client2server.JsonStar
import licos.json.element.village.iri.{Contexts, StarMessage}
import licos.knowledge.{Character, ClientToServer, Data2Knowledge, PrivateChannel, Role, Status}
import licos.protocol.element.village.part.character.{RoleCharacterProtocol, StatusCharacterProtocol}
import licos.protocol.element.village.part.{BaseProtocol, ChatSettingsProtocol, StarInfoProtocol, VillageProtocol}
import licos.util.TimestampGenerator
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer

@SuppressWarnings(Array[String]("org.wartremover.warts.OptionPartial"))
final case class StarProtocol(
    village:                    VillageInfo,
    serverTimestamp:            OffsetDateTime,
    clientTimestamp:            OffsetDateTime,
    isMarked:                   Boolean,
    myCharacter:                Character,
    myRole:                     Role,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Client2ServerVillageMessageProtocolForLogging {

  val json: Option[JsonStar] = {
    Some(
      new JsonStar(
        BaseProtocol(
          Contexts.get(StarMessage),
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
        StarInfoProtocol(
          village.id,
          village.token,
          serverTimestamp,
          clientTimestamp,
          isMarked
        ).json
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonStar =>
      Json.toJson(j)
    }
  }

}

object StarProtocol {

  @SuppressWarnings(
    Array[String](
      "org.wartremover.warts.Any",
      "org.wartremover.warts.MutableDataStructures",
      "org.wartremover.warts.OptionPartial"
    )
  )
  def read(json: JsonStar, villageInfoFromLobby: VillageInfoFromLobby): Option[StarProtocol] = {
    VillageInfoFactory.create(villageInfoFromLobby, json.base) match {
      case Some(village: VillageInfo) =>
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

        val myCharacterOpt: Option[Character] =
          Data2Knowledge.characterOpt(json.myCharacter.name.en, json.myCharacter.id)
        val myRoleOpt: Option[Role] = village.cast.parse(json.myCharacter.role.name.en)

        if (myCharacterOpt.nonEmpty && myRoleOpt.nonEmpty) {

          Some(
            StarProtocol(
              village,
              OffsetDateTime.parse(json.star.serverTimestamp),
              OffsetDateTime.parse(json.star.clientTimestamp),
              json.star.isMarked,
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
