package utilities;

import Controllers.LogInOptionsController;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Styling {
    public static Font bodyFont = new Font("Bell MT", Font.PLAIN, 14);
    public static Font headingFont = new Font("Franklin Gothic Book", Font.PLAIN, 18);
    public static Font mainHeadingFont = new Font("Franklin Gothic Book", Font.PLAIN, 26);

    public static void setLabelBody(JLabel obj) {
        obj.setFont(bodyFont);
    }

    public static void setLabelMainHeading(JLabel obj) {
        obj.setFont(mainHeadingFont);
    }

    public static Font setLabelHeading(JLabel obj) {
        obj.setFont(headingFont);
        return null;
    }

    public static Font setTextField(JTextField obj) {
        obj.setFont(bodyFont);
        obj.setBackground(Color.WHITE);
        obj.setForeground(new Color(105, 105, 105));
        obj.setPreferredSize(new Dimension(300, 30));
        obj.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(211, 211, 211), 1), // Light grey border
                BorderFactory.createEmptyBorder(3, 5, 3, 5) // Padding inside the field
        ));
        obj.setOpaque(true);
        return null;
    }

    public static Font setPasswordField(JPasswordField obj) {
        obj.setFont(bodyFont);
        obj.setBackground(Color.WHITE);
        obj.setForeground(new Color(105, 105, 105));
        obj.setPreferredSize(new Dimension(300, 30));
        obj.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(211, 211, 211), 1), // Light grey border
                BorderFactory.createEmptyBorder(3, 5, 3, 5) // Padding inside the field
        ));
        obj.setOpaque(true);
        return null;
    }

    public static void setButton(JButton button) {
        button.setFont(headingFont);
        button.setBackground(ColorPalette.BLUE);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {

                button.setBackground(ColorPalette.HOVER_BLUE); // Change to hover color
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {

                button.setBackground(ColorPalette.BLUE); // Revert to original color
            }

        });
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    public static void setTransparentButton(JButton btn){
        btn.setContentAreaFilled(false); // Remove the content area fill
        btn.setBorderPainted(false);    // Remove the border
        btn.setFocusPainted(false);     // Remove focus indicator
        btn.setOpaque(false);
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); // Hand cursor on hover
    }

    public static void setDashboard(JFrame frame, JPanel dashboardPanel, Dimension d, String pageText){
        dashboardPanel.setBackground(ColorPalette.BLUE);
        dashboardPanel.setPreferredSize(new Dimension(d.width, 100));
        dashboardPanel.setLayout(new BorderLayout());

        //Add image in the top left corner
        JLabel logo = new JLabel(new ImageIcon(new ImageIcon("src/Images/MetroLogo.png")
                .getImage()
                .getScaledInstance(d.width / 8, d.height / 10, Image.SCALE_SMOOTH)));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        dashboardPanel.add(logo, BorderLayout.WEST);

        JLabel logout = new JLabel(new ImageIcon(new ImageIcon("src/Images/logout.png")
                .getImage()
                .getScaledInstance(d.width/35, d.height/21, Image.SCALE_SMOOTH)));
        logout.setAlignmentX(Component.CENTER_ALIGNMENT);
        logout.setBorder(BorderFactory.createEmptyBorder(0,0,0,20));
        JButton logoutbtn = new JButton(logout.getIcon());
        Styling.setTransparentButton(logoutbtn);
        dashboardPanel.add(logoutbtn, BorderLayout.EAST);

        logoutbtn.addActionListener(e -> {
            // Show a confirmation dialog
            MessageDialog.showConfirmation(
                    "Are you sure you want to log out?",
                    () -> {
                        new LogInOptionsController();
                        frame.dispose();
                    },
                    () -> {

                    }
            );
        });

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.setBackground(ColorPalette.BLUE);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(30,0,0,40));

        JLabel pageName = new JLabel(pageText);
        setLabelMainHeading(pageName);
        pageName.setForeground(ColorPalette.YELLOW);
        pageName.setAlignmentX(Component.CENTER_ALIGNMENT);

        titlePanel.add(pageName);
        dashboardPanel.add(titlePanel, BorderLayout.CENTER);
    }

    public static void setBigButton(RoundedButton btn) {
        btn.setBackground(ColorPalette.BLUE); // Set button background to blue
        btn.setForeground(Color.WHITE); // Set text color to white
        btn.setPreferredSize(new Dimension(250, 250)); // Set button size

        btn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Set hand cursor
        btn.setHorizontalTextPosition(SwingConstants.CENTER); // Center text horizontally
        btn.setVerticalTextPosition(SwingConstants.BOTTOM); // Place text below the icon

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(ColorPalette.HOVER_BLUE); // Change to hover color
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(ColorPalette.BLUE); // Revert to original color
            }
        });
    }

    public static void setComboBox(JComboBox<String> comboBox) {
        comboBox.setFont(bodyFont); // Set font
        comboBox.setForeground(Color.BLACK); // Text color
        comboBox.setBackground(Color.WHITE); // Background color
        comboBox.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // Border styling
        comboBox.setPreferredSize(new Dimension(200, 30)); // Size
    }
}

