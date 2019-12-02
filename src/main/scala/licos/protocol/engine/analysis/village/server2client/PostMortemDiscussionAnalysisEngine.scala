package licos.protocol.engine.analysis.village.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.PostMortemDiscussionProtocol
import licos.protocol.engine.processing.village.VillageBOX

import scala.util.Try

trait PostMortemDiscussionAnalysisEngine {
  def process(
      box:                  VillageBOX,
      postMortemDiscussion: PostMortemDiscussionProtocol
  ): Try[VillageMessageProtocol]
}

object PostMortemDiscussionAnalysisEngine {
  val name: String = "village.server2client.PostMortemDiscussion"
}
