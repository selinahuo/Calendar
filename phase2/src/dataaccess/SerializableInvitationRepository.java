package dataaccess;

import entities.Invitation;
import usecases.invitations.IInvitationRepository;

import java.util.ArrayList;

class SerializableInvitationRepository extends SerializableRepository<Invitation> implements IInvitationRepository {
    SerializableInvitationRepository() {
        super("./invitations.ser");
    }

    @Override
    public void saveInvitation(Invitation invitation) {
        saveItem(invitation);
    }

    @Override
    public boolean editInvitationRespondingMessage(String invitationID, String respondingMessage, String userID) {
        return editSingular(
                (Invitation i) -> i.getInvitationID().equals(invitationID) && i.getInviteeID().equals(userID),
                (Invitation i) -> i.setRespondingMessage(respondingMessage)
        );
    }

    @Override
    public boolean editAccept(String invitationID, Boolean accept, String userID) {
        return editSingular(
                (Invitation i) -> i.getInvitationID().equals(invitationID) && i.getInviteeID().equals(userID),
                (Invitation i) -> i.setAccept(accept)
        );
    }

    @Override
    public Invitation fetchInvitationByIDAndUserID(String invitationID, String userID) {
        return fetchSingular(
                (Invitation i) ->
                        i.getInvitationID().equals(invitationID)
                        && (i.getInviterID().equals(userID) || i.getInviteeID().equals(userID))
        );
    }

    @Override
    public ArrayList<Invitation> fetchInvitationsByInviterID(String inviterID) {
        return fetchPlural((Invitation i) -> i.getInviterID().equals(inviterID));
    }

    @Override
    public ArrayList<Invitation> fetchInvitationsByInviteeID(String inviteeID) {
        return fetchPlural((Invitation i) -> i.getInviteeID().equals(inviteeID));
    }

    @Override
    public boolean deleteInvitation(String invitationID, String inviterID) {
        return deleteSingular((Invitation i) ->
                i.getInvitationID().equals(invitationID) && i.getInviterID().equals(inviterID));
    }

    @Override
    public void deleteInvitationsByEventID(String eventID) {
        deletePlural((Invitation i) -> i.getEventID().equals(eventID));
    }
}
