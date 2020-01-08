package protocol.engine.village.analysis.server2client;

import licos.protocol.element.village.VillageMessageProtocol;
import licos.protocol.element.village.server2client.ChatFromServerProtocol;
import licos.protocol.engine.analysis.village.server2client.ChatFromServerAnalysisEngine;
import licos.protocol.engine.processing.village.VillageBOX;
import licos.protocol.engine.processing.village.VillageBOXNotFoundException;
import protocol.element.VillageMessageTestProtocol;
import protocol.engine.village.JVillageBox;
import protocol.engine.village.example.server2client.ChatFromServer;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JChatFromServerAE implements ChatFromServerAnalysisEngine {
    @Override
    public Try<VillageMessageProtocol> process(VillageBOX box, ChatFromServerProtocol chatFromServer) {
        if (box instanceof JVillageBox) {
            return Success.apply(VillageMessageTestProtocol.apply(new ChatFromServer("").type()));
        } else {
            return Failure.apply(new VillageBOXNotFoundException(null, null));
        }
    }
}
