package licos.json.validation.village

import licos.{LiCOSOnline, WerewolfWorld}
import play.api.libs.json.Reads
import play.api.libs.json.Reads.{max, min, pattern}
import play.api.libs.functional.syntax._

import scala.util.matching.Regex

object BaseValidation {
  object `@context` {
    val item: Reads[String] = pattern(
      WerewolfWorld.context("""(?:base|error|character|role|board|chat|vote|votingResult|scroll|star|flavorText)""").r
    )
  }
  val `@id`: Reads[String] = pattern(
    LiCOSOnline
      .state("""(?:board|error|(?:flavorText#[0-9]+/)?chat|scroll|star|system|vote|flavorText)Message""")
      .r
  )
  val phaseTimeLimit: Reads[Int] = min(-1) keepAnd max(Int.MaxValue)
  val timestampRegex: Regex =
    """2[0-9]{3}-(?:0[0-9]|1[0-2])-(?:[0-2][0-9]|3[0-1])T(?:[0-1][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]\.[0-9]{3}(?:[+\-](?:0[0-9]|1[0-3]):[0-5][0-9]|Z)""".r
  private val timestamp: Reads[String] = pattern(timestampRegex)
  val phaseStartTime:    Reads[String] = timestamp
  val serverTimestamp:   Reads[String] = timestamp
  val clientTimestamp:   Reads[String] = timestamp
  val directionality:    Reads[String] = pattern("""(?:client to server|server to client)""".r)
  val intensionalDisclosureRange: Reads[String] = pattern(
    """(?:public|private|werewolf|seer|hunter|master|grave|onymousAudience|anonymousAudience)""".r
  )
}
