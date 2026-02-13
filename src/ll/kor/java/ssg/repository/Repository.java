package ll.kor.java.ssg.repository;

public abstract class Repository {
    protected int lastId;

    public Repository() {
        lastId = 0;
    }

    public int getLastId() {
        return lastId;
    }

    public int getNewId() {
        return lastId + 1;
    }
}