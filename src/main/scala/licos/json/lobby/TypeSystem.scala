package licos.json.lobby

abstract class TypeSystem(`type`: String) {
  protected def validType: String

  def isValid: Boolean = {
    `type` == validType
  }
}
