package licos.json.engine.processing

import licos.json.engine.analysis.lobby.client2server._
import licos.json.engine.analysis.village
import licos.json.engine.analysis.village.client2server._
import licos.json.engine.analysis.village.server2client._

/** A village processing engine factory.
  */
class VillageProcessingEngineFactory extends ProcessingEngineFactory {

  private var readyEngine: Option[ReadyAnalysisEngine] = None
  private var receivedPlayerMessageEngine: Option[ReceivedPlayerMessageAnalysisEngine] = None
  private var receivedSystemMessageEngine: Option[ReceivedSystemMessageAnalysisEngine] = None
  private var receivedFlavorTextMessageEngine: Option[ReceivedFlavorTextMessageAnalysisEngine] = None
  private var chatFromClientEngine: Option[village.client2server.ChatAnalysisEngine] = None
  private var chatFromServerEngine: Option[village.server2client.ChatAnalysisEngine] = None
  private var audienceChatFromClientEngine: Option[licos.json.engine.analysis.village.client2server.AudienceChatAnalysisEngine] = None
  private var audienceChatFromServerEngine: Option[licos.json.engine.analysis.village.server2client.AudienceChatAnalysisEngine] = None
  private var boardEngine: Option[BoardAnalysisEngine] = None
  private var voteEngine: Option[VoteAnalysisEngine] = None
  private var scrollEngine: Option[ScrollAnalysisEngine] = None
  private var starEngine: Option[StarAnalysisEngine] = None
  private var phaseEngine: Option[PhaseAnalysisEngine] = None
  private var flavorTextEngine: Option[FlavorTextAnalysisEngine] = None
  private var gameResultEngine: Option[GameResultAnalysisEngine] = None
  private var buildVillageEngine: Option[BuildVillageAnalysisEngine] = None
  private var leaveWaitingPageEngine: Option[LeaveWaitingPageAnalysisEngine] = None
  private var nextGameInvitationEngine: Option[NextGameInvitationAnalysisEngine] = None
  private var nextGameInvitationIsClosedEngine: Option[NextGameInvitationIsClosedAnalysisEngine] = None
  private var errorFromClientEngine: Option[licos.json.engine.analysis.village.client2server.ErrorAnalysisEngine] = None
  private var errorFromServerEngine: Option[licos.json.engine.analysis.village.server2client.ErrorAnalysisEngine] = None

  /** Create a village processing engine.
    *
    * @return a processing engine.
    */
  override def create: VillageProcessingEngine = {
    new VillageProcessingEngine(
      readyEngine: Option[ReadyAnalysisEngine],
      receivedPlayerMessageEngine: Option[ReceivedPlayerMessageAnalysisEngine],
      receivedSystemMessageEngine: Option[ReceivedSystemMessageAnalysisEngine],
      receivedFlavorTextMessageEngine: Option[ReceivedFlavorTextMessageAnalysisEngine],
      chatFromClientEngine: Option[village.client2server.ChatAnalysisEngine],
      chatFromServerEngine: Option[village.server2client.ChatAnalysisEngine],
      audienceChatFromClientEngine: Option[licos.json.engine.analysis.village.client2server.AudienceChatAnalysisEngine],
      audienceChatFromServerEngine: Option[licos.json.engine.analysis.village.server2client.AudienceChatAnalysisEngine],
      boardEngine: Option[BoardAnalysisEngine],
      voteEngine: Option[VoteAnalysisEngine],
      scrollEngine: Option[ScrollAnalysisEngine],
      starEngine: Option[StarAnalysisEngine],
      phaseEngine: Option[PhaseAnalysisEngine],
      flavorTextEngine: Option[FlavorTextAnalysisEngine],
      gameResultEngine: Option[GameResultAnalysisEngine],
      buildVillageEngine: Option[BuildVillageAnalysisEngine],
      leaveWaitingPageEngine: Option[LeaveWaitingPageAnalysisEngine],
      nextGameInvitationEngine: Option[NextGameInvitationAnalysisEngine],
      nextGameInvitationIsClosedEngine: Option[NextGameInvitationIsClosedAnalysisEngine],
      errorFromClientEngine: Option[licos.json.engine.analysis.village.client2server.ErrorAnalysisEngine],
      errorFromServerEngine: Option[licos.json.engine.analysis.village.server2client.ErrorAnalysisEngine]
    )
  }

