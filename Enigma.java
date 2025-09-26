public class Enigma {

    private String rotorInit[] = {"#GNUAHOVBIPWCJQXDKRYELSZFMT",
            "#EJOTYCHMRWAFKPUZDINSXBGLQV",
            "#BDFHJLNPRTVXZACEGIKMOQSUWY",
            "#NWDKHGXZVRIFJBLMAOPSCYUTQE",
            "#TGOWHLIFMCSZYRVXQABUPEJKND"};

    private Rotor[] rotors;

    public Enigma(int id1, int id2, int id3, String start){
    
        rotors = new Rotor[3];
        rotors[0] = new Rotor(rotorInit[id1-1], start.charAt(0)); // Inner
        rotors[1] = new Rotor(rotorInit[id2-1], start.charAt(1)); // Middle
        rotors[2] = new Rotor(rotorInit[id3-1], start.charAt(2)); // Outer
    }


    public String encrypt(String message) {
        StringBuilder out = new StringBuilder();

        for (char c : message.toCharArray()) {  // Loop over every character in the string

            int innerIndex = rotors[0].indexOf(c); // Get index of character from inner ring
            char outerChar1 = rotors[2].charAt(innerIndex); // Match to new character in outer ring

            int middleIndex = rotors[1].indexOf(outerChar1); // Get index of character from middle ring
            char finalChar = rotors[2].charAt(middleIndex); // Match to final encrypted character in the outer ring

            // Write char and rotate
            out.append(finalChar);
            rotate();
        }

        return out.toString();
    }

    public String decrypt(String message) {
        StringBuilder out = new StringBuilder();

        for (char c : message.toCharArray()) { // Loop over every character in the string

            int outerIndex1 = rotors[2].indexOf(c); // Find the index of the character on the outer ring
            char middleChar = rotors[1].charAt(outerIndex1); // Get character in the middle Ring

            int outerIndex2 = rotors[2].indexOf(middleChar); // Find the second (middleChar) index in the outer ring
            char finalChar = rotors[0].charAt(outerIndex2); // Get final unencrypted char from the inner ring

            // Write char and rotate
            out.append(finalChar);
            rotate();
        }

        return out.toString();
    }

    private void rotate(){
        if(rotors[0].rotate()){ // Rotates inner ring
            if(rotors[1].rotate()){ // Rotate middle if inner loops
                rotors[2].rotate(); // Rotate outer if middle loops
            }
        }
    }

}
