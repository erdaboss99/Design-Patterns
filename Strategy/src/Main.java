abstract class AbstractDuck {
    public abstract void Fly();
    public abstract void Quack();
}
class NiceDuck extends AbstractDuck {
    QuackStrategy quackStrategy; // Objektum összetétel van referenciám egy másik objektumra, ezen keresztül használom.
    FlyStrategy flyStrategy; // Objektum összetétel
    // Objektum összetétel a konstruktoron keresztül kap értéket
    public NiceDuck(QuackStrategy quackStrategy, FlyStrategy flyStrategy) { // Ez a Dependency Injection / Felelősség Injektálás

        // Dependency Injection: Értéket adok egy objektum összetételnek, Innentől fogva ez befolyásolja a viselkedést.
        // Úgy hápogok, ahogy kiválasztom a stratégiát
        this.quackStrategy = quackStrategy;
        this.flyStrategy = flyStrategy; // Ez a Dependency Injection
    }
    @Override
    public void Fly() {
        flyStrategy.Fly(); // Felelősség delegálás
    }
    @Override
    public void Quack() {
        // Ez a felelősség átadás
        quackStrategy.Quack();
        // Felelősség átadás = a felelősség delegálása , nem én akarok hápogni, a stratégia hápog helyettem
    }
}
abstract class QuackStrategy {
    public abstract void Quack();
}
abstract class FlyStrategy {
    public abstract void Fly();
}
class NotFlying extends FlyStrategy {
    @Override
    public void Fly() {
        System.out.println("Not flying!");
    }
}
class NotQuacking extends QuackStrategy {
    @Override
    public void Quack() {
        System.out.println("Not quacking!");
    }
}

public class Main {
    public static void main(String[] args) {
        AbstractDuck duck1 = new NiceDuck(new NotQuacking(), new NotFlying());
        duck1.Fly();
        duck1.Quack();
        // AbstractDuck -> Gof1 miatt. Használjuk a legősibbet amíg jó.
    }
}