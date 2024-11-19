package co.edu.uptc.Logic;

import co.edu.uptc.Model.City;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que representa un grafo de ciudades en el departamento de Boyacá, Colombia.
 * Permite modelar las ciudades como vértices y las carreteras entre ellas como aristas con pesos correspondientes a distancias.
 */
public class BoyacaGraph {
    private Graph<City, DefaultWeightedEdge> graph;
    private Map<String, City> cityMap;

    /**
     * Constructor que inicializa el grafo de Boyacá y carga las ciudades y carreteras predefinidas.
     */
    public BoyacaGraph() {
        graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        cityMap = new HashMap<>();
        addCities();
        addRoads();
    }

    /**
     * Agrega las ciudades predefinidas al grafo.
     */
    private void addCities() {
        addCity(new City("Tunja", 400, 100));
        addCity(new City("Duitama", 700, 200));
        addCity(new City("Sogamoso", 750, 400));
        addCity(new City("Paipa", 550, 300));
        addCity(new City("Villa de Leyva", 100, 200));
        addCity(new City("Chiquinquirá", 50, 350));
        addCity(new City("Samacá", 250, 150));
        addCity(new City("Lago de Tota", 800, 500));
        addCity(new City("Monguí", 780, 550));
        addCity(new City("Cómbita", 475, 150));
        addCity(new City("Sáchica", 200, 180));
        addCity(new City("Saboyá", 100, 300));
        addCity(new City("Aquitania", 820, 460));
        addCity(new City("Tibasosa", 650, 250));
        addCity(new City("Nobsa", 670, 300));
    }

    /**
     * Agrega una ciudad al grafo.
     *
     * @param city Objeto {@link City} que representa la ciudad a agregar.
     */
    private void addCity(City city) {
        graph.addVertex(city);
        cityMap.put(city.getName(), city);
    }

    /**
     * Agrega las carreteras (aristas) predefinidas al grafo junto con sus distancias.
     */
    private void addRoads() {
        setRoad("Tunja", "Duitama", 50);
        setRoad("Tunja", "Sogamoso", 75);
        // ... (Más carreteras)
        setRoad("Duitama", "Nobsa", 15);
    }

    /**
     * Conecta dos ciudades con una carretera y asigna una distancia.
     *
     * @param cityName1  Nombre de la primera ciudad.
     * @param cityName2  Nombre de la segunda ciudad.
     * @param distanceKm Distancia en kilómetros entre las dos ciudades.
     */
    private void setRoad(String cityName1, String cityName2, double distanceKm) {
        City city1 = cityMap.get(cityName1);
        City city2 = cityMap.get(cityName2);

        if (city1 != null && city2 != null) {
            DefaultWeightedEdge edge = graph.addEdge(city1, city2);
            if (edge != null) {
                graph.setEdgeWeight(edge, distanceKm);
            }
        }
    }

    /**
     * Calcula el tiempo estimado de viaje entre dos ciudades basado en el modo de transporte y la distancia.
     *
     * @param transportMode Modo de transporte (e.g., "Bicicleta", "Automóvil", "Moto", "A Pie").
     * @param distanceKm    Distancia en kilómetros.
     * @return Tiempo estimado de viaje en minutos.
     * @throws IllegalArgumentException Si el modo de transporte no es válido.
     */
    public double getTime(String transportMode, double distanceKm) {
        double speedKmPerH;
        switch (transportMode) {
            case "Bicicleta":
                speedKmPerH = 15;
                break;
            case "Automóvil":
                speedKmPerH = 60;
                break;
            case "Moto":
                speedKmPerH = 45;
                break;
            case "A Pie":
                speedKmPerH = 5;
                break;
            default:
                throw new IllegalArgumentException("Modo de transporte no válido: " + transportMode);
        }
        return (distanceKm / speedKmPerH) * 60;
    }

    /**
     * Devuelve el grafo de ciudades y carreteras.
     *
     * @return Grafo de tipo {@link Graph} que contiene las ciudades como vértices y las carreteras como aristas.
     */
    public Graph<City, DefaultWeightedEdge> getGraph() {
        return graph;
    }

    /**
     * Devuelve un mapa de las ciudades del grafo.
     *
     * @return Mapa de tipo {@link Map} que asocia nombres de ciudades con objetos {@link City}.
     */
    public Map<String, City> getCityMap() {
        return cityMap;
    }
}
