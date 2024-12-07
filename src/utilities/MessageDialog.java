package utilities;

import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MessageDialog {
    public static void showSuccess(String message) {
        showStyledMessage(message, "Success", "success");
    }

    public static void showFail(String message) {
        showStyledMessage(message, "Error", "error");
    }

    private static Icon createCustomIcon(String path) {
        ImageIcon icon = new ImageIcon(path);
        return new ImageIcon(icon.getImage());
    }
    private static ImageIcon createTitleBarIcon(String path) {
        return new ImageIcon(path);
    }

    public static void showStyledMessage(String message, String title, String type) {
        // Label text
        JLabel messageLabel = new JLabel(message);
        messageLabel.setFont(Styling.bodyFont);
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setBorder(new EmptyBorder(10, 10, 10, 10));

        Icon icon = null;
        if ("success".equalsIgnoreCase(type)) {
            // Add your success icon image path here
            icon = createCustomIcon("src/Images/success-icon.png");
        } else if ("error".equalsIgnoreCase(type)) {
            // Add your error icon image path here
            icon = createCustomIcon("src/Images/error-icon.png");
        }

        // Custom dialog
        JDialog dialog = new JDialog();
        dialog.setTitle(title);
        dialog.setSize(400, 150);
        dialog.setLocationRelativeTo(null);
        dialog.getContentPane().setBackground(ColorPalette.BLUE);

        // Icon and text panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(ColorPalette.BLUE);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

        // Icon
        JLabel iconLabel = new JLabel(icon);
        mainPanel.add(iconLabel, BorderLayout.WEST);
        mainPanel.add(messageLabel, BorderLayout.EAST);
        dialog.add(mainPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(ColorPalette.BLUE);
        JButton okButton = new JButton("OK");
        Styling.setButton(okButton);
        okButton.addActionListener(e -> dialog.dispose());
        buttonPanel.add(okButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setModal(true);
        dialog.setVisible(true);
    }

    public static void showConfirmation(String message, Runnable onYes, Runnable onNo) {
        // Label text
        JLabel messageLabel = new JLabel(message);
        messageLabel.setFont(Styling.bodyFont);
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Add your confirmation icon image path here
        Icon icon = createCustomIcon("src/Images/warning.png");


        // Custom dialog
        JDialog dialog = new JDialog();
        dialog.setTitle("Confirmation");
        dialog.setSize(400, 150);
        dialog.setLocationRelativeTo(null);
        dialog.getContentPane().setBackground(ColorPalette.BLUE);
        dialog.setIconImage(createTitleBarIcon("src/Images/warning.png").getImage());



        // Icon and text panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(ColorPalette.BLUE);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

        // Icon
        JLabel iconLabel = new JLabel(icon);
        mainPanel.add(iconLabel, BorderLayout.WEST);
        mainPanel.add(messageLabel, BorderLayout.EAST);
        dialog.add(mainPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(ColorPalette.BLUE);

        JButton yesButton = new JButton("Yes");
        Styling.setButton(yesButton);
        yesButton.addActionListener(e -> {
            if (onYes != null) {
                onYes.run();
            }
            dialog.dispose();
        });

        JButton noButton = new JButton("No");
        Styling.setButton(noButton);
        noButton.addActionListener(e -> {
            if (onNo != null) {
                onNo.run();
            }
            dialog.dispose();
        });

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setModal(true);
        dialog.setVisible(true);
    }
}
