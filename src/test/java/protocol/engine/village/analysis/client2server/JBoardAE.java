package protocol.engine.village.analysis.client2server;

import static protocol.engine.village.example.client2server.Board$.MODULE$;

import licos.protocol.element.village.VillageMessageProtocol;
import licos.protocol.element.village.client2server.BoardProtocol;
import licos.protocol.engine.analysis.village.client2server.BoardAnalysisEngine;
import licos.protocol.engine.processing.village.VillageBOX;
import licos.protocol.engine.processing.village.VillageBOXNotFoundException;
import protocol.element.VillageMessageTestProtocol;
import protocol.engine.village.JVillageBox;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JBoardAE implements BoardAnalysisEngine {
    @Override
    public Try<VillageMessageProtocol> process(VillageBOX box, BoardProtocol board) {
        if (box instanceof JVillageBox) {
            return Success.apply(VillageMessageTestProtocol.apply(MODULE$.type()));
        } else {
            return Failure.apply(new VillageBOXNotFoundException(null, null));
        }
    }
}
