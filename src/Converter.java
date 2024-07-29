import java.io.File;

//Encapsulation
public abstract class Converter {
    protected String outputFileName;

    public Converter(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    //Overloading 
    public abstract void convert(File[] files);

    public void convert(File file) {
        convert(new File[] {file});
    }
}
