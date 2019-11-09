package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.IdSearch
import element.JsonTest
import licos.json.element.lobby.JsonIdSearch
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.IdSearchAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class IdSearchAE extends IdSearchAnalysisEngine {
  override def process(box: BOX, idSearch: JsonIdSearch): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(IdSearch.`type`)))
      case _ => Left(Json.toJson(idSearch))
    }
  }
}
