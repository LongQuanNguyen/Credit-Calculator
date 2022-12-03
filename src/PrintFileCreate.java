import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PrintFileCreate {
    private String fileStatus;
    private String fileName;

    PrintFileCreate(){
        this.fileStatus = "File is not created";
    }

    public void fileCreate(String aFileFame){
        this.fileName = aFileFame;
        try {
            File myFile = new File(fileName + ".txt");
            if (myFile.createNewFile()) {
                this.fileStatus ="File created: " + myFile.getName() + " at " + myFile.getAbsolutePath();
            } else {
                this.fileStatus = "File already exist";
            }
        }catch (IOException e){
            this.fileStatus = "Error: file create error occurred!";
            e.printStackTrace();
        }
    }

    public void fileWrite(String content){
        try{
            FileWriter myWriter = new FileWriter(this.fileName + ".txt");
            myWriter.write(content);
            myWriter.close();
            this.fileStatus = "Written to " + this.fileName + ".txt successfully";
        } catch (IOException e) {
            this.fileStatus = "Error: file write error occurred!";
            e.printStackTrace();
        }
    }
    public String getFileStatus(){
        return this.fileStatus;
    }

    public static void main(String[] args) {
        PrintFileCreate test = new PrintFileCreate();
        test.fileCreate("Test");
        System.out.println(test.getFileStatus());
        test.fileWrite("It's working!");
        System.out.println(test.getFileStatus());
    }
}