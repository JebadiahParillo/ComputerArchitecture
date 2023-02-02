public class computer {

    //bit to determine if the computer is halted or not
    private bit halt_bit;

    private memory computer_mem;

    public longword PC;

    public longword[] registers;

    public longword op1;
    public longword op2;

    public longword result;
    public longword currentInstruction = new longword(0);


    public computer() throws Exception {
        this.halt_bit = new bit(true);
        this.computer_mem = new memory();
        this.PC = new longword(0);
        this.registers = new longword[16];
        this.op1 = new longword(0);
        this.op2 = new longword(0);
    }

    public void run() throws Exception {
        while(halt_bit.getValue()){
           fetch();
           decode();
           execute();
           store();
        }
    }
    public void fetch() throws Exception {
        currentInstruction.set(computer_mem.read(PC).getSigned());
        //set the instruction and then add 2 bytes
        PC = rippleAdder.add(PC,new longword(16));
    }
    public void decode() throws Exception {
        //shift and mask to find the corresponding 4 bits for each
        //0000 0000 0000 0000 0000 xxxx 0000 0000 --> 0000 0000 0000 0000 0000 0000 0000 xxxx
        longword masked_op1 = currentInstruction.and(new longword(3840));
        //0000 0000 0000 0000 0000 0000 0000 xxxx --> 0000 0000 0000 0000 0000 0000 0000 xxxx
        longword shifted_op1 = masked_op1.rightShift(8);
        op1 = registers[shifted_op1.getSigned()];
        //0000 0000 0000 0000 0000 0000 xxxx 0000 --> 0000 0000 0000 0000 0000 0000 0000 xxxx
        longword masked_op2 = currentInstruction.and(new longword(240));
        //0000 0000 0000 0000 0000 0000 0000 xxxx
        longword shifted_op2 = masked_op2.rightShift(4);
        op2 = registers[shifted_op2.getSigned()];
    }
    public void execute() throws Exception {
        //0000 0000 0000 0000 xxxx 0000 0000 0000 --> 0000 0000 0000 0000 0000 0000 0000 xxxx
        bit b1 = currentInstruction.getBit(16);
        bit b2 = currentInstruction.getBit(17);
        bit b3 = currentInstruction.getBit(18);
        bit b4 = currentInstruction.getBit(19);
        result = ALU.doOp(b1,b2,b3,b4,op1,op2);
    }
    public void store() throws Exception {
        //0000 0000 0000 0000 0000 0000 0000 1111
        result.copy(registers[currentInstruction.and(new longword(15)).getSigned()]);
    }

}
