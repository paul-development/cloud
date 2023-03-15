package de.paul.database.type;

/**
 * JavaDoc this file!
 * Created: 15.03.2023
 * <p>
 * author Paul.
 */
public enum DatabaseType {

    VARCHAR("varchar", 255),
    INT("int", 0),
    LONGBLOB("longblob", 0);

    String name;
    int lengt;

    DatabaseType(String name, int lengt) {
        this.name = name;
        this.lengt = lengt;
    }

    public String append() {
        return name + (lengt == 0 ? "" : "(" + lengt + ")");
    }

    public int getLengt() {
        return lengt;
    }

    public String getName() {
        return name;
    }
}
