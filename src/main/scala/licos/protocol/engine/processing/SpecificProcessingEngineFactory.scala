package licos.protocol.engine.processing

import licos.protocol.engine.processing.auth.AuthProcessingEngineFactory
import licos.protocol.engine.processing.lobby.LobbyProcessingEngineFactory
import licos.protocol.engine.processing.village.VillageProcessingEngineFactory
import licos.protocol.engine.processing.village.server2logger.VillageProcessingEngineFactory4Logger

object SpecificProcessingEngineFactory {
  def create(specificProcessingEngine: SpecificProcessingEngine): ProcessingEngineFactory = {
    specificProcessingEngine match {
      case LobbyPE          => new LobbyProcessingEngineFactory()
      case VillagePE        => new VillageProcessingEngineFactory()
      case VillagePE4Logger => new VillageProcessingEngineFactory4Logger()
      case AuthPE           => new AuthProcessingEngineFactory()
    }
  }
}
