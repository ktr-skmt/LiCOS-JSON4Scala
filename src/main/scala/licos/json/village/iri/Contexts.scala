package licos.json.village.iri

object Contexts {
  def get(message: Message): Seq[String] = {
    message match {
      case BoardMessage =>
        Seq[String](
          BaseContext.iri,
          BoardContext.iri
        )
      case ErrorMessage =>
        Seq[String](
          BaseContext.iri,
          ErrorContext.iri
        )
      case PlayerMessage =>
        Seq[String](
          BaseContext.iri,
          ChatContext.iri
        )
      case ScrollMessage =>
        Seq[String](
          BaseContext.iri,
          ScrollContext.iri
        )
      case SystemMessage =>
        Seq[String](
          BaseContext.iri,
          VotingResultContext.iri
        )
      case VoteMessage =>
        Seq[String](
          BaseContext.iri,
          VoteContext.iri
        )
      case FlavorTextMessage =>
        Seq[String](
          BaseContext.iri,
          FlavorTextContext.iri
        )
      case DummyMessage =>
        Nil
    }
  }
}
