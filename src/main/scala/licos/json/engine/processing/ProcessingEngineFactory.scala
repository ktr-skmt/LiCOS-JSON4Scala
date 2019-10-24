package licos.json.engine.processing

/** An abstract factory of processing engine.
  */
trait ProcessingEngineFactory {

  /** Create a processing engine.
    *
    * @return a processing engine.
    */
  def create: ProcessingEngine
}