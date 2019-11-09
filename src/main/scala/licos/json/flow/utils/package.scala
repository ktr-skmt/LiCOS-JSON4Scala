package licos.json.flow

import licos.json.element.Element
import licos.json.element.lobby.TypeSystem
import play.api.libs.json.JsValue

import scala.reflect.ClassTag

/** This package is for extending scala.Option implicitly. This is used for flow controllers.
  *
  * @author Kotaro Sakamoto
  */
package object utils {

  implicit class FlowControllerUtilsWithTypeSystem[A <: TypeSystem: ClassTag](op: Either[JsValue, A]) {

    /** Runs scala.Option.getOrElse after checking a type correctness of A.
      *
      * @param default the default expression.
      * @return Returns the option's value if the option is nonempty and the value's type is correct, otherwise return the result of evaluating default.
      */
    def >>>[B <: Either[JsValue, Element]](default: B): Either[JsValue, Element] = op match {
      case Right(a) if implicitly[ClassTag[A]].runtimeClass.isInstance(a) && a.isValid => Right(a)
      case _                                                                           => default
    }
  }

  implicit class FlowControllerUtils[A <: Element: ClassTag](op: Either[JsValue, Element]) {

    /** Runs scala.Option.getOrElse.
      *
      * @param default the default expression.
      * @return Returns the option's value if the option is nonempty, otherwise return the result of evaluating default.
      */
    def >->[B <: Either[JsValue, Element]](default: B): Either[JsValue, Element] =
      op match {
        case Right(a) => Right(a)
        case _        => default
      }
  }
}
