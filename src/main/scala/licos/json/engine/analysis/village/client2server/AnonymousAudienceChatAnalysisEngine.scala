package licos.json.engine.analysis.village.client2server

import licos.json.engine.analysis.village.MetaAnonymousAudienceChatAnalysisEngine

trait AnonymousAudienceChatAnalysisEngine extends MetaAnonymousAudienceChatAnalysisEngine

object AnonymousAudienceChatAnalysisEngine {

  /** Anonymous-audience-chat analysis engine name.
    */
  val name:         String  = "village.client2server.AnonymousAudienceChatAnalysisEngine"
  val isFromServer: Boolean = false

}
