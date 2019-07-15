package licos.bson.village;

import licos.bson.village.agent.BsonResultAgent;
import licos.bson.village.role.BsonResultRole;
import licos.json.village.JsonGameResult;
import licos.json.village.agent.JsonResultAgent;
import licos.json.village.role.JsonResultRole;
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
@Entity("gameResults")
public class BsonGameResult extends BsonElementToJsonElement {
    @Id @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter @Reference
    private BsonBase base;

    @Getter @Setter @Reference
    private List<BsonResultAgent> agent;

    @Getter @Setter @Reference
    private List<BsonResultRole> role;

    @SuppressWarnings("unused")
    private BsonGameResult() {
        // Do nothing
    }

    public BsonGameResult(ObjectId _id,
                          BsonBase base,
                          List<BsonResultAgent> agent,
                          List<BsonResultRole> role) {
        this._id = _id;
        this.base = base;
        this.agent = agent;
        this.role = role;
    }

    private List<JsonResultAgent> agentToJson() {
        return elementToJson(agent).
                map(element -> (JsonResultAgent) element).
                collect(Collectors.toCollection(LinkedList::new));
    }

    private List<JsonResultRole> roleToJson() {
        return elementToJson(role).
                map(element -> (JsonResultRole) element).
                collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public JsonGameResult toJson() {
        return new JsonGameResult(
                base.toJson(),
                agentToJson(),
                roleToJson()
        );
    }
}
