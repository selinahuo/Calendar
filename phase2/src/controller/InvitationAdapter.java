package controller;

import controller.viewmodels.ListModel;
import controller.viewmodels.SingularInvitationModel;
import controller.viewmodels.SingularModel;
import entities.Invitation;

import java.util.ArrayList;

class InvitationAdapter {
    private static String createInvitationString(Invitation invitation) {
        String statusString = InvitationAdapter.createInvitationStatusString(invitation);
        return String.format("ID: %s | Invitation for: %s | To: %s | From: %s | Status: %s",
                invitation.getInvitationID(), invitation.getEventID(), invitation.getInviteeID(), invitation.getInviterID(), statusString);
    }

    private static String createInvitationStatusString(Invitation invitation) {
        String statusString;
        if (invitation.getAccept() == null) {
            statusString = "Awaiting Response";
        } else if (invitation.getAccept()) {
            statusString = "Accepted";
        } else {
            statusString = "Declined";
        }
        return statusString;
    }

    static SingularInvitationModel createInvitationSingularModel(Invitation inv) {
        if (inv == null) {
            return null;
        }
        String statusString = InvitationAdapter.createInvitationStatusString(inv);
        String entityString = String.format("ID: %s | Invitation for: %s | Status: %s\n",
                inv.getInvitationID(), inv.getEventID(), statusString);
        entityString += String.format("%s said: %s | %s responded: %s", inv.getInviterID(), inv.getInitialMessage(), inv.getInviteeID(), inv.getRespondingMessage());
        return new SingularInvitationModel(entityString, inv.getInviterID(), inv.getInvitationID(), inv.getAccept());
    }

    static ListModel createInvitationListModel(ArrayList<Invitation> invitations) {
        ArrayList<String> invitationList = new ArrayList<>();
        for (Invitation invitation: invitations) {
            invitationList.add(createInvitationString(invitation));
        }
        return new ListModel(invitationList);
    }
}
