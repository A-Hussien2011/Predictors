public class Main {

    public static final int branch_taken_instruction = 0;
    public static final int branch_notTaken_instruction = 1;
    public static final int normal_instruction = 2;
    public static int[] program;

    public static void main(String[] args) {
        initializeProgram(1000);
        Tournament tournament = new Tournament();
        Correlation correlation = new Correlation();
        double tournamentAcc = tournament.getTournamentAccuracy(program);
        System.out.println("Tournament accuracy: " + tournamentAcc);
//        double correlationAcc = correlation.getCorrelationAccuracy(program);
    }

    private static void initializeProgram(int length) {
        program = new int[length];
        int random;
        for(int i = 0; i < length; i++){
            random = (int)(Math.random() * 3);
            program[i] = random;
        }
    }
}
