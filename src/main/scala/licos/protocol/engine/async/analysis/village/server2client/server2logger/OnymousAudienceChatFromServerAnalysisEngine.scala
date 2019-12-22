package licos.protocol.engine.async.analysis.village.server2client.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.server2logger.OnymousAudienceChatFromServerProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.concurrent.{ExecutionContext, Future}

trait OnymousAudienceChatFromServerAnalysisEngine extends VillageMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(
      box:                           VillageBOX,
      onymousAudienceChatFromServer: OnymousAudienceChatFromServerProtocol
  )(implicit ec:                     ExecutionContext): Future[VillageMessageProtocol]
}

object OnymousAudienceChatFromServerAnalysisEngine {
  val name: String = "village.server2client.server2logger.OnymousAudienceChatFromServer"
}
