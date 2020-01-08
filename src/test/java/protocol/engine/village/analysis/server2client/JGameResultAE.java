package protocol.engine.village.analysis.server2client;

import licos.protocol.element.village.VillageMessageProtocol;
import licos.protocol.element.village.server2client.GameResultProtocol;
import licos.protocol.engine.analysis.village.server2client.GameResultAnalysisEngine;
import licos.protocol.engine.processing.village.VillageBOX;
import licos.protocol.engine.processing.village.VillageBOXNotFoundException;
import protocol.element.VillageMessageTestProtocol;
import protocol.engine.village.JVillageBox;
import protocol.engine.village.example.server2client.GameResult;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JGameResultAE implements GameResultAnalysisEngine {
    @Override
    public Try<VillageMessageProtocol> process(VillageBOX box, GameResultProtocol gameResult) {
        if (box instanceof JVillageBox) {
            return Success.apply(VillageMessageTestProtocol.apply(new GameResult("").type()));
        } else {
            return Failure.apply(new VillageBOXNotFoundException(null, null));
        }
    }
}
