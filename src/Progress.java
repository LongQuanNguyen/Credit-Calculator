import java.util.NoSuchElementException;
import java.util.TreeMap;

public class Progress {
    private TreeMap<String, Class> ClassMap;

    private int totalRequireCredit;

    private int seniorRequiredCredit;

    public Progress(){
        this.ClassMap = new TreeMap<>();
        this.totalRequireCredit = 0;
    }

    public boolean hasClass(String aClassName){
        return ClassMap.containsKey(aClassName.toUpperCase());
    }

    public void setTotalRequireCredit(int numOfYear){
        if(numOfYear == 3){
            this.seniorRequiredCredit = 42;
            this.totalRequireCredit = 90;
        } else if (numOfYear == 4) {
            this.seniorRequiredCredit = 66;
            this.totalRequireCredit = 120;
        } else {
            this.totalRequireCredit = 0;
        }
    }

    public void addClass(Class aClass){
        if (ClassMap.containsKey(aClass.getClassName())){
            throw new IllegalStateException(" Class " + aClass.getClassName() + " is already in the system!");
        }
        ClassMap.put(aClass.getClassName(), aClass);
    }

    public void removeClass(String aClassName){
        if (!ClassMap.containsKey(aClassName.toUpperCase())){
            throw new NoSuchElementException(aClassName +" is not in the system!");
        } else {
            ClassMap.remove(aClassName.toUpperCase());
        }
    }

    public int getTotalCredit(){
        double totalCredit = 0;
        for (String key : ClassMap.keySet()){
            totalCredit += ClassMap.get(key).getClassCredit();
        }
        return (int)totalCredit;
    }

    public int getSeniorRequiredCredit(){
        return this.seniorRequiredCredit;
    }

    public int getTotalClass(){
        int totalClass = 0;
        for (String key : ClassMap.keySet()){
            totalClass += 1;
        }
        return totalClass;
    }

    public String getProgram(){
        if (this.totalRequireCredit == 90){
            return "three-year program";
        } else if (this.totalRequireCredit == 120) {
            return "four-year program";
        } else{
            return "unspecified program";
        }
    }

    public int getTotalRequireCredit(){
        return this.totalRequireCredit;
    }

    public String progressPercentage(){
        float completedPercentage = (float)getTotalCredit() / (float)this.totalRequireCredit * 100;
        String completed = String.format("%.2f", completedPercentage);
        return "You have completed <" + completed + "%> of your degree.";
    }

    public double getTotalSeniorCredit(){
        double senCred = 0;
        for(String key : ClassMap.keySet()){
            if(ClassMap.get(key).getClassLevel() >= 200){
                senCred += ClassMap.get(key).getClassCredit();
            }
        }
        return senCred;
    }

    public String getClassList() {
        String result = "";
        for (String key : ClassMap.keySet()){
            result += "\t" + ClassMap.get(key).toString() + "\n";
        }
        return result;
    }

    public int getAverage(){
        int totalGrade = 0;
        for (String key : ClassMap.keySet()){
            totalGrade += ClassMap.get(key).getClassGrade();
        }
        int Average = (int)((double)totalGrade / (double)this.getTotalClass());
        return Average;
    }

    public String toString(){
        String result = "";
        result += "You are in a(n) " + this.getProgram() + ". Require total of <" + this.totalRequireCredit + "> credits, <" + this.seniorRequiredCredit + "> of which are senior credits (> 200 level).";
        result += "\nYour current average is <" + this.getAverage() + ">. " + this.progressPercentage();
        result += "\nYou are taken <" + getTotalClass() + "> classes resulting in <" + this.getTotalCredit() + "> credits, <" + this.getTotalSeniorCredit() + "> of which are senior credits.\nThey are:\n";
        result += this.getClassList();
        return result;
    }

    public static void main(String[] args) {
        Class c1 = new Class("Math", 225, 3, 85);
        Class c2 = new Class("Cmpt", 214, 3, 86);
        Class c3 = new Class("Cmpt", 260, 3, 87);
        Class c4 = new Class("Cmpt", 270, 3, 88);
        Progress test = new Progress();
        test.setTotalRequireCredit(3);
        test.addClass(c1);
        test.addClass(c2);
        test.addClass(c3);
        test.addClass(c4);
        System.out.println(test.toString());
        System.out.println("Has Cmpt 226? " + test.hasClass("Cmpt 226"));
        System.out.println("Has Cmpt 214? " + test.hasClass("Cmpt 214"));
        test.removeClass("Cmpt 214");
        System.out.println(test.toString());
    }
}