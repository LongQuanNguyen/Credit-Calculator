public class Class {
    // class name = subject name + class level
    private String subjectName;
    private int classLevel;
    private double classCredit;

    private int classGrade;

    public Class(String name, int level, int grade, double credit){
        this.subjectName = name.toUpperCase();
        this.classLevel = level;
        this.classGrade = grade;
        this.classCredit = credit;
    }

    public String getSubjectName(){
        return this.subjectName;
    }

    public void setSubjectName(String newSubjectName){
        this.subjectName = newSubjectName.toUpperCase();
    }

    public int getClassLevel(){
        return this.classLevel;
    }

    public void setClassLevel(int newLevel) {
        this.classLevel = newLevel;
    }

    public double getClassCredit() {
        return classCredit;
    }

    public void setClassCredit(int newCredit) {
        this.classCredit = newCredit;
    }

    public int getClassGrade(){
        return this.classGrade;
    }

    public void setClassGrade(int newGrade){
        this.classGrade = newGrade;
    }

    public String getClassName(){
        return this.subjectName + " " + this.classLevel;
    }

    public String toString(){
        return "Name:  " + this.getClassName() + "  |  Credit:  " + this.classCredit + "  |  Grade:  " + this.classGrade;
    }

    /**
     * A method for testing
     */
    public static void main(String[] args){
        Class aClass = new Class("math", 225, 3, 87);
        System.out.println(aClass.toString());
        System.out.println(aClass.getSubjectName());
        System.out.println(aClass.getClassLevel());
        System.out.println(aClass.getClassCredit());
        System.out.println(aClass.getClassGrade());
        aClass.setSubjectName("cmpt");
        aClass.setClassLevel(270);
        aClass.setClassCredit(1);
        aClass.setClassGrade(90);
        System.out.println(aClass.toString());
    }
}