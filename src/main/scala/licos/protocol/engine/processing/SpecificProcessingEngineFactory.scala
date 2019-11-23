package licos.protocol.engine.processing

import licos.protocol.engine.processing.server2logger.VillageProcessingEngineFactory4Logger

object SpecificProcessingEngineFactory {
  def create(specificProcessingEngine: SpecificProcessingEngine): ProcessingEngineFactory = {
    specificProcessingEngine match {
      case LobbyPE          => new LobbyProcessingEngineFactory()
      case VillagePE        => new VillageProcessingEngineFactory()
      case VillagePE4Logger => new VillageProcessingEngineFactory4Logger()
    }
  }
}
