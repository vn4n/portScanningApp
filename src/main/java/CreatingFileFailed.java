//exception witch uses to show that json file failed to create
public class CreatingFileFailed extends RuntimeException{
    public CreatingFileFailed(String message) {
        super(message);
    }
}
