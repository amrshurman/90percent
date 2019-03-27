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
 *
 * @author Jacob
 */
public class CharacterDisplay extends JPanel {
    
    private JLabel imageLabel = new JLabel();
    private PasswordCharacter character = null;
    
    public CharacterDisplay(PasswordCharacter character){
        this(character, true);
    }
    
    public CharacterDisplay(PasswordCharacter character, boolean changable){
        init(character, changable);
    }
    
    private void init(PasswordCharacter character, boolean changable){
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 1; c.gridheight = 1;
        c.gridy = 0; c.gridx = 0;
        c.weightx = 1; c.weighty = 1;
        imageLabel.setOpaque(true);
        this.add(imageLabel, c);
        c.weightx = 0; c.weighty = 0;
        
        if (changable){
            JComboBox colors = new JComboBox(PasswordCharacter.COLORS);
            c.gridy = 1; c.gridx = 0;
            this.add(colors, c);
            JComboBox animals = new JComboBox(PasswordCharacter.ANIMALS);
            c.gridy = 2; c.gridx = 0;
            this.add(animals, c);
            
            if (character != null){
                colors.setSelectedItem(character.getColor());
                animals.setSelectedItem(character.getAnimal());
            }
            
            ActionListener al = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    PasswordCharacter newChar = new PasswordCharacter((String)colors.getSelectedItem(), (String)animals.getSelectedItem());
                    setCharacter(newChar);
                }
            };
            colors.addActionListener(al);
            animals.addActionListener(al);
            this.setCharacter(character);
        } else {
            JLabel colors = new JLabel(character.getColor());
            c.gridy = 1; c.gridx = 0;
            this.add(colors, c);
            JLabel animals = new JLabel(character.getAnimal());
            c.gridy = 2; c.gridx = 0;
            this.add(animals, c);
            this.setCharacter(character);
        }
    }
    
    private void setCharacter(PasswordCharacter character){
        this.character = character;
        
        if (character == null){
            imageLabel.setIcon(null);
            imageLabel.setBackground(new Color(0, 0, 0, 0));
        } else {
            
            ImageIcon icon = null;
            try {
                icon = new ImageIcon(ImageIO.read(ClassLoader.getSystemResourceAsStream("animals/"+character.getAnimal()+".png")));
            } catch (IOException ex) {
                Logger.getLogger(CharacterDisplay.class.getName()).log(Level.SEVERE, null, ex);
            }
            //ImageIcon icon = new ImageIcon("./res/animals/"+character.getAnimal()+".png");
            //System.out.println(icon + " | " + character.getAnimal());
            imageLabel.setIcon(icon);
            imageLabel.setBackground(getCharacterColor(character));
        }
    }
    
    public PasswordCharacter getCharacter() { return character; }
    
    
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
