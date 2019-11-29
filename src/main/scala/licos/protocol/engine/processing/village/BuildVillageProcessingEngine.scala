package licos.protocol.engine.processing.village

import licos.entity.{Village, VillageCreationException, VillageFactory}
import licos.json.element.lobby.client2server.JsonBuildVillage
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.BuildVillageProtocol
import licos.protocol.engine.analysis.village.client2server.BuildVillageAnalysisEngine
import licos.protocol.engine.processing.{JSON2ProtocolException, VillageBOX}

import scala.util.{Failure, Success, Try}

object BuildVillageProcessingEngine {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Nothing"))
  def process(
      box:            VillageBOX,
      villageFactory: VillageFactory,
      engine:         BuildVillageAnalysisEngine,
      json:           JsonBuildVillage
  ): Try[VillageMessageProtocol] = {
    villageFactory.create match {
      case Success(village: Village) =>
        BuildVillageProtocol.read(json, village) match {
          case Some(protocol) =>
            engine.process(box, protocol)
          case None => Failure(new JSON2ProtocolException(BuildVillageAnalysisEngine.name))
        }
      case Failure(_: Throwable) => Failure(new VillageCreationException(BuildVillageAnalysisEngine.name))
    }
  }
}
