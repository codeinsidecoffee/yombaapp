package com.yomba.model;

public class InstructionBean {
    String instructionNo,instruction_media_type,instruction_data;

    public InstructionBean(String instructionNo, String instruction_media_type, String instruction_data) {
        this.instructionNo = instructionNo;
        this.instruction_media_type = instruction_media_type;
        this.instruction_data = instruction_data;
    }

    public String getInstructionNo() {
        return instructionNo;
    }

    public void setInstructionNo(String instructionNo) {
        this.instructionNo = instructionNo;
    }

    public String getInstruction_media_type() {
        return instruction_media_type;
    }

    public void setInstruction_media_type(String instruction_media_type) {
        this.instruction_media_type = instruction_media_type;
    }

    public String getInstruction_data() {
        return instruction_data;
    }

    public void setInstruction_data(String instruction_data) {
        this.instruction_data = instruction_data;
    }

    @Override
    public String toString() {
        return "InstructionBean{" +
                "instructionNo='" + instructionNo + '\'' +
                ", instruction_media_type='" + instruction_media_type + '\'' +
                ", instruction_data='" + instruction_data + '\'' +
                '}';
    }
}
