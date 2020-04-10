package usecases.invitations;

import entities.Invitation;

import java.util.ArrayList;

/**
 * Provides a public interface for invitation repository operations
 */
public interface IInvitationRepository {
    /**
     * Save an invitation to the repository
     * @param invitation invitation to save
     */
    void saveInvitation(Invitation invitation);

    /**
     * Edit invitation's responding message
     * @param invitationID ID of invitation
     * @param respondingMessage contents of the responding message
     * @param userID ID of the responder
     * @return whether edit was successful
     */
    boolean editInvitationRespondingMessage(String invitationID, String respondingMessage, String userID);

    /**
     * Edit invitation's accept status
     * @param invitationID ID of invitation
     * @param accept true or false to accept or decline
     * @param userID ID of the responder
     * @return whether edit was successful
     */
    boolean editAccept(String invitationID, Boolean accept, String userID);

    /**
     * Fetch an invitation by ID and associated user (invitee or inviter)
     * @param invitationID ID of invitation
     * @param userID ID of invitee or inviter
     * @return invitation entity
     */
    Invitation fetchInvitationByIDAndUserID(String invitationID, String userID);

    /**
     * Fetch invitations invited by an inviter
     * @param inviterID ID of inviter
     * @return list of matching invitation entities
     */
    ArrayList<Invitation> fetchInvitationsByInviterID(String inviterID);

    /**
     * Fetch invitations to an invitee
     * @param inviteeID ID of invitee
     * @return list of matching invitation entities
     */
    ArrayList<Invitation> fetchInvitationsByInviteeID(String inviteeID);

    /**
     * Delete invitation by ID belonging to inviter
     * @param invitationID ID of invitation
     * @param inviterID ID of inviter
     * @return whether deletion was successful
     */
    boolean deleteInvitation(String invitationID, String inviterID);

    /**
     * Delete invitation by the event it references
     * @param eventID ID of event
     */
    void deleteInvitationsByEventID(String eventID);
}
