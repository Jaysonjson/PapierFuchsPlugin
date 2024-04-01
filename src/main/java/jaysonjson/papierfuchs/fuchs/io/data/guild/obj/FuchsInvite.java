package jaysonjson.papierfuchs.fuchs.io.data.guild.obj;

import java.util.UUID;

public class FuchsInvite {
    private UUID inviter = null;
    private UUID invitee = null;

    public FuchsInvite(UUID inviter, UUID invitee) {
        this.inviter = inviter;
        this.invitee = invitee;
    }

    public UUID getInvitee() {
        return invitee;
    }

    public UUID getInviter() {
        return inviter;
    }

    public void setInvitee(UUID invitee) {
        this.invitee = invitee;
    }

    public void setInviter(UUID inviter) {
        this.inviter = inviter;
    }
}
