package co.edu.uptc.Presentation;

import co.edu.uptc.Model.City;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.List;

/**
 * Panel personalizado para representar gráficamente un grafo de ciudades y rutas.
 * Permite visualizar los vértices (ciudades) y las aristas (rutas) con opciones para resaltar
 * caminos específicos.
 */
public class GraphPanel extends JPanel {
    private Graph<City, DefaultWeightedEdge> graph;
    private List<DefaultWeightedEdge> highlightedPath;
    private int offsetX = 0;
    private int offsetY = 0;
    private int width = 0;
    private int height = 0;

    /**
     * Constructor para inicializar el panel con un grafo de ciudades.
     *
     * @param graph Grafo de ciudades y rutas a representar.
     */
    public GraphPanel(Graph<City, DefaultWeightedEdge> graph) {
        this.graph = graph;
        this.highlightedPath = null;
        calculateMapDimensions();
    }

    /**
     * Calcula las dimensiones del mapa para ajustar la posición de las ciudades dentro del panel.
     */
    private void calculateMapDimensions() {
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;

        for (City city : graph.vertexSet()) {
            int x = city.getX();
            int y = city.getY();
            minX = Math.min(minX, x);
            minY = Math.min(minY, y);
            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
        }

        width = maxX - minX;
        height = maxY - minY;
        offsetX = (getWidth() - width) / 2;
        offsetY = (getHeight() - height) / 2;
    }

    /**
     * Establece un camino resaltado para ser dibujado en el grafo.
     *
     * @param path Lista de aristas que representan el camino a resaltar.
     */
    public void setHighlightedPath(List<DefaultWeightedEdge> path) {
        this.highlightedPath = path;
        repaint();
    }

    /**
     * Método sobrescrito para pintar los elementos gráficos del panel.
     *
     * @param g Objeto Graphics utilizado para dibujar.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        calculateMapDimensions();

        // Dibuja las aristas del grafo
        for (DefaultWeightedEdge edge : graph.edgeSet()) {
            City city1 = graph.getEdgeSource(edge);
            City city2 = graph.getEdgeTarget(edge);

            Point p1 = new Point(city1.getX() + offsetX, city1.getY() + offsetY);
            Point p2 = new Point(city2.getX() + offsetX, city2.getY() + offsetY);

            if (highlightedPath != null && highlightedPath.contains(edge)) {
                g2.setColor(Color.RED);
                g2.setStroke(new BasicStroke(3));
            } else {
                g2.setColor(Color.GRAY);
                g2.setStroke(new BasicStroke(1));
            }

            g2.draw(new Line2D.Double(p1, p2));

            // Etiqueta de peso en la arista
            int weight = (int) graph.getEdgeWeight(edge);
            g2.setColor(Color.BLACK);

            int x = (p1.x + p2.x) / 2;
            int y = (p1.y + p2.y) / 2;
            g2.drawString(weight + " km", x, y);
        }

        // Dibuja los vértices del grafo
        int cityRadius = 15;
        for (City city : graph.vertexSet()) {
            Point point = new Point(city.getX() + offsetX, city.getY() + offsetY);

            g2.setColor(Color.BLUE);
            g2.fillOval(point.x - cityRadius / 2, point.y - cityRadius / 2, cityRadius, cityRadius);

            // Etiqueta del nombre de la ciudad
            g2.setColor(Color.BLACK);
            g2.drawString(city.getName(), point.x - cityRadius / 2, point.y - cityRadius / 2 - 5);
        }
    }
}
