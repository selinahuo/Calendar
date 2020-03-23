package usecases.invitations;

import entities.Invitation;

import java.util.ArrayList;

public interface IInvitationRepository {
    boolean saveInvitation(Invitation invitation);
    boolean editInvitationRespondingMessage(String invitationID, String respondingMessage);
    boolean editAccept(String invitationID, Boolean accept);

    Invitation fetchInvitationByUserID(String invitationID, String userID);

    ArrayList<Invitation> fetchInvitationsByInviterID(String inviterID);

    ArrayList<Invitation> fetchInvitationsByInviteeID(String inviteeID);

    boolean deleteInvitation(String invitationID, String inviterID);

    boolean deleteInvitationsByEventID(String eventID);
}
