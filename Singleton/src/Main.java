class Singleton {
    // statikus mező az egyetlen példány számára
    private static Singleton uniqueInstance = null;
    // privát konstruktor, hogy ne lehessen 'new' kulcsszóval példányosítani private Singleton() { }
// biztosítja számunkra a példányosítást és egyben visszaadja a példányt // mindenkinek ugyanazt
    public static Singleton GetInstance() {
        if (uniqueInstance==null) { // megvizsgálja, hogy létezik-e már egy példány
            uniqueInstance = new Singleton(); // ha nem, akkor létrehozza azt
        }
        // visszaadja a példányt
        return uniqueInstance;
    }
}

public class Main {
    public static void main(String[] args) {
        //a konstruktor private, nem lehet new kulcsszóval példányosítani
        Singleton s1 = Singleton.GetInstance();
        Singleton s2 = Singleton.GetInstance();
        // Teszt: ugyanaz a példány-e a kettő?
        if (s1 == s2)
        {
            System.out.println("Same, so there is only one instance.");
        }
    }
}