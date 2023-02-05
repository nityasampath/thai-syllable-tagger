import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.StringBuilder;

public class Tagger
{
    public static void main(String[] args) throws IOException 
    {
        //creates input and output streams from passed arguments
        String input = args[0];
        String output = args[1];
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(input), "UTF-8"));
        
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), "UTF-8"));
        
        String line;
        
        while ((line = reader.readLine()) != null) {//reads from input file
            writer.write(FST.process(line)); //writes to output file
            writer.newLine();
        }

        writer.close();
    }

    static class FST
    {
        final static String V1 = "เแโใไ";
        final static String C1 = "กขฃคฅฆงจฉชซฌญฎฏฐฑฒณดตถทธนบปผฝพฟภมยรฤลฦวศษสหฬอฮ";
        final static String C2 = "รลวนม";
        final static String V2 = "ิีึืุูั็";
        final static String T  =  "\u0E48\u0E49\u0E4A\u0E4B";
        final static String V3 = "าอยว";
        final static String C3 = "งนมดบกยว";

        static public String process(String s_in)
        {
            StringBuilder ln = new StringBuilder("");

            int state = 0; //marks current state
            int i = 0; // marks current character in line
        
            //transitions to next state according to characters in line
            //each case corresponds to a state of the machine
            while (i < s_in.length()) {
                switch (state) {
                    case 0: //start state
                        if (V1.contains(s_in.charAt(i) + "")) {
                            state = 1;
                        } else if (C1.contains(s_in.charAt(i) + "")) {
                            state = 2;
                        }
                        ln.append(s_in.charAt(i));
                        i++;
                        break;
                    case 1:
                        if (C1.contains(s_in.charAt(i) + "")) {
                            state = 2;
                        }
                        ln.append(s_in.charAt(i));
                        i++;
                        break;
                    case 2: 
                        if (C2.contains(s_in.charAt(i) + "")) {
                            state = 3;
                        } else if (V2.contains(s_in.charAt(i) + "")) {
                            state = 4;
                        } else if (T.contains(s_in.charAt(i) + "")) {
                            state = 5;
                        } else if (V3.contains(s_in.charAt(i) + "")) {
                            state = 6;
                        } else if (C3.contains(s_in.charAt(i) + "")) {
                            state = 9;
                        } else if (V1.contains(s_in.charAt(i) + "")) {
                            state = 7;
                        } else if (C1.contains(s_in.charAt(i) + "")) {
                            state = 8;
                        }
                        ln.append(s_in.charAt(i));
                        i++;
                        break;
                    case 3: 
                        if (V2.contains(s_in.charAt(i) + "")) {
                            state = 4;
                        } else if (T.contains(s_in.charAt(i) + "")) {
                            state = 5;
                        } else if (V3.contains(s_in.charAt(i) + "")) {
                            state = 6;
                        } else if (C3.contains(s_in.charAt(i) + "")) {
                            state = 9;
                        }
                        ln.append(s_in.charAt(i));
                        i++;
                        break;
                    case 4: 
                        if (T.contains(s_in.charAt(i) + "")) {
                            state = 5;
                        } else if (V3.contains(s_in.charAt(i) + "")) {
                            state = 6;
                        } else if (C3.contains(s_in.charAt(i) + "")) {
                            state = 9;
                        } else if (V1.contains(s_in.charAt(i) + "")) {
                            state = 7;
                        } else if (C1.contains(s_in.charAt(i) + "")) {
                            state = 8;
                        }
                        ln.append(s_in.charAt(i));
                        i++;
                        break;
                    case 5: 

                        if (V3.contains(s_in.charAt(i) + "")) {
                            state = 6;
                        } else if (C3.contains(s_in.charAt(i) + "")) {
                            state = 9;
                        } else if (V1.contains(s_in.charAt(i) + "")) {
                            state = 7;
                        } else if (C1.contains(s_in.charAt(i) + "")) {
                            state = 8;
                        }
                        ln.append(s_in.charAt(i));
                        i++;
                        break;
                    case 6: 
                        if (C3.contains(s_in.charAt(i) + "")) {
                            state = 9;
                        } else if (V1.contains(s_in.charAt(i) + "")) {
                            state = 7;
                        } else if (C1.contains(s_in.charAt(i) + "")) {
                            state = 8;
                        }
                        ln.append(s_in.charAt(i));
                        i++;
                        break;
                    //final states
                    case 7: 
                        //adds space before previous character
                        ln.insert(ln.length() - 1, " ");
                        state = 1;
                        break;
                    case 8: 
                        //adds space before previous character
                        ln.insert(ln.length() - 1, " ");
                        state = 2;
                        break;
                    case 9: 
                        //adds space at current position
                        ln.append(" ");
                        state = 0;
                        break;
                }
            }

            String s_out = ln.toString();

            return s_out;
        }
    }

}
