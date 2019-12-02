package protocol.engine.village.analysis.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.PostMortemDiscussionProtocol
import licos.protocol.engine.analysis.village.server2client.PostMortemDiscussionAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.PostMortemDiscussion
import protocol.engine.village.{NoVillageBOXException, VillageBox}

import scala.util.{Failure, Success, Try}

final class PostMortemDiscussionAE extends PostMortemDiscussionAnalysisEngine {
  override def process(
      box:                  VillageBOX,
      postMortemDiscussion: PostMortemDiscussionProtocol
  ): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(PostMortemDiscussion.`type`))
      case _ => Failure(new NoVillageBOXException())
    }
  }
}
