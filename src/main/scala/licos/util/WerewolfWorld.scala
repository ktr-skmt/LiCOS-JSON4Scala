package licos.util

/** This object has methods for https://werewolf.world .
  *
  * @author Kotaro Sakamoto
  */
object WerewolfWorld {
  private val baseUrl: String = "https://werewolf.world"
  val version:         String = "0.3"

  /** Returns a URI of a JSON-LD context.
    *
    * @param jsonldFileName a JSON-LD file name.
    * @return a URI of a JSON-LD context.
    */
  def context(jsonldFileName: String): String = {
    s"$baseUrl/village/context/$version/$jsonldFileName.jsonld"
  }

  /** Returns a URL of a character icon.
    *
    * @param character an lower-case initial letter of a character name.
    * @return a URI of a character icon.
    */
  def characterIcon(character: String): String = {
    s"$baseUrl/image/$version/character_icons/50x50/${character}_50x50.png"
  }

  /** Returns a URL of a role icon.
    *
    * @param role an lower-case role name.
    * @return a URL of a role icon.
    */
  def roleIcon(role: String): String = {
    s"$baseUrl/image/$version/role_icons/50x50withTI/${role}_50x50.png"
  }

}
