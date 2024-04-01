package jaysonjson.papierfuchs.fuchs.object.intern.entity;

import jaysonjson.papierfuchs.fuchs.object.intern.entity.objects.PetEntity;
import jaysonjson.papierfuchs.fuchs.registry.FRO;

public class EntityList {

    //public static FuchsRegistryObject<TestEntity> TEST = new FuchsRegistryObject<>(new TestEntity("test", EntityTypes.ZOMBIE));
    //public static FuchsRegistryObject<FuchsEntity<SoulOfTheCorruptEntity>> SOUL_OF_THE_CORRUPT = new FuchsRegistryObject<>(new FuchsEntity<>("soul_of_the_corrupt", SoulOfTheCorruptEntity.class));
    public static FRO<PetEntity> PET = new FRO<>(new PetEntity("pet"));

    /*public static FRO<SoulOfTheCorruptEntity> SOUL_OF_THE_CORRUPT = new FRO<>(new SoulOfTheCorruptEntity("soul_of_the_corrupt"));
    public static FRO<BeeStaffEntity> BEE_STAFF = new FRO<>(new BeeStaffEntity("bee_staff"));
    public static FRO<PetEntity> PET = new FRO<>(new PetEntity("pet"));
    public static FRO<RevivedSoulEntity> REVIVED_SOUL = new FRO<>(new RevivedSoulEntity("revived_soul"));
    public static FRO<TestOfFearBossEntity> TEST_OF_FEAR_BOSS = new FRO<>(new TestOfFearBossEntity("test_of_fear_boss"));*/
}
