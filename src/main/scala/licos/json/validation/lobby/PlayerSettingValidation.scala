package licos.json.validation.lobby

object PlayerSettingValidation {

  import play.api.libs.json.Reads
  import play.api.libs.json.Reads._
  import play.api.libs.functional.syntax._

  val current: Reads[Int] = min(-1) keepAnd max(Int.MaxValue)

  object robot {
    val min:     Reads[Int] = Reads.min(0) keepAnd Reads.max(15)
    val current: Reads[Int] = Reads.min(-1) keepAnd Reads.max(15)
  }

  object human {
    val max:     Reads[Int] = Reads.min(0) keepAnd Reads.max(15)
    val current: Reads[Int] = Reads.min(-1) keepAnd Reads.max(15)
  }

}
