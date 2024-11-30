/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author laiba
 */
public class SplashWindowController {

    // SplashWindow splashWindowView
    
    public SplashWindowController() 
    {
        // splashWindowView = new SplashWindow(getInternetSpeed());
    }
    
    public void getInternetSpeed()
    {
        String fileUrl = "https://link.testfile.org/PDF10MB";
        int fileSizeInBytes = 10 * 1024 * 1024; // 10 MB in bytes

        try {
            long startTime = System.currentTimeMillis();

            // open connection
            URL url = new URL(fileUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("Unable to connect to the server.");
                return;
            }

            // read file (simulate)
            try (BufferedInputStream in = new BufferedInputStream(connection.getInputStream())) {
                byte[] buffer = new byte[1024];
                while (in.read(buffer) != -1) {
                }
            }

            long endTime = System.currentTimeMillis();

            // calculate
            double timeTakenInSeconds = (endTime - startTime) / 1000.0;
            double speedMbps = (fileSizeInBytes / (1024.0 * 1024.0)) / timeTakenInSeconds * 8; // covert to Mbps

            System.out.printf("Download Speed: %.2f Mbps%n", speedMbps);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
    
    
}
