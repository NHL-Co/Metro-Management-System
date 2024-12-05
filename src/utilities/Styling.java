/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author laiba
 */
public class Styling {
    public static Font bodyFont = new Font("Bell MT", Font.PLAIN, 14);
    public static Font headingFont = new Font("Lucida Sans", Font.PLAIN, 18);
    public static Font mainHeadingFont = new Font("Lucida Sans", Font.BOLD, 24);
    
    public static void setLabelBody(JLabel obj)
    {
        obj.setFont(bodyFont);
        
    }
    
    public static void setLabelMainHeading(JLabel obj)
    {
        obj.setFont(mainHeadingFont);
    }
    
    public static void setLabelHeading(JLabel obj)
    {
        obj.setFont(headingFont);
    }
    
    public static void setTextField(JTextField obj)
    {
        obj.setFont(bodyFont);
        
        obj.setBackground(Color.WHITE);
        obj.setForeground(new Color(105, 105, 105)); 
        obj.setPreferredSize(new Dimension(200, 30));

        obj.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(211, 211, 211), 1), // Light grey border
                BorderFactory.createEmptyBorder(3, 5, 3, 5) // Padding inside the field
        ));
        
        obj.setOpaque(true);
    }
    
    public static void setPasswordField(JPasswordField obj)
    {
        obj.setFont(bodyFont);
        
        obj.setBackground(Color.WHITE);
        obj.setForeground(new Color(105, 105, 105)); 
        obj.setPreferredSize(new Dimension(200, 30));

        obj.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(211, 211, 211), 1), // Light grey border
                BorderFactory.createEmptyBorder(3, 5, 3, 5) // Padding inside the field
        ));
        
        obj.setOpaque(true);
    }
    
}
