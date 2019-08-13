package engine.village.analysis

import engine.village.VillageBox
import engine.village.example.NextGameInvitation
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.NextGameInvitationAnalysisEngine
import licos.json.village.invite.JsonNextGameInvitation
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
