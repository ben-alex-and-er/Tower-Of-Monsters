public interface Monster {

    int SEWeighting = 10;
    int NPEWeighting = 3;
    int AIOOBEWeighting = 3;

    abstract void strike(Character enemy);

    abstract void SyntaxError(Character enemy);

    abstract void NullPointerException();

    abstract void ArrayIndexOutOfBoundException(Character enemy);
}