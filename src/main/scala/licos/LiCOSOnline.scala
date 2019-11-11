package licos

import licos.WerewolfWorld.version

object LiCOSOnline {

  /** Returns a base part of a URI of a JSON-LD's id.
    *
    * @return a base part of a URI of a JSON-LD's id.
    */
  val stateVillage: String = {
    s"https://licos.online/state/$version/village"
  }

  /** Returns a URI of a JSON-LD's id.
    *
    * @param path a remaining part of a URI of a JSON-LD's id.
    * @return a URI of a JSON-LD's id.
    */
  def state(path: String): String = {
    s"$stateVillage#[1-9][0-9]*/$path"
  }
}
