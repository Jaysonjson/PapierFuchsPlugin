package jaysonjson.papierfuchs.fuchs.io.data.player.data.cosmetic;

public class FPCosmetic {

    private FPCHat hat = new FPCHat();
    private FPCPet pet = new FPCPet();

    public FPCHat getHat() {
        return hat;
    }

    public FPCPet getPet() {
        return pet;
    }

    public void setHat(FPCHat hat) {
        this.hat = hat;
    }

    public void setPet(FPCPet pet) {
        this.pet = pet;
    }

    public boolean hasPet() {
        return !getPet().getCurrent().equals("");
    }

    public boolean hasHat() {
        return !getHat().getCurrent().equals("");
    }


}
