package controller.viewmodels;

/**
 * A view model for representing single entities
 */
public class SingularModel extends ViewModel {
    private String entityString;
    private String entityOwner;
    private String entityID;

    /**
     * Construct a SingularModel
     *
     * @param entityString a string representing the entity
     * @param entityOwner ID of the entity's owner
     * @param entityID unique ID of the entity
     */
    public SingularModel(String entityString, String entityOwner, String entityID) {
        this.entityString = entityString;
        this.entityOwner = entityOwner;
        this.entityID = entityID;
    }

    /**
     * Get a string representing the entity of the model
     *
     * @return string representing the entity
     */
    public String getEntityString() {
        return entityString;
    }

    /**
     * Get the ID of the entity's owner
     *
     * @return ID of the entity's owner
     */
    public String getEntityOwner() {
        return entityOwner;
    }

    /**
     * Get the entity's unique ID
     *
     * @return unique entity ID
     */
    public String getEntityID() {
        return entityID;
    }
}
