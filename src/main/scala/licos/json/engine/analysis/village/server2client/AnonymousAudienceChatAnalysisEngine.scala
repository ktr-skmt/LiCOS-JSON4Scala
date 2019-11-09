package licos.json.engine.analysis.village.server2client

import licos.json.engine.analysis.village.MetaAnonymousAudienceChatAnalysisEngine

trait AnonymousAudienceChatAnalysisEngine extends MetaAnonymousAudienceChatAnalysisEngine

object AnonymousAudienceChatAnalysisEngine {

  /**
    * Anonymous-audience-chat analysis engine name.
    */
  val name:         String  = "village.server2client.AnonymousAudienceChatAnalysisEngine"
  val isFromServer: Boolean = true

}
