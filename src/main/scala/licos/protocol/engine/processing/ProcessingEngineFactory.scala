package licos.protocol.engine.processing

trait ProcessingEngineFactory {
  def create: ProcessingEngine
}
