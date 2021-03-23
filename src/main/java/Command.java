//interface of commands
import org.apache.commons.collections.map.MultiValueMap;

public interface Command {
     public void execute(MultiValueMap pool, String threads); //executing command (scanning for example)
}
