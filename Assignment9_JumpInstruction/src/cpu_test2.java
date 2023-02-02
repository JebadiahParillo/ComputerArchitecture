public class cpu_test2 {
    static void runTests() throws Exception {
        computer com = new computer();
        String[] temp = {"jump 8","halt", "halt","halt",
                "move R0 10","move R1 10","compare R0 R1","ifequal 4","halt","halt","interrupt 0",
                "move R0 11","move R1 10","compare R0 R1","ifnotequal 4","halt","halt","interrupt 0",
                "move R0 11","move R1 10","compare R0 R1","ifgreaterthan 4","halt","halt","interrupt 0",
                "move R0 9","move R1 10","compare R0 R1","iflessthan 2","halt","interrupt 0","ifnotequal -6"};
        //this instruction string will test all comparisons and jumps including a negative jump and should result in 4 register interrupts and a final branch back to a halt
        com.preload(Assembler.assemble(temp));
        com.run();
        if(com.registerInterruptcnt == 4){
            System.out.println("Conditionals and Jump tests have passed the register interrupt was caught 4 times as expected.");
        }
        else{
            throw new Exception("The Conditional and Jump tests have failed the instruction set was not run correctly.");
        }
    }
}
