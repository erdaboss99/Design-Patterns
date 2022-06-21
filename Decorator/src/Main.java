abstract class Hotdrink {
    abstract void getHotdrinkDescription();
}
class Hotcoffee extends Hotdrink{
    @Override
    void getHotdrinkDescription() {
        System.out.print("\nHot Coffee drink,");
    }
}
abstract class HotdrinkFlavour extends Hotdrink {
    Hotdrink baseHotdrink;
    public HotdrinkFlavour(Hotdrink base) {
        this.baseHotdrink = base;
    }
    @Override
    void getHotdrinkDescription() {
        baseHotdrink.getHotdrinkDescription();
        extraFlavourAddition();
    }
    protected abstract void extraFlavourAddition();
}
class SugarFlavour extends HotdrinkFlavour {
    public SugarFlavour(Hotdrink base) {
        super(base);
    }
    @Override
    protected void extraFlavourAddition() {
        System.out.print(" with sugar,");
    }

}
class MilkFlavour extends HotdrinkFlavour {
    public MilkFlavour(Hotdrink base) {
        super(base);
    }
    @Override
    protected void extraFlavourAddition() {
        System.out.print(" with milk,");
    }

}
public class Main {
    public static void main(String[] args) {

        Hotdrink HotcoffeeSugar = new SugarFlavour(new Hotcoffee());
        HotcoffeeSugar.getHotdrinkDescription();

        Hotdrink HotcoffeeMilkSugar = new MilkFlavour(new SugarFlavour(new Hotcoffee()));
        HotcoffeeMilkSugar.getHotdrinkDescription();
    }
}