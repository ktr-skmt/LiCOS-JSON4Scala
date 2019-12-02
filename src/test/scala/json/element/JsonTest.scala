package json.element

import play.api.libs.json.{Json, OFormat}

final case class JsonTest(text: String)

object JsonTest {
  implicit val jsonFormat: OFormat[JsonTest] = Json.format[JsonTest]
}
