public class Enigma{

    private String rotorInit[] = {"#GNUAHOVBIPWCJQXDKRYELSZFMT",
        "#EJOTYCHMRWAFKPUZDINSXBGLQV",
        "#BDFHJLNPRTVXZACEGIKMOQSUWY",
        "#NWDKHGXZVRIFJBLMAOPSCYUTQE",
        "#TGOWHLIFMCSZYRVXQABUPEJKND"};

    private static final String ALPHA = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // fixed inner ring

    private Rotor rotors[];
        
    public Enigma(int id1, int id2, int id3, String start){

        rotors = new Rotor[3];
        rotors[0] = new Rotor(rotorInit[id1-1], start.charAt(0));
        rotors[1] = new Rotor(rotorInit[id2-1], start.charAt(1));
        rotors[2] = new Rotor(rotorInit[id3-1], start.charAt(2));
        
    }


    public String decrypt(String message){        
        StringBuilder out = new StringBuilder();
        for(int k = 0; k < message.length(); k++){
            char c = message.charAt(k);
            rotate(); // rotate rotors first
            if (c == '#') {
                out.append('#');
            } else {
                int j2 = rotors[2].indexOf(c);// map back through inner rotor
                char m2= ALPHA.charAt(j2);// map back through outer rotor
                int j1 = rotors[1].indexOf(m2);
                char m1= ALPHA.charAt(j1);
                int j0 = rotors[0].indexOf(m1);
                char outChar = ALPHA.charAt(j0);// get final char
                out.append(outChar);// append to output
            }
        }

            return out.toString();
        }


    
    public String encrypt(String message){
        StringBuilder out = new StringBuilder();
        for(int k = 0; k < message.length(); k++){
            char c = message.charAt(k);
            rotate(); // rotate rotors first
            if (c == '#') {
                out.append('#');// keep spaces as is
            } else {
                int i0 = ALPHA.indexOf(c);
                char m1= rotors[0].charAt(i0);
                int i1 = ALPHA.indexOf(m1);
                char m2= rotors[1].charAt(i1);
                int i2 = ALPHA.indexOf(m2);
                char outChar = rotors[2].charAt(i2);
                out.append(outChar);
            }
        }

            return out.toString();
    }

    
private void rotate(){
    if (rotors[2].rotate()) {      // step RIGHT rotor every letter
        if (rotors[1].rotate()) {  // carry to MIDDLE if right says true
            rotors[0].rotate();    // carry to LEFT if middle says true
        }
    }
}
    
}
