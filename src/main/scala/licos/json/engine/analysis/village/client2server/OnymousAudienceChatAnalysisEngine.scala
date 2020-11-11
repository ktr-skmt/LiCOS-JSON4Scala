package licos.json.engine.analysis.village.client2server

import licos.json.engine.analysis.village.MetaOnymousAudienceChatAnalysisEngine

trait OnymousAudienceChatAnalysisEngine extends MetaOnymousAudienceChatAnalysisEngine

object OnymousAudienceChatAnalysisEngine {

  /** Onymous-audience-chat analysis engine name.
    */
  val name:         String  = "village.client2server.OnymousAudienceChatAnalysisEngine"
  val isFromServer: Boolean = false

}
