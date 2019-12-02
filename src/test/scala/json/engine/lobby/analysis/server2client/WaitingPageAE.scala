package json.engine.lobby.analysis.server2client

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.server2client.WaitingPage
import licos.json.element.lobby.server2client.JsonWaitingPage
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2client.WaitingPageAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class WaitingPageAE extends WaitingPageAnalysisEngine {
  override def process(box: BOX, waitingPage: JsonWaitingPage): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(WaitingPage.`type`)))
      case _ => Left(Json.toJson(waitingPage))
    }
  }
}
