package protocol.engine.auth;

import licos.json2protocol.auth.Json2AuthMessageProtocol;
import licos.protocol.element.auth.AuthMessageProtocol;
import licos.protocol.engine.processing.AuthPE$;
import licos.protocol.engine.processing.SpecificProcessingEngineFactory$;
import licos.protocol.engine.processing.auth.AuthProcessingEngine;
import licos.protocol.engine.processing.auth.AuthProcessingEngineFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.api.libs.json.Json;
import protocol.element.AuthMessageTestProtocol;
import protocol.engine.AuthExample;
import protocol.engine.auth.analysis.robot2server.JAuthenticationAndAuthorizationRequestAE;
import protocol.engine.auth.analysis.server2robot.JAuthenticationRequestResponseAE;
import protocol.engine.auth.analysis.server2robot.JAuthorizationRequestResponseAE;
import protocol.engine.auth.example.robot2server.AuthenticationAndAuthorizationRequest;
import protocol.engine.auth.example.server2robot.AuthenticationRequestResponse;
import protocol.engine.auth.example.server2robot.AuthorizationRequestResponse;
import scala.Option;
import scala.util.Try;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class JAuthProcessingEngineSuite {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Parameters
    public static AuthExample[] data() {
        return new AuthExample[] {
                new AuthenticationAndAuthorizationRequest("authenticationAndAuthorizationRequest.json"),
                new AuthenticationRequestResponse("authenticationRequestResponse.json"),
                new AuthorizationRequestResponse("authorizationRequestResponse.json")
        };
    }

    @Parameter
    public AuthExample jsonExample;

    private final AuthProcessingEngineFactory processingEngineFactory = (
            (AuthProcessingEngineFactory) SpecificProcessingEngineFactory$.MODULE$
            .create(AuthPE$.MODULE$))
            .set(new JAuthenticationAndAuthorizationRequestAE())
            .set(new JAuthenticationRequestResponseAE())
            .set(new JAuthorizationRequestResponseAE());

    private final AuthProcessingEngine processingEngine = processingEngineFactory.create();

    @Test
    public void test() {
        String jsonType = jsonExample.type();
        URL url = jsonExample.path();
        log.info(url.toString());
        try {
            URLConnection connection = url.openConnection();

            try (BufferedReader br =  new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream(),
                            StandardCharsets.UTF_8)
            )) {
                String msg = br
                        .lines()
                        .collect(Collectors.joining("\n"));
                log.debug(msg);
                Option<AuthMessageProtocol> protocolOpt = Json2AuthMessageProtocol.toProtocolOpt(Json.parse(msg));
                if (protocolOpt.nonEmpty()) {
                    AuthMessageProtocol protocol = protocolOpt.get();
                    Try<AuthMessageProtocol> responseTry = processingEngine.process(new JAuthBox(), protocol);
                    if (responseTry.isSuccess()) {
                        AuthMessageProtocol response = responseTry.get();
                        if (response instanceof AuthMessageTestProtocol) {
                            assertEquals(((AuthMessageTestProtocol) response).text(), jsonType);
                        } else {
                            fail("No AuthMessageTestProtocol");
                        }
                    } else {
                        fail(
                                String.join(
                                "No response is generated.",
                                        responseTry
                                                .failed()
                                                .get()
                                                .getMessage(),
                                        msg
                                )
                        );
                    }
                } else {
                    fail("No protocol");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
