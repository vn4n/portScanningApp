//class witch keeps and returns parameters for command
import java.util.List;

public class CommandParameters {
    private List<String> parameters;
    public CommandParameters(List<String> list) {

            parameters = list;
            if (list.size()<3){
                throw new UnknownCommandException("Unsupported type of command");
            }

    }

    public String getIP() {

        return parameters.get(1);
    }


    public String getPort() {

        return parameters.get(2);
    }


    public String getNumberOfThreats() {
        if (parameters.size()<4){
            return "";
        }
        return parameters.get(3);
    }
}
