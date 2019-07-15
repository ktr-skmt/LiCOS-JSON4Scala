package licos.bson.village.role;

import licos.bson.village.BsonName;
import licos.bson.village.agent.BsonSimpleAgent;
import licos.json.village.agent.JsonSimpleAgent;
import licos.json.village.role.JsonResultRole;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 * Created on 2018/01/11.
 * </pre>
 *
 * @author K.Sakamoto
 */
@Entity("resultRoles")
public class BsonResultRole extends BsonAbstractRole {
    @Getter @Setter
    private boolean isMine;

    @Getter @Setter
    private int numberOfAgents;

    @Getter @Setter @Reference
    private List<BsonSimpleAgent> agentId;

    @SuppressWarnings("unused")
    private BsonResultRole() {
        // Do nothing
    }

    public BsonResultRole(ObjectId _id,
                          String $context,
                          String $id,
                          boolean isMine,
                          BsonName name,
                          String image,
                          int numberOfAgents,
                          List<BsonSimpleAgent> agentId) {
        this._id = _id;
        this.$context = $context;
        this.$id = $id;
        this.isMine = isMine;
        this.name = name;
        this.image = image;
        this.numberOfAgents = numberOfAgents;
        this.agentId = agentId;
    }

    public JsonResultRole toJson() {
        Iterator<BsonSimpleAgent> agentIterator = agentId.iterator();
        List<JsonSimpleAgent> agentList = new LinkedList<>();
        while (agentIterator.hasNext()) {
            agentList.add(agentIterator.next().toJson());
        }
        return new JsonResultRole(
                $context,
                $id,
                isMine,
                name.toJson(),
                image,
                numberOfAgents,
                agentList
        );
    }
}
