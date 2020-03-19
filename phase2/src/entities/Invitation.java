package entities;

import java.io.Serializable;
import java.util.UUID;

public class Invitation implements Serializable {
    private String invitationID = UUID.randomUUID().toString();
    private String eventID;
    private String inviterID;
    private String inviteeID;
    private String initialMessage;
    private String respondingMessage;
    private Boolean accept;
    private Boolean acknowledged;

    public Invitation(String eventID, String inviterID, String inviteeID, String initialMessage) {
        this.eventID = eventID;
        this.inviterID = inviterID;
        this.inviteeID = inviteeID;
        this.initialMessage = initialMessage;
    }

    public String getInvitationID() {
        return invitationID;
    }

    public String getEventID() {
        return eventID;
    }

    public String getInviterID() {
        return inviterID;
    }

    public String getInviteeID() {
        return inviteeID;
    }

    public String getInitialMessage() {
        return initialMessage;
    }

    public String getRespondingMessage() {
        return respondingMessage;
    }

    public void setRespondingMessage(String respondingMessage) {
        this.respondingMessage = respondingMessage;
    }

    public Boolean getAccept() {
        return accept;
    }

    public void setAccept(Boolean accept) {
        this.accept = accept;
    }

    public Boolean getAcknowledged() {
        return acknowledged;
    }

    public void setAcknowledged(Boolean acknowledged) {
        this.acknowledged = acknowledged;
    }
}
