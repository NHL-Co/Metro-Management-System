/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

/**
 *
 * @author laiba
 */
public class MessageDialog {
    public static void showSuccess(String message)
    {
        //JOptionPane.showMessageDialog(logInOptionsView, message, "Success", JOptionPane.INFORMATION_MESSAGE);
        System.out.println(message);
    }
    
    public static void showFail(String message)
    {
        //JOptionPane.showMessageDialog(parentComponent,message, "Error", JOptionPane.ERROR_MESSAGE);
        System.out.println(message);
    }
}
