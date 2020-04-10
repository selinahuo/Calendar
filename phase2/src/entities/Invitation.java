package entities;

import java.io.Serializable;
import java.util.UUID;

/**
 * Class representing an invitation to an event
 */
public class Invitation implements Serializable {
    private final String invitationID = UUID.randomUUID().toString();
    private String eventID;
    private String inviterID;
    private String inviteeID;
    private String initialMessage;
    private String respondingMessage = "";
    private Boolean accept;

    /**
     * Construct a new invitation
     * @param eventID ID of event of invitation
     * @param inviterID ID of inviting user
     * @param inviteeID ID of user being invited
     * @param initialMessage a message from the inviting user to the invitee
     */
    public Invitation(String eventID, String inviterID, String inviteeID, String initialMessage) {
        this.eventID = eventID;
        this.inviterID = inviterID;
        this.inviteeID = inviteeID;
        this.initialMessage = initialMessage;
    }

    /**
     * Get invitation's unique ID
     * @return invitation's unique ID
     */
    public String getInvitationID() {
        return invitationID;
    }

    /**
     * Get ID of event associated with invitation
     * @return unique event ID
     */
    public String getEventID() {
        return eventID;
    }

    /**
     * Get user ID of inviting user
     * @return inviting user's ID
     */
    public String getInviterID() {
        return inviterID;
    }

    /**
     * Get user ID of invited user
     * @return invitee user's ID
     */
    public String getInviteeID() {
        return inviteeID;
    }

    /**
     * Get the inviting user's initial message
     * @return initial invitation message
     */
    public String getInitialMessage() {
        return initialMessage;
    }

    /**
     * Get the invitee's responding message to the inviter
     * @return invitee's responding message
     */
    public String getRespondingMessage() {
        return respondingMessage;
    }

    /**
     * Set the invitee's responding message to the inviter
     * @param respondingMessage invitee's responding message to set
     */
    public void setRespondingMessage(String respondingMessage) {
        this.respondingMessage = respondingMessage;
    }

    /**
     * Get whether the invitee has accepted the invitation or null if no response
     * @return whether invitee has accepted or null
     */
    public Boolean getAccept() {
        return accept;
    }

    /**
     * Set whether invitee has accepted the invitation
     * @param accept true to accept, false to decline
     */
    public void setAccept(Boolean accept) {
        this.accept = accept;
    }
}
