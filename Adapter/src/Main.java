abstract class Human
{
    public abstract String GetName();
    public abstract int GetIQ();
}
class Robot {
    String ID;
    int memory; //memoria MB-ban megadva
    public Robot(String ID, int memory)
    {
        this.ID = ID;
        this.memory = memory;
    }
    public String GetID() {
        return ID;
    }
    public int GetMemory() {
        return memory;
    }
}
class RobotToHuman extends Human
{
    Robot robot;
    public RobotToHuman(Robot robot)
    {
        this.robot = robot;
    }
    @Override
    public String GetName()
    {
        return robot.GetID();
    }
    @Override
    public int GetIQ()
    {
        return robot.GetMemory() / 1024; // 1GB memória = 1 IQ
    }
}

public class Main {
    public static void main(String[] args) {
        Robot R2D2 = new Robot("R2D2", 80000);
        Human R2D2wrapper = new RobotToHuman(R2D2);
        System.out.println("Name: " + R2D2wrapper.GetName());
        System.out.println("IQ-ja: " + R2D2wrapper.GetIQ());
    }
}