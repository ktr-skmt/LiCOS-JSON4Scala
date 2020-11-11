package licos.protocol.element.village.server2client

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.character.JsonCharacter
import licos.json.element.village.role.JsonRole
import licos.json.element.village.JsonBoardResult
import licos.json.element.village.server2client.JsonPhase
import licos.knowledge.{Data2Knowledge, Night, Role}
import licos.protocol.element.village.part.{BoardResultProtocol, UpdateProtocol}
import licos.protocol.element.village.part.character.CharacterProtocol
import licos.protocol.element.village.part.role.RoleProtocol
import play.api.libs.json.{JsValue, Json}

final case class NightPhaseProtocol(village: VillageInfo, character: Seq[CharacterProtocol], role: Seq[RoleProtocol])
    extends Server2ClientVillageMessageProtocol {

  private lazy val json: Option[JsonPhase] = {
    server2logger.NightPhaseProtocol(village, character, role, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object NightPhaseProtocol {

  def read(json: JsonPhase, villageInfoFromLobby: VillageInfoFromLobby): Option[NightPhaseProtocol] = {
    import cats.implicits._
    if (json.base.phase === Night.label) {
      VillageInfoFactory
        .createOpt(villageInfoFromLobby, json.base)
        .map { village: VillageInfo =>
          NightPhaseProtocol(
            village,
            json.character.flatMap { jsonCharacter: JsonCharacter =>
              for {
                character <- Data2Knowledge.characterOpt(jsonCharacter.name.en, jsonCharacter.id).toList
                phase     <- Data2Knowledge.phaseOpt(jsonCharacter.update.phase).toList
                status    <- Data2Knowledge.statusOpt(jsonCharacter.status).toList
              } yield {
                CharacterProtocol(
                  character,
                  village.id,
                  village.language,
                  jsonCharacter.isMine,
                  status,
                  UpdateProtocol(
                    phase,
                    jsonCharacter.update.day
                  ),
                  jsonCharacter.isAChoice
                )
              }
            },
            json.role.flatMap { jsonRole: JsonRole =>
              Data2Knowledge.roleOpt(jsonRole.name.en, jsonRole.numberOfPlayers).toList.map { role: Role =>
                RoleProtocol(
                  role,
                  jsonRole.isMine,
                  jsonRole.numberOfPlayers,
                  jsonRole.board.flatMap { jsonBoardResult: JsonBoardResult =>
                    for {
                      character <- Data2Knowledge
                        .characterOpt(jsonBoardResult.character.name.en, jsonBoardResult.character.id)
                        .toList
                      polarity <- Data2Knowledge.polarityMarkOpt(jsonBoardResult.polarity).toList
                      phase    <- Data2Knowledge.phaseOpt(jsonBoardResult.phase).toList
                    } yield {
                      BoardResultProtocol(
                        character,
                        polarity,
                        phase,
                        jsonBoardResult.day,
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
    } else {
      Option.empty[NightPhaseProtocol]
    }
  }

}
