package dev.rick.mandjesenpuutjesbackend.exceptions;

public class NothingFoundForUserException extends RuntimeException {

    public NothingFoundForUserException(String username) {
        super("Nothing found for user: "+username);
    }
}
