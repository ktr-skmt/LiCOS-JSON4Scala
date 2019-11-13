package licos.json.flow

import licos.json.element.Element
import licos.json.element.lobby.TypeSystem
import play.api.libs.json.JsValue

import scala.reflect.ClassTag

/** This package is used for flow controllers.
  *
  * @author Kotaro Sakamoto
  */
package object utils {

  implicit class FlowControllerUtilsWithTypeSystem[A <: TypeSystem: ClassTag](op: Either[JsValue, A]) {

    /** getOrElse after checking a type correctness of A.
      *
      * @param default the default expression.
      * @return Returns the Either if the Either is the Right and its type is correct, otherwise return the result of evaluating default.
      */
    @SuppressWarnings(Array[String]("org.wartremover.warts.Nothing"))
    def >>>[B <: Either[JsValue, Element]](default: B): Either[JsValue, Element] = op match {
      case Right(a) if implicitly[ClassTag[A]].runtimeClass.isInstance(a) && a.isValid => Right(a)
      case _                                                                           => default
    }
  }

  implicit class FlowControllerUtils[A <: Element: ClassTag](op: Either[JsValue, Element]) {

    /** getOrElse.
      *
      * @param default the default expression.
      * @return Returns the Either if the Either is the Right, otherwise return the result of evaluating default.
      */
    @SuppressWarnings(Array[String]("org.wartremover.warts.Nothing"))
    def >->[B <: Either[JsValue, Element]](default: B): Either[JsValue, Element] =
      op match {
        case Right(a) => Right(a)
        case _        => default
      }
  }
}
