package licos.bson.element.village;

import licos.bson.element.village.agent.BsonRoleAgent;
import licos.bson.element.village.agent.BsonSimpleAgent;
import licos.json.element.village.JsonChatFromClient;
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
@Entity("chatsFromClient")
public class BsonChatFromClient extends BsonElement {
    @Id @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter @Reference
    private BsonBase base;

    @Getter @Setter @Reference
    private BsonRoleAgent myAgent;

    @Getter @Setter @Reference
    private BsonSimpleAgent agent;

    @Getter @Setter
    private boolean isMine;

    @Getter @Setter @Reference
    private BsonChatText text;

    @Getter @Setter
    private int characterLimit;

    @Getter @Setter
    private boolean isOver;

    @SuppressWarnings("unused")
    private BsonChatFromClient() {
        // Do nothing
    }

    public BsonChatFromClient(ObjectId _id,
                              BsonBase base,
                              BsonRoleAgent myAgent,
                              BsonSimpleAgent agent,
                              boolean isMine,
                              BsonChatText text,
                              int characterLimit,
                              boolean isOver) {
        this._id = _id;
        this.base = base;
        this.myAgent = myAgent;
        this.agent = agent;
        this.isMine = isMine;
        this.text = text;
        this.characterLimit = characterLimit;
        this.isOver = isOver;
    }

    @Override
    public JsonChatFromClient toJson() {
        return new JsonChatFromClient(
                base.toJson(),
                myAgent.toJson(),
                agent.toJson(),
                isMine,
                text.toJson(),
                characterLimit,
                isOver
        );
    }
}
