package utility;

public final class IdGenerator {

    public static long generateID(int collectionSize) {
        return System.currentTimeMillis() + collectionSize;
    }
}
