/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import utilities.ColorPalette;
import utilities.Product;
import utilities.Styling;

/**
 *
 * @author laiba
 */
public class GenerateBillView extends JFrame {
    private final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    private JPanel dashboardPanel;
    private JPanel centerPanel;
    private JPanel leftPane, rightPane;
    private JPanel productsPanel; // in left pane
    private JPanel billItems; // in right pane :
    private JButton confirmBtn;
    private JCheckBox cashCheckBox;
    
    private HashMap<Product, JPanel> productCards; // track product cards
    private HashMap<String, Integer> inBill;
    
    public GenerateBillView(ArrayList<Product> products)
    {
        productCards = new HashMap<>();
        inBill = new HashMap<>();
        
        setTitle("Generate Bill");
        ImageIcon icon = new ImageIcon("src/Images/MetroLogo.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(d.width, d.height); 
        setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        
        init();
        initProducts(products);
        setVisible(true);
    }
    
    public void init()
    {
        setLayout(new BorderLayout());
        dashboardPanel = new JPanel();
        Styling.setDashboard(this, dashboardPanel, d, "Generate Bill");
        
        centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        
        // left panel
        makeLeftPanel();
        
        // right panel
        makeRightPanel();
        
        // add to frame
        add(dashboardPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPane, BorderLayout.EAST);
    }
    
    public void makeLeftPanel()
    {
        leftPane = new JPanel(new BorderLayout(1, 1));
        leftPane.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        leftPane.setBackground(Color.WHITE);
        
        JLabel productsTitle = new JLabel("Products");
        productsTitle.setHorizontalAlignment(JLabel.CENTER);
        Styling.setLabelMainHeading(productsTitle);
        
        //----------products cards
        productsPanel = new JPanel(new GridLayout(0, 4, 2, 10));
        JScrollPane productsScroll = new JScrollPane(productsPanel);
        productsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        productsScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Styling.styleScrollBar(productsScroll);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)(screenSize.width * 0.30);
        productsPanel.setPreferredSize(new Dimension(width, 1000));
        productsPanel.setBackground(Color.WHITE);
        
        leftPane.add(productsTitle, BorderLayout.NORTH);
        leftPane.add(productsScroll, BorderLayout.CENTER);
        
        centerPanel.add(leftPane, BorderLayout.CENTER);
    }
    
    public void makeRightPanel()
    {
        rightPane = new JPanel(new BorderLayout());
        rightPane.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        rightPane.setBackground(Color.WHITE);
        
        //-----------top of the bill sidebar
        JLabel billLbl = new JLabel("Bill");
        Styling.setLabelMainHeading(billLbl);
        billLbl.setHorizontalAlignment(JLabel.CENTER);
        rightPane.setPreferredSize(new Dimension(300, getHeight()));
        
        //-----------bottom of the bill sidebar
        JPanel bottomBill = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        bottomBill.setBackground(Color.WHITE);
        confirmBtn = new JButton("Confirm Bill");
        Styling.setButton(confirmBtn);
        cashCheckBox = new JCheckBox("Cash");
        cashCheckBox.setFont(Styling.bodyFont);        
        cashCheckBox.setFont(cashCheckBox.getFont().deriveFont(16));        
        bottomBill.add(cashCheckBox);
        bottomBill.add(confirmBtn);
        
        //----------------middle of the bill sidebar: bill items
        billItems = new JPanel();
        billItems.setBackground(Color.WHITE);
        billItems.setLayout(new BoxLayout(billItems, BoxLayout.Y_AXIS));
        JScrollPane billScroll = new JScrollPane(billItems);
        billScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        billScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Styling.styleScrollBar(billScroll);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)(screenSize.width * 0.50);
        billItems.setPreferredSize(new Dimension(width, 1000));
        
