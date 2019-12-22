package licos.protocol.engine.async.processing

import licos.protocol.engine.async.processing.auth.AuthProcessingEngineFactory
import licos.protocol.engine.async.processing.lobby.LobbyProcessingEngineFactory
import licos.protocol.engine.async.processing.village.VillageProcessingEngineFactory
import licos.protocol.engine.async.processing.village.server2logger.VillageProcessingEngineFactory4Logger

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
