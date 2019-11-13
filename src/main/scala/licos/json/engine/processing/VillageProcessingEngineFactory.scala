package licos.json.engine.processing

import licos.json.engine.analysis.lobby.client2server._
import licos.json.engine.analysis.village
import licos.json.engine.analysis.village.client2server._
import licos.json.engine.analysis.village.server2client._

/** A village processing engine factory.
  */
@SuppressWarnings(Array[String]("org.wartremover.warts.Var", "org.wartremover.warts.Overloading"))
class VillageProcessingEngineFactory extends ProcessingEngineFactory {

  private var readyEngine:                     Option[ReadyAnalysisEngine]                      = None
  private var receivedPlayerMessageEngine:     Option[ReceivedChatMessageAnalysisEngine]        = None
  private var receivedSystemMessageEngine:     Option[ReceivedSystemMessageAnalysisEngine]      = None
  private var receivedFlavorTextMessageEngine: Option[ReceivedFlavorTextMessageAnalysisEngine]  = None
  private var chatFromClientEngine:            Option[village.client2server.ChatAnalysisEngine] = None
  private var chatFromServerEngine:            Option[village.server2client.ChatAnalysisEngine] = None
  private var onymousAudienceChatFromClientEngine: Option[village.client2server.OnymousAudienceChatAnalysisEngine] =
    None
  private var onymousAudienceChatFromServerEngine: Option[village.server2client.OnymousAudienceChatAnalysisEngine] =
    None
  private var anonymousAudienceChatFromClientEngine: Option[village.client2server.AnonymousAudienceChatAnalysisEngine] =
    None
  private var anonymousAudienceChatFromServerEngine: Option[village.server2client.AnonymousAudienceChatAnalysisEngine] =
    None
  private var boardEngine:                      Option[BoardAnalysisEngine]                       = None
  private var onymousAudienceBoardEngine:       Option[OnymousAudienceBoardAnalysisEngine]        = None
  private var voteEngine:                       Option[VoteAnalysisEngine]                        = None
  private var scrollEngine:                     Option[ScrollAnalysisEngine]                      = None
  private var onymousAudienceScrollEngine:      Option[OnymousAudienceScrollAnalysisEngine]       = None
  private var starEngine:                       Option[StarAnalysisEngine]                        = None
  private var phaseEngine:                      Option[PhaseAnalysisEngine]                       = None
  private var flavorTextEngine:                 Option[FlavorTextAnalysisEngine]                  = None
  private var gameResultEngine:                 Option[GameResultAnalysisEngine]                  = None
  private var buildVillageEngine:               Option[BuildVillageAnalysisEngine]                = None
  private var leaveWaitingPageEngine:           Option[LeaveWaitingPageAnalysisEngine]            = None
  private var nextGameInvitationEngine:         Option[NextGameInvitationAnalysisEngine]          = None
  private var nextGameInvitationIsClosedEngine: Option[NextGameInvitationIsClosedAnalysisEngine]  = None
  private var errorFromClientEngine:            Option[village.client2server.ErrorAnalysisEngine] = None
  private var errorFromServerEngine:            Option[village.server2client.ErrorAnalysisEngine] = None

  /** Creates a village processing engine.
    *
    * @return a processing engine.
    */
  override def create: VillageProcessingEngine = {
    new VillageProcessingEngine(
      readyEngine:                           Option[ReadyAnalysisEngine],
      receivedPlayerMessageEngine:           Option[ReceivedChatMessageAnalysisEngine],
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
      errorFromServerEngine:                 Option[village.server2client.ErrorAnalysisEngine]
    )
  }

  def set(readyEngine: ReadyAnalysisEngine): VillageProcessingEngineFactory = {
    this.readyEngine = Option(readyEngine)
    this
  }

  def set(receivedPlayerMessageEngine: ReceivedChatMessageAnalysisEngine): VillageProcessingEngineFactory = {
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

  def set(
      onymousAudienceChatFromClientEngine: village.client2server.OnymousAudienceChatAnalysisEngine
  ): VillageProcessingEngineFactory = {
    this.onymousAudienceChatFromClientEngine = Option(onymousAudienceChatFromClientEngine)
    this
  }

  def set(
      onymousAudienceChatFromServerEngine: village.server2client.OnymousAudienceChatAnalysisEngine
  ): VillageProcessingEngineFactory = {
    this.onymousAudienceChatFromServerEngine = Option(onymousAudienceChatFromServerEngine)
    this
  }

  def set(
      anonymousAudienceChatFromClientEngine: village.client2server.AnonymousAudienceChatAnalysisEngine
  ): VillageProcessingEngineFactory = {
    this.anonymousAudienceChatFromClientEngine = Option(anonymousAudienceChatFromClientEngine)
    this
  }

  def set(
      anonymousAudienceChatFromServerEngine: village.server2client.AnonymousAudienceChatAnalysisEngine
  ): VillageProcessingEngineFactory = {
    this.anonymousAudienceChatFromServerEngine = Option(anonymousAudienceChatFromServerEngine)
    this
  }

  def set(boardEngine: BoardAnalysisEngine): VillageProcessingEngineFactory = {
    this.boardEngine = Option(boardEngine)
    this
  }

  def set(onymousAudienceBoardEngine: OnymousAudienceBoardAnalysisEngine): VillageProcessingEngineFactory = {
    this.onymousAudienceBoardEngine = Option(onymousAudienceBoardEngine)
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

  def set(onymousAudienceScrollEngine: OnymousAudienceScrollAnalysisEngine): VillageProcessingEngineFactory = {
    this.onymousAudienceScrollEngine = Option(onymousAudienceScrollEngine)
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

  def set(
      nextGameInvitationIsClosedEngine: NextGameInvitationIsClosedAnalysisEngine
  ): VillageProcessingEngineFactory = {
    this.nextGameInvitationIsClosedEngine = Option(nextGameInvitationIsClosedEngine)
    this
  }

  def set(errorFromClientEngine: village.client2server.ErrorAnalysisEngine): VillageProcessingEngineFactory = {
    this.errorFromClientEngine = Option(errorFromClientEngine)
    this
  }

  def set(errorFromServerEngine: village.server2client.ErrorAnalysisEngine): VillageProcessingEngineFactory = {
    this.errorFromServerEngine = Option(errorFromServerEngine)
    this
  }
}
