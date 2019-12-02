package protocol.engine.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.OnymousAudienceScrollProtocol
import licos.protocol.engine.analysis.village.client2server.OnymousAudienceScrollAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.OnymousAudienceScroll
import protocol.engine.village.{NoVillageBOXException, VillageBox}

import scala.util.{Failure, Success, Try}

final class OnymousAudienceScrollAE extends OnymousAudienceScrollAnalysisEngine {
  override def process(
      box:                   VillageBOX,
      onymousAudienceScroll: OnymousAudienceScrollProtocol
  ): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(OnymousAudienceScroll.`type`))
      case _ => Failure(new NoVillageBOXException())
    }
  }
}
