package protocol.engine.auth.analysis.server2robot;

import licos.protocol.element.auth.AuthMessageProtocol;
import licos.protocol.element.auth.server2robot.AuthenticationRequestResponseProtocol;
import licos.protocol.engine.analysis.auth.server2robot.AuthenticationRequestResponseAnalysisEngine;
import licos.protocol.engine.processing.auth.AuthBOX;
import licos.protocol.engine.processing.auth.AuthBOXNotFoundException;
import protocol.element.AuthMessageTestProtocol;
import protocol.engine.auth.JAuthBox;
import protocol.engine.auth.example.server2robot.AuthenticationRequestResponse$;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JAuthenticationRequestResponseAE implements AuthenticationRequestResponseAnalysisEngine {
    @Override
    public Try<AuthMessageProtocol> process(AuthBOX box, AuthenticationRequestResponseProtocol authenticationRequestResponseProtocol) {
        if (box instanceof JAuthBox) {
            return Success.apply(AuthMessageTestProtocol.apply(AuthenticationRequestResponse$.MODULE$.type()));
        } else {
            return Failure.apply(new AuthBOXNotFoundException(null, null));
        }
    }
}
