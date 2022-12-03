import java.util.NoSuchElementException;
import java.util.TreeMap;

public class Progress {
    private TreeMap<Integer, Class> ClassMap;

    private int totalRequireCredit;

    public Progress(){
        this.ClassMap = new TreeMap<>();
        this.totalRequireCredit = 0;
    }

    public boolean hasClassLevel(int aClassLevel){
        return ClassMap.containsKey(aClassLevel);
    }

    public void setTotalRequireCredit(int numOfYear){
        if(numOfYear == 3){
            this.totalRequireCredit = 90;
        } else if (numOfYear == 4) {
            this.totalRequireCredit = 120;
        } else {
            this.totalRequireCredit = 0;
        }
    }

    public void addClass(Class aClass){
        if (ClassMap.containsKey(aClass.getClassLevel())){
            throw new IllegalStateException(" Class " + aClass.getClassName() + " " + aClass.getClassLevel() + " is already in the system!");
        }
        ClassMap.put(aClass.getClassLevel(), aClass);
    }

    public void removeClass(int classDropLevel){
        if (!ClassMap.containsKey(classDropLevel)){
            throw new NoSuchElementException(" Class level " + classDropLevel +" is not in the system!");
        } else {
            ClassMap.remove(classDropLevel);
        }
    }

    public int getTotalCredit(){
        int totalCredit = 0;
        for (Integer key : ClassMap.keySet()){
            totalCredit += ClassMap.get(key).getClassCredit();
        }
        return totalCredit;
    }

    public int getTotalClass(){
        int totalClass = 0;
        for (Integer key : ClassMap.keySet()){
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

    public String progressPercentage(){
        float completedPercentage = (float)getTotalCredit() / (float)this.totalRequireCredit * 100;
        String completed = String.format("%.2f", completedPercentage);
        return "You have completed " + completed + " %";
    }

    public int getTotalSeniorCredit(){
        int senCred = 0;
        for(int key : ClassMap.keySet()){
            if(key >= 200){
                senCred += ClassMap.get(key).getClassCredit();
            }
        }
        return senCred;
    }

    public String getClassList() {
        String result = "";
        for (Integer key : ClassMap.keySet()){
            result += "\t" + ClassMap.get(key).toString() + "\n";
        }
        return result;
    }

    public int getAverage(){
        int totalGrade = 0;
        for (Integer key : ClassMap.keySet()){
            totalGrade += ClassMap.get(key).getClassGrade();
        }
        int Average = (int)((float)totalGrade / (float)this.getTotalClass());
        return Average;
    }

    public String toString(){
        String result = "";
        result += "You are in a " + this.getProgram() + ", with total <" + this.totalRequireCredit + "> required credits. Your current average is <" + this.getAverage() + ">.\n";
        result += "You are taking or have taken <" + getTotalClass() + "> classes resulting in <" + this.getTotalCredit() + "> credits, <" + this.getTotalSeniorCredit() + "> of which are senior credits.\nThey are:\n";
        result += this.getClassList();
        result += this.progressPercentage() + "\n";
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
        System.out.println("Has 226? " + test.hasClassLevel(226));
        test.removeClass(225);
        System.out.println(test.toString());
    }
}