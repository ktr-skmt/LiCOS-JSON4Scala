package licos.json.engine.analysis.village.server2client

import licos.json.engine.analysis.village.MetaErrorAnalysisEngine

trait ErrorAnalysisEngine extends MetaErrorAnalysisEngine

object ErrorAnalysisEngine {

  /**
    * Error analysis engine name.
    */
  val name:         String  = "village.server2client.ErrorAnalysisEngine"
  val isFromServer: Boolean = true

}
