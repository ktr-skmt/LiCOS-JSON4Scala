package licos.json.engine.processing

import com.typesafe.scalalogging.Logger
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server._
import licos.json.engine.analysis.village
import licos.json.engine.analysis.village.client2server._
import licos.json.engine.analysis.village.server2client._
import licos.json.flow.{FlowController, VillageFlowController}
import licos.json.element.lobby.{JsonBuildVillage, JsonLeaveWaitingPage, JsonReady}
import licos.json.element.village._
import licos.json.element.village.invite.{JsonNextGameInvitation, JsonNextGameInvitationIsClosed}
import licos.json.element.village.receipt.{
  JsonReceivedChatMessage,
  JsonReceivedFlavorTextMessage,
  JsonReceivedSystemMessage
}
import play.api.libs.json.{JsValue, Json}

/** This class implements the processing engine that aggregates and runs analysis engines for village.
  *
  * @param readyEngine the analysis engine for Ready JSON.
  * @param receivedChatMessageEngine the analysis engine for Received-player-message JSON.
  * @param receivedSystemMessageEngine the analysis engine for Received-system-message JSON.
  * @param receivedFlavorTextMessageEngine the analysis engine for Flavor-text-message JSON.
  * @param chatFromClientEngine the analysis engine for Chat-from-client JSON.
  * @param chatFromServerEngine the analysis engine for Chat-from-server JSON.
  * @param onymousAudienceChatFromClientEngine the analysis engine for Onymous-audience-chat-from-client JSON.
  * @param onymousAudienceChatFromServerEngine the analysis engine for Onymous-audience-chat-from-server JSON.
  * @param anonymousAudienceChatFromClientEngine the analysis engine for Anonymous-audience-chat-from-client JSON.
  * @param anonymousAudienceChatFromServerEngine the analysis engine for Anonymous-audience-chat-from-server JSON.
  * @param boardEngine the analysis engine for Board JSON.
  * @param onymousAudienceBoardEngine the analysis engine for Onymous-audience-board JSON.
  * @param voteEngine the analysis engine for Vote JSON.
  * @param scrollEngine the analysis engine for Scroll JSON.
  * @param onymousAudienceScrollEngine the analysis engine for Onymous-audience-scroll JSON.
  * @param starEngine the analysis engine for Star JSON.
  * @param phaseEngine the analysis engine for Phase JSON.
  * @param flavorTextEngine the analysis engine for Flavor-text JSON.
  * @param gameResultEngine the analysis engine for Game-result JSON.
  * @param buildVillageEngine the analysis engine for Build-village JSON.
  * @param leaveWaitingPageEngine the analysis engine for Leave-waiting-page JSON.
  * @param errorFromClientEngine the analysis engine for Error-from-client JSON.
  * @param errorFromServerEngine the analysis engine for Error-from-server JSON.
  * @author Kotaro Sakamoto
  */
