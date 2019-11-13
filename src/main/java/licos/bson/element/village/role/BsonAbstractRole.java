package licos.bson.element.village.role;

import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import licos.bson.element.village.BsonName;
import licos.bson.element.village.BsonElement;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

public abstract class BsonAbstractRole extends BsonElement {

    @Id
    @SuppressWarnings("unused")
    protected ObjectId _id;

    @Getter @Setter
    protected String $context;

    @Getter @Setter
    protected String $id;

    @Getter @Setter @Reference
    protected BsonName name;

    @Getter @Setter
    protected String image;
}
