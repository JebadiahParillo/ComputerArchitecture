public class main {
    public static void main(String args[])throws Exception{
        System.out.println("Bit testing");
        System.out.println("__________________________");
        bit_test.runTests();
        System.out.println("__________________________");
        System.out.println();
        System.out.println("Longword testing");
        System.out.println("____________________________________________");
        longword_test.runTests();
        System.out.println("____________________________________________");
        System.out.println();
        System.out.println("Add/Subtract testing");
        System.out.println("____________________________________________");
        rippleadder_test.runTests();
        System.out.println("____________________________________________");
        System.out.println("Multiply testing");
        System.out.println("____________________________________________");
        multiplier_test.runTests();
        System.out.println("____________________________________________");
        System.out.println("ALU testing");
        System.out.println("____________________________________________");
        ALU_test.runTests();
        System.out.println("____________________________________________");
        System.out.println("Memory testing");
        System.out.println("____________________________________________");
        memory_test.runTests();
        System.out.println("____________________________________________");
        System.out.println("Computer testing");
        System.out.println("____________________________________________");
        cpu_test.runTests();
        System.out.println("____________________________________________");
        System.out.println("Assembler testing");
        System.out.println("____________________________________________");
        assembler_test.runTests();
        System.out.println("____________________________________________");
        System.out.println("Jump testing");
        System.out.println("____________________________________________");
        cpu_test2.runTests();
        System.out.println("____________________________________________");
        System.out.println("Push/Pop/Call/Return testing");
        System.out.println("____________________________________________");
        cpu_test3.runTests();
        System.out.println("____________________________________________");

    }
}
