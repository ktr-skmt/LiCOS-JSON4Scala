package licos.bson.village.agent;

import licos.bson.village.BsonAvatar;
import licos.bson.village.BsonName;
import licos.bson.village.role.BsonSimpleRole;
import licos.json.village.agent.JsonStatusAgent;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

@Entity("statusAgents")
public class BsonStatusAgent extends BsonAbstractAgent {
    @Getter @Setter @Reference
    private BsonSimpleRole role;

    @Getter @Setter
    private String status;

    @Getter @Setter
    private BsonAvatar avatar;

    @SuppressWarnings("unused")
    private BsonStatusAgent() {}

    public BsonStatusAgent(ObjectId _id,
                           String $context,
                           String $id,
                           long id,
                           BsonName name,
                           String image,
                           BsonSimpleRole role,
                           String status,
                           BsonAvatar avatar) {
        this._id = _id;
        this.$context = $context;
        this.$id = $id;
        this.id = id;
        this.name = name;
        this.image = image;
        this.role = role;
        this.status = status;
        this.avatar = avatar;
    }

    @Override
    public JsonStatusAgent toJson() {
        return new JsonStatusAgent(
                $context,
                $id,
                id,
                name.toJson(),
                image,
                role.toJson(),
                status,
                avatar.toJson()
        );
    }
}
