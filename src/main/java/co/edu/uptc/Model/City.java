package co.edu.uptc.Model;

/**
 * Representa una ciudad con un nombre y coordenadas en un mapa.
 */
public class City {
    private String name;
    private int x;
    private int y;

    /**
     * Constructor que inicializa una ciudad con su nombre y posición en el mapa.
     *
     * @param name Nombre de la ciudad.
     * @param x    Coordenada X de la ciudad en el mapa.
     * @param y    Coordenada Y de la ciudad en el mapa.
     */
    public City(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    /**
     * Devuelve el nombre de la ciudad.
     *
     * @return Nombre de la ciudad.
     */
    public String getName() {
        return name;
    }

    /**
     * Devuelve la coordenada X de la ciudad en el mapa.
     *
     * @return Coordenada X de la ciudad.
     */
    public int getX() {
        return x;
    }

    /**
     * Devuelve la coordenada Y de la ciudad en el mapa.
     *
     * @return Coordenada Y de la ciudad.
     */
    public int getY() {
        return y;
    }
}