  def set(readyEngine: ReadyAnalysisEngine): VillageProcessingEngineFactory = {
    this.readyEngine = Option(readyEngine)
    this
  }

  def set(receivedPlayerMessageEngine: ReceivedPlayerMessageAnalysisEngine): VillageProcessingEngineFactory = {
    this.receivedPlayerMessageEngine = Option(receivedPlayerMessageEngine)
    this
  }

  def set(receivedSystemMessageEngine: ReceivedSystemMessageAnalysisEngine): VillageProcessingEngineFactory = {
    this.receivedSystemMessageEngine = Option(receivedSystemMessageEngine)
    this
  }

  def set(receivedFlavorTextMessageEngine: ReceivedFlavorTextMessageAnalysisEngine): VillageProcessingEngineFactory = {
    this.receivedFlavorTextMessageEngine = Option(receivedFlavorTextMessageEngine)
    this
  }

  def set(chatFromClientEngine: village.client2server.ChatAnalysisEngine): VillageProcessingEngineFactory = {
    this.chatFromClientEngine = Option(chatFromClientEngine)
    this
  }

  def set(chatFromServerEngine: village.server2client.ChatAnalysisEngine): VillageProcessingEngineFactory = {
    this.chatFromServerEngine = Option(chatFromServerEngine)
    this
  }

  def set(audienceChatFromClientEngine: licos.json.engine.analysis.village.client2server.AudienceChatAnalysisEngine): VillageProcessingEngineFactory = {
    this.audienceChatFromClientEngine = Option(audienceChatFromClientEngine)
    this
  }

  def set(audienceChatFromServerEngine: licos.json.engine.analysis.village.server2client.AudienceChatAnalysisEngine): VillageProcessingEngineFactory = {
    this.audienceChatFromServerEngine = Option(audienceChatFromServerEngine)
    this
  }

  def set(boardEngine: BoardAnalysisEngine): VillageProcessingEngineFactory = {
    this.boardEngine = Option(boardEngine)
    this
  }

  def set(voteEngine: VoteAnalysisEngine): VillageProcessingEngineFactory = {
    this.voteEngine = Option(voteEngine)
    this
  }

  def set(scrollEngine: ScrollAnalysisEngine): VillageProcessingEngineFactory = {
    this.scrollEngine = Option(scrollEngine)
    this
  }

  def set(starEngine: StarAnalysisEngine): VillageProcessingEngineFactory = {
    this.starEngine = Option(starEngine)
    this
  }

  def set(phaseEngine: PhaseAnalysisEngine): VillageProcessingEngineFactory = {
    this.phaseEngine = Option(phaseEngine)
    this
  }

  def set(flavorTextEngine: FlavorTextAnalysisEngine): VillageProcessingEngineFactory = {
    this.flavorTextEngine = Option(flavorTextEngine)
    this
  }

  def set(gameResultEngine: GameResultAnalysisEngine): VillageProcessingEngineFactory = {
    this.gameResultEngine = Option(gameResultEngine)
    this
  }

  def set(buildVillageEngine: BuildVillageAnalysisEngine): VillageProcessingEngineFactory = {
    this.buildVillageEngine = Option(buildVillageEngine)
    this
  }

  def set(leaveWaitingEngine: LeaveWaitingPageAnalysisEngine): VillageProcessingEngineFactory = {
    this.leaveWaitingPageEngine = Option(leaveWaitingEngine)
    this
  }

  def set(nextGameInvitationEngine: NextGameInvitationAnalysisEngine): VillageProcessingEngineFactory = {
    this.nextGameInvitationEngine = Option(nextGameInvitationEngine)
    this
  }

  def set(nextGameInvitationIsClosedEngine: NextGameInvitationIsClosedAnalysisEngine): VillageProcessingEngineFactory = {
    this.nextGameInvitationIsClosedEngine = Option(nextGameInvitationIsClosedEngine)
    this
  }

  def set(errorFromClientEngine: licos.json.engine.analysis.village.client2server.ErrorAnalysisEngine): VillageProcessingEngineFactory = {
    this.errorFromClientEngine = Option(errorFromClientEngine)
    this
  }

  def set(errorFromServerEngine: licos.json.engine.analysis.village.server2client.ErrorAnalysisEngine): VillageProcessingEngineFactory = {
    this.errorFromServerEngine = Option(errorFromServerEngine)
    this
  }
}