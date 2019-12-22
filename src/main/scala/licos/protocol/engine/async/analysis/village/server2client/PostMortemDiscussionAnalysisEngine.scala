package licos.protocol.engine.async.analysis.village.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.PostMortemDiscussionProtocol
import licos.protocol.engine.processing.village.VillageBOX

import scala.concurrent.{ExecutionContext, Future}

trait PostMortemDiscussionAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(
      box:                  VillageBOX,
      postMortemDiscussion: PostMortemDiscussionProtocol
  )(implicit ec:            ExecutionContext): Future[VillageMessageProtocol]
}

object PostMortemDiscussionAnalysisEngine {
  val name: String = "village.server2client.PostMortemDiscussion"
}
