package test.ret;

public class Test2 {
    public static void main(String[] args) {
        new Sub().hello();
        System.out.printf("%s\n", "------------");
        new Sub().hello();
    }
}

class Base {
    static {
        System.out.printf("%s - %s - %s\n", "base", "static", "block");
    }
    {
        System.out.printf("%s - %s - %s\n", "base", "instance", "block");
    }

    public Base() {
        System.out.printf("%s - %s\n", "base", "constructor");
    }

    public void hello() {
        System.out.printf("%s - %s\n", "base", "method");
    }
}

class Sub extends Base {
    static {
        System.out.printf("%s - %s - %s\n", "sub", "static", "block");
    }
    {
        System.out.printf("%s - %s - %s\n", "sub", "instance", "block");
    }

    public Sub() {
        System.out.printf("%s - %s\n", "sub", "constructor");
    }

    @Override
    public void hello() {
        // super.hello();
        System.out.printf("%s - %s\n", "sub", "method");
    }
}