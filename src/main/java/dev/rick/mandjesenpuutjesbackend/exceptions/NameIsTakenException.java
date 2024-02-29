package dev.rick.mandjesenpuutjesbackend.exceptions;

public class NameIsTakenException extends RuntimeException {

    public NameIsTakenException(String name) {
        super("Name is taken: "+name);
    }

    public NameIsTakenException(long id) {
        super("ID is taken: "+id);
    }
}
