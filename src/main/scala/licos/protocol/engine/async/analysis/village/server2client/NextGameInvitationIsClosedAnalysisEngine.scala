package licos.protocol.engine.async.analysis.village.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.NextGameInvitationIsClosedProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.concurrent.{ExecutionContext, Future}

trait NextGameInvitationIsClosedAnalysisEngine extends VillageMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(
      box:                        VillageBOX,
      nextGameInvitationIsClosed: NextGameInvitationIsClosedProtocol
  )(implicit ec:                  ExecutionContext): Future[VillageMessageProtocol]
}

object NextGameInvitationIsClosedAnalysisEngine {
  val name: String = "village.server2client.NextGameInvitationIsClosed"
}
