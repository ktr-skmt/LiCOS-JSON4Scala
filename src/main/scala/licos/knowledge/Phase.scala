package licos.knowledge

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.duration._

sealed abstract class Phase(val label: String) {
  override def toString: String = label

  override def equals(o: Any): Boolean = {
    o match {
      case p: Phase =>
        p.label == label
      case _ => false
    }
  }

  def timeLimit(day: Int, numberOfAlivePlayers: Int): Option[FiniteDuration]
}

case object FlavorText extends Phase("flavor text") {
  override def timeLimit(day: Int, numberOfAlivePlayers: Int): Option[FiniteDuration] = Option.empty[FiniteDuration]
}

case object Morning extends Phase("morning") {
  override def timeLimit(day: Int, numberOfAlivePlayers: Int): Option[FiniteDuration] = {
    if (1 < day) {
      if (numberOfAlivePlayers <= 6) {
        Some((4 * 60).seconds)
      } else if (numberOfAlivePlayers <= 12) {
        Some((6 * 60).seconds)
      } else {
        Some((8 * 60).seconds)
      }
    } else {
      Some((5 * 60).seconds)
    }
  }
}

case object Noon extends Phase("noon") {
  override def timeLimit(day: Int, numberOfAlivePlayers: Int): Option[FiniteDuration] = Some(60.seconds)
}

case object Night extends Phase("night") {
  override def timeLimit(day: Int, numberOfAlivePlayers: Int): Option[FiniteDuration] = Some(60.seconds)
}

case object Result extends Phase("result") {
  override def timeLimit(day: Int, numberOfAlivePlayers: Int): Option[FiniteDuration] = Option.empty[FiniteDuration]
}

case object PostMortemDiscussion extends Phase("postmortem") {
  override def timeLimit(day: Int, numberOfAlivePlayers: Int): Option[FiniteDuration] = Some((24 * 60 * 60).seconds)
}
