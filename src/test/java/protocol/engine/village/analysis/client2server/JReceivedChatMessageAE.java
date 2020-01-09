package protocol.engine.village.analysis.client2server;

import static protocol.engine.village.example.client2server.ReceivedChatMessage$.MODULE$;

import licos.protocol.element.village.VillageMessageProtocol;
import licos.protocol.element.village.client2server.ReceivedChatMessageProtocol;
import licos.protocol.engine.analysis.village.client2server.ReceivedChatMessageAnalysisEngine;
import licos.protocol.engine.processing.village.VillageBOX;
import licos.protocol.engine.processing.village.VillageBOXNotFoundException;
import protocol.element.VillageMessageTestProtocol;
import protocol.engine.village.JVillageBox;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JReceivedChatMessageAE implements ReceivedChatMessageAnalysisEngine {
    @Override
    public Try<VillageMessageProtocol> process(VillageBOX box, ReceivedChatMessageProtocol receivedChatMessage) {
        if (box instanceof JVillageBox) {
            return Success.apply(VillageMessageTestProtocol.apply(MODULE$.type()));
        } else {
            return Failure.apply(new VillageBOXNotFoundException(null, null));
        }
    }
}
