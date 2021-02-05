package jaysonjson.papierfuchs.skillclass.fighter;

import jaysonjson.papierfuchs.skillclass.zAbstractClass;

public class zFighterClass extends zAbstractClass {
    public zFighterSubClass subClass = zFighterSubClass.DPS;

    public zFighterSubClass getSubClass() {
        return subClass;
    }
}
