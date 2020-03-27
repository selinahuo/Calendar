package dataaccess;

import entities.Invitation;
import usecases.invitations.IInvitationRepository;

import java.util.ArrayList;

public class SerializableInvitationRepository extends SerializableRepository<Invitation> implements IInvitationRepository {
    public SerializableInvitationRepository() {
        super("./invitations.ser");
    }

    @Override
    public boolean saveInvitation(Invitation invitation) {
        return false;
    }

    @Override
    public boolean editInvitationRespondingMessage(String invitationID, String respondingMessage) {
        return false;
    }

    @Override
    public boolean editAccept(String invitationID, Boolean accept) {
        return false;
    }

    @Override
    public Invitation fetchInvitationByUserID(String invitationID, String userID) {
        return null;
    }

    @Override
    public ArrayList<Invitation> fetchInvitationsByInviterID(String inviterID) {
        return null;
    }

    @Override
    public ArrayList<Invitation> fetchInvitationsByInviteeID(String inviteeID) {
        return null;
    }

    @Override
    public boolean deleteInvitation(String invitationID, String inviterID) {
        return false;
    }

    @Override
    public boolean deleteInvitationsByEventID(String eventID) {
        return false;
    }
}
