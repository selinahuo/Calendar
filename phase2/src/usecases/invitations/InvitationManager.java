package usecases.invitations;

import entities.CalendarEvent;
import entities.Invitation;
import usecases.events.EventManager;
import usecases.events.IEventDeletionObserver;

import java.util.ArrayList;

public class InvitationManager implements IEventDeletionObserver {
    private IInvitationRepository repository;
    private EventManager eventManager;

    public InvitationManager(IInvitationRepository repository, EventManager eventManager) {
        this.repository = repository;
        this.eventManager = eventManager;
    }

    public String createInvitation(String eventID, String inviterID, String inviteeID, String initialMessage) {
        Invitation invitation = new Invitation(eventID, inviterID, inviteeID, initialMessage);
        repository.saveInvitation(invitation);
        return invitation.getInvitationID();
    }

    // Create singular
    public Invitation getInvitationByIDAndUserID(String invitationID, String userID) {
        return repository.fetchInvitationByIDAndUserID(invitationID, userID);
    }

    public ArrayList<Invitation> getInvitationsByInviterID(String inviterID) {
        return repository.fetchInvitationsByInviterID(inviterID);
    }

    public ArrayList<Invitation> getInvitationsByInviteeID(String inviteeID) {
        return repository.fetchInvitationsByInviteeID(inviteeID);
    }

    public boolean acceptInvitation(String invitationID, String respondingMessage, Boolean accept, String userID) {
        Invitation invitation = repository.fetchInvitationByIDAndUserID(invitationID, userID);
        if (repository.editInvitationRespondingMessage(invitationID, respondingMessage, userID)) {
            if (accept) {
                eventManager.addCollaborator(invitation.getEventID(), userID);
            }
            return repository.editAccept(invitationID, accept, userID);
        }
        return false;
    }

    public boolean deleteInvitation(String invitationID, String inviterID) {
        return repository.deleteInvitation(invitationID, inviterID);
    }

    @Override
    public void handleEventDeletion(CalendarEvent event) {
        repository.deleteInvitationsByEventID(event.getEventID());
    }
}
