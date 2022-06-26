package org.techtown.policy_briefing_app;

public class Subsidy {

    public long col0;           //
    public String program_name; // 1
    public String expenditure;  // 2
    public long budget;         // 3
    public String ministry;     // 4

    /** getter */
    public String getProgram_name() {
        return program_name;
    }
    public String getExpenditure() {
        return expenditure;
    }
    public long getBudget() {
        return budget;
    }
    public String getMinistry() {
        return ministry;
    }

    /** setter */
    public void setProgram_name(String program_name) {
        this.program_name = program_name;
    }
    public void setExpenditure(String expenditure) {
        this.expenditure = expenditure;
    }
    public void setBudget(long budget) {
        this.budget = budget;
    }
    public void setMinistry(String ministry) {
        this.ministry = ministry;
    }

}
