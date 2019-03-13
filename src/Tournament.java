public class Tournament {

    //2bit states
    private final int weakTaken = 0;
    private final int strongTaken = 1;
    private final int weakNotTaken = 2;
    private final int strongNotTaken = 3;
    private int state = strongTaken;
    private boolean stateBoolean = true;

    private final boolean localTableSelector = true;
    private final boolean globalTableSelector = false;
    private boolean tableSelector = localTableSelector;

    private boolean[] localTable;
    private int localTablePointer = 0;
    private boolean[] globalTable;
    private int globalTablePointer = 0;

    public Tournament() {
        //0 initial value predicting stateBoolean
        localTable = new boolean[8];
        globalTable = new boolean[8];
    }

    public double getTournamentAccuracy(int[] program){
        double wrongPredictions = 0;
        for(int i = 0; i < program.length; i++){
            if(program[i] == Main.normal_instruction) continue;
            stateBoolean = program[i] == Main.branch_taken_instruction;
            if(tableSelector == localTableSelector){
                if(localTable[localTablePointer] != stateBoolean) wrongPredictions ++;
                updateState(localTable[localTablePointer] == stateBoolean);
                localTablePointer = (localTablePointer + 1) % 8;
            } else {
                if(globalTable[globalTablePointer] == stateBoolean) wrongPredictions ++;
                updateState(globalTable[globalTablePointer] == stateBoolean);
                globalTablePointer = (globalTablePointer + 1) % 8;
            }
        }
        System.out.println("Tournament wrong predictions: " + wrongPredictions);
        return 1 - wrongPredictions/program.length;
    }

    //2-bit prediction
    public void updateState(boolean prediction){
        if(prediction){
            switch (state){
                case strongTaken:
                    state = strongTaken;
                    stateBoolean = true;
                    break;
                case weakTaken:
                    state = strongTaken;
                    stateBoolean = true;
                    break;
                case strongNotTaken:
                    state = weakNotTaken;
                    stateBoolean = false;
                    break;
                case weakNotTaken:
                    state = weakTaken;
                    stateBoolean = true;
                    if(tableSelector == localTableSelector){
                        localTable[localTablePointer] = stateBoolean;
                    } else{
                        globalTable[globalTablePointer] = stateBoolean;
                    }
                    break;
            }
        } else{
            switch (state){
                case strongTaken:
                    state = weakTaken;
                    stateBoolean = true;
                    break;
                case weakTaken:
                    state = weakNotTaken;
                    if(tableSelector == localTableSelector){
                        localTable[localTablePointer] = stateBoolean;
                    } else{
                        globalTable[globalTablePointer] = stateBoolean;
                    }
                    stateBoolean = false;
                    break;
                case strongNotTaken:
                    state = strongNotTaken;
                    stateBoolean = false;
                    break;
                case weakNotTaken:
                    state = strongNotTaken;
                    stateBoolean = false;
                    break;
            }
        }
    }
}
