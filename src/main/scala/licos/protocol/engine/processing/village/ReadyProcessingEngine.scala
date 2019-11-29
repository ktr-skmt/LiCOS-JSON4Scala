package licos.protocol.engine.processing.village

import licos.entity.{Village, VillageCreationException, VillageFactory}
import licos.json.element.lobby.client2server.JsonReady
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.ReadyProtocol
import licos.protocol.engine.analysis.village.client2server.ReadyAnalysisEngine
import licos.protocol.engine.processing.{JSON2ProtocolException, VillageBOX}

import scala.util.{Failure, Success, Try}

object ReadyProcessingEngine {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Nothing"))
  def process(
      box:            VillageBOX,
      villageFactory: VillageFactory,
      engine:         ReadyAnalysisEngine,
      json:           JsonReady
  ): Try[VillageMessageProtocol] = {
    villageFactory.create match {
      case Success(village: Village) =>
        ReadyProtocol.read(json, village) match {
          case Some(protocol) =>
            engine.process(box, protocol)
          case None => Failure(new JSON2ProtocolException(ReadyAnalysisEngine.name))
        }
      case Failure(_: Throwable) => Failure(new VillageCreationException(ReadyAnalysisEngine.name))
    }
  }
}
