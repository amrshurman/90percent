/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordtester;

/**
 * A data structure that contains all the data on a password
 * @author Jacob
 */
public class Password {
    private PasswordCharacter[] seq = null;
    
    /**
     * Creates a new password with the given character pair sequence
     * @param sequence An array of the sequence of character pairs for this password
     */
    public Password(PasswordCharacter[] sequence) {
        this.seq = sequence;
        
        for (int i = 0; i < seq.length; i++)
            if (seq[i] == null)
                seq[i] = new PasswordCharacter(PasswordCharacter.COLORS[0], PasswordCharacter.ANIMALS[0]);
    }
    
    /**
     * Getting the character pair in this password at index i
     * @param index The index of the character pair in this password to get
     * @return The Character pair at index i of this password
     */
    public PasswordCharacter getCharacter(int index) { return seq[index]; }
    /**
     * Gets the length of this password
     * @return The length of this password
     */
    public int getLength() { return seq.length; }
    
    /**
     * Checks if this password is equal to another password
     * @param other The other password to check if this password is the same as
     * @return True if this password and the other password are equal
     */
    public boolean equals(Password other){
        if (seq.length != other.seq.length)
            return false;
        
        for (int i = 0; i < seq.length; i++)
            if (!seq[i].equals(other.seq[i]))
                return false;
        
        return true;
    }
    
    /**
     * Generates a random password of the given length
     * @param length The length of the password
     * @return A random password of the given length
     */
    public static Password createRandomPassword(int length){
        PasswordCharacter[] seq = new PasswordCharacter[length];
        for (int i = 0; i < length; i++)
            seq[i] = PasswordCharacter.createRandomCharacter();
        return new Password(seq);
    }
}
