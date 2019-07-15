package licos.bson.village;

import licos.json.village.JsonChatFromServer;
import licos.json.village.JsonFlavorText;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Entity("flavorTexts")
public class BsonFlavorText extends BsonElementToJsonElement {
    @Id @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter @Reference
    private BsonBase base;

    @Getter @Setter @Reference
    private List<BsonChatFromServer> flavorText;

    @SuppressWarnings("unused")
    private BsonFlavorText() {
        // Do nothing
    }

    public BsonFlavorText(ObjectId _id,
                          BsonBase base,
                          List<BsonChatFromServer> flavorText) {
        this._id = _id;
        this.base = base;
        this.flavorText = flavorText;
    }

    private List<JsonChatFromServer> flavorTextToJson() {
        return elementToJson(flavorText).
                map(element -> (JsonChatFromServer) element).
                collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public JsonFlavorText toJson() {
        return new JsonFlavorText(base.toJson(), flavorTextToJson());
    }
}