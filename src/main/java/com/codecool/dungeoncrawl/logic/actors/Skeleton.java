package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Skeleton extends Actor {
    private final double moveChance = 0.5;

    public Skeleton(Cell cell) {
        super(cell);
        this.setIsEnemy();
        this.setDmg(2);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    @Override
    public void onRefresh() {
        int[][] offsets = { {-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        boolean attackDone = false;
        for (int[] offset : offsets) {
            Actor target = getCell().getNeighbour(offset[0], offset[1]).getActor();
            if (target != null && target.getClass() == Player.class) {
                attackDone = true;
                attack(offset[0], offset[1]);
                break;
            }
        }
        if (!attackDone) {
            if (Math.random() < moveChance) return;
            int[] targetOffset = offsets[(int)(Math.random() * 4)];
            Cell target = getCell().getNeighbour(targetOffset[0], targetOffset[1]);
            if (target.getActor() == null)
                move(targetOffset[0], targetOffset[1], CellType.FLOOR);
        }
    }
}
