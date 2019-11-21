package licos.protocol.engine.processing

sealed trait SpecificProcessingEngine

case object LobbyPE extends SpecificProcessingEngine

case object VillagePE extends SpecificProcessingEngine
