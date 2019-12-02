package licos.json.element.village.iri

object Contexts {

  def get(message: Message): Seq[Context] = {
    message match {
      case BoardMessage =>
        Seq[Context](
          BaseContext,
          BoardContext
        )
      case ChatMessage =>
        Seq[Context](
          BaseContext,
          ChatContext
        )
      case ErrorMessage =>
        Seq[Context](
          BaseContext,
          ErrorContext
        )
      case ScrollMessage =>
        Seq[Context](
          BaseContext,
          ScrollContext
        )
      case SystemMessage =>
        Seq[Context](
          BaseContext,
          VotingResultContext
        )
      case VoteMessage =>
        Seq[Context](
          BaseContext,
          VoteContext
        )
      case FlavorTextMessage =>
        Seq[Context](
          BaseContext,
          FlavorTextContext
        )
      case StarMessage =>
        Seq[Context](
          BaseContext,
          StarContext
        )
      case DummyMessage =>
        Nil
    }
  }

}
