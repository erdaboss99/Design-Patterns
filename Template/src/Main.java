abstract class DrinkTemplate {
    public void DrinkInstruction() { // Ez a függvény nem kapta meg a virtual jelzőt a sorrend betartása miatt.
        WaterBoiler();// kötelező és közös lépés
        Make(); // inversion of control, mert a gyermek TypeSelection metódusa fog futni
        Sweeten(); // kötelező és nem közös lépés
        Rum(); // opcionális
        Serve();
    }
    private void WaterBoiler() {// kötelező közös lépés, ennek szintén nem kell felűlírhatónak lennie
        System.out.println("Boiling Water 98..99..100c");
    }
    protected abstract void Make(); // Ki kell dolgoznia a konkrét osztálynak.
    protected abstract void Sweeten(); // Ezek kötelező nem közös lépések.
    protected void Rum(){} // Ez egy hook, vagyis egy opcionális lépés.
    private void Serve() { // kötelező közös lépés
        System.out.println("We pour the freshly made drink into a cup and we serve it for the guest!");
    }
}
class Tea extends DrinkTemplate {
    @Override
    protected void Make()
    {
        // ezt ki kell dolgozni, hiszen másképp főzök teát, mint kávét
        System.out.println("We put the teabag into the cup!");
    }
    @Override
    protected void Sweeten()
    {
        System.out.println("Small sugar and 5 drops of lemon!"); }
    }
class Coffee extends DrinkTemplate {
    @Override
    protected void Make()
    {
        // ezt ki kell dolgozni, hiszen másképp főzök teát, mint kávét
        System.out.println("We pour the hot water on the coffee!");
    }
    @Override
    protected void Sweeten()
        {
            System.out.println("Small sugar and 50ml milk!");
        }
}

public class Main {
    public static void main(String[] args) {
        DrinkTemplate tea = new Tea();
        DrinkTemplate coffee = new Coffee();
        tea.DrinkInstruction(); // a késői kötés miatt a Tea főz és édesít metódusa fut
        coffee.DrinkInstruction();// a késői kötés miatt a Kávé főz és édesít metódusa fut
    }
}