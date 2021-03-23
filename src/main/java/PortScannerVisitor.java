//class witch uses to create command's factory
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PortScannerVisitor implements Visitor {
AbstractCommandsFactory factory;
    @Override
    public AbstractCommandsFactory visit(String s) {
        List<String > commandList = new ArrayList<>();
        String [] tmp = s.split(" ");
        for (String str:tmp)
            commandList.add(str);

        factory = new ScannerCommandFactory(commandList);
        return factory;

    }
}
