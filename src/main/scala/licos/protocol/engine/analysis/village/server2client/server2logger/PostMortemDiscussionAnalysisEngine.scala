package licos.protocol.engine.analysis.village.server2client.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.server2logger.PostMortemDiscussionProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.util.Try

trait PostMortemDiscussionAnalysisEngine extends VillageMessageAnalysisEngine {
  def process(
      box:                  VillageBOX,
      postMortemDiscussion: PostMortemDiscussionProtocol
  ): Try[VillageMessageProtocol]
}

object PostMortemDiscussionAnalysisEngine {
  val name: String = "village.server2client.server2logger.PostMortemDiscussion"
}
