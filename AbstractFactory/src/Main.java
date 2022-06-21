abstract class Chassis { }
class AudiChassis extends Chassis { }
class MercedesChassis extends Chassis { }
abstract class Engine { }
class AudiEngine extends Engine { }
class MercedesEngine extends Engine { }

abstract class VehicleFactory
{
    public abstract Chassis CreateChassis();
    public abstract Engine CreateEngine();
}
class AudiFactory extends VehicleFactory
{
    @Override
    public  Chassis CreateChassis() {
        return new AudiChassis();
    }

    @Override
    public  Engine CreateEngine() {
        return new AudiEngine();
    }
}
class MercedesFactory extends VehicleFactory
{
    @Override
    public  Chassis CreateChassis() {
        return new MercedesChassis();
    }

    @Override
    public  Engine CreateEngine() {
        return new MercedesEngine();
    }
}

public class Main {
    public static void main(String[] args) {
        VehicleFactory factory = new AudiFactory();
        Chassis chassis1 = factory.CreateChassis();
        Engine engine1 = factory.CreateEngine();
    }
}