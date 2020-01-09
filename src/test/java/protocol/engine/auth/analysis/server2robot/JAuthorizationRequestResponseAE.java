package protocol.engine.auth.analysis.server2robot;

import licos.protocol.element.auth.AuthMessageProtocol;
import licos.protocol.element.auth.server2robot.AuthorizationRequestResponseProtocol;
import licos.protocol.engine.analysis.auth.server2robot.AuthorizationRequestResponseAnalysisEngine;
import licos.protocol.engine.processing.auth.AuthBOX;
import licos.protocol.engine.processing.auth.AuthBOXNotFoundException;
import protocol.element.AuthMessageTestProtocol;
import protocol.engine.auth.JAuthBox;
import static protocol.engine.auth.example.server2robot.AuthorizationRequestResponse$.MODULE$;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JAuthorizationRequestResponseAE implements AuthorizationRequestResponseAnalysisEngine {

    @Override
    public Try<AuthMessageProtocol> process(AuthBOX box, AuthorizationRequestResponseProtocol authorizationRequestResponseProtocol) {
        if (box instanceof JAuthBox) {
            return Success.apply(AuthMessageTestProtocol.apply(MODULE$.type()));
        } else {
            return Failure.apply(new AuthBOXNotFoundException(null, null));
        }
    }
}
