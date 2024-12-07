/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author laiba
 */
public class MessageDialog {
    public static void showSuccess(String message)
    {
        showStyledMessage(message, "Success", "success");
    }
    
    public static void showFail(String message)
    {
        showStyledMessage(message, "Error", "error");
    }
    
    private static Icon createCustomIcon(String path) {
        ImageIcon icon = new ImageIcon(path);
        return new ImageIcon(icon.getImage());
    }

    public static void showStyledMessage(String message, String title, String type) {

        // label text
        JLabel messageLabel = new JLabel(message);
        messageLabel.setFont(Styling.bodyFont);
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        Icon icon = null;
        if ("success".equalsIgnoreCase(type)) {
            icon = createCustomIcon("src/Images/success-icon.png");
        } else if ("error".equalsIgnoreCase(type)) {
            icon = createCustomIcon("src/Images/error-icon.png");
        }

        // custom dialog
        JDialog dialog = new JDialog();
        dialog.setTitle(title);
        dialog.setSize(400, 150);
        dialog.setLocationRelativeTo(null);
        dialog.getContentPane().setBackground(ColorPalette.BLUE);
        
        // icon and text panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(ColorPalette.BLUE);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

        // icon
        JLabel iconLabel = new JLabel(icon);
        mainPanel.add(iconLabel, BorderLayout.WEST);
        mainPanel.add(messageLabel, BorderLayout.EAST);
        dialog.add(mainPanel, BorderLayout.CENTER);

        // button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(ColorPalette.BLUE);
        JButton okButton = new JButton("OK");
        Styling.setButton(okButton);
        okButton.addActionListener(e -> {
            dialog.dispose();
        });
        buttonPanel.add(okButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setModal(true);
        dialog.setVisible(true);
        
    }
}
