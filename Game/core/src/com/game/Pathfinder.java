package com.game;

public interface Pathfinder {
    public Path findPath(Mover mover, int sx, int sy, int tx, int ty);
}