import java.util.Objects;

public class Assembler {
    public static String[] assemble(String[] instruction) throws Exception {

        //make a string array to store our results
        String[] preloadString = new String[instruction.length];

        for(int i = 0; i<instruction.length; i++) {

            //split the passed instruction at the spaces
            String [] splitArray = instruction[i].split(" ");
            //halt is the instruction
            if (splitArray[0].equals("halt")) {
                preloadString[i] = "0000000000000000";
            }

            //move is the instruction
            else if (splitArray[0].equals("move")) {
                longword R1Longword = new longword();
                if(splitArray[1].length() > 2) {
                    //this is getting the storage register
                    String R1 = Character.toString(splitArray[1].charAt(1))+Character.toString(splitArray[1].charAt(2));
                    R1Longword = new longword(Integer.parseInt(R1)).leftShift(28);
                }
                else{
                    //this is getting the storage register
                    String R1 = Character.toString(splitArray[1].charAt(1));
                    R1Longword = new longword(Integer.parseInt(R1)).leftShift(28);
                }
                //this is the value that is getting stored in the register
                int moveValue = Integer.parseInt(splitArray[2]);
                longword moveLongword = new longword(moveValue).leftShift(24);

                //assign the string array value to the correct conversion
                preloadString[i] = "0001"
                        +stringBuilder(R1Longword,4)
                        +stringBuilder(moveLongword,8);
            }
            //there are 2 different interrupts 0 and 1 this catches them
            else if(splitArray[0].equals("interrupt")){
                if(Objects.equals(splitArray[1], "1")){
                    preloadString[i] = "0010000000000001";
                }
                else if(splitArray[1].equals("0")){
                    preloadString[i] = "0010000000000000";
                }

            }
            else if (splitArray[0].equals("jump")) {
                int jump_address = Integer.parseInt(splitArray[1]);
                longword jump_longword = new longword(jump_address).leftShift(20);
                preloadString[i] = "0011"+stringBuilder(jump_longword,12);
            }
            else if(splitArray[0].equals("compare")){
                longword R2Longword = new longword();
                longword R3Longword = new longword();
                String R2 ="";
                String R3 ="";
                if(splitArray[1].length() > 2 && splitArray[2].length() > 2){
                    R2 = Character.toString(splitArray[1].charAt(1)+splitArray[1].charAt(2));
                    R2Longword = new longword(Integer.parseInt(R2)).leftShift(28);
                    R3 = Character.toString(splitArray[2].charAt(1)+splitArray[1].charAt(2));
                    R3Longword = new longword(Integer.parseInt(R3)).leftShift(28);
                }
                else if(splitArray[2].length() > 2) {
                    R2 = Character.toString(splitArray[1].charAt(1));
                    R2Longword = new longword(Integer.parseInt(R2)).leftShift(28);
                    R3 = Character.toString(splitArray[2].charAt(1))+Character.toString(splitArray[2].charAt(2));
                    R3Longword = new longword(Integer.parseInt(R3)).leftShift(28);
                }
                else if(splitArray[1].length() > 2) {
                    //this is getting the storage register
                    R2 = Character.toString(splitArray[1].charAt(1))+Character.toString(splitArray[1].charAt(2));
                    R2Longword = new longword(Integer.parseInt(R2)).leftShift(28);
                    R3 = Character.toString(splitArray[2].charAt(1));
                    R3Longword = new longword(Integer.parseInt(R3)).leftShift(28);
                }
                else{
                    R2 = Character.toString(splitArray[1].charAt(1));
                    R2Longword = new longword(Integer.parseInt(R2)).leftShift(28);
                    //this is getting the storage register
                    R3 = Character.toString(splitArray[2].charAt(1));
                    R3Longword = new longword(Integer.parseInt(R3)).leftShift(28);
                }
                preloadString[i] = "01000000"
                        +stringBuilder(R2Longword,4)
                        +stringBuilder(R3Longword,4);
            }
            else if(splitArray[0].equals("iflessthan")){
                preloadString[i] = "010100" + stringBuilder(new longword(Integer.parseInt(splitArray[1])).leftShift(22),10);
            }
            else if(splitArray[0].equals("ifgreaterthan")){
                preloadString[i] = "010110" + stringBuilder(new longword(Integer.parseInt(splitArray[1])).leftShift(22),10);

            }
            else if(splitArray[0].equals("ifequal")){
                preloadString[i] = "010101" + stringBuilder(new longword(Integer.parseInt(splitArray[1])).leftShift(22),10);
            }
            else if(splitArray[0].equals("ifnotequal")){
                preloadString[i] = "010100" + stringBuilder(new longword(Integer.parseInt(splitArray[1])).leftShift(22),10);

            }

            else {
                longword R1Longword = new longword();
                longword R2Longword = new longword();
                longword R3Longword = new longword();
                if(splitArray[1].length() > 2 && splitArray[2].length() > 2 && splitArray[3].length() > 2){
                    //this is getting the first stored value register value
                    String R1 = Character.toString(splitArray[1].charAt(1)) + Character.toString(splitArray[1].charAt(2));
                    R1Longword = new longword(Integer.parseInt(R1)).leftShift(28);
                    //this is getting the second stored value register value
                    String R2 = Character.toString(splitArray[2].charAt(1))+Character.toString(splitArray[1].charAt(2));
                    R2Longword = new longword(Integer.parseInt(R2)).leftShift(28);
                    //this is getting the storage register
                    String R3 = Character.toString(splitArray[3].charAt(1))+Character.toString(splitArray[1].charAt(2));
                    R3Longword = new longword(Integer.parseInt(R3)).leftShift(28);
                }
                else if(splitArray[1].length() > 2){
                    //this is getting the first stored value register value
                    String R1 = Character.toString(splitArray[1].charAt(1)) + Character.toString(splitArray[1].charAt(2));
                    R1Longword = new longword(Integer.parseInt(R1)).leftShift(28);
                    //this is getting the second stored value register value
                    String R2 = Character.toString(splitArray[2].charAt(1));
                    R2Longword = new longword(Integer.parseInt(R2)).leftShift(28);
                    //this is getting the storage register
                    String R3 = Character.toString(splitArray[3].charAt(1));
                    R3Longword = new longword(Integer.parseInt(R3)).leftShift(28);
                } else if (splitArray[2].length() > 2) {
                    //this is getting the second stored value register value
                    String R2 = Character.toString(splitArray[2].charAt(1))+Character.toString(splitArray[2].charAt(2));
                    R2Longword = new longword(Integer.parseInt(R2)).leftShift(28);
                    //this is getting the first stored value register value
                    String R1 = Character.toString(splitArray[1].charAt(1));
                    R1Longword = new longword(Integer.parseInt(R1)).leftShift(28);
                    //this is getting the storage register
                    String R3 = Character.toString(splitArray[3].charAt(1));
                    R3Longword = new longword(Integer.parseInt(R3)).leftShift(28);
                }
                else if (splitArray[3].length() > 2){
                    //this is getting the storage register
                    String R3 = Character.toString(splitArray[3].charAt(1))+Character.toString(splitArray[3].charAt(2));
                    R3Longword = new longword(Integer.parseInt(R3)).leftShift(28);
                    //this is getting the first stored value register value
                    String R1 = Character.toString(splitArray[1].charAt(1));
                    R1Longword = new longword(Integer.parseInt(R1)).leftShift(28);
                    //this is getting the second stored value register value
                    String R2 = Character.toString(splitArray[2].charAt(1));
                    R2Longword = new longword(Integer.parseInt(R2)).leftShift(28);
                }else{
                    //this is getting the first stored value register value
                    String R1 = Character.toString(splitArray[1].charAt(1));
                    R1Longword = new longword(Integer.parseInt(R1)).leftShift(28);
                    //this is getting the second stored value register value
                    String R2 = Character.toString(splitArray[2].charAt(1));
                    R2Longword = new longword(Integer.parseInt(R2)).leftShift(28);
                    //this is getting the storage register
                    String R3 = Character.toString(splitArray[3].charAt(1));
                    R3Longword = new longword(Integer.parseInt(R3)).leftShift(28);
                }

                //this is assigning the return string to the correct op
                preloadString[i] = ALU.noOp(splitArray[0])
                        +stringBuilder(R1Longword,4)
                        +stringBuilder(R2Longword,4)
                        +stringBuilder(R3Longword,4);
            }
        }

        return preloadString;
    }
    //starts at the left side of a longword and builds a string of the specified length
    public static String stringBuilder(longword stringBuild, int length) throws Exception {
        String output = "";
        for(int i = 0; i<length; i ++){
            if(stringBuild.getBit(i).getValue()){
                output = output + '1';
            }
            else{
                output = output + '0';
            }
        }
        return output;
    }
}
