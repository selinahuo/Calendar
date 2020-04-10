package usecases.invitations;

import entities.CalendarEvent;
import entities.Invitation;
import usecases.events.EventManager;
import usecases.events.IEventDeletionObserver;

import java.util.ArrayList;

/**
 * Manager for invitation use cases
 */
public class InvitationManager implements IEventDeletionObserver {
    private IInvitationRepository repository;
    private EventManager eventManager;

    /**
     * Construct an InvitationManager
     * @param repository injected invitation repository
     * @param eventManager injected EventManager for events that invitations are for
     */
    public InvitationManager(IInvitationRepository repository, EventManager eventManager) {
        this.repository = repository;
        this.eventManager = eventManager;
    }

    /**
     * Create an invitation
     * @param eventID ID of event associated with invitation
     * @param inviterID ID of inviter
     * @param inviteeID ID of invitee
     * @param initialMessage contents of inviter's initial message
     * @return unique ID of invitation or null if not created successfully
     */
    public String createInvitation(String eventID, String inviterID, String inviteeID, String initialMessage) {
        if (eventManager.getEventByIDAndOwnerID(eventID, inviterID) == null) {
            return null;
        }
        Invitation invitation = new Invitation(eventID, inviterID, inviteeID, initialMessage);
        repository.saveInvitation(invitation);
        return invitation.getInvitationID();
    }

    /**
     * Get invitation by ID and involved user (inviter or invitee)
     * @param invitationID ID of invitation
     * @param userID ID of involved user
     * @return matching invitation entity
     */
    public Invitation getInvitationByIDAndUserID(String invitationID, String userID) {
        return repository.fetchInvitationByIDAndUserID(invitationID, userID);
    }

    /**
     * Get invitations from an inviter
     * @param inviterID ID of inviter
     * @return list of matching invitation entities
     */
    public ArrayList<Invitation> getInvitationsByInviterID(String inviterID) {
        return repository.fetchInvitationsByInviterID(inviterID);
    }

    /**
     * Get invitations to an invitee
     * @param inviteeID ID of inviter
     * @return list of matching invitation entities
     */
    public ArrayList<Invitation> getInvitationsByInviteeID(String inviteeID) {
        return repository.fetchInvitationsByInviteeID(inviteeID);
    }

    /**
     * Accept/decline an invitation
     * @param invitationID ID of invitation
     * @param respondingMessage content of invitee's responding message
     * @param accept whether to accept or decline the invitation
     * @param userID ID of invitee
     * @return whether operation was successful
     */
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

    /**
     * Delete an invitation from inviter
     * @param invitationID ID of invitation
     * @param inviterID ID of inviter
     * @return whether deletion was successful
     */
    public boolean deleteInvitation(String invitationID, String inviterID) {
        return repository.deleteInvitation(invitationID, inviterID);
    }

    /**
     * Respond to event deletion by deleting invitations associated with now deleted events
     * @param event event which was deleted and reacted to
     */
    @Override
    public void handleEventDeletion(CalendarEvent event) {
        repository.deleteInvitationsByEventID(event.getEventID());
    }
}
