package protocol.engine.village.example.server2client

import protocol.engine.ServerToClientVillageExample

final case class PostMortemDiscussion(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = PostMortemDiscussion.`type`
}

object PostMortemDiscussion {
  val `type`: String = "village.server2client.PostMortemDiscussion"
}
