package licos.protocol.engine.async.analysis.village.server2client.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.server2logger.PostMortemDiscussionProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.concurrent.{ExecutionContext, Future}

trait PostMortemDiscussionAnalysisEngine extends VillageMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(
      box:                  VillageBOX,
      postMortemDiscussion: PostMortemDiscussionProtocol
  )(implicit ec:            ExecutionContext): Future[VillageMessageProtocol]
}

object PostMortemDiscussionAnalysisEngine {
  val name: String = "village.server2client.server2logger.PostMortemDiscussion"
}
