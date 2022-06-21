import java.util.ArrayList;

interface IObserver {
    // értékék amiket megkapnak az observerek a Subjecttől, push-os megoldás
    void Update(float temp, float humidity, float pressure);
}
interface ISubject {
    // observer regisztrálásra
    void RegisterObserver(IObserver observer);
    // observer törlésre
    void RemoveObserver(IObserver observer);
    // meghívódik, hogy értesítse az megfigyelőket
    // amikor a Subject állapota megváltozik
    void NotifyObservers();
}
interface IDisplayElement {
    // megjelenítés
    void Display();
}

class CurrentConditionsDisplay implements IObserver, IDisplayElement {
    private float temperature;
    private float humidity;
    private ISubject weatherData;
    // a konstruktor megkapja a weatherData objektumot
    // (a Subject) és arra használjuk, hogy
    // a display-t observerként regisztráljuk
    public CurrentConditionsDisplay(ISubject weatherData)
    {
        this.weatherData = weatherData;
        weatherData.RegisterObserver(this);
    }
    // amikor az Update() meghívódik, mentjük a temperature-t és a humidity-t // majd meghívjuk a Display()-t
    public void Update(float temperature, float humidity, float pressure)
    {
        this.temperature = temperature;
        this.humidity = humidity;
        Display();
    }
    // Megjelenítjük a legújabb eredményeket
    public void Display()
    {
        System.out.println("Current conditions: " + temperature + "F degrees and " + humidity + "% humidity");
    }
}

// implementáljuk a Subject interfészt
class WeatherData implements ISubject {
    // hozzáadunk egy listát amiben observereket tárolunk
    private ArrayList<IObserver> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData()
    {
        // létrehozzuk az observereket tároló listát
        observers = new ArrayList<IObserver>();
    }

    public void RegisterObserver(IObserver observer) {
        // amikor egy observer regisztrál, egyszerűen hozzáadjuk a listához
        observers.add(observer);
    }

    public void RemoveObserver(IObserver observer) {
        // amikor egy observer kéri a törlését, egyszerűen töröljük a listából
        int i = observers.indexOf(observer);
        if (i >= 0)
        {
            observers.remove(observer);
        }
    }

    // itt szólunk az observereknek az állapotról
    // mivel mind observerek, van Update() metódusuk, így tudjuk őket értesíteni
    public void NotifyObservers() {
        for (IObserver o : observers) {
            IObserver observer = (IObserver)o;
            observer.Update(temperature, humidity, pressure); // ez push-os
            // observer.Update(this); // ez pull-os
        }
    }

    // amikor a Weather Station-től megkapjuk a frissített értékeket,
    //értesítjük az observereket
    public void MeasurementsChanged()
    {
        NotifyObservers();
    }
    // értékek beállítása hogy tesztelhessük a megjelenítést
    public void SetMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        MeasurementsChanged();
    }
    // egyéb metódusok
}

public class Main {
    public static void main(String[] args) {
        // létrehozzuk a weatherData objektumot
        WeatherData weatherData = new WeatherData();
        // létrehozzuk a displayt és odaajuk neki a weatherData-t
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        // új időjárási mérésértékek szimulálása
        weatherData.SetMeasurements(80, 65, 30.4f);
        weatherData.SetMeasurements(82, 70, 29.2f);
        weatherData.SetMeasurements(78, 90, 29.2f);
    }
}