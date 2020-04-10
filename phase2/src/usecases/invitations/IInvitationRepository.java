package usecases.invitations;

import entities.Invitation;

import java.util.ArrayList;

public interface IInvitationRepository {
    void saveInvitation(Invitation invitation);

    boolean editInvitationRespondingMessage(String invitationID, String respondingMessage, String userID);
    boolean editAccept(String invitationID, Boolean accept, String userID);

    Invitation fetchInvitationByIDAndUserID(String invitationID, String userID);
    ArrayList<Invitation> fetchInvitationsByInviterID(String inviterID);

    ArrayList<Invitation> fetchInvitationsByInviteeID(String inviteeID);

    boolean deleteInvitation(String invitationID, String inviterID);

    void deleteInvitationsByEventID(String eventID);
}