class VillageProcessingEngine(
    readyEngine:                           Option[ReadyAnalysisEngine],
    receivedChatMessageEngine:             Option[ReceivedChatMessageAnalysisEngine],
    receivedSystemMessageEngine:           Option[ReceivedSystemMessageAnalysisEngine],
    receivedFlavorTextMessageEngine:       Option[ReceivedFlavorTextMessageAnalysisEngine],
    chatFromClientEngine:                  Option[village.client2server.ChatAnalysisEngine],
    chatFromServerEngine:                  Option[village.server2client.ChatAnalysisEngine],
    onymousAudienceChatFromClientEngine:   Option[village.client2server.OnymousAudienceChatAnalysisEngine],
    onymousAudienceChatFromServerEngine:   Option[village.server2client.OnymousAudienceChatAnalysisEngine],
    anonymousAudienceChatFromClientEngine: Option[village.client2server.AnonymousAudienceChatAnalysisEngine],
    anonymousAudienceChatFromServerEngine: Option[village.server2client.AnonymousAudienceChatAnalysisEngine],
    boardEngine:                           Option[BoardAnalysisEngine],
    onymousAudienceBoardEngine:            Option[OnymousAudienceBoardAnalysisEngine],
    voteEngine:                            Option[VoteAnalysisEngine],
    scrollEngine:                          Option[ScrollAnalysisEngine],
    onymousAudienceScrollEngine:           Option[OnymousAudienceScrollAnalysisEngine],
    starEngine:                            Option[StarAnalysisEngine],
    phaseEngine:                           Option[PhaseAnalysisEngine],
    flavorTextEngine:                      Option[FlavorTextAnalysisEngine],
    gameResultEngine:                      Option[GameResultAnalysisEngine],
    buildVillageEngine:                    Option[BuildVillageAnalysisEngine],
    leaveWaitingPageEngine:                Option[LeaveWaitingPageAnalysisEngine],
    nextGameInvitationEngine:              Option[NextGameInvitationAnalysisEngine],
    nextGameInvitationIsClosedEngine:      Option[NextGameInvitationIsClosedAnalysisEngine],
    errorFromClientEngine:                 Option[village.client2server.ErrorAnalysisEngine],
    errorFromServerEngine:                 Option[village.server2client.ErrorAnalysisEngine])
    extends ProcessingEngine {

  override protected val flowController: FlowController = new VillageFlowController()

  private final val logger: Logger = Logger[VillageProcessingEngine]

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param msg a JSON message.
    * @return a play.api.libs.json.JsValue option.
    */
  override def process(box: BOX, msg: String): Option[JsValue] = {

    val jsValue: JsValue = Json.parse(msg)

    def log(label: String): Unit = {
      val format: String = "process %s"
      logger.info(format.format(label))
    }

    flowController.flow(jsValue) match {
      case Some(ready: JsonReady) =>
        log("JsonReady")
        readyEngine.flatMap(_.process(box, ready))
      case Some(receivedChatMessage: JsonReceivedChatMessage) =>
        log("JsonReceivedChatMessage")
        receivedChatMessageEngine.flatMap(_.process(box, receivedChatMessage))
      case Some(receivedSystemMessage: JsonReceivedSystemMessage) =>
        log("JsonReceivedSystemMessage")
        receivedSystemMessageEngine.flatMap(_.process(box, receivedSystemMessage))
      case Some(receivedFlavorTextMessage: JsonReceivedFlavorTextMessage) =>
        log("JsonReceivedFlavorTestMessage")
        receivedFlavorTextMessageEngine.flatMap(_.process(box, receivedFlavorTextMessage))
      case Some(chatFromClient: JsonChatFromClient) =>
        log("JsonChatFromClient")
        chatFromClientEngine.flatMap(_.process(box, chatFromClient))
      case Some(chatFromServer: JsonChatFromServer) =>
        log("JsonChatFromServer")
        chatFromServerEngine.flatMap(_.process(box, chatFromServer))
      case Some(onymousAudienceChat: JsonOnymousAudienceChat) =>
        log("JsonOnymousAudienceChat")
        if (onymousAudienceChat.isFromServer) {
          log("JsonOnymousAudienceChatFromServer")
          onymousAudienceChatFromServerEngine.flatMap(_.process(box, onymousAudienceChat))
        } else {
          log("JsonOnymousAudienceChatFromClient")
          onymousAudienceChatFromClientEngine.flatMap(_.process(box, onymousAudienceChat))
        }
      case Some(anonymousAudienceChat: JsonAnonymousAudienceChat) =>
        log("JsonAnonymousAudienceChat")
        if (anonymousAudienceChat.isFromServer) {
          log("JsonAnonymousAudienceChatFromServer")
          anonymousAudienceChatFromServerEngine.flatMap(_.process(box, anonymousAudienceChat))
        } else {
          log("JsonAnonymousAudienceChatFromClient")
          anonymousAudienceChatFromClientEngine.flatMap(_.process(box, anonymousAudienceChat))
        }
      case Some(board: JsonBoard) =>
        log("JsonBoard")
        boardEngine.flatMap(_.process(box, board))
      case Some(onymousAudienceBoard: JsonOnymousAudienceBoard) =>
        log("JsonOnymousAudienceBoard")
        onymousAudienceBoardEngine.flatMap(_.process(box, onymousAudienceBoard))
      case Some(vote: JsonVote) =>
        log("JsonVote")
        voteEngine.flatMap(_.process(box, vote))
      case Some(scroll: JsonScroll) =>
        log("JsonScroll")
        scrollEngine.flatMap(_.process(box, scroll))
      case Some(onymousAudienceScroll: JsonOnymousAudienceScroll) =>
        log("JsonOnymousAudienceScroll")
        onymousAudienceScrollEngine.flatMap(_.process(box, onymousAudienceScroll))
      case Some(star: JsonStar) =>
        log("JsonStar")
        starEngine.flatMap(_.process(box, star))
      case Some(phase: JsonPhase) =>
        log("JsonPhase")
        phaseEngine.flatMap(_.process(box, phase))
      case Some(flavorText: JsonFlavorText) =>
        log("JsonFlavorText")
        flavorTextEngine.flatMap(_.process(box, flavorText))
      case Some(gameResult: JsonGameResult) =>
        log("JsonGameResult")
        gameResultEngine.flatMap(_.process(box, gameResult))
      case Some(buildVillage: JsonBuildVillage) =>
        log("JsonBuildVillage")
        buildVillageEngine.flatMap(_.process(box, buildVillage))
      case Some(leaveWaitingPage: JsonLeaveWaitingPage) =>
        log("JsonLeaveWaitingPage")
        leaveWaitingPageEngine.flatMap(_.process(box, leaveWaitingPage))
      case Some(nextGameInvitation: JsonNextGameInvitation) =>
        log("JsonNextGameInvitation")
        nextGameInvitationEngine.flatMap(_.process(box, nextGameInvitation))
      case Some(nextGameInvitationIsClosed: JsonNextGameInvitationIsClosed) =>
        log("JsonNextGameInvitationIsClosed")
        nextGameInvitationIsClosedEngine.flatMap(_.process(box, nextGameInvitationIsClosed))
      case Some(error: JsonError) =>
        log("JsonError")
        if (error.isFromServer) {
          log("JsonErrorFromServer")
          errorFromServerEngine.flatMap(_.process(box, error))
        } else {
          log("JsonErrorFromClient")
          errorFromClientEngine.flatMap(_.process(box, error))
        }
      case _ =>
        log("return None")
        None
    }
  }
}
