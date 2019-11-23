package json.engine.lobby.analysis

import json.engine.lobby.LobbyBox
import json.engine.lobby.example.WaitingPage
import json.element.JsonTest
import licos.json.element.lobby.JsonWaitingPage
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2client.WaitingPageAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class WaitingPageAE extends WaitingPageAnalysisEngine {
  override def process(box: BOX, waitingPage: JsonWaitingPage): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(WaitingPage.`type`)))
      case _ => Left(Json.toJson(waitingPage))
    }
  }
}
