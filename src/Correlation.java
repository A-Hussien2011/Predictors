public class Correlation {
    public int misses = 0;
    private int lookBehindCount;

    public Correlation(int lookBehindCount) {
        this.lookBehindCount = lookBehindCount;
    }

    public double getCorrelationAccuracy(int[] program) {

        CorrelatingPredictor predictor = new CorrelatingPredictor(lookBehindCount);

        for (int i = 0; i < program.length; i++) {

            boolean isTaken = program[i] == Main.BRANCH_TAKEN;
            boolean predictedTaken = predictor.getNextPrediction(isTaken);

            if (predictedTaken != isTaken) misses++;
        }

        return (1 - ((double) misses / program.length)) * 100;
    }

}
