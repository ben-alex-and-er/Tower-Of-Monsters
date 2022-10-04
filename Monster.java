public interface Monster {

    int SEWeighting = 10;
    int NPEWeighting = 3;
    int AIOOBEWeighting = 3;

    abstract void strike(Character enemy);

    abstract void syntaxError(Character enemy);

    abstract void nullPointerException();

    abstract void arrayIndexOutOfBoundException(Character enemy);
}