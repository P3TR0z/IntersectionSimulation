package JunctionSim;

// Simulation helper class
public final class Environment {
    public static final int SIMULATION_LENGTH = 10; // in cycles
    public static final int CYCLE_LENGTH = 60; // how long a cycle should last in ms
    public static final int NUMBER_OF_INTERSECTIONS = 5;
    public static final int CAR_SPEED = 10; // amount of time needed for one car to pass through the intersection
    public static final int CAR_DELAY = 2; // delay between each car's movement
    public static final int NORTH_WIDTH = 4; // number of vertical lanes heading North;
    public static final int SOUTH_WIDTH = 4;
    public static final int EAST_WIDTH = 4;
    public static final int WEST_WIDTH = 4; // number of horizontal lanes heading West
    public static final int VERTICAL_WIDTH = NORTH_WIDTH + SOUTH_WIDTH;
    public static final int HORIZONTAL_WIDTH = EAST_WIDTH + SOUTH_WIDTH;
    public static int VERTICAL_INITIAL_WEIGHT = 3; // initial cars on each lane
    public static int HORIZONTAL_INITIAL_WEIGHT = 10;
    public static int VERTICAL_HIGH_DENSITY_THRESHOLD = 100;
    public static int HORIZONTAL_HIGH_DENSITY_THRESHOLD = 100;
    public static int CAR_SPAWN_DELAY = 20; // amount of time needed for new cars to appear in an intersection
    public static int CAR_SPAWN_AMOUNT = 1; // amount of cars spawned on every lane of each axis, every CAR_SPAWN_DELAY ms.
                                            // up for rename

    public static void SetInitialWeight(int verticalInit, int horizontalInit)
    {
        VERTICAL_INITIAL_WEIGHT = verticalInit;
        HORIZONTAL_INITIAL_WEIGHT = horizontalInit;
    }
    // todo
    public void ShowEnvironment()
    {}
}
