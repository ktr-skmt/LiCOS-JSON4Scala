package licos.protocol.engine.processing.village

import java.time.OffsetDateTime
import java.util.UUID

import licos.entity.{
  CharacterException,
  PhaseException,
  RoleException,
  Village,
  VillageCreationException,
  VillageFactory
}
import licos.json.element.village.client2server.JsonBoard
import licos.knowledge.{Character, Data2Knowledge, Phase, Role}
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.BoardProtocol
import licos.protocol.element.village.part.VillageProtocol
import licos.protocol.engine.analysis.village.client2server.BoardAnalysisEngine
import licos.protocol.engine.processing.{JSON2ProtocolException, VillageBOX}

import scala.concurrent.duration._
import scala.util.{Failure, Success, Try}

object BoardProcessingEngine {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Nothing"))
  def process(
      box:             VillageBOX,
      villageFactory:  VillageFactory,
      villageProtocol: VillageProtocol,
      engine:          BoardAnalysisEngine,
      json:            JsonBoard
  ): Try[VillageMessageProtocol] = {
    Data2Knowledge.phaseOpt(json.base.phase) match {
      case Some(phase: Phase) =>
        Data2Knowledge.characterOpt(json.myCharacter.name.en, json.myCharacter.id) match {
          case Some(myCharacter: Character) =>
            box.villageInfoFromLobby.cast.parse(json.myCharacter.role.name.en) match {
              case Some(myRole: Role) =>
                villageFactory
                  .setVillageProtocol(villageProtocol)
                  .setToken(UUID.fromString(json.base.token))
                  .setDay(json.base.day)
                  .setPhaseStartTime(OffsetDateTime.parse(json.base.phaseStartTime))
                  .setPhaseTimeLimit(json.base.phaseTimeLimit.seconds)
                  .setPhase(phase)
                  .setMyCharacter(myCharacter)
                  .setMyRole(myRole)
                  .create match {
                  case Success(village: Village) =>
                    BoardProtocol.read(json, village) match {
                      case Some(protocol) =>
                        engine.process(box, protocol)
                      case None => Failure(new JSON2ProtocolException(BoardAnalysisEngine.name))
                    }
                  case Failure(_: Throwable) => Failure(new VillageCreationException(BoardAnalysisEngine.name))
                }
              case None => Failure(new RoleException(BoardAnalysisEngine.name))
            }
          case None => Failure(new CharacterException(BoardAnalysisEngine.name))
        }
      case None => Failure(new PhaseException(BoardAnalysisEngine.name))
    }
  }
}
