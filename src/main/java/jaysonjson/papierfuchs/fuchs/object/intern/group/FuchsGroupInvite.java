package jaysonjson.papierfuchs.fuchs.object.intern.group;

import jaysonjson.papierfuchs.fuchs.io.data.guild.obj.FuchsInvite;

import java.util.UUID;

public class FuchsGroupInvite extends FuchsInvite {
    public FuchsGroupInvite(UUID inviter, UUID invitee) {
        super(inviter, invitee);
    }
}
