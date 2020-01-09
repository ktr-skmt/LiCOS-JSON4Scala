package protocol.engine.auth.analysis.robot2server;

import licos.protocol.element.auth.AuthMessageProtocol;
import licos.protocol.element.auth.robot2server.AuthenticationAndAuthorizationRequestProtocol;
import licos.protocol.engine.analysis.auth.robot2server.AuthenticationAndAuthorizationRequestAnalysisEngine;
import licos.protocol.engine.processing.auth.AuthBOX;
import licos.protocol.engine.processing.auth.AuthBOXNotFoundException;
import protocol.element.AuthMessageTestProtocol;
import protocol.engine.auth.JAuthBox;
import static protocol.engine.auth.example.robot2server.AuthenticationAndAuthorizationRequest$.MODULE$;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JAuthenticationAndAuthorizationRequestAE implements AuthenticationAndAuthorizationRequestAnalysisEngine {
    @Override
    public Try<AuthMessageProtocol> process(AuthBOX box, AuthenticationAndAuthorizationRequestProtocol authenticationAndAuthorizationRequestProtocol) {
        if (box instanceof JAuthBox) {
            return Success.apply(AuthMessageTestProtocol.apply(MODULE$.type()));
        } else {
            return Failure.apply(new AuthBOXNotFoundException(null, null));
        }
    }
}
