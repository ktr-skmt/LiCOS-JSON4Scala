package licos.bson.village.agent;

import licos.bson.village.BsonName;
import licos.bson.village.role.BsonSimpleRole;
import licos.json.village.agent.JsonRoleAgent;
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
@Entity("roleAgents")
public class BsonRoleAgent extends BsonAbstractAgent {
    @Getter @Setter @Reference
    private BsonSimpleRole role;

    @SuppressWarnings("unused")
    private BsonRoleAgent() {
        // Do nothing
    }

    public BsonRoleAgent(ObjectId _id,
                         String $context,
                         String $id,
                         long id,
                         BsonName name,
                         String image,
                         BsonSimpleRole role) {
        this._id = _id;
        this.$context = $context;
        this.$id = $id;
        this.id = id;
        this.name = name;
        this.image = image;
        this.role = role;
    }

    @Override
    public JsonRoleAgent toJson() {
        return new JsonRoleAgent(
                $context,
                $id,
                id,
                name.toJson(),
                image,
                role.toJson()
        );
    }
}
