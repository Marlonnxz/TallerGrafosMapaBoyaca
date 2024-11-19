package co.edu.uptc.Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import co.edu.uptc.Model.City;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import java.util.List;

public class GraphPanel extends JPanel {
    private Graph<City, DefaultWeightedEdge> graph;
    private List<DefaultWeightedEdge> highlightedPath;
    private int offsetX = 0;
    private int offsetY = 0;
    private int width = 0;
    private int height = 0;

    public GraphPanel(Graph<City, DefaultWeightedEdge> graph) {
        this.graph = graph;
        this.highlightedPath = null;
        calculateMapDimensions();
    }

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

    public void setHighlightedPath(List<DefaultWeightedEdge> path) {
        this.highlightedPath = path;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        calculateMapDimensions();

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

            int weight = (int) graph.getEdgeWeight(edge);
            g2.setColor(Color.BLACK);

            int x = (p1.x + p2.x) / 2;
            int y = (p1.y + p2.y) / 2;
            g2.drawString(weight + " km", x, y);
        }

        int cityRadius = 15;
        for (City city : graph.vertexSet()) {
            Point point = new Point(city.getX() + offsetX, city.getY() + offsetY);

            g2.setColor(Color.BLUE);
            g2.fillOval(point.x - cityRadius / 2, point.y - cityRadius / 2, cityRadius, cityRadius);

            g2.setColor(Color.BLACK);
            g2.drawString(city.getName(), point.x - cityRadius / 2, point.y - cityRadius / 2 - 5);
        }
    }
}