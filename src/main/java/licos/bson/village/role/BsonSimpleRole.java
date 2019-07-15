package licos.bson.village.role;

import licos.bson.village.BsonName;
import licos.json.village.role.JsonSimpleRole;
import org.bson.types.ObjectId;

/**
 * <pre>
 * Created on 2018/01/11.
 * </pre>
 *
 * @author K.Sakamoto
 */
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
