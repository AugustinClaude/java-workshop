package fr.epita.assistants.drawing;

public abstract class Entity implements IDrawable {
    private long id;
    private static long SEQUENCE = 0;

    public Entity() {
        id = SEQUENCE;
        SEQUENCE++;
    }

    public long getId() {
        return id;
    }

    public abstract void draw();
}
