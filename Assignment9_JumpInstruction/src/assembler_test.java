public class assembler_test {
    public static void runTests() throws Exception {
        String[] expectedResults = {"0001000011111111","0001000100000010",
                "1110000000010010","0010000000000000","1111000000011010",
                "0010000000000001","0011000000001010","0101000000001010","0100000000001010"};
        String[] instructions = {"move R0 -1", "move R1 +2", "add R0 R1 R2",
                "interrupt 0","sub R0 R1 R10" , "interrupt 1","jump 10","iflessthan 10","compare R0 R10"};
        String[] results = Assembler.assemble(instructions);
        int cnt = 1;
        for(int i =0; i < results.length; i++){
            if(results[i].equals(expectedResults[i])){
                System.out.println("Instruction ["+i+"] was converted correctly. "+ instructions[i]+ " is "+expectedResults[i]);
                if(cnt == results.length){
                    System.out.println("Assembler Passed!");
                }
            }
            else{
                throw new Exception("Instruction " + i + " was not converted correctly. Assembler Failed.");
            }
            cnt++;
        }
    }
}
