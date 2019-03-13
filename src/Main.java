public class Main {

    public static final int BRANCH_TAKEN = 0;
    public static final int BRANCH_NOT_TAKEN = 1;
    public static final int NORMAL_INSTRUCTION = 2;
    public static int[] program;

    public static void main(String[] args) {
        initializeProgram(1000);

        System.out.println("Instructions length = " + 1000);

        Tournament tournament = new Tournament();
        double tournamentAcc = tournament.getTournamentAccuracy(program);
        System.out.println("Tournament accuracy: " + tournamentAcc + "%");

        Correlation correlation = new Correlation(7);
        System.out.println("Correlation look-behind count = " + 7);
        double correlationAcc = correlation.getCorrelationAccuracy(program);
        System.out.println("Correlation wrong predictions = " + correlation.misses);
        System.out.println("Correlation accuracy: " + correlationAcc + "%");
    }

    private static void initializeProgram(int length) {
        program = new int[length];
        int random;
        for (int i = 0; i < length; i++) {
            random = (int) (Math.random() * 2);
            program[i] = random;
        }
    }
}
