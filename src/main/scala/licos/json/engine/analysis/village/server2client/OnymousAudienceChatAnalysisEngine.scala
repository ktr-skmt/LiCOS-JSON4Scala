package licos.json.engine.analysis.village.server2client

import licos.json.engine.analysis.village.MetaOnymousAudienceChatAnalysisEngine

trait OnymousAudienceChatAnalysisEngine extends MetaOnymousAudienceChatAnalysisEngine

object OnymousAudienceChatAnalysisEngine {

  /**
    * Onymous-audience-chat analysis engine name.
    */
  val name:         String  = "village.server2client.OnymousAudienceChatAnalysisEngine"
  val isFromServer: Boolean = true

}
