package licos.json.validation.lobby

object RoleSettingValidation {

  import play.api.libs.json._
  import play.api.libs.json.Reads._
  import play.api.libs.functional.syntax._

  val hunter:      Reads[Int] = min(0) keepAnd max(15)
  val madman:      Reads[Int] = min(0) keepAnd max(15)
  val mason:       Reads[Int] = min(0) keepAnd max(15)
  val medium:      Reads[Int] = min(0) keepAnd max(15)
  val seer:        Reads[Int] = min(0) keepAnd max(15)
  val villager:    Reads[Int] = min(0) keepAnd max(15)
  val werehamster: Reads[Int] = min(0) keepAnd max(15)
  val werewolf:    Reads[Int] = min(0) keepAnd max(15)

}
