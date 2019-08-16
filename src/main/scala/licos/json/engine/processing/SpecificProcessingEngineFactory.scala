package licos.json.engine.processing

/** Specific processing engine factory.
  */
object SpecificProcessingEngineFactory {

  /** Create a processing engine factory.
    *
    * @param specificProcessingEngine a specific processing engine such as a LobbyPE and a VillagePE.
    * @return a specific processing engine factory such as a lobby processing engine factory and a village processing engine factory.
    */
  def create(specificProcessingEngine: SpecificProcessingEngine): ProcessingEngineFactory = {
    specificProcessingEngine match {
      case LobbyPE => new LobbyProcessingEngineFactory()
      case VillagePE => new VillageProcessingEngineFactory()
    }
  }
}
