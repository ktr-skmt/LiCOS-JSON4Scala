package licos.protocol.engine.processing

object SpecificProcessingEngineFactory {
  def create(specificProcessingEngine: SpecificProcessingEngine): ProcessingEngineFactory = {
    specificProcessingEngine match {
      case LobbyPE   => new LobbyProcessingEngineFactory()
      case VillagePE => new VillageProcessingEngineFactory()
    }
  }
}
