package licos.knowledge

sealed abstract class PolarityMark(val label: String) {
  override def toString: String = label
}

case object QuestionMark extends PolarityMark("?")
case object CrossMark extends PolarityMark("x")
case object TriangleMark extends PolarityMark("Δ")
case object CircleMark extends PolarityMark("o")