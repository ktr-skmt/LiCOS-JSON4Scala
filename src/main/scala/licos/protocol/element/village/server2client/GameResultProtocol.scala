package licos.protocol.element.village.server2client

import licos.entity.Village
import licos.json.element.village.JsonGameResult
import licos.json.element.village.character.{JsonResultCharacter, JsonSimpleCharacter}
import licos.json.element.village.role.JsonResultRole
import licos.knowledge.{Character, Data2Knowledge, Outcome, Role, Status}
import licos.protocol.element.village.part.character.{ResultCharacterProtocol, SimpleCharacterProtocol}
import licos.protocol.element.village.part.role.ResultRoleProtocol
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer

final case class GameResultProtocol(
    village:   Village,
    character: Seq[ResultCharacterProtocol],
    role:      Seq[ResultRoleProtocol]
) extends Server2ClientVillageMessageProtocol {

  private val json: Option[JsonGameResult] = {
    server2logger.GameResultProtocol(village, character, role, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map(Json.toJson)

}

object GameResultProtocol {

  def read(json: JsonGameResult, village: Village): Option[GameResultProtocol] = {

    val characterBuffer = ListBuffer.empty[ResultCharacterProtocol]
    val roleBuffer      = ListBuffer.empty[ResultRoleProtocol]

    json.character foreach { jsonResultCharacter: JsonResultCharacter =>
      val characterOpt: Option[Character] =
        Data2Knowledge.characterOpt(jsonResultCharacter.name.en, jsonResultCharacter.id)
      val roleOpt:   Option[Role]    = village.cast.parse(jsonResultCharacter.role.name.en)
      val statusOpt: Option[Status]  = Data2Knowledge.statusOpt(jsonResultCharacter.status)
      val resultOpt: Option[Outcome] = Data2Knowledge.outcomeOpt(jsonResultCharacter.result)
      if (characterOpt.nonEmpty && roleOpt.nonEmpty && statusOpt.nonEmpty && resultOpt.nonEmpty && village.tokenOpt.nonEmpty) {
        characterBuffer += ResultCharacterProtocol(
          characterOpt.get,
          jsonResultCharacter.isMine,
          roleOpt.get,
          statusOpt.get,
          resultOpt.get,
          village.tokenOpt.get,
          jsonResultCharacter.avatar.name,
          jsonResultCharacter.avatar.image,
          village.id,
          village.language
        )
      }
    }

    json.role foreach { jsonResultRole: JsonResultRole =>
      val roleOpt: Option[Role] = Data2Knowledge.roleOpt(jsonResultRole.name.en, jsonResultRole.numberOfPlayers)
      val characterBuffer = ListBuffer.empty[SimpleCharacterProtocol]
      jsonResultRole.character foreach { jsonSimpleCharacter: JsonSimpleCharacter =>
        val characterOpt = Data2Knowledge.characterOpt(jsonSimpleCharacter.name.en, jsonSimpleCharacter.id)
        if (characterOpt.nonEmpty) {
          characterBuffer += SimpleCharacterProtocol(
            characterOpt.get,
            village.id,
            village.language
          )
        }
      }
      if (roleOpt.nonEmpty) {
        roleBuffer += ResultRoleProtocol(
          roleOpt.get,
          jsonResultRole.isMine,
          characterBuffer.result,
          village.id,
          village.language
        )
      }
    }

    Some(
      GameResultProtocol(
        village,
        characterBuffer.result,
        roleBuffer.result
      )
    )
  }

}
