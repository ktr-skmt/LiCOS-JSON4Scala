package licos.json.engine.processing

import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.{BuildVillageAnalysisEngine, LeaveWaitingPageAnalysisEngine, ReadyAnalysisEngine}
import licos.json.engine.analysis.village.{BoardAnalysisEngine, ChatFromClientAnalysisEngine, ChatFromServerAnalysisEngine, ErrorAnalysisEngine, FlavorTextAnalysisEngine, GameResultAnalysisEngine, NextGameInvitationAnalysisEngine, NextGameInvitationIsClosedAnalysisEngine, PhaseAnalysisEngine, ReceivedFlavorTextMessageAnalysisEngine, ReceivedPlayerMessageAnalysisEngine, ReceivedSystemMessageAnalysisEngine, ScrollAnalysisEngine, StarAnalysisEngine, VoteAnalysisEngine}
import licos.json.flow.{FlowController, VillageFlowController}
import licos.json.lobby.{JsonBuildVillage, JsonLeaveWaitingPage, JsonReady}
import licos.json.village.invite.{JsonNextGameInvitation, JsonNextGameInvitationIsClosed}
import licos.json.village.{JsonBoard, JsonChatFromClient, JsonChatFromServer, JsonError, JsonFlavorText, JsonGameResult, JsonPhase, JsonScroll, JsonStar, JsonVote}
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
  * @param starEngine the analysis engine for Star JSON.
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
                              starEngine: StarAnalysisEngine,
                              phaseEngine: PhaseAnalysisEngine,
                              flavorTextEngine: FlavorTextAnalysisEngine,
                              gameResultEngine: GameResultAnalysisEngine,
                              buildVillageEngine: BuildVillageAnalysisEngine,
                              leaveWaitingPageEngine: LeaveWaitingPageAnalysisEngine,
                              nextGameInvitationEngine: NextGameInvitationAnalysisEngine,
                              nextGameInvitationIsClosedEngine: NextGameInvitationIsClosedAnalysisEngine,
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

    def log(label: String): Unit = {
      val format: String = "VillageProcessingEngine.process %s%n"
      System.err.printf(format, label)
    }

    flowController.flow(jsValue) match {
      case Some(ready: JsonReady) =>
        log("JsonReady")
        readyEngine.process(box, ready)
      case Some(receivedPlayerMessage: JsonReceivedPlayerMessage) =>
        log("JsonReceivedPlayerMessage")
        receivedPlayerMessageEngine.process(box, receivedPlayerMessage)
      case Some(receivedSystemMessage: JsonReceivedSystemMessage) =>
        log("JsonReceivedSystemMessage")
        receivedSystemMessageEngine.process(box, receivedSystemMessage)
      case Some(receivedFlavorTextMessage: JsonReceivedFlavorTextMessage) =>
        log("JsonReceivedFlavorTestMessage")
        receivedFlavorTextMessageEngine.process(box, receivedFlavorTextMessage)
      case Some(chatFromClient: JsonChatFromClient) =>
        log("JsonChatFromClient")
        chatFromClientEngine.process(box, chatFromClient)
      case Some(chatFromServer: JsonChatFromServer) =>
        log("JsonChatFromServer")
        chatFromServerEngine.process(box, chatFromServer)
      case Some(board: JsonBoard) =>
        log("JsonBoard")
        boardEngine.process(box, board)
      case Some(vote: JsonVote) =>
        log("JsonVote")
        voteEngine.process(box, vote)
      case Some(scroll: JsonScroll) =>
        log("JsonScroll")
        scrollEngine.process(box, scroll)
      case Some(star: JsonStar) =>
        log("JsonStar")
        starEngine.process(box, star)
      case Some(phase: JsonPhase) =>
        log("JsonPhase")
        phaseEngine.process(box, phase)
      case Some(flavorText: JsonFlavorText) =>
        log("JsonFlavorText")
        flavorTextEngine.process(box, flavorText)
      case Some(gameResult: JsonGameResult) =>
        log("JsonGameResult")
        gameResultEngine.process(box, gameResult)
      case Some(buildVillage: JsonBuildVillage) =>
        log("JsonBuildVillage")
        buildVillageEngine.process(box, buildVillage)
      case Some(leaveWaitingPage: JsonLeaveWaitingPage) =>
        log("JsonLeaveWaitingPage")
        leaveWaitingPageEngine.process(box, leaveWaitingPage)
      case Some(nextGameInvitation: JsonNextGameInvitation) =>
        log("JsonNextGameInvitation")
        nextGameInvitationEngine.process(box, nextGameInvitation)
      case Some(nextGameInvitationIsClosed: JsonNextGameInvitationIsClosed) =>
        log("JsonNextGameInvitationIsClosed")
        nextGameInvitationIsClosedEngine.process(box, nextGameInvitationIsClosed)
      case Some(error: JsonError) =>
        log("JsonError")
        errorEngine.process(box, error)
      case _ =>
        log("return None")
        None
    }
  }
}