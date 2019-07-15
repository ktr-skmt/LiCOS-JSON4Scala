package licos.bson.village;

import licos.bson.village.agent.BsonAgent;
import licos.bson.village.role.BsonRole;
import licos.json.village.JsonPhase;
import licos.json.village.agent.JsonAgent;
import licos.json.village.role.JsonRole;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * Created on 2018/01/11.
 * </pre>
 *
 * @author K.Sakamoto
 */
@Entity("phases")
public class BsonPhase extends BsonElementToJsonElement {
    @Id @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter @Reference
    private BsonBase base;

    @Getter @Setter @Reference
    private List<BsonAgent> agent;

    @Getter @Setter @Reference
    private List<BsonRole> role;

    @SuppressWarnings("unused")
    private BsonPhase() {
        // Do nothing
    }

    public BsonPhase(ObjectId _id,
                     BsonBase base,
                     List<BsonAgent> agent,
                     List<BsonRole> role) {
        this._id = _id;
        this.base = base;
        this.agent = agent;
        this.role = role;
    }

    private List<JsonAgent> agentToJson() {
        return elementToJson(agent).
                map(element -> (JsonAgent) element).
                collect(Collectors.toCollection(LinkedList::new));
    }

    private List<JsonRole> roleToJson() {
        return elementToJson(role).
                map(element -> (JsonRole) element).
                collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public JsonPhase toJson() {
        return new JsonPhase(
                base.toJson(),
                agentToJson(),
                roleToJson()
        );
    }
}
