/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordtester;

/**
 *
 * @author Jacob
 */
public class Password {
    private PasswordCharacter[] seq = null;
    
    public Password(PasswordCharacter[] sequence) {
        this.seq = sequence;
        
        for (int i = 0; i < seq.length; i++)
            if (seq[i] == null)
                seq[i] = new PasswordCharacter(PasswordCharacter.COLORS[0], PasswordCharacter.ANIMALS[0]);
    }
    
    public PasswordCharacter getCharacter(int index) { return seq[index]; }
    public int getLength() { return seq.length; }
    
    public boolean equals(Password other){
        if (seq.length != other.seq.length)
            return false;
        
        for (int i = 0; i < seq.length; i++)
            if (!seq[i].equals(other.seq[i]))
                return false;
        
        return true;
    }
    
    public static Password createRandomPassword(int length){
        PasswordCharacter[] seq = new PasswordCharacter[length];
        for (int i = 0; i < length; i++)
            seq[i] = PasswordCharacter.createRandomCharacter();
        return new Password(seq);
    }
}
