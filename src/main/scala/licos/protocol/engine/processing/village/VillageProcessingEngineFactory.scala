package licos.protocol.engine.processing.village

import licos.protocol.engine.analysis.village.client2server._
import licos.protocol.engine.analysis.village.server2client._
import licos.protocol.engine.processing.ProcessingEngineFactory

@SuppressWarnings(Array[String]("org.wartremover.warts.Overloading", "org.wartremover.warts.Var"))
final class VillageProcessingEngineFactory extends ProcessingEngineFactory {

  private var anonymousAudienceChatFromClientAnalysisEngine: Option[AnonymousAudienceChatFromClientAnalysisEngine] =
    Option.empty[AnonymousAudienceChatFromClientAnalysisEngine]
  private var boardAnalysisEngine:        Option[BoardAnalysisEngine]        = Option.empty[BoardAnalysisEngine]
  private var buildVillageAnalysisEngine: Option[BuildVillageAnalysisEngine] = Option.empty[BuildVillageAnalysisEngine]
  private var chatFromClientAnalysisEngine: Option[ChatFromClientAnalysisEngine] =
    Option.empty[ChatFromClientAnalysisEngine]
  private var errorFromClientAnalysisEngine: Option[ErrorFromClientAnalysisEngine] =
    Option.empty[ErrorFromClientAnalysisEngine]
  private var leaveWaitingPageAnalysisEngine: Option[LeaveWaitingPageAnalysisEngine] =
    Option.empty[LeaveWaitingPageAnalysisEngine]
  private var onymousAudienceBoardAnalysisEngine: Option[OnymousAudienceBoardAnalysisEngine] =
    Option.empty[OnymousAudienceBoardAnalysisEngine]
  private var onymousAudienceChatFromClientAnalysisEngine: Option[OnymousAudienceChatFromClientAnalysisEngine] =
    Option.empty[OnymousAudienceChatFromClientAnalysisEngine]
  private var onymousAudienceScrollAnalysisEngine: Option[OnymousAudienceScrollAnalysisEngine] =
    Option.empty[OnymousAudienceScrollAnalysisEngine]
  private var readyAnalysisEngine: Option[ReadyAnalysisEngine] = Option.empty[ReadyAnalysisEngine]
  private var receivedChatMessageAnalysisEngine: Option[ReceivedChatMessageAnalysisEngine] =
    Option.empty[ReceivedChatMessageAnalysisEngine]
  private var receivedFlavorTextMessageAnalysisEngine: Option[ReceivedFlavorTextMessageAnalysisEngine] =
    Option.empty[ReceivedFlavorTextMessageAnalysisEngine]
  private var receivedSystemMessageAnalysisEngine: Option[ReceivedSystemMessageAnalysisEngine] =
    Option.empty[ReceivedSystemMessageAnalysisEngine]
  private var scrollAnalysisEngine: Option[ScrollAnalysisEngine] = Option.empty[ScrollAnalysisEngine]
  private var starAnalysisEngine:   Option[StarAnalysisEngine]   = Option.empty[StarAnalysisEngine]
  private var voteAnalysisEngine:   Option[VoteAnalysisEngine]   = Option.empty[VoteAnalysisEngine]
  private var anonymousAudienceChatFromServerAnalysisEngine: Option[AnonymousAudienceChatFromServerAnalysisEngine] =
    Option.empty[AnonymousAudienceChatFromServerAnalysisEngine]
  private var chatFromServerAnalysisEngine: Option[ChatFromServerAnalysisEngine] =
    Option.empty[ChatFromServerAnalysisEngine]
  private var errorFromServerAnalysisEngine: Option[ErrorFromServerAnalysisEngine] =
    Option.empty[ErrorFromServerAnalysisEngine]
  private var firstMorningPhaseAnalysisEngine: Option[FirstMorningPhaseAnalysisEngine] =
    Option.empty[FirstMorningPhaseAnalysisEngine]
  private var flavorTextAnalysisEngine:   Option[FlavorTextAnalysisEngine]   = Option.empty[FlavorTextAnalysisEngine]
  private var gameResultAnalysisEngine:   Option[GameResultAnalysisEngine]   = Option.empty[GameResultAnalysisEngine]
  private var morningPhaseAnalysisEngine: Option[MorningPhaseAnalysisEngine] = Option.empty[MorningPhaseAnalysisEngine]
  private var nextGameInvitationAnalysisEngine: Option[NextGameInvitationAnalysisEngine] =
    Option.empty[NextGameInvitationAnalysisEngine]
  private var nextGameInvitationIsClosedAnalysisEngine: Option[NextGameInvitationIsClosedAnalysisEngine] =
    Option.empty[NextGameInvitationIsClosedAnalysisEngine]
  private var nightPhaseAnalysisEngine: Option[NightPhaseAnalysisEngine] = Option.empty[NightPhaseAnalysisEngine]
  private var noonPhaseAnalysisEngine:  Option[NoonPhaseAnalysisEngine]  = Option.empty[NoonPhaseAnalysisEngine]
  private var onymousAudienceChatFromServerAnalysisEngine: Option[OnymousAudienceChatFromServerAnalysisEngine] =
    Option.empty[OnymousAudienceChatFromServerAnalysisEngine]
  private var postMortemDiscussionAnalysisEngine: Option[PostMortemDiscussionAnalysisEngine] =
    Option.empty[PostMortemDiscussionAnalysisEngine]

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
      onymousAudienceChatFromServerAnalysisEngine,
      postMortemDiscussionAnalysisEngine
    )
  }

  def set(
      anonymousAudienceChatFromClientAnalysisEngine: AnonymousAudienceChatFromClientAnalysisEngine
  ): VillageProcessingEngineFactory = {
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

  def set(
      onymousAudienceChatFromClientAnalysisEngine: OnymousAudienceChatFromClientAnalysisEngine
  ): VillageProcessingEngineFactory = {
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

  def set(
      receivedFlavorTextMessageAnalysisEngine: ReceivedFlavorTextMessageAnalysisEngine
  ): VillageProcessingEngineFactory = {
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

  def set(
      anonymousAudienceChatFromServerAnalysisEngine: AnonymousAudienceChatFromServerAnalysisEngine
  ): VillageProcessingEngineFactory = {
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

  def set(
      nextGameInvitationIsClosedAnalysisEngine: NextGameInvitationIsClosedAnalysisEngine
  ): VillageProcessingEngineFactory = {
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

  def set(
      onymousAudienceChatFromServerAnalysisEngine: OnymousAudienceChatFromServerAnalysisEngine
  ): VillageProcessingEngineFactory = {
    this.onymousAudienceChatFromServerAnalysisEngine = Option(onymousAudienceChatFromServerAnalysisEngine)
    this
  }

  def set(postMortemDiscussionAnalysisEngine: PostMortemDiscussionAnalysisEngine): VillageProcessingEngineFactory = {
    this.postMortemDiscussionAnalysisEngine = Option(postMortemDiscussionAnalysisEngine)
    this
  }
}
