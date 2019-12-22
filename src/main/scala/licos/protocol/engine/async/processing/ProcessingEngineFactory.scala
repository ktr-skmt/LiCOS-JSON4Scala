package licos.protocol.engine.async.processing

trait ProcessingEngineFactory {
  def create: ProcessingEngine
}
