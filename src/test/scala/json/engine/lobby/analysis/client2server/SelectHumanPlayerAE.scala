package json.engine.lobby.analysis.client2server

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.client2server.SelectHumanPlayer
import licos.json.element.lobby.client2server.JsonSelectHumanPlayer
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.SelectHumanPlayerAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class SelectHumanPlayerAE extends SelectHumanPlayerAnalysisEngine {
  override def process(box: BOX, selectHumanPlayer: JsonSelectHumanPlayer): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(SelectHumanPlayer.`type`)))
      case _ => Left(Json.toJson(selectHumanPlayer))
    }
  }
}
