public class Rotor {

    
    private String rotorValues;
    private char startChar;
        
    public Rotor(String v, char c){
        this.rotorValues = new String(v);
        this.startChar = c;
        
        while(!this.rotate());
            
    }
    
    public boolean rotate(){
        char last= rotorValues.charAt(rotorValues.length()-1); // get last char
        String rest= rotorValues.substring(0, rotorValues.length() -1); // get all but last char
        rotorValues = last + rest; //combine 
        return rotorValues.charAt(0) == startChar; // check if at start char
               
    }
    

    public int indexOf(char c){
        return rotorValues.indexOf(c); // return index of char
    }

    public char charAt(int idx){
        return rotorValues.charAt(idx); // return char at index
    }
}
    
