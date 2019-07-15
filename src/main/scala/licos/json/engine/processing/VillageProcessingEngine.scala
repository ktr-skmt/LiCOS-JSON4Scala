package licos.json.engine.processing

import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.{BuildVillageAnalysisEngine, LeaveWaitingPageAnalysisEngine, ReadyAnalysisEngine}
import licos.json.engine.analysis.village.{BoardAnalysisEngine, ChatFromClientAnalysisEngine, ChatFromServerAnalysisEngine, ErrorAnalysisEngine, FlavorTextAnalysisEngine, GameResultAnalysisEngine, PhaseAnalysisEngine, ReceivedFlavorTextMessageAnalysisEngine, ReceivedPlayerMessageAnalysisEngine, ReceivedSystemMessageAnalysisEngine, ScrollAnalysisEngine, VoteAnalysisEngine}
import licos.json.flow.{FlowController, VillageFlowController}
import licos.json.lobby.{JsonBuildVillage, JsonLeaveWaitingPage, JsonReady}
import licos.json.village.{JsonBoard, JsonChatFromClient, JsonChatFromServer, JsonError, JsonFlavorText, JsonGameResult, JsonPhase, JsonScroll, JsonVote}
import licos.json.village.receipt.{JsonReceivedFlavorTextMessage, JsonReceivedPlayerMessage, JsonReceivedSystemMessage}
import play.api.libs.json.{JsValue, Json}

/** This class implements the processing engine that aggregates and runs analysis engines for village.
  *
  * @param readyEngine the analysis engine for Ready JSON.
  * @param receivedPlayerMessageEngine the analysis engine for Received-player-message JSON.
  * @param receivedSystemMessageEngine the analysis engine for Received-system-message JSON.
  * @param receivedFlavorTextMessageEngine the analysis engine for Flavor-text-message JSON.
  * @param chatFromClientEngine the analysis engine for Chat-from-client JSON.
  * @param chatFromServerEngine the analysis engine for Chat-from-server JSON.
  * @param boardEngine the analysis engine for Board JSON.
  * @param voteEngine the analysis engine for Vote JSON.
  * @param scrollEngine the analysis engine for Scroll JSON.
  * @param phaseEngine the analysis engine for Phase JSON.
  * @param flavorTextEngine the analysis engine for Flavor-text JSON.
  * @param gameResultEngine the analysis engine for Game-result JSON.
  * @param buildVillageEngine the analysis engine for Build-village JSON.
  * @param leaveWaitingPageEngine the analysis engine for Leave-waiting-page JSON.
  * @param errorEngine the analysis engine for Error JSON.
  * @author Kotaro Sakamoto
  */
class VillageProcessingEngine(readyEngine: ReadyAnalysisEngine,
                              receivedPlayerMessageEngine: ReceivedPlayerMessageAnalysisEngine,
                              receivedSystemMessageEngine: ReceivedSystemMessageAnalysisEngine,
                              receivedFlavorTextMessageEngine: ReceivedFlavorTextMessageAnalysisEngine,
                              chatFromClientEngine: ChatFromClientAnalysisEngine,
                              chatFromServerEngine: ChatFromServerAnalysisEngine,
                              boardEngine: BoardAnalysisEngine,
                              voteEngine: VoteAnalysisEngine,
                              scrollEngine: ScrollAnalysisEngine,
                              phaseEngine: PhaseAnalysisEngine,
                              flavorTextEngine: FlavorTextAnalysisEngine,
                              gameResultEngine: GameResultAnalysisEngine,
                              buildVillageEngine: BuildVillageAnalysisEngine,
                              leaveWaitingPageEngine: LeaveWaitingPageAnalysisEngine,
                              errorEngine: ErrorAnalysisEngine) extends ProcessingEngine {

  override protected val flowController: FlowController = new VillageFlowController()

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param msg a JSON message.
    * @return a play.api.libs.json.JsValue option.
    */
  override def process(box: BOX, msg: String): Option[JsValue] = {

    val jsValue: JsValue = Json.parse(msg)

    flowController.flow(jsValue) match {
      case Some(ready: JsonReady) =>
        System.err.println("VillageProcessingEngine.process JsonReady")
        readyEngine.process(box, ready)
      case Some(receivedPlayerMessage: JsonReceivedPlayerMessage) =>
        System.err.println("VillageProcessingEngine.process JsonReceivedPlayerMessage")
        receivedPlayerMessageEngine.process(box, receivedPlayerMessage)
      case Some(receivedSystemMessage: JsonReceivedSystemMessage) =>
        System.err.println("VillageProcessingEngine.process JsonReceivedSystemMessage")
        receivedSystemMessageEngine.process(box, receivedSystemMessage)
      case Some(receivedFlavorTextMessage: JsonReceivedFlavorTextMessage) =>
        System.err.println("VillageProcessingEngine.process JsonReceivedFlavorTestMessage")
        receivedFlavorTextMessageEngine.process(box, receivedFlavorTextMessage)
      case Some(chatFromClient: JsonChatFromClient) =>
        System.err.println("VillageProcessingEngine.process JsonChatFromClient")
        chatFromClientEngine.process(box, chatFromClient)
      case Some(chatFromServer: JsonChatFromServer) =>
        System.err.println("VillageProcessingEngine.process JsonChatFromServer")
        chatFromServerEngine.process(box, chatFromServer)
      case Some(board: JsonBoard) =>
        System.err.println("VillageProcessingEngine.process JsonBoard")
        boardEngine.process(box, board)
      case Some(vote: JsonVote) =>
        System.err.println("VillageProcessingEngine.process JsonVote")
        voteEngine.process(box, vote)
      case Some(scroll: JsonScroll) =>
        System.err.println("VillageProcessingEngine.process JsonScroll")
        scrollEngine.process(box, scroll)
      case Some(phase: JsonPhase) =>
        System.err.println("VillageProcessingEngine.process JsonPhase")
        phaseEngine.process(box, phase)
      case Some(flavorText: JsonFlavorText) =>
        System.err.println("VillageProcessingEngine.process JsonFlavorText")
        flavorTextEngine.process(box, flavorText)
      case Some(gameResult: JsonGameResult) =>
        System.err.println("VillageProcessingEngine.process JsonGameResult")
        gameResultEngine.process(box, gameResult)
      case Some(buildVillage: JsonBuildVillage) =>
        System.err.println("VillageProcessingEngine.process JsonBuildVillage")
        buildVillageEngine.process(box, buildVillage)
      case Some(leaveWaitingPage: JsonLeaveWaitingPage) =>
        System.err.println("VillageProcessingEngine.process JsonLeaveWaitingPage")
        leaveWaitingPageEngine.process(box, leaveWaitingPage)
      case Some(error: JsonError) =>
        System.err.println("VillageProcessingEngine.process JsonError")
        errorEngine.process(box, error)
      case _ =>
        System.err.println("VillageProcessingEngine.process return None")
        None
    }
  }
}
