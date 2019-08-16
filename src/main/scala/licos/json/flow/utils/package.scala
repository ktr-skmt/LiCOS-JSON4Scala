package licos.json.flow

import licos.json.element.lobby.TypeSystem

import scala.reflect.ClassTag

/** This package is for extending scala.Option implicitly. This is used for flow controllers.
  *
  * @author Kotaro Sakamoto
  */
package object utils {

  implicit class FlowControllerUtilsWithTypeSystem[A <: TypeSystem: ClassTag, B](op: Option[A] ) {

    /** Runs scala.Option.getOrElse after checking a type correctness of A.
      *
      * @param default the default expression.
      * @return Returns the option's value if the option is nonempty and the value's type is correct, otherwise return the result of evaluating default.
      */
    def >>>(default: B): Any = op match {
      case Some(a) if implicitly[ClassTag[A]].runtimeClass.isInstance(a) && a.isValid => a
      case _ => default
    }
  }

  implicit class FlowControllerUtils[A: ClassTag, B](op: Option[A]) {

    /** Runs scala.Option.getOrElse.
      *
      * @param default the default expression.
      * @return Returns the option's value if the option is nonempty, otherwise return the result of evaluating default.
      */
    def >->(default: B): Any = op.getOrElse(default)
  }
}
