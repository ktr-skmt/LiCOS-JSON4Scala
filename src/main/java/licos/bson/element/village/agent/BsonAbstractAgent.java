package licos.bson.element.village.agent;

import licos.bson.element.village.BsonName;
import licos.bson.element.village.BsonElement;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

public abstract class BsonAbstractAgent extends BsonElement {
    @Id @SuppressWarnings("unused")
    protected ObjectId _id;

    @Getter @Setter
    protected String $context;

    @Getter @Setter
    protected String $id;

    @Getter @Setter
    protected long id;

    @Getter @Setter @Reference
    protected BsonName name;

    @Getter @Setter
    protected String image;
}
