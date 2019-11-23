package json.engine.village.analysis.server2client

import json.engine.village.VillageBox
import json.engine.village.example.server2client.NextGameInvitation
import json.element.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.server2client.NextGameInvitationAnalysisEngine
import licos.json.element.village.invite.JsonNextGameInvitation
import play.api.libs.json.{JsValue, Json}

class NextGameInvitationAE extends NextGameInvitationAnalysisEngine {

  override def process(box: BOX, nextGameInvitation: JsonNextGameInvitation): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox => Right(Json.toJson(JsonTest(NextGameInvitation.`type`)))
      case _ => Left(Json.toJson(nextGameInvitation))
    }
  }
}
