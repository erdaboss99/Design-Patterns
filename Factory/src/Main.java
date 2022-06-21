public class Main {
    abstract static class ClassificationFactory
    {
        public Classification CreateClassification()
        {
            // itt a gyártás előtt lehet ezt-azt csinálni, pl. logolni
            return Classify();
        }
        public abstract Classification Classify();
    }
    static class ExactClassificationFactory1 extends ClassificationFactory
    {
        @Override
        public Classification Classify() {
            return new A_Classification();
        }
    }
    static class ExactClassificationFactory2 extends ClassificationFactory
    {
        @Override
        public Classification Classify() {
            return new B_Classification();
        }
    }
    interface Classification {
        void Classify();
    }
    static class A_Classification implements Classification
    {
        public void Classify() {
            System.out.println("A-mínősítésben részesül!");
        }
    }
    static class B_Classification implements Classification
    {
        public void Classify() {
            System.out.println("B-mínősítésben részesül!");
        }
    }
    public static void main(String[] args) {
        ClassificationFactory[] classifier = new ClassificationFactory[2];
        classifier[0] = new ExactClassificationFactory1();
        classifier[1] = new ExactClassificationFactory2();
        for(ClassificationFactory c : classifier)
        {
            Classification classification = c.CreateClassification();
            classification.Classify();
        }

    }
}