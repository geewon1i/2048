package view;

import util.ColorMap;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;

public class GridComponent extends JComponent implements Serializable {
    private static final long serialVersionUID = -7925221370350593834L;
    private int row;
    private int col;
    private int number;
    static Font font = new Font("SansSerif", Font.BOLD, 43);

    public GridComponent(int row, int col, int gridSize) {
        this.setSize(gridSize, gridSize);
        this.row = row;
        this.col = col;
        this.number = 0;
    }

    public GridComponent(int row, int col, int number, int gridSize) {
        this.setSize(gridSize, gridSize);
        this.row = row;
        this.col = col;
        this.number = number;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.printComponents(g);
//        Graphics2D g2d = (Graphics2D) g.create();
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2d.setStroke(new BasicStroke(3));
//        g2d.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, 20, 20));
//        g2d.dispose();
        if (number ==2) {
            g.setColor(new Color(236,228,219 ));
            g.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2,20,20);
            g.setColor(ColorMap.getColor(number));
            g.setFont(font);
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            int textWidth = metrics.stringWidth(String.valueOf(number));
            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
            g.drawString(String.valueOf(number), x, y);
        }
        else if (number ==4) {
            g.setColor(new Color(236,225,203 ));
            g.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2,20,20);
            g.setColor(ColorMap.getColor(number));
            g.setFont(font);
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            int textWidth = metrics.stringWidth(String.valueOf(number));
            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
            g.drawString(String.valueOf(number), x, y);
        }
        else if (number ==8) {
            g.setColor(new Color(233,181,130 ));
            g.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2,20,20);
            g.setColor(ColorMap.getColor(number));
            g.setFont(font);
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            int textWidth = metrics.stringWidth(String.valueOf(number));
            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
            g.drawString(String.valueOf(number), x, y);
        }
        else if (number ==16) {
            g.setColor(new Color(233,154,109 ));
            g.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2,20,20);
            g.setColor(ColorMap.getColor(number));
            g.setFont(font);
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            int textWidth = metrics.stringWidth(String.valueOf(number));
            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
            g.drawString(String.valueOf(number), x, y);
        }
        else if (number ==32) {
            g.setColor(new Color(231,131,102 ));
            g.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2,20,20);
            g.setColor(ColorMap.getColor(number));
            g.setFont(font);
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            int textWidth = metrics.stringWidth(String.valueOf(number));
            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
            g.drawString(String.valueOf(number), x, y);
        }
        else if (number ==64) {
            g.setColor(new Color(229,105,71 ));
            g.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2,20,20);
            g.setColor(ColorMap.getColor(number));
            g.setFont(font);
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            int textWidth = metrics.stringWidth(String.valueOf(number));
            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
            g.drawString(String.valueOf(number), x, y);
        }
        else if (number ==128) {
            g.setColor(new Color(232,209,128 ));
            g.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2,20,20);
            g.setColor(ColorMap.getColor(number));
            g.setFont(font);
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            int textWidth = metrics.stringWidth(String.valueOf(number));
            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
            g.drawString(String.valueOf(number), x, y);
        }
        else if (number ==256) {
            g.setColor(new Color(208, 180, 87));
            g.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2,20,20);
            g.setColor(ColorMap.getColor(number));
            g.setFont(font);
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            int textWidth = metrics.stringWidth(String.valueOf(number));
            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
            g.drawString(String.valueOf(number), x, y);
        }
        else if (number ==512) {
            g.setColor(new Color(194, 158, 19));
            g.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2,20,20);
            g.setColor(ColorMap.getColor(number));
            g.setFont(font);
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            int textWidth = metrics.stringWidth(String.valueOf(number));
            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
            g.drawString(String.valueOf(number), x, y);
        }
        else if (number ==1024) {
            g.setColor(new Color(250, 197, 3));
            g.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2,20,20);
            g.setColor(ColorMap.getColor(number));
            g.setFont(font);
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            int textWidth = metrics.stringWidth(String.valueOf(number));
            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
            g.drawString(String.valueOf(number), x, y);
        }
        else if (number ==2048) {
                g.setColor(new Color(232, 128, 128));
                g.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2,20,20);
                g.setColor(ColorMap.getColor(number));
                g.setFont(font);
                FontMetrics metrics = g.getFontMetrics(g.getFont());
                int textWidth = metrics.stringWidth(String.valueOf(number));
                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
                g.drawString(String.valueOf(number), x, y);
            }
        else {
            g.setColor(new Color(202,193,181));
            g.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2,20,20);
        }
    }


    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
