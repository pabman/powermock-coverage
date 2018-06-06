package mocktest;

public class ClassA {

    public String doSomething() {
        ClassB classB = new ClassB();
        String something = classB.getSomething();
        if (something.equals("The actual something")) {
            System.out.println("Got the actual something");
        } else {
            System.out.println("Got something else");
        }

        return something;
    }

}
