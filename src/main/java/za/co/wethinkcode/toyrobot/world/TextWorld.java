package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;

import java.util.List;

import java.util.Random;

import java.util.ArrayList;

public class TextWorld extends AbstractWorld {
    
    private int numberOfObstacles = 0;
    List<Obstacle> randomObstacles = new ArrayList<Obstacle>();

    public TextWorld() {
        Random rand = new Random();
        this.numberOfObstacles = rand.nextInt(10);
        for (int i = 0; i < this.numberOfObstacles; i++) {
            this.randomObstacles.add(generateRandomObstacle());
        }
    }

    public Obstacle generateRandomObstacle() {
        Random rand = new Random();
        int randomX = rand.nextInt(301) - 200;
        int randomY = rand.nextInt(301) - 200;
        return new SquareObstacle(randomX, randomY);
    }

    @Override
    public void showObstacles() {
        System.out.println( "There are some obstacles:");
        for (Obstacle obstacle: this.randomObstacles) {
            System.out.println(String.format("- At position %d, %d (to %d,%d)",obstacle.getBottomLeftX(),obstacle.getBottomLeftY(),obstacle.getBottomLeftX() + obstacle.getSize(),obstacle.getBottomLeftY() + obstacle.getSize()));
        }
    }

    @Override
    public List<Obstacle> getObstacles() {
        return this.randomObstacles;
    }
}

