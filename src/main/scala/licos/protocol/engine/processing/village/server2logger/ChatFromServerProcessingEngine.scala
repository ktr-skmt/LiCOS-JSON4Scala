package licos.protocol.engine.processing.village.server2logger

import java.time.OffsetDateTime
import java.util.UUID

import licos.entity.{PhaseException, Village, VillageCreationException, VillageFactory}
import licos.json.element.village.server2client.JsonChatFromServer
import licos.knowledge.{Data2Knowledge, Phase}
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.part.VillageProtocol
import licos.protocol.element.village.server2client.server2logger.ChatFromServerProtocol
import licos.protocol.engine.analysis.village.server2client.server2logger.ChatFromServerAnalysisEngine
import licos.protocol.engine.processing.{JSON2ProtocolException, VillageBOX}

import scala.concurrent.duration._
import scala.util.{Failure, Success, Try}

object ChatFromServerProcessingEngine {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Nothing"))
  def process(
      box:             VillageBOX,
      villageFactory:  VillageFactory,
      villageProtocol: VillageProtocol,
      engine:          ChatFromServerAnalysisEngine,
      json:            JsonChatFromServer
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
            ChatFromServerProtocol.read(json, village) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(ChatFromServerAnalysisEngine.name))
            }
          case Failure(_: Throwable) => Failure(new VillageCreationException(ChatFromServerAnalysisEngine.name))
        }
      case None => Failure(new PhaseException(ChatFromServerAnalysisEngine.name))
    }
  }
}
