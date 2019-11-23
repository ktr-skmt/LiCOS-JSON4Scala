package licos.protocol.engine.processing

import licos.protocol.engine.analysis.village.client2server.{
  AnonymousAudienceChatFromClientAnalysisEngine,
  BoardAnalysisEngine,
  BuildVillageAnalysisEngine,
  ChatFromClientAnalysisEngine,
  ErrorFromClientAnalysisEngine,
  LeaveWaitingPageAnalysisEngine,
  OnymousAudienceBoardAnalysisEngine,
  OnymousAudienceChatFromClientAnalysisEngine,
  OnymousAudienceScrollAnalysisEngine,
  ReadyAnalysisEngine,
  ReceivedChatMessageAnalysisEngine,
  ReceivedFlavorTextMessageAnalysisEngine,
  ReceivedSystemMessageAnalysisEngine,
  ScrollAnalysisEngine,
  StarAnalysisEngine,
  VoteAnalysisEngine
}
import licos.protocol.engine.analysis.village.server2client.{
  AnonymousAudienceChatFromServerAnalysisEngine,
  ChatFromServerAnalysisEngine,
  ErrorFromServerAnalysisEngine,
  FirstMorningPhaseAnalysisEngine,
  FlavorTextAnalysisEngine,
  GameResultAnalysisEngine,
  MorningPhaseAnalysisEngine,
  NextGameInvitationAnalysisEngine,
  NextGameInvitationIsClosedAnalysisEngine,
  NightPhaseAnalysisEngine,
  NoonPhaseAnalysisEngine,
  OnymousAudienceChatFromServerAnalysisEngine
}

class VillageProcessingEngineFactory extends ProcessingEngineFactory {

  private var anonymousAudienceChatFromClientAnalysisEngine: Option[AnonymousAudienceChatFromClientAnalysisEngine] =
    None
  private var boardAnalysisEngine:                         Option[BoardAnalysisEngine]                         = None
  private var buildVillageAnalysisEngine:                  Option[BuildVillageAnalysisEngine]                  = None
  private var chatFromClientAnalysisEngine:                Option[ChatFromClientAnalysisEngine]                = None
  private var errorFromClientAnalysisEngine:               Option[ErrorFromClientAnalysisEngine]               = None
  private var leaveWaitingPageAnalysisEngine:              Option[LeaveWaitingPageAnalysisEngine]              = None
  private var onymousAudienceBoardAnalysisEngine:          Option[OnymousAudienceBoardAnalysisEngine]          = None
  private var onymousAudienceChatFromClientAnalysisEngine: Option[OnymousAudienceChatFromClientAnalysisEngine] = None
  private var onymousAudienceScrollAnalysisEngine:         Option[OnymousAudienceScrollAnalysisEngine]         = None
  private var readyAnalysisEngine:                         Option[ReadyAnalysisEngine]                         = None
  private var receivedChatMessageAnalysisEngine:           Option[ReceivedChatMessageAnalysisEngine]           = None
  private var receivedFlavorTextMessageAnalysisEngine:     Option[ReceivedFlavorTextMessageAnalysisEngine]     = None
  private var receivedSystemMessageAnalysisEngine:         Option[ReceivedSystemMessageAnalysisEngine]         = None
  private var scrollAnalysisEngine:                        Option[ScrollAnalysisEngine]                        = None
  private var starAnalysisEngine:                          Option[StarAnalysisEngine]                          = None
  private var voteAnalysisEngine:                          Option[VoteAnalysisEngine]                          = None
  private var anonymousAudienceChatFromServerAnalysisEngine: Option[AnonymousAudienceChatFromServerAnalysisEngine] =
    None
  private var chatFromServerAnalysisEngine:                Option[ChatFromServerAnalysisEngine]                = None
  private var errorFromServerAnalysisEngine:               Option[ErrorFromServerAnalysisEngine]               = None
  private var firstMorningPhaseAnalysisEngine:             Option[FirstMorningPhaseAnalysisEngine]             = None
  private var flavorTextAnalysisEngine:                    Option[FlavorTextAnalysisEngine]                    = None
  private var gameResultAnalysisEngine:                    Option[GameResultAnalysisEngine]                    = None
  private var morningPhaseAnalysisEngine:                  Option[MorningPhaseAnalysisEngine]                  = None
  private var nextGameInvitationAnalysisEngine:            Option[NextGameInvitationAnalysisEngine]            = None
  private var nextGameInvitationIsClosedAnalysisEngine:    Option[NextGameInvitationIsClosedAnalysisEngine]    = None
  private var nightPhaseAnalysisEngine:                    Option[NightPhaseAnalysisEngine]                    = None
  private var noonPhaseAnalysisEngine:                     Option[NoonPhaseAnalysisEngine]                     = None
  private var onymousAudienceChatFromServerAnalysisEngine: Option[OnymousAudienceChatFromServerAnalysisEngine] = None

