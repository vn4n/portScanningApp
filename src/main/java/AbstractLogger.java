//abstract logger witch uses to log scanning information to JSON file
public interface AbstractLogger {

    public void log(String pool); // log information from the scanner command

    public void openLog(); //configure and open I/O


    public void closeLog(); //close IO

}