        rightPane.add(billLbl, BorderLayout.NORTH);
        rightPane.add(billScroll, BorderLayout.CENTER);
        rightPane.add(bottomBill, BorderLayout.SOUTH);
    }
    
    public void initProducts(ArrayList<Product> products)
    {
        for(Product p : products)
        {
            JPanel card = makeProductCard(p);
            productsPanel.add(card);
        }
    }
    
    public JPanel makeProductCard(Product product)
    {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setPreferredSize(new Dimension(100, 100)); // Adjusted size to accommodate the image
        card.setBackground(Color.WHITE);

        // Product Image
        JLabel imageLabel = new JLabel();
        ImageIcon productImage = new ImageIcon("src/Images/Products/" + product.getProductId() +".png");
        imageLabel.setIcon(new ImageIcon(productImage.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        // Product Info Panel (for ID, Name, and Prices)
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(4, 1, 0, 0));
        infoPanel.setBackground(Color.WHITE);

        // Product ID
        JLabel idLabel = new JLabel("#: " + product.getProductId());
        Styling.setLabelBody(idLabel);
        idLabel.setHorizontalAlignment(JLabel.CENTER);

        // Product Name
        JLabel nameLabel = new JLabel(product.getName());
        Styling.setLabelBody(nameLabel);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setFont(nameLabel.getFont().deriveFont(16f));
        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD));

        // Product Prices
        JLabel priceLbl = new JLabel(String.format("Unit: Rs.%.1f", product.getSalePrice()));
        JLabel priceLblCarton = new JLabel(String.format("Carton: Rs.%.1f", product.getPriceByCarton()));
        
        Styling.setLabelBody(priceLbl);
        Styling.setLabelBody(priceLblCarton);
        priceLbl.setHorizontalAlignment(JLabel.CENTER);
        priceLblCarton.setHorizontalAlignment(JLabel.CENTER);

        // Add labels to the info panel
        infoPanel.add(idLabel);
        infoPanel.add(nameLabel);
        infoPanel.add(priceLbl);
        infoPanel.add(priceLblCarton);
        
        // Add buttons to button panel
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 1));
        JButton addUnit = new JButton("Add");
        JButton addCarton = new JButton("Add Carton");
        btnPanel.setBackground(Color.WHITE);
        Styling.setButton(addUnit);
        Styling.setButton(addCarton);
        btnPanel.add(addUnit); // [0]
        btnPanel.add(addCarton); // [1]

        // Add components to the card
        card.add(imageLabel, BorderLayout.NORTH);
        card.add(infoPanel, BorderLayout.CENTER);
        card.add(btnPanel, BorderLayout.SOUTH);
        
        productCards.put(product, btnPanel);
        return card;
    }
    
    public void addProductToBill(Product p, int qty)
    {
        if (inBill.containsKey(p.getName())) {
            inBill.put(p.getName(), qty);

            updateProductLabel(p.getName(), qty, p.getSalePrice());
        } else {
            inBill.put(p.getName(), qty);

            JLabel billProdLbl = new JLabel(String.format("%s........(%d) %.1f", p.getName(), qty, p.getSalePrice()));
            Styling.setLabelBody(billProdLbl);
            billProdLbl.setFont(billProdLbl.getFont().deriveFont(14));
            billProdLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
            billItems.add(billProdLbl);
        }
        billItems.revalidate();
        billItems.repaint();
    }
    
    public void addProductCartonToBill(Product p, int qty)
    {
        if (inBill.containsKey(p.getName()) && inBill.containsKey(" - carton")) {
            inBill.put(p.getName(), qty);

            updateProductLabel(p.getName() + " - carton", qty, p.getSalePrice());
        } else {
            inBill.put(p.getName(), qty);

            JLabel billProdLbl = new JLabel(String.format("%s........(%d) %.1f", p.getName() + " - carton", qty, p.getSalePrice()));
            billProdLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
            Styling.setLabelBody(billProdLbl);
            billProdLbl.setFont(billProdLbl.getFont().deriveFont(14));
            billItems.add(billProdLbl);
           
        }
        billItems.revalidate();
        billItems.repaint();
    }

    public HashMap<Product, JPanel> getProductCards() {
        return productCards;
    }
    
    private void updateProductLabel(String productName, int qty, double price) {
        for (java.awt.Component component : billItems.getComponents()) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if (label.getText().contains(productName)) {
                    label.setText(String.format("%s........(%d) %.1f", productName, qty, price));
                    break;
                }
            }
        }
    }
    
    public void clearBill()
    {
        inBill.clear();
        billItems.removeAll();
        billItems.revalidate();
        billItems.repaint();
    }
    
    public void setConfirmBtnListener(ActionListener al)
    {
        confirmBtn.addActionListener(al);
    }

    public JCheckBox getCashCheckBox() {
        return cashCheckBox;
    }
    
    
}
