package licos

/** This object has methods for https://werewolf.world .
  *
  * @author Kotaro Sakamoto
  */
object WerewolfWorld {
  private val baseUrl: String = "https://werewolf.world"
  private val version: String = "0.3"
  //private val imageVersion: String = "0.3"

  /** Returns a URI of a JSON-LD context.
    *
    * @param jsonldFileName a JSON-LD file name.
    * @return a URI of a JSON-LD context.
    */
  def context(jsonldFileName: String): String = {
    s"$baseUrl/context/$version/$jsonldFileName.jsonld"
  }

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
    stateVillage.concat(path)
  }
}
