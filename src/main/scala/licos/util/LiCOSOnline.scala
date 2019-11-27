package licos.util

import licos.util.WerewolfWorld.version

object LiCOSOnline {

  /** Returns a base part of a URI of a JSON-LD's id.
    *
    * @return a base part of a URI of a JSON-LD's id.
    */
  val stateVillage: String = {
    s"https://licos.online/state/$version/village"
  }

  /** Returns a URI regex of a JSON-LD's id.
    *
    * @param path a remaining part of a URI of a JSON-LD's id.
    * @return a URI regex of a JSON-LD's id.
    */
  def stateRegex(path: String): String = {
    s"$stateVillage#[1-9][0-9]*/$path"
  }

  /** Returns a URI of a JSON-LD's id.
    *
    * @param villageId a village id.
    * @return a URI of a JSON-LD's id.
    */
  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def stateVillage(villageId: Long): String = {
    s"$stateVillage#$villageId"
  }

  /** Returns a URI of a JSON-LD's id.
    *
    * @param villageId a village id.
    * @param path a remaining part of a URI of a JSON-LD's id.
    * @return a URI of a JSON-LD's id.
    */
  def state(villageId: Long, path: String): String = {
    s"${stateVillage(villageId)}/$path"
  }
}
