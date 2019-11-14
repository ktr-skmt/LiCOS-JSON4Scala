package licos.bson.element.village;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import licos.json.element.village.JsonChatFromServer;
import licos.json.element.village.JsonFlavorText;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Entity("flavorTexts")
public class BsonFlavorText extends BsonElementToJsonElement {

    @Id
    @SuppressWarnings("unused")
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