  override def create: VillageProcessingEngine = {
    new VillageProcessingEngine(
      anonymousAudienceChatFromClientAnalysisEngine,
      boardAnalysisEngine,
      buildVillageAnalysisEngine,
      chatFromClientAnalysisEngine,
      errorFromClientAnalysisEngine,
      leaveWaitingPageAnalysisEngine,
      onymousAudienceBoardAnalysisEngine,
      onymousAudienceChatFromClientAnalysisEngine,
      onymousAudienceScrollAnalysisEngine,
      readyAnalysisEngine,
      receivedChatMessageAnalysisEngine,
      receivedFlavorTextMessageAnalysisEngine,
      receivedSystemMessageAnalysisEngine,
      scrollAnalysisEngine,
      starAnalysisEngine,
      voteAnalysisEngine,
      anonymousAudienceChatFromServerAnalysisEngine,
      chatFromServerAnalysisEngine,
      errorFromServerAnalysisEngine,
      firstMorningPhaseAnalysisEngine,
      flavorTextAnalysisEngine,
      gameResultAnalysisEngine,
      morningPhaseAnalysisEngine,
      nextGameInvitationAnalysisEngine,
      nextGameInvitationIsClosedAnalysisEngine,
      nightPhaseAnalysisEngine,
      noonPhaseAnalysisEngine,
      onymousAudienceChatFromServerAnalysisEngine
    )
  }

  def set(anonymousAudienceChatFromClientAnalysisEngine: AnonymousAudienceChatFromClientAnalysisEngine)
    : VillageProcessingEngineFactory = {
    this.anonymousAudienceChatFromClientAnalysisEngine = Option(anonymousAudienceChatFromClientAnalysisEngine)
    this
  }

  def set(boardAnalysisEngine: BoardAnalysisEngine): VillageProcessingEngineFactory = {
    this.boardAnalysisEngine = Option(boardAnalysisEngine)
    this
  }

  def set(buildVillageAnalysisEngine: BuildVillageAnalysisEngine): VillageProcessingEngineFactory = {
    this.buildVillageAnalysisEngine = Option(buildVillageAnalysisEngine)
    this
  }

  def set(chatFromClientAnalysisEngine: ChatFromClientAnalysisEngine): VillageProcessingEngineFactory = {
    this.chatFromClientAnalysisEngine = Option(chatFromClientAnalysisEngine)
    this
  }

  def set(errorFromClientAnalysisEngine: ErrorFromClientAnalysisEngine): VillageProcessingEngineFactory = {
    this.errorFromClientAnalysisEngine = Option(errorFromClientAnalysisEngine)
    this
  }

  def set(leaveWaitingPageAnalysisEngine: LeaveWaitingPageAnalysisEngine): VillageProcessingEngineFactory = {
    this.leaveWaitingPageAnalysisEngine = Option(leaveWaitingPageAnalysisEngine)
    this
  }

  def set(onymousAudienceBoardAnalysisEngine: OnymousAudienceBoardAnalysisEngine): VillageProcessingEngineFactory = {
    this.onymousAudienceBoardAnalysisEngine = Option(onymousAudienceBoardAnalysisEngine)
    this
  }

  def set(onymousAudienceChatFromClientAnalysisEngine: OnymousAudienceChatFromClientAnalysisEngine)
    : VillageProcessingEngineFactory = {
    this.onymousAudienceChatFromClientAnalysisEngine = Option(onymousAudienceChatFromClientAnalysisEngine)
    this
  }

  def set(onymousAudienceScrollAnalysisEngine: OnymousAudienceScrollAnalysisEngine): VillageProcessingEngineFactory = {
    this.onymousAudienceScrollAnalysisEngine = Option(onymousAudienceScrollAnalysisEngine)
    this
  }

  def set(readyAnalysisEngine: ReadyAnalysisEngine): VillageProcessingEngineFactory = {
    this.readyAnalysisEngine = Option(readyAnalysisEngine)
    this
  }

