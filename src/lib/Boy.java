package lib;

import app.UseInterface;

public class Boy implements UseInterface {
    int a, b;

    public Boy(String name) {
        System.out.println("Name is:" + name);
        this.a = 0;
        this.b = 0;
    }

    public static void main(String args[]) {
        System.out.println("In main()");
        Boy obj = new Boy("Ram");
        obj.shout();
    }

    public void shout() {
        System.out.println("Shout!");
        System.out.println("a:" + this.a);
        System.out.println("b:" + this.b);
    }

    public void getvalue(int p, int q) {
        this.a = p;
        this.b = q;
    }

    public void use() {
        System.out.println("Use()");
    }
}