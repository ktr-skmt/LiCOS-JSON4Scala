package licos.protocol.engine.processing

import licos.json.flow.FlowController

trait ProcessingEngine {
  protected val flowController: FlowController
}
