public class Class {
    private String className;
    private int classLevel;
    private int classCredit;

    private int classGrade;

    public Class(String name, int level, int credit, int grade){
        this.className = name;
        this.classLevel = level;
        this.classCredit = credit;
        this.classGrade = grade;
    }

    public String getClassName(){
        return this.className;
    }

    public void setClassName(String newName){
        this.className = newName;
    }

    public int getClassLevel(){
        return this.classLevel;
    }

    public void setClassLevel(int newLevel) {
        this.classLevel = newLevel;
    }

    public int getClassCredit() {
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

    public String toString(){
        return "Name: " + this.className + " | Level: " + this.classLevel + " | Credit: " + this.classCredit + " | Grade: " + this.classGrade;
    }

    /**
     * A method for testing
     */
    public static void main(String[] args){
        Class aClass = new Class("math", 225, 3, 87);
        System.out.println(aClass.toString());
        System.out.println(aClass.getClassName());
        System.out.println(aClass.getClassLevel());
        System.out.println(aClass.getClassCredit());
        System.out.println(aClass.getClassGrade());
        aClass.setClassName("cmpt");
        aClass.setClassLevel(270);
        aClass.setClassCredit(1);
        aClass.setClassGrade(90);
        System.out.println(aClass.toString());
    }
}