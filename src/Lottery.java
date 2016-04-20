import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Lottery implements Serializable {

    private transient int teamSize;

    public ArrayList<String> getAlreadySelectedMembers() {
        return alreadySelectedMembers;
    }

    private ArrayList<String> alreadySelectedMembers;

    public Lottery() {
        teamSize = Team.getTeamMembers().length;
        try {
            if (DataHandler.deserializeLottery() != null) {
                alreadySelectedMembers = DataHandler.deserializeLottery();
            } else {
                alreadySelectedMembers = new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            alreadySelectedMembers = new ArrayList<>();
        }
    }

    private void selectMemberRandomly() {
        int randomNumber = ThreadLocalRandom.current().nextInt(teamSize);
        if (alreadySelectedMembers.contains(Team.getTeamMembers()[randomNumber])) {
            selectMemberRandomly();
        } else {
            alreadySelectedMembers.add(Team.getTeamMembers()[randomNumber]);
        }
    }

    private void clearListOfSelectedMember() {
        alreadySelectedMembers.clear();
        System.out.println("And once again!");
    }

    public String showMustGoOn() throws IOException {
        if (alreadySelectedMembers.size() == Team.getTeamMembers().length) clearListOfSelectedMember();
        selectMemberRandomly();
        DataHandler.serialize(this);
        return alreadySelectedMembers.get(alreadySelectedMembers.size() - 1);
    }

}
