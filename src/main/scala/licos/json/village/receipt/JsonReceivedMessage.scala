package licos.json.village.receipt

import licos.json.lobby.TypeSystem

abstract class JsonReceivedMessage(`type`: String,
                                   token: String,
                                   villageId: Long) extends TypeSystem(`type`)// {
  //def key: String
//}