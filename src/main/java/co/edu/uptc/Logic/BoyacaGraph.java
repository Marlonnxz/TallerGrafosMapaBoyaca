package co.edu.uptc.Logic;

import co.edu.uptc.Model.City;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que representa un grafo de las ciudades de Boyacá, donde los vértices son las ciudades
 * y las aristas representan las carreteras entre ellas con sus distancias asociadas.
 */
public class BoyacaGraph {
    private Graph<City, DefaultWeightedEdge> graph;
    private Map<String, City> cityMap;

    /**
     * Constructor de la clase BoyacaGraph.
     * Inicializa el grafo, el mapa de ciudades y agrega las ciudades y las carreteras correspondientes.
     */
    public BoyacaGraph() {
        graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        cityMap = new HashMap<>();
        addCities();
        addRoads();
    }

    /**
     * Método que agrega las ciudades al grafo.
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
     * Método que agrega una ciudad al grafo y la mapea por su nombre.
     * @param city Objeto City que representa la ciudad a agregar.
     */
    private void addCity(City city) {
        graph.addVertex(city);
        cityMap.put(city.getName(), city);
    }

    /**
     * Método que agrega las carreteras entre las ciudades del grafo con sus respectivas distancias.
     */
    private void addRoads() {
        setRoad("Tunja", "Duitama", 50);
        setRoad("Tunja", "Sogamoso", 75);
        setRoad("Tunja", "Villa de Leyva", 38);
        setRoad("Tunja", "Chiquinquirá", 90);
        setRoad("Tunja", "Paipa", 40);
        setRoad("Tunja", "Samacá", 30);
        setRoad("Tunja", "Lago de Tota", 90);
        setRoad("Tunja", "Monguí", 90);
        setRoad("Tunja", "Cómbita", 15);
        setRoad("Cómbita", "Paipa", 15);
        setRoad("Paipa", "Duitama", 15);
        setRoad("Duitama", "Sogamoso", 25);
        setRoad("Villa de Leyva", "Sáchica", 10);
        setRoad("Sáchica", "Tunja", 28);
        setRoad("Chiquinquirá", "Saboyá", 10);
        setRoad("Saboyá", "Samacá", 20);
        setRoad("Sogamoso", "Aquitania", 40);
        setRoad("Aquitania", "Lago de Tota", 10);
        setRoad("Sogamoso", "Monguí", 15);
        setRoad("Villa de Leyva", "Sáchica", 10);
        setRoad("Sáchica", "Tunja", 28);
        setRoad("Chiquinquirá", "Saboyá", 10);
        setRoad("Saboyá", "Samacá", 20);
        setRoad("Sogamoso", "Aquitania", 40);
        setRoad("Aquitania", "Lago de Tota", 10);
        setRoad("Sogamoso", "Monguí", 15);
        setRoad("Tunja", "Tibasosa", 58);
        setRoad("Tibasosa", "Duitama", 8);
        setRoad("Tibasosa", "Nobsa", 7);
        setRoad("Nobsa", "Sogamoso", 8);
        setRoad("Duitama", "Nobsa", 15);
    }

    /**
     * Método que crea una carretera entre dos ciudades con una distancia específica.
     * @param cityName1 Nombre de la primera ciudad.
     * @param cityName2 Nombre de la segunda ciudad.
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
     * Calcula el tiempo estimado de viaje entre dos puntos según el modo de transporte.
     * @param transportMode Modo de transporte: "Bicicleta", "Automóvil", "Moto", "A Pie".
     * @param distanceKm Distancia en kilómetros a recorrer.
     * @return Tiempo estimado en minutos.
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
     * Obtiene el grafo de ciudades.
     * @return Grafo que representa las ciudades y sus carreteras.
     */
    public Graph<City, DefaultWeightedEdge> getGraph() {
        return graph;
    }

    /**
     * Obtiene el mapa de ciudades.
     * @return Mapa que relaciona el nombre de la ciudad con su objeto City correspondiente.
     */
    public Map<String, City> getCityMap() {
        return cityMap;
    }
}