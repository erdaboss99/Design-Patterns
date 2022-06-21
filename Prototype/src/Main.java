import java.lang.Cloneable;

abstract class Vehicle implements Cloneable {
    private String type;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    private int passengerCount;
    public int getPassengerCount() {
        return passengerCount;
    }
    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }
    private double tankSize;
    public double getTankSize() {
        return tankSize;
    }
    public void setTankSize(double tankSize) {
        this.tankSize = tankSize;
    }
    private String color;
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public Vehicle(String type, int passengerCount, double tankSize)
    {
        this.type = type;
        this.passengerCount = passengerCount;
        this.tankSize = tankSize;
    }
    public Object Clone() throws CloneNotSupportedException{
        return super.clone();
    }
    @Override
    public String toString() {
        return type + " " + passengerCount + " " + tankSize + " " + color;
    }
}

class Racecar extends Vehicle {
    private int topSpeed;
    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        this.topSpeed = topSpeed;
    }

    public Racecar(String type, int passengerCount, double tankSize, int topSpeed)  {
        super(type, passengerCount, tankSize);
        this.topSpeed = topSpeed;
    }

    @Override
    public String toString() {
        return super.toString() + " " + topSpeed;
    }
}
class Truck extends Vehicle {
    private double maxWeight;

    public Truck(String type, int passengerCount, double tankSize, double maxWeight) {
        super(type, passengerCount, tankSize);
        this.maxWeight = maxWeight;
    }

    @Override
    public String toString() {
        return super.toString() + " " + maxWeight;
    }
}
class Factory {
    public Vehicle[] factoryLine(Vehicle vehicle, String color, int count) throws CloneNotSupportedException {
        Vehicle[] temp = new Vehicle[count];
        for (int i = 0; i < count; i++)
        {
            temp[i] = (Vehicle)vehicle.Clone();
            temp[i].setColor(color);
        }
        return temp;
    }
}
public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        //a versenyautó és a teherautó prototípus létrehozása
        Vehicle prototype1 = new Racecar("Aston Martin", 4, 180, 220);
        Vehicle prototype2 = new Truck("Scania", 3, 200, 1000);
        Factory factory = new Factory();
        // legyárt 10 piros versenyautót
        Vehicle[] racecars = factory.factoryLine(prototype1, "Red", 10);
        for(Vehicle r : racecars)
        {
            System.out.println(r);
        }
        // legyárt 20 szürke teherautót
        Vehicle[] trucks = factory.factoryLine(prototype2, "Blue", 20);
        for(Vehicle t : trucks) {
            System.out.println(t);
        }
    }
}

