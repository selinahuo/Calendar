package usecases.invitations;

import entities.Invitation;
import usecases.events.IEventDeletionObserver;

import java.util.ArrayList;

public class InvitationManager implements IEventDeletionObserver {
    private IInvitationRepository repository;

    public InvitationManager(IInvitationRepository repository) {
        this.repository = repository;
    }

    public String createInvitation(String eventID, String inviterID, String inviteeID, String initialMessage) {
        Invitation invitation = new Invitation(eventID, inviterID, inviteeID, initialMessage);
        boolean trySave = repository.saveInvitation(invitation);
        if (trySave) {
            return invitation.getInvitationID();
        } else {
            return null;
        }
    }

    public Invitation getInvitationByUserID(String invitationID, String userID) {
        return repository.fetchInvitationByUserID(invitationID, userID);
    }

    public ArrayList<Invitation> getInvitationsByInviterID(String inviterID) {
        return repository.fetchInvitationsByInviterID(inviterID);
    }

    public ArrayList<Invitation> getInvitationsByInviteeID(String inviteeID) {
        return repository.fetchInvitationsByInviteeID(inviteeID);
    }

    boolean acceptInvitation(String invitationID, String respondingMessage, Boolean accept) {
        return repository.editInvitationRespondingMessage(invitationID, respondingMessage)
                && repository.editAccept(invitationID, accept);
    }

    boolean deleteInvitation(String invitationID, String inviterID) {
        return repository.deleteInvitation(invitationID, inviterID);
    }

    @Override
    public void handleEventDeletion(String eventID) {
        repository.deleteInvitationsByEventID(eventID);
    }
}
