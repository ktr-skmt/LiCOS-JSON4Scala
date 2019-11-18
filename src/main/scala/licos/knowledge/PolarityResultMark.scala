package licos.knowledge

sealed abstract class PolarityResultMark(val label: String) {
  override def toString: String = label
}

case object QuestionResultMark extends PolarityResultMark("?")
case object CrossResultMark extends PolarityResultMark("x")
case object TriangleResultMark extends PolarityResultMark("Δ")
case object CircleResultMark extends PolarityResultMark("o")
case object ConfirmedCircleResultMark extends PolarityResultMark("positive")
case object ConfirmedBlackResultMark extends PolarityResultMark("negative")
