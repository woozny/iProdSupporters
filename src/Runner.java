import java.io.IOException;

public class Runner {
    public static void main(String[] args) throws IOException {
        Lottery lottery = new Lottery();
        System.out.println(lottery.showMustGoOn());
    }
}
