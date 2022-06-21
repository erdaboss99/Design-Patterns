abstract class Subject {
    public abstract void Request();
}

class RealSubject extends Subject { // valódi munka "tárgy"amit tenni akarunk a valódi objektum, amit a proxy elrejt
    @Override
    public void Request() {
        System.out.println("I'm calling the RealSubject.Request() method");
    }
}

class Proxy extends Subject {
    // The 'Proxy' osztály
    // Tartalmaz egy referenciát a tényleges objektumra, hogy el tudja azt érni.
    // Szabályozza a hozzáférést a tényleges objektumhoz, feladata lehet a tényleges
    // objektum létrehozása és törlése is.
    private RealSubject realSubject;
    @Override
    public void Request() {
        if(realSubject == null)
            realSubject = new RealSubject();
        realSubject.Request();
    }
}
public class Main {
    public static void main(String[] args) {
        // Készítünk egy helyettest és kérünk egy szolgáltatást.
        Proxy proxy = new Proxy();
        proxy.Request();
    }
}