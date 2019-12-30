package licos.protocol.element.village.server2client

import java.net.URL

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.character.{JsonResultCharacter, JsonSimpleCharacter}
import licos.json.element.village.role.JsonResultRole
import licos.json.element.village.server2client.JsonGameResult
import licos.knowledge.{Character, Data2Knowledge, Role}
import licos.protocol.element.village.part.character.{ResultCharacterProtocol, SimpleCharacterProtocol}
import licos.protocol.element.village.part.role.ResultRoleProtocol
import play.api.libs.json.{JsValue, Json}

final case class GameResultProtocol(
    village:   VillageInfo,
    character: Seq[ResultCharacterProtocol],
    role:      Seq[ResultRoleProtocol]
) extends Server2ClientVillageMessageProtocol {

  private val json: Option[JsonGameResult] = {
    server2logger.GameResultProtocol(village, character, role, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object GameResultProtocol {

  def read(json: JsonGameResult, villageInfoFromLobby: VillageInfoFromLobby): Option[GameResultProtocol] = {
    VillageInfoFactory
      .create(villageInfoFromLobby, json.base)
      .map { village: VillageInfo =>
        GameResultProtocol(
          village,
          json.character.flatMap { jsonResultCharacter: JsonResultCharacter =>
            for {
              character <- Data2Knowledge.characterOpt(jsonResultCharacter.name.en, jsonResultCharacter.id).toList
              role      <- village.cast.parse(jsonResultCharacter.role.name.en).toList
              status    <- Data2Knowledge.statusOpt(jsonResultCharacter.status).toList
              result    <- Data2Knowledge.outcomeOpt(jsonResultCharacter.result).toList
            } yield {
              ResultCharacterProtocol(
                character,
                jsonResultCharacter.isMine,
                role,
                status,
                result,
                village.token,
                jsonResultCharacter.avatar.name,
                new URL(jsonResultCharacter.avatar.image),
                village.id,
                village.language
              )
            }
          },
          json.role.flatMap { jsonResultRole: JsonResultRole =>
            Data2Knowledge.roleOpt(jsonResultRole.name.en, jsonResultRole.numberOfPlayers).toList.map { role: Role =>
              ResultRoleProtocol(
                role,
                jsonResultRole.isMine,
                jsonResultRole.character.flatMap { jsonSimpleCharacter: JsonSimpleCharacter =>
                  Data2Knowledge.characterOpt(jsonSimpleCharacter.name.en, jsonSimpleCharacter.id).toList.map {
                    character: Character =>
                      SimpleCharacterProtocol(
                        character,
                        village.id,
                        village.language
                      )
                  }
                },
                village.id,
                village.language
              )
            }
          }
        )
      }
  }

}
