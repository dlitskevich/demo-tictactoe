package lib;

import javax.swing.*;
import java.awt.event.*;

public class RenderTTT {
    TicTacToe game;
    JFrame MainFrame;
    JButton[] cells;
    JLabel title;

    public RenderTTT() {
        game = new TicTacToe();
        MainFrame = new JFrame();
        cells = new JButton[9];
        drawGrid();
        drawControls();
        drawInfo();
        MainFrame.setSize(400, 500);// 400 width and 500 height
        MainFrame.setLayout(null);// using no layout managers
        MainFrame.setVisible(true);// making the frame visible
        MainFrame.setTitle("TicTacToe");
    }

    private void drawGrid() {
        int width = 100;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                final JButton cell = new JButton("");
                final int id = row * 3 + col;
                cell.setBounds(20 + row * width, 60 + col * width, width, width);
                cell.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!game.getFinished() && cell.getText() == "") {
                            cell.setText(game.getPlayer() == 1 ? "x" : "o");
                        }
                        game.move(id);
                        updateInfo();
                    }
                });
                cells[row * 3 + col] = cell;

                MainFrame.add(cell);

            }
        }
    }

    private void resetGrid() {
        for (int i = 0; i < cells.length; i++) {
            cells[i].setText("");
        }
    }

    private void drawControls() {
        final JButton resetBtn = new JButton("New Game");
        resetBtn.setBounds(20, 420, 100, 40);
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGrid();
                game.resetGame();
                updateInfo();

            }
        });

        MainFrame.add(resetBtn);
    }

    private void drawInfo() {
        title = new JLabel("Cross turn");
        title.setBounds(20, 20, 100, 40);
        MainFrame.add(title);
    }

    private void updateInfo() {
        String state = game.getPlayer() == 1 ? "Cross" : "Circle";
        if (game.getFinished()) {
            state += " " + "won!";
            title.setText(state);
            return;
        }
        if (game.getTurn() == 9) {
            title.setText("Draw");
            return;
        }
        state += " " + "turn";
        title.setText(state);

    }

}
