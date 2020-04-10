package controller.viewmodels;

/**
 * A view model for representing a single invitation
 */
public class SingularInvitationModel extends SingularModel {
    private Boolean accept;

    /**
     * Construct a view model for a singular invitation
     *
     * @param entityString a string representing the invitation
     * @param entityOwner ID of the inviter of the invitation
     * @param entityID unique ID of the entity
     * @param accept whether the invitation has been accepted
     */
    public SingularInvitationModel(String entityString, String entityOwner, String entityID, Boolean accept) {
        super(entityString, entityOwner, entityID);
        this.accept = accept;
    }

    /**
     * Get whether the invitation has been accepted
     *
     * @return whether the invitation has been accepted
     */
    public Boolean getAccept() {
        return accept;
    }
}
