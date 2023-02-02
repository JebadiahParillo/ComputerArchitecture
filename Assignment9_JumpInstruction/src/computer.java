public class computer {
    public int registerInterruptcnt = 0;
    //bit to determine if the computer is halted or not
    public boolean preloadTest = false;
    public boolean haltTest = false;
    public boolean registerTest = false;
    private bit halt_bit;
    private memory computer_mem;
    public longword PC;
    public longword[] registers;
    private longword op1;
    private longword op2;
    private longword op3;
    private longword result = new longword(0);
    private longword currentInstruction = new longword(0);
    private bit b1;
    private bit b2;
    private bit b3;
    private bit b4;
    private int mov_value;

    bit conditional_b0 = new bit();
    bit conditional_b1= new bit();

    bit cond_code_b0= new bit();
    bit cond_code_b1= new bit();
    int jump_value;
    int conditional_address;


    public computer() throws Exception {
        this.halt_bit = new bit(true);
        this.computer_mem = new memory(false);
        this.PC = new longword(0);
        this.registers = new longword[16];
        this.op1 = new longword(0);
        this.op2 = new longword(0);
        this.b1 = new bit(false);
        this.b2 = new bit(false);
        this.b3 = new bit(false);
        this.b4 = new bit(false);
        for(int i = 0; i < registers.length; i++){
            registers[i] = new longword(0);
        }
    }
    void setRegisters(int position, longword value) throws Exception{
        //set the value of the register at the specified position with the specified longword
        int temp = value.getSigned();
        this.registers[position].set(temp);
    }
    longword getRegisters(int position){
        return this.registers[position];
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
        //set the current instruction with the value at the index of program counter in memory
        currentInstruction.set(computer_mem.read(PC).getSigned());
        //increment the program counter(memory index) by 2
        PC = rippleAdder.add(PC,new longword(2));
    }
    public void decode() throws Exception {
        //store the 4 opcode instruction bits
        b4 = currentInstruction.getBit(3);
        b3 = currentInstruction.getBit(2);
        b2 = currentInstruction.getBit(1);
        b1 = currentInstruction.getBit(0);
        //when the instruction is not a halt or mov instruction or jump or branch
        if(!(!b1.getValue() && !b2.getValue() && !b3.getValue() && !b4.getValue())&&!(!b1.getValue()&&!b2.getValue()&&b3.getValue()&&!b4.getValue())&&!(!b1.getValue() && !b2.getValue() && b3.getValue() && b4.getValue())&&!(!b1.getValue()&&b2.getValue()&&!b3.getValue()&&!b4.getValue())){
            //mask and shift to find the specified register index
             longword op1_mask = new longword(251658240);
             longword op1_masked = currentInstruction.and(op1_mask);
             longword op1_shifted = op1_masked.rightShift(24);
             //store the value in the specified register into op1
             op1 = registers[op1_shifted.getSigned()];

            //mask and shift to find the specified register index
            longword op2_masked = currentInstruction.and(new longword(15728640));
            longword op2_shifted = op2_masked.rightShift(20);
            //store the value in the specified register into op2
            op2 = registers[op2_shifted.getSigned()];

         }
        //when the instruction is a comparison we need op3
        else if (!b1.getValue()&&b2.getValue()&&!b3.getValue()&&!b4.getValue()) {
            longword op3_masked = currentInstruction.and(new longword(983040));
            longword op3_shifted = op3_masked.rightShift(16);
            op3 = registers[op3_shifted.getSigned()];
        }
    }
    public void execute() throws Exception {
        longword compare_result = new longword();
        //changes the halt bit and stops running the computer
        if(!b1.getValue() && !b2.getValue() && !b3.getValue() && !b4.getValue()){
            halt_bit.clear();
            haltTest = true;
        }
        //determines if the instruction is a Mov
        else if(!b1.getValue() && !b2.getValue() && !b3.getValue() && b4.getValue()){
            //the value being Moved is negative
            if(currentInstruction.getBit(8).getValue())
                //reverse the bits of the negative number
                mov_value = rippleAdder.add(new longword(-256),currentInstruction.and(new longword(16711680)).rightShift(16)).getSigned();
            else{
                //store the positive Mov value
                mov_value = currentInstruction.and(new longword(16711680)).rightShift(16).getSigned();
            }
        }
        //when the instruction is a jump
        else if(!b1.getValue() && !b2.getValue() && b3.getValue() && b4.getValue()){
            jump_value = currentInstruction.and(new longword(268369920)).rightShift(16).getSigned();
        }
        //0100 compare the values in the specified register area and assign 2 bits as specified
        else if(!b1.getValue()&& b2.getValue()&&!b3.getValue()&&!b4.getValue()) {
            compare_result = rippleAdder.subtract(op2,op3);
            if(compare_result.getSigned() > 0){
                conditional_b0.set(true);
                conditional_b1.set(false);
            }
            else if(compare_result.getSigned() < 0){
                conditional_b0.set(false);
                conditional_b1.set(false);
            }
            else if (compare_result.getSigned() == 0){
                conditional_b0.set(false);
                conditional_b1.set(true);
            }
        }
        //0101 when the instruction is a conditional get the address value to add
        else if(!b1.getValue()&& b2.getValue()&&!b3.getValue()&& b4.getValue()){
            longword temp;
            cond_code_b0=currentInstruction.getBit(4);
            cond_code_b1=currentInstruction.getBit(5);

            if(currentInstruction.getBit(6).getValue()){
                temp = currentInstruction.and(new longword(16711680)).rightShift(16);
                for(int i = 0; i<24; i++){
                    temp.setBit(i,new bit(true));
                }
            }
            else {
                temp = currentInstruction.and(new longword(16711680)).rightShift(16);
            }
            conditional_address = temp.getSigned();
        }
        //The instruction is an interrupt
        else if(!b1.getValue()&&!b2.getValue()&&b3.getValue()&&!b4.getValue()){
            //this is the memory interrupt
            if (currentInstruction.getBit(15).getValue()) {
                for(int i = 0; i< 1024; i+=4) {
                    System.out.println("Computer Memory: " + i + " " + computer_mem.read(new longword(i)));
                }
                preloadTest = true;
            }
            //this is the register interrupt
            else{
                for(int i = 0; i<registers.length; i++){
                    System.out.println("Register " + i + " : "+ registers[i]);
                }
                registerTest = true;
                registerInterruptcnt++;
            }
        }
        //do the operation and store the value
        else{
            result = ALU.doOp(b1, b2, b3, b4, op1, op2);}
    }
    public void store() throws Exception {
        //make sure the computer is still running
        if(halt_bit.getValue()) {
            //when the instruction is Mov
            if (!b1.getValue() && !b2.getValue() && !b3.getValue() && b4.getValue()) {
                //mask and shift the get the correct register
                longword register_mask = currentInstruction.and(new longword(251658240));
                longword register_shift = register_mask.rightShift(24);
                //store the mov value in the specified register
                setRegisters(register_shift.getSigned(),new longword(mov_value));
            }
            //the instruction is a jump
            else if(!b1.getValue() && !b2.getValue() && b3.getValue() && b4.getValue()) {
                if(jump_value >0 && jump_value<4095) {
                    PC.set(jump_value);
                }
            }
            //if the instruction is a branch
            else if(!b1.getValue()&& b2.getValue()&&!b3.getValue()&& b4.getValue()) {
                //when the conditional bits assigned earlier and the ones in the current instruction are equal
                if (cond_code_b1.getValue() == conditional_b1.getValue() || cond_code_b0.getValue()==conditional_b0.getValue()) {
                    if(conditional_address > -512 && conditional_address < 511) {
                        PC = rippleAdder.add(PC, new longword(conditional_address));
                    }
                }
            }

            //when the instruction is an ALU op
            else {
                //mask and shift the get the correct register
                longword register_mask = currentInstruction.and(new longword(983040));
                longword register_shift = register_mask.rightShift(16);
                //store the alu result in the specified register
                setRegisters(register_shift.getSigned(),result);
            }

        }
        //reset values
        result.set(0);
        mov_value = 0;
        jump_value = 0;
        cond_code_b0= new bit();
        cond_code_b1= new bit();
    }
    void preload(String[] preloadString) throws Exception {
        for(int i = 0; i < preloadString.length; i++){
            longword mem_longword = new longword(0);
            for(int k = 15; k>=0; k--){
                if (preloadString[i].charAt(k) == '1'){
                    mem_longword.setBit(k,new bit(true));
                }
            }
            computer_mem.write(new longword(i * 2), mem_longword);
        }
    }
}
