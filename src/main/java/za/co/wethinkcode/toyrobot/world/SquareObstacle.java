package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;

public class SquareObstacle implements Obstacle {

    private int X = 0;
    private int Y = 0;

    public SquareObstacle(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    public int getX() {
        return this.X;
    }

    public int getY() {
        return this.Y;
    }

    public int getBottomLeftX() {
        return this.X;
    }

    public int getBottomLeftY() {
        return this.Y;
    }

    public int getSize() {
        return 5;
    }

    public boolean blocksPosition(Position position) {
        boolean firstBoolean = position.getX() >= this.X;
        boolean secondBoolean = position.getX() < (this.X + getSize());
        boolean thirdBoolean = position.getY() >= this.Y;
        boolean fourthBoolean = position.getY() < (this.Y + getSize());
        return (firstBoolean && secondBoolean && thirdBoolean && fourthBoolean);
    }

    public boolean blocksPath(Position  a, Position b) {
        
        int minY;
        int maxY;
        int minX;
        int maxX;

        if (a.getX() > b.getX()) {
            minX = b.getX();
            maxX = a.getX();
        }
        else {
            maxX = b.getX();
            minX = a.getX();
        }

        if (a.getY() > b.getY()) {
            minY = b.getY();
            maxY = a.getY();
        }
        else {
            maxY = b.getY();
            minY = a.getY();
        }

        if (a.getX() == b.getX()) {
            for (int i = minY; i <= maxY; i++) {
                if (blocksPosition(new Position(a.getX(), i))) {
                    return true;
                }
            }
        }
        else if (a.getY() == b.getY()) {
            for (int i = minX; i <= maxX; i++) {
                if (blocksPosition(new Position(i, a.getY()))) {
                    return true;
                }
            }
        }
        return false;
    }
}
