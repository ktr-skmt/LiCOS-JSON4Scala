package json.engine.lobby.analysis.client2server

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.client2server.SelectOnymousAudience
import licos.json.element.lobby.client2server.JsonSelectOnymousAudience
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.SelectOnymousAudienceAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class SelectOnymousAudienceAE extends SelectOnymousAudienceAnalysisEngine {
  override def process(box: BOX, selectOnymousAudience: JsonSelectOnymousAudience): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(SelectOnymousAudience.`type`)))
      case _ => Left(Json.toJson(selectOnymousAudience))
    }
  }
}
