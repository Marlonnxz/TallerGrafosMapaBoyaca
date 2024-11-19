package co.edu.uptc.Model;

/**
 * Representa una ciudad con nombre y posición en el mapa.
 */
public class City {
    private String name;
    private int x;
    private int y;

    public City(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}