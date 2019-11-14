package licos.json.engine.analysis.village.client2server

import licos.json.engine.analysis.village.MetaErrorAnalysisEngine

trait ErrorAnalysisEngine extends MetaErrorAnalysisEngine

object ErrorAnalysisEngine {

  /**
    * Error analysis engine name.
    */
  val name:         String  = "village.client2server.ErrorAnalysisEngine"
  val isFromServer: Boolean = false

}
