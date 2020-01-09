package protocol.engine.async.village.analysis.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.PostMortemDiscussionProtocol
import licos.protocol.engine.async.analysis.village.server2client.PostMortemDiscussionAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.PostMortemDiscussion
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class PostMortemDiscussionAE extends PostMortemDiscussionAnalysisEngine {
  override def process(
      box:                  VillageBOX,
      postMortemDiscussion: PostMortemDiscussionProtocol
  )(implicit ec:            ExecutionContext): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Future.successful(VillageMessageTestProtocol(PostMortemDiscussion.`type`))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
