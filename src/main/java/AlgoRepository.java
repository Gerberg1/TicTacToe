import java.util.ArrayList;
import java.util.List;

public class AlgoRepository {
    Algo algo = new Algo("X");

    public void setAlgoColor() {
        algo.setColor("O");

    }

    public int getValue(List<Field> fields) {
        int value = 0;
        for (Field f : fields) {
            if (algo.getColor().equalsIgnoreCase("X")) {
                {
                    if (f.isX()) {
                        value = value + f.getValue();
                    } else if (f.isO()) {
                        value = value - f.getValue();
                    }
                }
            } else {
                if (f.isO()) {
                    value = value + f.getValue();
                } else if (f.isX()) {
                    value = value - f.getValue();
                }
            }

        }
        return value;
    }


    public List<Field> getEmptyFields(FieldRepository fieldRepository) {
        List<Field> emptyFields = new ArrayList<>();

        List<Field> fields = fieldRepository.getFields();
        for (Field f : fields) {
            if (fieldRepository.checkPiecesWithOutSOUT(String.valueOf(f.getNumber()))) {
                emptyFields.add(f);
            }
        }
        return emptyFields;
    }


    public void startAlphaBeta(FieldRepository fieldRepository) {
        List<Field> emptyFields = getEmptyFields(fieldRepository);
        String type = algo.getColor();

        int bestMove = -1;
        int bestScore = Integer.MIN_VALUE;

        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        for (Field f : emptyFields) {
            FieldRepository tempRepository = new FieldRepository(fieldRepository);
            tempRepository.placePiece(type, String.valueOf(f.getNumber()));
            int score = alphaBeta(tempRepository, 9, false, alpha, beta);
            if (score > bestScore) {
                bestScore = score;
                bestMove = f.getNumber();
            }
        }
        if (bestMove != -1) {
            fieldRepository.placePiece(type, String.valueOf(bestMove));
        }
    }


    public int alphaBeta(FieldRepository fieldRepository, int depth, boolean isMaxing, int alpha, int beta) {
        List<Field> emptyFields = getEmptyFields(fieldRepository);

        if (emptyFields.isEmpty() || depth == 0) {
            return getValue(fieldRepository.getFields());
        }

        String type = algo.getColor();

        if (!isMaxing && algo.getColor().equalsIgnoreCase("X")) {
            type = "O";}
        if (!isMaxing && algo.getColor().equalsIgnoreCase("O")) {
            type = "X";
        }

        int bestScore = Integer.MIN_VALUE;
        if (!isMaxing) {
            bestScore = Integer.MAX_VALUE;
        }

        for (Field f : emptyFields) {
            FieldRepository tempRepository = new FieldRepository(fieldRepository);
            tempRepository.placePiece(type, String.valueOf(f.getNumber()));

            int score = alphaBeta(tempRepository, depth - 1, !isMaxing, alpha, beta);

            if (isMaxing) {
                bestScore = Math.max(bestScore, score);
                alpha = Math.max(alpha, bestScore);
            } else {
                bestScore = Math.min(bestScore, score);
                beta = Math.min(beta, bestScore);
            }
            if (beta <= alpha) {
                break;
            }

        }

        return bestScore;
    }


}


