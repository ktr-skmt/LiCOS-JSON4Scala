package licos.json.validation.village

object ScrollValidation {

  import play.api.libs.json.Reads
  import play.api.libs.json.Reads.{max, min, pattern}
  import play.api.libs.functional.syntax._

  val nodeId:       Reads[String] = pattern(""".{0,16384}""".r)
  val scrollTop:    Reads[Int]    = min(0) keepAnd max(Int.MaxValue)
  val scrollHeight: Reads[Int]    = min(0) keepAnd max(Int.MaxValue)
  val offsetHeight: Reads[Int]    = min(0) keepAnd max(Int.MaxValue)
}
