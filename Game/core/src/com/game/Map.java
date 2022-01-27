public interface Map {
    public int getWidthInTiles();
    public int getHeightInTiles();
    public boolean blocked(Ship ship, int x, int y);
    public float getCost(Ship ship, int sx, int sy, int tx, int ty);
    public void pathFinderVisited(int x, int y);
}