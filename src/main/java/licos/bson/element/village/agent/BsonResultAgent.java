package licos.bson.element.village.agent;

import licos.bson.element.village.BsonAvatar;
import licos.bson.element.village.BsonName;
import licos.bson.element.village.role.BsonSimpleRole;
import licos.json.element.village.agent.JsonResultAgent;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

/**
 * <pre>
 * Created on 2018/01/10.
 * </pre>
 *
 * @author K.Sakamoto
 */
@Entity("resultAgents")
public class BsonResultAgent extends BsonAbstractAgent {
    @Getter @Setter
    private boolean isMine;

    @Getter @Setter @Reference
    private BsonSimpleRole role;

    @Getter @Setter
    private String status;

    @Getter @Setter
    private String result;

    @Getter @Setter
    private BsonAvatar avatar;

    @SuppressWarnings("unused")
    private BsonResultAgent() {
        // Do nothing
    }

    public BsonResultAgent(ObjectId _id,
                           String $context,
                           String $id,
                           long id,
                           BsonName name,
                           String image,
                           boolean isMine,
                           BsonSimpleRole role,
                           String status,
                           String result,
                           BsonAvatar avatar) {
        this._id = _id;
        this.$context = $context;
        this.$id = $id;
        this.id = id;
        this.name = name;
        this.image = image;
        this.isMine = isMine;
        this.role = role;
        this.status = status;
        this.result = result;
        this.avatar = avatar;
    }

    @Override
    public JsonResultAgent toJson() {
        return new JsonResultAgent(
                $context,
                $id,
                id,
                name.toJson(),
                image,
                isMine,
                role.toJson(),
                status,
                result,
                avatar.toJson()
        );
    }
}
