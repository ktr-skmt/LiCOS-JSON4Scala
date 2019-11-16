package licos.bson.element.village.role;

import licos.bson.element.village.BsonName;
import licos.json.element.village.role.JsonSimpleRole;
import org.bson.types.ObjectId;

public class BsonSimpleRole extends BsonAbstractRole {

    @SuppressWarnings("unused")
    private BsonSimpleRole() {
        // Do nothing
    }

    public BsonSimpleRole(ObjectId _id,
                          String $context,
                          String $id,
                          BsonName name,
                          String image) {
        this._id = _id;
        this.$context = $context;
        this.$id = $id;
        this.name = name;
        this.image = image;
    }

    public JsonSimpleRole toJson() {
        return new JsonSimpleRole(
                $context,
                $id,
                name.toJson(),
                image
        );
    }
}
