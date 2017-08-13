package co.bugg.housingsaver.util.json;

/**
 * Class for storing coordinates more conveniently than a string. Something like this may already exist
 * in Forge or something, but I wasn't able to find it, so whatever.
 */
public class Coordinates {

    /**
     * Constructor
     * @param x X-coordinate
     * @param y Y-coordinate
     * @param z Z-coordinate
     */
    public Coordinates(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double x;
    public double y;
    public double z;
}
