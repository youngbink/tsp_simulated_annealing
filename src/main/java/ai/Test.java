package ai;

import java.text.NumberFormat;

/**
 * Created by youngbinkim on 1/27/16.
 */
public class Test {
    public static void main (String ... args) {
        Test test = new Test();
        //System.out.println(test.factorial(155, 1));
        System.out.println(test.factorial1(155));
        Runtime runtime = Runtime.getRuntime();

        NumberFormat format = NumberFormat.getInstance();

        StringBuilder sb = new StringBuilder();
        long maxMemory = runtime.maxMemory();
        long allocatedMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();

        sb.append("free memory: " + format.format(freeMemory / 1024) + "<br/>");
        sb.append("allocated memory: " + format.format(allocatedMemory / 1024) + "<br/>");
        sb.append("max memory: " + format.format(maxMemory / 1024) + "<br/>");
        sb.append("total free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024) + "<br/>");

        System.out.println(sb);


    }

    public Test(){
        Dog d = new Dog();
        Animal animal = (Animal) d;
        animal.bark();
    }

    private double factorial1(int n) {
        if (1 == n)
            return n;
        else
            return n * factorial1(n-1);
    }

    public double factorial (double n, double sum) {
        if (1 == n)
            return sum;
        else
            return factorial(n-1, sum * n);
    }

    public class Animal {
        public void bark() {
            System.out.println("bark..");
        }
    }

    public class Dog extends Animal{
        public void bark() {
            System.out.println("DOGDOGW");
        }
    }
}
