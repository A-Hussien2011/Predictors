class CorrelatingPredictor {
    private static final int STRONGLY_TAKEN = 0b11;
    private static final int TAKEN = 0b10;
    private static final int NOT_TAKEN = 0b01;
    private static final int STRONGLY_NOT_TAKEN = 0b00;

    private int[] patternHistoryTable;
    private int globalHistoryRegister;
    private int lookBehindCount;


    public CorrelatingPredictor(int lookBehindCount) {
        this.lookBehindCount = lookBehindCount;
        this.patternHistoryTable = new int[(int) Math.pow(2, lookBehindCount)];

        for (int i = 0; i < patternHistoryTable.length; i++) {
            patternHistoryTable[i] = NOT_TAKEN;
        }
    }

    public boolean getNextPrediction(boolean isTaken) {
        globalHistoryRegister <<= 1;

        //Updates GHR and makes sure it doesn't exceed the lookBehindCount max value
        if (isTaken) globalHistoryRegister |= 1;

        globalHistoryRegister &= (int) Math.pow(2, lookBehindCount) - 1;

        int oldPrediction = patternHistoryTable[globalHistoryRegister];

        //Updates PHT
        if (isTaken) patternHistoryTable[globalHistoryRegister] = Math.min(STRONGLY_TAKEN, oldPrediction + 1);
        else patternHistoryTable[globalHistoryRegister] = Math.max(STRONGLY_NOT_TAKEN, oldPrediction - 1);

        return oldPrediction >= TAKEN;
    }

}
