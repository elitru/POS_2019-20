package at.EliasTrummer.Uhrzeitanzeige.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DigitLabel extends JLabel{

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 800);
        DigitLabel l = new DigitLabel(-1);
        frame.add(l);
        frame.setVisible(true);
    }

    private int digit;

    public DigitLabel(int digit) {
        this.digit = digit;
        this.setBackground(new Color(51, 51, 51));
        this.setOpaque(true);
    }

    public void setDigit(int digit) {
        this.digit = digit;
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        double scaleX = this.getWidth() / 11.;
        double scaleY = this.getHeight() / 18.;

        AffineTransform atrans = new AffineTransform();
        atrans.scale(scaleX, scaleY);
        g2.transform(atrans);

        int[] segments = getSegmentsForNumber(digit);

        for (int i = 0; i < segments.length; i++) {
            g2.setColor(Color.red);
            g2.fillPolygon(getXSegment(segments[i]), getYSegment(segments[i]), getXSegment(segments[i]).length);
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(0.2f));
            g2.drawPolygon(getXSegment(segments[i]), getYSegment(segments[i]), getXSegment(segments[i]).length);
        }
    }

    private int[] getXSegment(int segment) {
        switch (segment) {

            case 0:
                return new int[]{2, 3, 8, 9, 8, 3};
            case 1:
                return new int[]{9, 10, 10, 9, 8, 8};
            case 2:
                return new int[]{9, 10, 10, 9, 8, 8};
            case 3:
                return new int[]{2, 3, 8, 9, 8, 3};
            case 4:
                return new int[]{2, 3, 3, 2, 1, 1};
            case 5:
                return new int[]{2, 3, 3, 2, 1, 1};
            case 6:
                return new int[]{2, 3, 8, 9, 8, 3};
            //oben doppelpunkt
            case -1:
                return new int[]{
                    4, 4, 7, 7
                };

            //unten doppelpunkt
            case -2:
                return new int[]{
                    4, 4, 7, 7
                };
            default:
                return null;
        }
    }

    private int[] getYSegment(int segment) {
        switch (segment) {
            case 0:
                return new int[]{2, 1, 1, 2, 3, 3};
            case 1:
                return new int[]{2, 3, 8, 9, 8, 3};
            case 2:
                return new int[]{9, 10, 15, 16, 15, 10};
            case 3:
                return new int[]{16, 17, 17, 16, 15, 15};
            case 4:
                return new int[]{9, 10, 15, 16, 15, 10};
            case 5:
                return new int[]{2, 3, 8, 9, 8, 3};
            case 6:
                return new int[]{9, 10, 10, 9, 8, 8};
            //oben doppelpunkt
            case -1:
                return new int[]{
                    4, 7, 7, 4
                };

            //unten doppelpunkt
            case -2:
                return new int[]{
                    11, 14, 14, 11
                };
            default:
                return null;

        }
    }

    private int[] getSegmentsForNumber(int n) {
        switch (n) {
            case -1:
                return new int[]{-1, -2};
            case 0:
                return new int[]{0, 1, 2, 3, 4, 5};
            case 1:
                return new int[]{1, 2};
            case 2:
                return new int[]{0, 1, 3, 4, 6};
            case 3:
                return new int[]{0, 1, 2, 3, 6};
            case 4:
                return new int[]{1, 2, 5, 6};
            case 5:
                return new int[]{0, 2, 3, 5, 6};
            case 6:
                return new int[]{0, 2, 3, 4, 5, 6};
            case 7:
                return new int[]{0, 1, 2};
            case 8:
                return new int[]{0, 1, 2, 3, 4, 5, 6};
            case 9:
                return new int[]{0, 1, 2, 3, 5, 6};
            default:
                return new int[0];
        }
    }
}

enum DigitType {
    HOUR_1,
    HOUR_2,
    MIN_1,
    MIN_2,
    SEC_1,
    SEC_2;
}
