import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class PostfixGraphPlotter extends JPanel {
    private final String postfixExpression;

    public PostfixGraphPlotter(String postfixExpression) {
        this.postfixExpression = postfixExpression;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw axes centered at (250, 250)
        g.setColor(Color.BLACK);
        g.drawLine(50, 250, 450, 250); // X-axis (Horizontal line)
        g.drawLine(250, 50, 250, 450); // Y-axis (Vertical line)

        // Draw axis labels for reference
        g.setColor(Color.RED);
        g.drawString("Y", 240, 60); // Y-axis label
        g.drawString("X", 440, 240); // X-axis label

        // Check if the expression contains 'x'
        if (!postfixExpression.contains("x")) {
            g.drawString("No variable 'x' found in the expression. Cannot plot graph.", 100, 200);
            return;
        }

        // Plot the graph
        g.setColor(Color.BLUE);

        // Plot the function for values of x in range -10 to 10 (scaled to -200 to 200 in pixels)
        for (int px = 0; px < 400; px++) {
            // Convert pixel to corresponding x value
            double x = (px - 200) / 20.0; // Scale x to range -10 to 10
            double y = evaluatePostfix(postfixExpression, x);

            // Adjust the scale of the y-axis to fit within the window
            int plotX = 250 + px; // X position on the window (centered at 250)
            int plotY = 250 - (int) (y * 20); // Adjust Y position based on the result (scaled by 20)

            if (plotY >= 50 && plotY <= 450) { // Keep within bounds
                g.fillOval(plotX, plotY, 2, 2); // Plot point
            }
        }
    }

    // Function to evaluate a postfix expression for a given x value
    public static double evaluatePostfix(String postfix, double x) {
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < postfix.length(); i++) {
            char current = postfix.charAt(i);

            if (Character.isDigit(current)) {
                stack.push((double) (current - '0'));
            } else if (current == 'x') {
                stack.push(x);
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

    public static void main(String[] args) {
        // Example postfix expression (corresponding to x^2)
        String postfix = "x2^";  // Example for x^2

        // Create and display the graph
        JFrame frame = new JFrame("Postfix Graph Plotter");
        PostfixGraphPlotter panel = new PostfixGraphPlotter(postfix);
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
