package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.colorchooser.*;
 
/* Used by ColorChooserDemo2.java. */
public class C_PalettePanel extends AbstractColorChooserPanel
                         implements ActionListener {
    JToggleButton red;
    JToggleButton yellow;
    JToggleButton green;
    JToggleButton blue;
    JToggleButton orange;
    JToggleButton pink;
    JToggleButton gray;
    JToggleButton black;
 
    public void updateChooser() {
        Color color = getColorFromModel();
        if (Color.red.equals(color)) {
            red.setSelected(true);
        } else if (Color.yellow.equals(color)) {
            yellow.setSelected(true);
        } else if (Color.green.equals(color)) {
            green.setSelected(true);
        } else if (Color.blue.equals(color)) {
            blue.setSelected(true);
        } else if (Color.orange.equals(color)) {
        	orange.setSelected(true);
        } else if (Color.pink.equals(color)) {
        	pink.setSelected(true);
        } else if (Color.darkGray.equals(color)) {
        	gray.setSelected(true);
        } else if (Color.black.equals(color)) {
        	black.setSelected(true);
        }
    }
 
    protected JToggleButton createButton(String name, Border normalBorder) {
        JToggleButton colorBtn = new JToggleButton();
        colorBtn.setPreferredSize(new Dimension(40,40));
        colorBtn.setActionCommand(name);
        colorBtn.addActionListener(this);

        colorBtn.setText(name);
        colorBtn.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
        colorBtn.setHorizontalAlignment(JButton.HORIZONTAL);
        colorBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        return colorBtn;
    }
 
    protected void buildChooser() {
        setLayout(new GridLayout(8, 0));
 
        ButtonGroup boxOfCrayons = new ButtonGroup();
        Border border = BorderFactory.createEmptyBorder(4,4,4,4);
 
        red = createButton("»¡°­", border);
        red.setBackground(Color.red);
        boxOfCrayons.add(red);
        add(red);
 
        yellow = createButton("³ë¶û", border);
        yellow.setBackground(Color.yellow);
        boxOfCrayons.add(yellow);
        add(yellow);
 
        green = createButton("ÃÊ·Ï", border);
        green.setBackground(Color.green);
        boxOfCrayons.add(green);
        add(green);
 
        blue = createButton("ÆÄ¶û", border);
        blue.setBackground(Color.blue);
        blue.setForeground(Color.white);
        boxOfCrayons.add(blue);
        add(blue);
        
        orange = createButton("ÁÖÈ²", border);
        orange.setBackground(Color.orange);
        boxOfCrayons.add(orange);
        add(orange);
        
        pink = createButton("ºÐÈ«", border);
        pink.setBackground(Color.pink);
        boxOfCrayons.add(pink);
        add(pink);
        
        gray = createButton("È¸»ö", border);
        gray.setBackground(Color.darkGray);
        gray.setForeground(Color.white);
        boxOfCrayons.add(gray);
        add(gray);
        
        black = createButton("°ËÁ¤", border);
        black.setBackground(Color.black);
        black.setForeground(Color.white);
        boxOfCrayons.add(black);
        add(black);
        
        
    }
 
    /** Returns an ImageIcon, or null if the path was invalid. *//*
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = CrayonPanel.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }*/
 
    public void actionPerformed(ActionEvent e) {
        Color newColor = null;
        String command = ((JToggleButton)e.getSource()).getActionCommand();
        if ("ÃÊ·Ï".equals(command)) {
            newColor = Color.green;
        }else if ("»¡°­".equals(command)) {
            newColor = Color.red;
        }else if ("³ë¶û".equals(command)) {
            newColor = Color.yellow;
        }else if ("ÆÄ¶û".equals(command)) {
            newColor = Color.blue;
        }else if ("ÁÖÈ²".equals(command)) {
        	newColor = Color.orange;
        }else if ("ºÐÈ«".equals(command)) {
        	newColor = Color.pink;
        }else if ("È¸»ö".equals(command)) {
        	newColor = Color.darkGray;
        }else if ("°ËÁ¤".equals(command)) {
        	newColor = Color.black;
        }
        
        getColorSelectionModel().setSelectedColor(newColor);
    }
 
    public String getDisplayName() {
        return null;
    }
 
    public Icon getSmallDisplayIcon() {
        return null;
    }
 
    public Icon getLargeDisplayIcon() {
        return null;
    }
}
