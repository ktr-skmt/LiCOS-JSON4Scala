package licos.json.element.village

import licos.bson.element.village.BsonName
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}

case class JsonName(en: String,
                    ar: Option[String],
                    de: Option[String],
                    es: Option[String],
                    fr: Option[String],
                    it: Option[String],
                    ja: Option[String],
                    pt: Option[String],
                    ru: Option[String],
                    uk: Option[String],
                    vi: Option[String],
                    zhCN: Option[String],
                    zhTW: Option[String]) extends JsonElement {
  def this(en: String,
           ar: String,
           de: String,
           es: String,
           it: String,
           fr: String,
           ja: String,
           pt: String,
           ru: String,
           uk: String,
           vi: String,
           zhCN: String,
           zhTW: String) = {
    this (
      en: String,
      Option(ar): Option[String],
      Option(de): Option[String],
      Option(es): Option[String],
      Option(it): Option[String],
      Option(fr): Option[String],
      Option(ja): Option[String],
      Option(pt): Option[String],
      Option(ru): Option[String],
      Option(uk): Option[String],
      Option(vi): Option[String],
      Option(zhCN): Option[String],
      Option(zhTW): Option[String]
    )
  }

  override def toBson: BsonName = {
    new BsonName(
      new ObjectId(),
      en: String,
      ar.getOrElse(en): String,
      de.getOrElse(en): String,
      es.getOrElse(en): String,
      it.getOrElse(en): String,
      fr.getOrElse(en): String,
      ja.getOrElse(en): String,
      pt.getOrElse(en): String,
      ru.getOrElse(en): String,
      uk.getOrElse(en): String,
      vi.getOrElse(en): String,
      zhCN.getOrElse(en): String,
      zhTW.getOrElse(en): String)
  }
}

object JsonName {
  implicit val jsonFormat: OFormat[JsonName] = Json.format[JsonName]
}