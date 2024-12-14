import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class PostfixGraph3DPlotter extends JPanel {
    private final String postfixExpression;

    public PostfixGraph3DPlotter(String postfixExpression) {
        this.postfixExpression = postfixExpression;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set rendering hints for smoother graphics
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw axes (in 3D space, z-axis is simulated)
        g.setColor(Color.BLACK);
        g.drawLine(50, 250, 450, 250); // X-axis (Horizontal line)
        g.drawLine(250, 50, 250, 450); // Y-axis (Vertical line)
        g.drawLine(250, 250, 450, 450); // Simulated Z-axis (Diagonal line)

        // Draw axis labels for reference
        g.setColor(Color.RED);
        g.drawString("Y", 240, 60); // Y-axis label
        g.drawString("X", 440, 240); // X-axis label
        g.drawString("Z", 450, 450); // Z-axis label

        // Check if the expression contains 'x' or 'y'
        if (!postfixExpression.contains("x") && !postfixExpression.contains("y")) {
            g.drawString("No variables 'x' or 'y' found in the expression. Cannot plot graph.", 100, 200);
            return;
        }

        // Plot the graph in a 3D-like manner
        g.setColor(Color.BLUE);

        // Loop through x and y range (e.g., -10 to 10 for both x and y)
        for (int px = -100; px < 100; px++) {
            for (int py = -100; py < 100; py++) {
                // Convert pixel to corresponding x and y values
                double x = px / 10.0; // Scaling factor for x
                double y = py / 10.0; // Scaling factor for y
                double z = evaluatePostfix(postfixExpression, x, y);

                // Project 3D point onto 2D screen, scaling z appropriately
                int plotX = 250 + px; // X position on the window (centered at 250)
                int plotY = 250 - py; // Y position on the window (centered at 250)
                int plotZ = (int) (z * 10); // Scaling factor for Z-axis values

                // Adjust the 2D projection of 3D points based on z-axis
                // The z value is now used to influence the offset for the points
                if (plotZ >= -50 && plotZ <= 50) { // Limit z values to prevent distortion
                    g.fillOval(plotX + plotZ / 5, plotY - plotZ / 5, 2, 2); // Apply Z-depth
                }
            }
        }
    }

    // Function to evaluate a postfix expression for a given x and y value
    public static double evaluatePostfix(String postfix, double x, double y) {
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < postfix.length(); i++) {
            char current = postfix.charAt(i);

            if (Character.isDigit(current)) {
                stack.push((double) (current - '0'));
            } else if (current == 'x') {
                stack.push(x);
            } else if (current == 'y') {
                stack.push(y);
            } else {
                double b = stack.pop();
                double a = stack.pop();

                switch (current) {
                    case '+': stack.push(a + b); break;
                    case '-': stack.push(a - b); break;
                    case '*': stack.push(a * b); break;
                    case '/': stack.push(a / b); break;
                    case '^': stack.push(Math.pow(a, b)); break;
                }
            }
        }

        return stack.pop();
    }

}
