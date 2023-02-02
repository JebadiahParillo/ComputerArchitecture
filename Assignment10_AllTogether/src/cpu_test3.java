public class cpu_test3 {
    static void runTests() throws Exception {

        //Creating a new computer
        computer test3 = new computer();

        String inputs[] = {"call 6","interrupt 0","halt","interrupt 1","return"};

        String[] outputs = Assembler.assemble(inputs);
        //both interrupts should be hit

        test3.preload(outputs);

        test3.run();




    }
}
