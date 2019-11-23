package licos.protocol.element.village.server2client

import licos.entity.Village
import licos.json.element.village.{JsonBoardResult, JsonPhase}
import licos.json.element.village.character.JsonCharacter
import licos.json.element.village.role.JsonRole
import licos.knowledge.{Character, Data2Knowledge, Morning, Phase, PolarityMark, Role, Status}
import licos.protocol.element.village.part.{BoardResultProtocol, UpdateProtocol}
import licos.protocol.element.village.part.character.CharacterProtocol
import licos.protocol.element.village.part.role.RoleProtocol
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer

final case class FirstMorningPhaseProtocol(
    village:   Village,
    character: Seq[CharacterProtocol],
    role:      Seq[RoleProtocol]
) extends Server2ClientVillageMessageProtocol {

  private val json: Option[JsonPhase] = {
    server2logger.FirstMorningPhaseProtocol(village, character, role, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map(Json.toJson)

}

object FirstMorningPhaseProtocol {

  def read(json: JsonPhase, village: Village): Option[FirstMorningPhaseProtocol] = {

    if (json.base.phase == Morning.label && json.base.day == 1) {

      val characterBuffer = ListBuffer.empty[CharacterProtocol]
      val roleBuffer      = ListBuffer.empty[RoleProtocol]

      json.character foreach { jsonCharacter: JsonCharacter =>
        val characterOpt: Option[Character] = Data2Knowledge.characterOpt(jsonCharacter.name.en, jsonCharacter.id)
        val phaseOpt:     Option[Phase]     = Data2Knowledge.phaseOpt(jsonCharacter.update.phase)
        if (characterOpt.nonEmpty && phaseOpt.nonEmpty) {
          val statusOpt: Option[Status] = Data2Knowledge.statusOpt(jsonCharacter.status)
          if (statusOpt.nonEmpty) {
            characterBuffer += CharacterProtocol(
              characterOpt.get,
              village.id,
              village.language,
              jsonCharacter.isMine,
              statusOpt.get,
              UpdateProtocol(
                phaseOpt.get,
                jsonCharacter.update.day
              ),
              jsonCharacter.isAChoice
            )
          }
        }
      }

      json.role foreach { jsonRole: JsonRole =>
        val roleOpt: Option[Role] = Data2Knowledge.roleOpt(jsonRole.name.en, jsonRole.numberOfPlayers)
        if (roleOpt.nonEmpty) {
          val boardResultBuffer = ListBuffer.empty[BoardResultProtocol]
          jsonRole.board foreach { jsonBoardResult: JsonBoardResult =>
            val characterOpt: Option[Character] =
              Data2Knowledge.characterOpt(jsonBoardResult.character.name.en, jsonBoardResult.character.id)
            val polarityOpt: Option[PolarityMark] = Data2Knowledge.polarityMarkOpt(jsonBoardResult.polarity)
            val phaseOpt:    Option[Phase]        = Data2Knowledge.phaseOpt(jsonBoardResult.phase)

            if (characterOpt.nonEmpty && polarityOpt.nonEmpty && phaseOpt.nonEmpty) {
              boardResultBuffer += BoardResultProtocol(
                characterOpt.get,
                polarityOpt.get,
                phaseOpt.get,
                jsonBoardResult.day,
                village.id,
                village.language
              )
            }
          }

          roleBuffer += RoleProtocol(
            roleOpt.get,
            jsonRole.isMine,
            jsonRole.numberOfPlayers,
            boardResultBuffer.result,
            village.id,
            village.language
          )
        }
      }

      Some(
        FirstMorningPhaseProtocol(
          village,
          characterBuffer.result,
          roleBuffer.result
        )
      )
    } else {
      None
    }
  }

}
