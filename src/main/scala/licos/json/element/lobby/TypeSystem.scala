package licos.json.element.lobby

import licos.json.element.Element

abstract class TypeSystem(`type`: String) extends Element {
  protected def validType: String

  def isValid: Boolean = {
    import cats.implicits._; `type` === validType
  }
}
