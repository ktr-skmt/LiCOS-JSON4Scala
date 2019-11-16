package licos.knowledge

sealed abstract class PolarityResultMark(val label: String) {
  override def toString: String = label
}

case object QuestionMark extends PolarityResultMark("?")
case object CrossMark extends PolarityResultMark("x")
case object TriangleMark extends PolarityResultMark("Δ")
case object CircleMark extends PolarityResultMark("o")
case object ConfirmedCircleMark extends PolarityResultMark("positive")
case object ConfirmedBlackMark extends PolarityResultMark("negative")