  def set(receivedChatMessageAnalysisEngine: ReceivedChatMessageAnalysisEngine): VillageProcessingEngineFactory = {
    this.receivedChatMessageAnalysisEngine = Option(receivedChatMessageAnalysisEngine)
    this
  }

  def set(receivedFlavorTextMessageAnalysisEngine: ReceivedFlavorTextMessageAnalysisEngine)
    : VillageProcessingEngineFactory = {
    this.receivedFlavorTextMessageAnalysisEngine = Option(receivedFlavorTextMessageAnalysisEngine)
    this
  }

  def set(receivedSystemMessageAnalysisEngine: ReceivedSystemMessageAnalysisEngine): VillageProcessingEngineFactory = {
    this.receivedSystemMessageAnalysisEngine = Option(receivedSystemMessageAnalysisEngine)
    this
  }

  def set(scrollAnalysisEngine: ScrollAnalysisEngine): VillageProcessingEngineFactory = {
    this.scrollAnalysisEngine = Option(scrollAnalysisEngine)
    this
  }

  def set(starAnalysisEngine: StarAnalysisEngine): VillageProcessingEngineFactory = {
    this.starAnalysisEngine = Option(starAnalysisEngine)
    this
  }

  def set(voteAnalysisEngine: VoteAnalysisEngine): VillageProcessingEngineFactory = {
    this.voteAnalysisEngine = Option(voteAnalysisEngine)
    this
  }

  def set(anonymousAudienceChatFromServerAnalysisEngine: AnonymousAudienceChatFromServerAnalysisEngine)
    : VillageProcessingEngineFactory = {
    this.anonymousAudienceChatFromServerAnalysisEngine = Option(anonymousAudienceChatFromServerAnalysisEngine)
    this
  }

  def set(chatFromServerAnalysisEngine: ChatFromServerAnalysisEngine): VillageProcessingEngineFactory = {
    this.chatFromServerAnalysisEngine = Option(chatFromServerAnalysisEngine)
    this
  }

  def set(errorFromServerAnalysisEngine: ErrorFromServerAnalysisEngine): VillageProcessingEngineFactory = {
    this.errorFromServerAnalysisEngine = Option(errorFromServerAnalysisEngine)
    this
  }

  def set(firstMorningPhaseAnalysisEngine: FirstMorningPhaseAnalysisEngine): VillageProcessingEngineFactory = {
    this.firstMorningPhaseAnalysisEngine = Option(firstMorningPhaseAnalysisEngine)
    this
  }

  def set(flavorTextAnalysisEngine: FlavorTextAnalysisEngine): VillageProcessingEngineFactory = {
    this.flavorTextAnalysisEngine = Option(flavorTextAnalysisEngine)
    this
  }

  def set(gameResultAnalysisEngine: GameResultAnalysisEngine): VillageProcessingEngineFactory = {
    this.gameResultAnalysisEngine = Option(gameResultAnalysisEngine)
    this
  }

  def set(morningPhaseAnalysisEngine: MorningPhaseAnalysisEngine): VillageProcessingEngineFactory = {
    this.morningPhaseAnalysisEngine = Option(morningPhaseAnalysisEngine)
    this
  }

  def set(nextGameInvitationAnalysisEngine: NextGameInvitationAnalysisEngine): VillageProcessingEngineFactory = {
    this.nextGameInvitationAnalysisEngine = Option(nextGameInvitationAnalysisEngine)
    this
  }

  def set(nextGameInvitationIsClosedAnalysisEngine: NextGameInvitationIsClosedAnalysisEngine)
    : VillageProcessingEngineFactory = {
    this.nextGameInvitationIsClosedAnalysisEngine = Option(nextGameInvitationIsClosedAnalysisEngine)
    this
  }

  def set(nightPhaseAnalysisEngine: NightPhaseAnalysisEngine): VillageProcessingEngineFactory = {
    this.nightPhaseAnalysisEngine = Option(nightPhaseAnalysisEngine)
    this
  }

  def set(noonPhaseAnalysisEngine: NoonPhaseAnalysisEngine): VillageProcessingEngineFactory = {
    this.noonPhaseAnalysisEngine = Option(noonPhaseAnalysisEngine)
    this
  }

  def set(onymousAudienceChatFromServerAnalysisEngine: OnymousAudienceChatFromServerAnalysisEngine)
    : VillageProcessingEngineFactory = {
    this.onymousAudienceChatFromServerAnalysisEngine = Option(onymousAudienceChatFromServerAnalysisEngine)
    this
  }
}
