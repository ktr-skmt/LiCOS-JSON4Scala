package licos.json.validation.village

import play.api.libs.json.Reads
import play.api.libs.json.Reads.pattern

object ArchitectureValidation {
  val playerType: Reads[String] = pattern("""(?:human|(?:fully |semi-)automated robot)""".r)
}
