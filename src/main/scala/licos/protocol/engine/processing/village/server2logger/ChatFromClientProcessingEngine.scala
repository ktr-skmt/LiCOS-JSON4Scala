package licos.protocol.engine.processing.village.server2logger

import java.time.OffsetDateTime
import java.util.UUID

import licos.entity.{PhaseException, Village, VillageCreationException, VillageFactory}
import licos.json.element.village.client2server.JsonChatFromClient
import licos.knowledge.{Data2Knowledge, Phase}
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.server2logger.ChatFromClientProtocol
import licos.protocol.element.village.part.VillageProtocol
import licos.protocol.engine.analysis.village.client2server.server2logger.ChatFromClientAnalysisEngine
import licos.protocol.engine.processing.{JSON2ProtocolException, VillageBOX}

import scala.concurrent.duration._
import scala.util.{Failure, Success, Try}

object ChatFromClientProcessingEngine {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Nothing"))
  def process(
      box:             VillageBOX,
      villageFactory:  VillageFactory,
      villageProtocol: VillageProtocol,
      engine:          ChatFromClientAnalysisEngine,
      json:            JsonChatFromClient
  ): Try[VillageMessageProtocol] = {
    Data2Knowledge.phaseOpt(json.base.phase) match {
      case Some(phase: Phase) =>
        villageFactory
          .setVillageProtocol(villageProtocol)
          .setToken(UUID.fromString(json.base.token))
          .setDay(json.base.day)
          .setPhaseStartTime(OffsetDateTime.parse(json.base.phaseStartTime))
          .setPhaseTimeLimit(json.base.phaseTimeLimit.seconds)
          .setPhase(phase)
          .create match {
          case Success(village: Village) =>
            ChatFromClientProtocol.read(json, village) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(ChatFromClientAnalysisEngine.name))
            }
          case Failure(_: Throwable) => Failure(new VillageCreationException(ChatFromClientAnalysisEngine.name))
        }
      case None => Failure(new PhaseException(ChatFromClientAnalysisEngine.name))
    }
  }
}
