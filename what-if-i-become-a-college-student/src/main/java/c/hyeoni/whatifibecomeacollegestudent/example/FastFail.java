package c.hyeoni.whatifibecomeacollegestudent.example;

public class FastFail {

    public void jobHunting() {
        // studying ...

        // fast fail
        if (!doYouKnowObject()) {
            throw new IllegalArgumentException();
        }

        // studying ...

        if (!doYouKnowJava()) {
            throw new IllegalArgumentException();
        }

        // studying ...

        if (!doYouKnowSpring()) {
            throw new IllegalArgumentException();
        }

        // studying ...
        // studying ...
        // studying ...
        // studying ...
        // studying ...
    }

    private boolean doYouKnowJava() {
        return true;
    }

    private boolean doYouKnowSpring() {
        return true;
    }

    private boolean doYouKnowObject() {
        return true;
    }
}
