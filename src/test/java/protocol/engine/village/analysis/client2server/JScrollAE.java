package protocol.engine.village.analysis.client2server;

import licos.protocol.element.village.VillageMessageProtocol;
import licos.protocol.element.village.client2server.ScrollProtocol;
import licos.protocol.engine.analysis.village.client2server.ScrollAnalysisEngine;
import licos.protocol.engine.processing.village.VillageBOX;
import licos.protocol.engine.processing.village.VillageBOXNotFoundException;
import protocol.element.VillageMessageTestProtocol;
import protocol.engine.village.JVillageBox;
import protocol.engine.village.example.client2server.Scroll;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JScrollAE implements ScrollAnalysisEngine {
    @Override
    public Try<VillageMessageProtocol> process(VillageBOX box, ScrollProtocol scroll) {
        if (box instanceof JVillageBox) {
            return Success.apply(VillageMessageTestProtocol.apply(new Scroll("").type()));
        } else {
            return Failure.apply(new VillageBOXNotFoundException(null, null));
        }
    }
}
