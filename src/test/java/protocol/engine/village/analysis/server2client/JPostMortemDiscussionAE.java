package protocol.engine.village.analysis.server2client;

import licos.protocol.element.village.VillageMessageProtocol;
import licos.protocol.element.village.server2client.PostMortemDiscussionProtocol;
import licos.protocol.engine.analysis.village.server2client.PostMortemDiscussionAnalysisEngine;
import licos.protocol.engine.processing.village.VillageBOX;
import licos.protocol.engine.processing.village.VillageBOXNotFoundException;
import protocol.element.VillageMessageTestProtocol;
import protocol.engine.village.JVillageBox;
import protocol.engine.village.example.server2client.PostMortemDiscussion;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JPostMortemDiscussionAE implements PostMortemDiscussionAnalysisEngine {
    @Override
    public Try<VillageMessageProtocol> process(VillageBOX box, PostMortemDiscussionProtocol postMortemDiscussion) {
        if (box instanceof JVillageBox) {
            return Success.apply(VillageMessageTestProtocol.apply(new PostMortemDiscussion("").type()));
        } else {
            return Failure.apply(new VillageBOXNotFoundException(null, null));
        }
    }
}
