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

    public GraphPanel(Graph<City, DefaultWeightedEdge> graph) {
        this.graph = graph;
        this.highlightedPath = null;
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


        for (DefaultWeightedEdge edge : graph.edgeSet()) {
            City city1 = graph.getEdgeSource(edge);
            City city2 = graph.getEdgeTarget(edge);

            Point p1 = new Point(city1.getX(), city1.getY());
            Point p2 = new Point(city2.getX(), city2.getY());


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
            Point point = new Point(city.getX(), city.getY());

            g2.setColor(Color.BLUE);
            g2.fillOval(point.x - cityRadius / 2, point.y - cityRadius / 2, cityRadius, cityRadius);

            g2.setColor(Color.BLACK);
            g2.drawString(city.getName(), point.x - cityRadius / 2, point.y - cityRadius / 2 - 5); // Alineación ajustada
        }
    }
}