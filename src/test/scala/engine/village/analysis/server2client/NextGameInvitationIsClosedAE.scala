package engine.village.analysis.server2client

import engine.village.VillageBox
import engine.village.example.server2client.NextGameInvitationIsClosed
import element.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.server2client.NextGameInvitationIsClosedAnalysisEngine
import licos.json.village.invite.JsonNextGameInvitationIsClosed
import play.api.libs.json.{JsValue, Json}

class NextGameInvitationIsClosedAE extends NextGameInvitationIsClosedAnalysisEngine {

  override def process(box: BOX,
                       nextGameInvitationIsClosed: JsonNextGameInvitationIsClosed): Option[JsValue] = {
    box match {
      case _: VillageBox => Option(Json.toJson(JsonTest(NextGameInvitationIsClosed.`type`)))
      case _ => None
    }
  }
}
