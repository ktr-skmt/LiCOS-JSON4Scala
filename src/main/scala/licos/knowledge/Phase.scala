package licos.knowledge

sealed abstract class Phase(val label: String) {
  override def toString: String = label
}

case object FlavorText extends Phase("flavor text")
case object Morning extends Phase("morning")
case object Noon extends Phase("noon")
case object Night extends Phase("night")
case object Result extends Phase("result")
case object PostMortemDiscussion extends Phase("postmortem")