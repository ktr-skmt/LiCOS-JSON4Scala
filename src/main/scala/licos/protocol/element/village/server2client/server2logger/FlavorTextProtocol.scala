package licos.protocol.element.village.server2client.server2logger

import licos.entity.Village
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.iri.{Contexts, FlavorTextMessage}
import licos.json.element.village.server2client.{JsonChatFromServer, JsonFlavorText}
import licos.knowledge.{Character, Data2Knowledge, FlavorText, PublicChannel, Role, ServerToClient, Status}
import licos.protocol.element.village.part.character.StatusCharacterProtocol
import licos.protocol.element.village.part.{BaseProtocol, ChatSettingsProtocol, VillageProtocol}
import licos.util.TimestampGenerator
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer

@SuppressWarnings(Array[String]("org.wartremover.warts.OptionPartial"))
final case class FlavorTextProtocol(
    village:                    Village,
    flavorText:                 Seq[licos.protocol.element.village.server2client.ChatFromServerProtocol],
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Server2ClientVillageMessageProtocolForLogging {

  val json: Option[JsonFlavorText] = {
    village.currentPhase = FlavorText
    if (village.isAvailable) {
      Some(
        new JsonFlavorText(
          BaseProtocol(
            Contexts.get(FlavorTextMessage),
            FlavorTextMessage,
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
            Option(TimestampGenerator.now),
            None,
            ServerToClient,
            PublicChannel,
            extensionalDisclosureRange,
            None,
            None
          ).json,
          flavorText.flatMap(_.json.toList)
        )
      )
    } else {
      None
    }
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonFlavorText =>
      Json.toJson(j)
    }
  }

}

object FlavorTextProtocol {

  @SuppressWarnings(
    Array[String](
      "org.wartremover.warts.Any",
      "org.wartremover.warts.OptionPartial",
      "org.wartremover.warts.MutableDataStructures"
    )
  )
  def read(json: JsonFlavorText, village: Village): Option[FlavorTextProtocol] = {
    val chatBuffer = ListBuffer.empty[licos.protocol.element.village.server2client.ChatFromServerProtocol]
    json.flavorText foreach { jsonChatFromServer: JsonChatFromServer =>
      licos.protocol.element.village.server2client.ChatFromServerProtocol
        .read(jsonChatFromServer, village) foreach chatBuffer.+=
    }

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
      FlavorTextProtocol(
        village,
        chatBuffer.result,
        statusCharacterBuffer.result
      )
    )
  }

}
