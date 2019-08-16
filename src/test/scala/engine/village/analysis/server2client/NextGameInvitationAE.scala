package engine.village.analysis.server2client

import engine.village.VillageBox
import engine.village.example.server2client.NextGameInvitation
import element.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.server2client.NextGameInvitationAnalysisEngine
import licos.json.element.village.invite.JsonNextGameInvitation
import play.api.libs.json.{JsValue, Json}

class NextGameInvitationAE extends NextGameInvitationAnalysisEngine {

  override def process(box: BOX,
                       nextGameInvitation: JsonNextGameInvitation): Option[JsValue] = {
    box match {
      case _: VillageBox => Option(Json.toJson(JsonTest(NextGameInvitation.`type`)))
      case _ => None
    }
  }
}
