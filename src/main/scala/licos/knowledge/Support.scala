package licos.knowledge

final case class Support(`for`: Map[Int, SupportedComposition])

final case class SupportedComposition(A: Composition, B: Composition, C: Composition)
