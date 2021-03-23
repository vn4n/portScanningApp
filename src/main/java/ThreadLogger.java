//class witch uses to log scan information (using multithreading)
import java.io.*;

public class ThreadLogger implements AbstractLogger {
    private Writer writer;
    @Override
    public void closeLog() {
        try {
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void openLog() {
        try {
            File file = new File(System.getProperty("user.home")+"/log.json");
            if(!file.exists()){
                if(!file.createNewFile())
                    throw new CreatingFileFailed("Creating file error");
            }
            writer = new BufferedWriter(new FileWriter(file));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void log(String pool) {
            try {
                writer.write(pool);
            }catch (IOException e){
                e.printStackTrace();
            }
    }
}
