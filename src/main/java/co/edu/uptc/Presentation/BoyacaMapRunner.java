package co.edu.uptc.Presentation;


import co.edu.uptc.Logic.BoyacaGraph;
import co.edu.uptc.Model.City;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BoyacaMapRunner extends JFrame {
    private BoyacaGraph boyacaGraph;
    private GraphPanel graphPanel;
    private JComboBox<String> startCityComboBox, endCityComboBox, transportComboBox;

    public BoyacaMapRunner() {
        boyacaGraph = new BoyacaGraph();
        setTitle("Mapa Interactivo - Boyacá");

        graphPanel = new GraphPanel(boyacaGraph.getGraph());

        graphPanel.setPreferredSize(new Dimension(1000, 900));

        setSize(graphPanel.getPreferredSize());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLookAndFeel();

        add(graphPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        startCityComboBox = new JComboBox<>(boyacaGraph.getCityMap().keySet().toArray(new String[0]));
        endCityComboBox = new JComboBox<>(boyacaGraph.getCityMap().keySet().toArray(new String[0]));
        transportComboBox = new JComboBox<>(new String[]{"Bicicleta", "Automóvil", "Moto", "A Pie"});


        JButton calculateButton = new JButton("Calcular Ruta");
        calculateButton.setPreferredSize(new Dimension(200, 50));
        calculateButton.setFont(new Font("Arial", Font.BOLD, 16));
        calculateButton.setBackground(new Color(30, 144, 255));
        calculateButton.setForeground(Color.BLACK);
        calculateButton.setBorder(BorderFactory.createLineBorder(new Color(215, 206, 206), 2));
        calculateButton.setFocusPainted(false);
        calculateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        calculateButton.addActionListener(e -> calculateShortestPath());

        controlPanel.add(new JLabel("Origen:"));
        controlPanel.add(startCityComboBox);
        controlPanel.add(new JLabel("Destino:"));
        controlPanel.add(endCityComboBox);
        controlPanel.add(new JLabel("Modo de transporte:"));
        controlPanel.add(transportComboBox);
        controlPanel.add(calculateButton);

        add(controlPanel, BorderLayout.NORTH);
    }

    private void calculateShortestPath() {
        String startCityName = (String) startCityComboBox.getSelectedItem();
        String endCityName = (String) endCityComboBox.getSelectedItem();
        String transportMode = (String) transportComboBox.getSelectedItem();

        if (startCityName.equals(endCityName)) {
            JOptionPane.showMessageDialog(this, "Selecciona ciudades diferentes.");
            return;
        }

        City startCity = boyacaGraph.getCityMap().get(startCityName);
        City endCity = boyacaGraph.getCityMap().get(endCityName);

        Graph<City, DefaultWeightedEdge> graph = boyacaGraph.getGraph();
        DijkstraShortestPath<City, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(graph);

        var path = dijkstra.getPath(startCity, endCity);
        if (path != null) {
            List<DefaultWeightedEdge> edges = path.getEdgeList();
            graphPanel.setHighlightedPath(edges);

            StringBuilder routeBuilder = new StringBuilder();
            List<City> vertices = path.getVertexList();
            for (int i = 0; i < vertices.size(); i++) {
                routeBuilder.append(vertices.get(i).getName());
                if (i < vertices.size() - 1) {
                    routeBuilder.append(" → ");
                }
            }

            double totalTime = edges.stream()
                    .mapToDouble(edge -> boyacaGraph.getTime(transportMode, graph.getEdgeWeight(edge)))
                    .sum();

            double speedKmPerH = getSpeed(transportMode);
            String speedMessage = "Velocidad media: " + speedKmPerH + " km/h (" + transportMode + ")";

            JOptionPane.showMessageDialog(this, "Ruta más corta:\n" + routeBuilder.toString() +
                    "\nTiempo estimado: " + String.format("%.2f", totalTime) + " minutos\n" + speedMessage);
        } else {
            JOptionPane.showMessageDialog(this, "No hay conexión entre estas ciudades.");
        }
    }

    private double getSpeed(String transportMode) {
        switch (transportMode) {
            case "Bicicleta":
                return 15;
            case "Automóvil":
                return 60;
            case "Moto":
                return 45;
            case "A Pie":
                return 5;
            default:
                throw new IllegalArgumentException("Modo de transporte no válido: " + transportMode);
        }
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BoyacaMapRunner app = new BoyacaMapRunner();
            app.setVisible(true);
        });
    }
}