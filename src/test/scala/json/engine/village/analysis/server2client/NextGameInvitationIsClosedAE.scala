package json.engine.village.analysis.server2client

import json.engine.village.VillageBox
import json.engine.village.example.server2client.NextGameInvitationIsClosed
import json.element.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.server2client.NextGameInvitationIsClosedAnalysisEngine
import licos.json.element.village.invite.JsonNextGameInvitationIsClosed
import play.api.libs.json.{JsValue, Json}

class NextGameInvitationIsClosedAE extends NextGameInvitationIsClosedAnalysisEngine {

  override def process(
      box:                        BOX,
      nextGameInvitationIsClosed: JsonNextGameInvitationIsClosed
  ): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox => Right(Json.toJson(JsonTest(NextGameInvitationIsClosed.`type`)))
      case _ => Left(Json.toJson(nextGameInvitationIsClosed))
    }
  }
}
