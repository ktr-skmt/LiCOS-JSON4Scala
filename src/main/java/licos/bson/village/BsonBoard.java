package licos.bson.village;

import licos.bson.village.agent.BsonRoleAgent;
import licos.bson.village.agent.BsonSimpleAgent;
import licos.bson.village.role.BsonSimpleRole;
import licos.json.village.JsonBoard;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

/**
 * <pre>
 * Created on 2018/01/11.
 * </pre>
 *
 * @author K.Sakamoto
 */
@Entity("boards")
public class BsonBoard extends BsonElement {
    @Id @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter @Reference
    private BsonBase base;

    @Getter @Setter @Reference
    private BsonRoleAgent myAgent;

    @Getter @Setter @Reference
    private BsonSimpleAgent agent;

    @Getter @Setter @Reference
    private BsonSimpleRole role;

    @Getter @Setter
    private String prediction;

    @SuppressWarnings("unused")
    private BsonBoard() {
        // Do nothing
    }

    public BsonBoard(ObjectId _id,
                     BsonBase base,
                     BsonRoleAgent myAgent,
                     BsonSimpleAgent agent,
                     BsonSimpleRole role,
                     String prediction) {
        this._id = _id;
        this.base = base;
        this.myAgent = myAgent;
        this.agent = agent;
        this.role = role;
        this.prediction = prediction;
    }

    @Override
    public JsonBoard toJson() {
        return new JsonBoard(
                base.toJson(),
                myAgent.toJson(),
                agent.toJson(),
                role.toJson(),
                prediction
        );
    }
}
