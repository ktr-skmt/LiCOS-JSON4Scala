package licos

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

  def characterImage(character: String): String = {
    s"$baseUrl/image/$version/character_icons/50x50/${character}_50x50.png"
  }

  def roleImage(role: String): String = {
    s"$baseUrl/image/$version/role_icons/50x50withTI/${role}_50x50.png"
  }

}
