package co.edu.uptc.Model;

/**
 * Clase que representa una ciudad con su nombre y posición en el mapa.
 */
public class City {
    private String name;
    private int x;
    private int y;

    /**
     * Constructor de la clase City.
     * @param name Nombre de la ciudad.
     * @param x Coordenada X de la posición de la ciudad en el mapa.
     * @param y Coordenada Y de la posición de la ciudad en el mapa.
     */
    public City(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    /**
     * Obtiene el nombre de la ciudad.
     * @return Nombre de la ciudad.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene la coordenada X de la posición de la ciudad en el mapa.
     * @return Coordenada X de la ciudad.
     */
    public int getX() {
        return x;
    }

    /**
     * Obtiene la coordenada Y de la posición de la ciudad en el mapa.
     * @return Coordenada Y de la ciudad.
     */
    public int getY() {
        return y;
    }
}