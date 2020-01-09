package json.engine.lobby.analysis.server2client

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.server2client.HumanPlayerSelectionPage
import licos.json.element.lobby.server2client.JsonHumanPlayerSelectionPage
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2client.HumanPlayerSelectionPageAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class HumanPlayerSelectionPageAE extends HumanPlayerSelectionPageAnalysisEngine {
  override def process(box: BOX, humanPlayerSelectionPage: JsonHumanPlayerSelectionPage): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(HumanPlayerSelectionPage.`type`)))
      case _ => Left(Json.toJson(humanPlayerSelectionPage))
    }
  }
}
