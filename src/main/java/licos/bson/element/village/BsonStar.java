package licos.bson.element.village;

import licos.bson.element.village.agent.BsonRoleAgent;
import licos.json.element.village.JsonStar;
import licos.json.element.village.JsonSubStar;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity("stars")
public class BsonStar extends BsonElement {

    @Id @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter @Reference
    private BsonBase base;

    @Getter @Setter @Reference
    private BsonRoleAgent myAgent;

    @Getter @Setter @Reference
    private BsonStarInfo star;

    @SuppressWarnings("unused")
    private BsonStar() {
        // Do nothing
    }

    public BsonStar(ObjectId _id,
                    BsonBase base,
                    BsonRoleAgent myAgent,
                    BsonStarInfo star) {
        this._id = _id;
        this.base = base;
        this.myAgent = myAgent;
        this.star = star;
    }

    @Override
    public JsonStar toJson() {
        return new JsonStar(
                base.toJson(),
                new JsonSubStar(
                    myAgent.toJson(),
                    star.toJson()
                )
        );
    }
}
