package controller.viewmodels;

import java.util.ArrayList;

public class SingularModel extends ViewModel {
    private String entityString;
    private String entityOwner;
    private String entityID;

    public SingularModel(String entityString, String entityOwner, String entityID) {
        this.entityString = entityString;
        this.entityOwner = entityOwner;
        this.entityID = entityID;
    }

    public String getEntityString() {
        return entityString;
    }

    public String getEntityOwner() {
        return entityOwner;
    }

    public String getEntityID() {
        return entityID;
    }
}
