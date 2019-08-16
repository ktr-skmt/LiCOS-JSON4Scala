package licos.bson.element.village;

import licos.bson.element.village.agent.BsonRoleAgent;
import licos.bson.element.village.agent.BsonSimpleAgent;
import licos.json.element.village.JsonVote;
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
@Entity("votes")
public class BsonVote extends BsonElement {
    @Id @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter @Reference
    private BsonBase base;

    @Getter @Setter @Reference
    private BsonSimpleAgent agent;

    @Getter @Setter @Reference
    private BsonRoleAgent myAgent;

    @SuppressWarnings("unused")
    private BsonVote() {
        // Do nothing
    }

    public BsonVote(ObjectId _id,
                    BsonBase base,
                    BsonSimpleAgent agent,
                    BsonRoleAgent myAgent) {
        this._id = _id;
        this.base = base;
        this.agent = agent;
        this.myAgent = myAgent;
    }

    @Override
    public JsonVote toJson() {
        return new JsonVote(
                base.toJson(),
                agent.toJson(),
                myAgent.toJson()
        );
    }
}
