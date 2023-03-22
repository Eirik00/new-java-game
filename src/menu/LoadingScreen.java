package menu;

import entity.Player;
import init.Worldgen;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class LoadingScreen {
    public void createAndShowGUI(Player sendPlayer) {
        Game game = new Game(sendPlayer);

        JFrame frame = new JFrame("Loading");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setPreferredSize(new Dimension(300, 30));
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(progressBar, gbc);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Worldgen worldgen = new Worldgen();

        SwingWorker<Void, Integer> firstWorker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws IOException, InterruptedException {

                worldgen.genWorld("tempworld", worldGenProgress -> {
                    double wgP = worldGenProgress;
                    publish((int) Math.round(80*(wgP/100)));
                });
                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                progressBar.setValue(chunks.get(chunks.size() - 1));
            }

            @Override
            protected void done() {
                // Initialize game and update progress using SwingWorker
                SwingWorker<Void, Integer> secondWorker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() throws IOException, InterruptedException {
                        game.createAndShowGUI(worldgen, this::publish);
                        return null;
                    }

                    @Override
                    protected void process(java.util.List<Integer> chunks) {
                        progressBar.setValue(chunks.get(chunks.size() - 1));
                    }

                    @Override
                    protected void done() {
                        // Close loading frame after completion
                        frame.dispose();
                    }
                };

                secondWorker.execute();
            }
        };
        firstWorker.execute();
    }
}