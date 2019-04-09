/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordtester;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A panel to display a single password character pair
 * @author Jacob
 */
public class CharacterDisplay extends JPanel {
    
    private JLabel imageLabel = new JLabel();
    private PasswordCharacter character = null;
    
    /**
     * Creates a panel to display the given character pair and is editable
     * @param character The initial character pair to display
     */
    public CharacterDisplay(PasswordCharacter character){
        this(character, true);
    }
    
    /**
     * Creates a panel to display the given character pair. May be editable
     * @param character The initial character pair to display
     * @param changable True if the character pair should be editable, false otherwise
     */
    public CharacterDisplay(PasswordCharacter character, boolean changable){
        init(character, changable);
    }
    
    /**
     * Initilizes the panel to display the given character pair. May be editable
     * @param character The initial character pair to display
     * @param changable True if the character pair should be editable, false otherwise
     */
    private void init(PasswordCharacter character, boolean changable){
        //Creates the layout for the panel
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 1; c.gridheight = 1;
        c.gridy = 0; c.gridx = 0;
        c.weightx = 1; c.weighty = 1;
        
        //Sets up the label to display the pair
        imageLabel.setOpaque(true);
        this.add(imageLabel, c);
        c.weightx = 0; c.weighty = 0;
        
        if (changable){
            //If the pair is editable, creates a pair of drop boxes to allow the user to change the pair
            JComboBox colors = new JComboBox(PasswordCharacter.COLORS);
            c.gridy = 1; c.gridx = 0;
            this.add(colors, c);
            JComboBox animals = new JComboBox(PasswordCharacter.ANIMALS);
            c.gridy = 2; c.gridx = 0;
            this.add(animals, c);
            
            //Setting the initial selected items in the drop boxes if there is an initial pair
            if (character != null){
                colors.setSelectedItem(character.getColor());
                animals.setSelectedItem(character.getAnimal());
            }
            
            //Creates an event listener for if the user changes either part of the pair to update the image
            ActionListener al = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    PasswordCharacter newChar = new PasswordCharacter((String)colors.getSelectedItem(), (String)animals.getSelectedItem());
                    setCharacter(newChar);
                }
            };
            colors.addActionListener(al);
            animals.addActionListener(al);
        } else {
            //If the pair in uneditable, creates a pair of labels to display what the pair is
            JLabel colors = new JLabel(character.getColor());
            c.gridy = 1; c.gridx = 0;
            this.add(colors, c);
            JLabel animals = new JLabel(character.getAnimal());
            c.gridy = 2; c.gridx = 0;
            this.add(animals, c);
        }
        
        //Sets the character
        this.setCharacter(character);
    }
    
    /**
     * Sets the character pair for this panel
     * @param character The character pair to display in this panel
     */
    private void setCharacter(PasswordCharacter character){
        this.character = character;
        
        //If there is no pair, setting the label to display them to blank
        if (character == null){
            imageLabel.setIcon(null);
            imageLabel.setBackground(new Color(0, 0, 0, 0));
        } else {
            //Loading the animal image to the pair
            ImageIcon icon = null;
            try {
                icon = new ImageIcon(ImageIO.read(ClassLoader.getSystemResourceAsStream("animals/"+character.getAnimal()+".png")));
            } catch (IOException ex) {
                Logger.getLogger(CharacterDisplay.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Setting the animal image, and backgound color of the label for the pair
            imageLabel.setIcon(icon);
            imageLabel.setBackground(getCharacterColor(character));
        }
    }
    
    /**
     * Returns the current character pair displayed by this panel
     * @return The current character pair displayed by this panel
     */
    public PasswordCharacter getCharacter() { return character; }
    
    /**
     * Gets the color that corresponds to the color of the given character pair
     * @param character The character pair to get the color for
     * @return The color for the given character pair
     */
    private static Color getCharacterColor(PasswordCharacter character){
        switch (character.getColor()){
            case PasswordCharacter.BLACK:
                return Color.BLACK;
            case PasswordCharacter.GREY:
                return Color.GRAY;
            case PasswordCharacter.WHITE:
                return Color.WHITE;
            case PasswordCharacter.RED:
                return Color.RED;
            case PasswordCharacter.BLUE:
                return Color.BLUE;
            case PasswordCharacter.YELLOW:
                return Color.YELLOW;
            case PasswordCharacter.ORANGE:
                return Color.ORANGE;
            case PasswordCharacter.GREEN:
                return Color.GREEN;
            case PasswordCharacter.PURPLE:
                return new Color(148, 0, 211);
            case PasswordCharacter.BROWN:
                return new Color(139, 69, 19);
            default:
                return new Color(0, 0, 0, 0);
        }
    }
}
