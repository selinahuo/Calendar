package controller.viewmodels;

public class SingularInvitationModel extends SingularModel {
    private Boolean accept;

    public SingularInvitationModel(String entityString, String entityOwner, String entityID, Boolean accept) {
        super(entityString, entityOwner, entityID);
        this.accept = accept;
    }

    public Boolean getAccept() {
        return accept;
    }
}
