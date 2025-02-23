import java.util.ArrayList;
import java.util.List;

public class FieldRepository {

    public FieldRepository(){}

    public FieldRepository(FieldRepository other) {
        this.fields = new ArrayList<>();
        for (Field f : other.getFields()) {
            this.fields.add(new Field(f));
        }
    }

    private List<Field> fields = List.of(new Field(1, 3), new Field(2, 2), new Field(3, 3), new Field(4, 2), new Field(5, 4), new Field(6, 2), new Field(7, 3), new Field(8, 2), new Field(9, 3));

    public List<Field> getFields(){
        return fields;
    }

    public Field getField(int number){
        for (Field f : fields){
            if (f.getNumber()==number){
                return f;
            }
        }
        return null;
    }

    public void placePiece(String type, String field) {
        int number = Integer.parseInt(field);
        if (type.equals("O")){
            getField(number).setO(true);
        }
        if (type.equals("X"))
            getField(number).setX(true);
    }
    public void printFields() {
        String line = "-------";
        System.out.println(line);
        System.out.println(getLine1('a'));
        System.out.println(getLine2('a'));
        System.out.println(getLine3('a'));
        System.out.println(line);
    }

    public void printFieldsWithNumbers() {
        String line = "-------";
        System.out.println(line);
        System.out.println(getLine1('b'));
        System.out.println(getLine2('b'));
        System.out.println(getLine3('b'));
        System.out.println(line);
    }

    public String getLine1(char b){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("|");
        for (int i = 0; i < 3; i++) {
            if (fields.get(i).isX()) {
                stringBuilder.append("X|");
            } else if (fields.get(i).isO()) {
                stringBuilder.append("O|");
            } else {
                if (b=='b'){
                    stringBuilder.append(fields.get(i).getNumber());
                    stringBuilder.append("|");
                }
                else {
                    stringBuilder.append(" |");
                }
            }
        }
        return stringBuilder.toString();
    }

    public String getLine2(char b){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("|");
        for (int i = 3; i < 6; i++) {
            if (fields.get(i).isX()) {
                stringBuilder.append("X|");
            } else if (fields.get(i).isO()) {
                stringBuilder.append("O|");
            } else if (b=='b'){
                stringBuilder.append(fields.get(i).getNumber());
                stringBuilder.append("|");
            }
            else {
                stringBuilder.append(" |");
            }
        }
        return stringBuilder.toString();
    }

    public String getLine3(char b){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("|");
        for (int i = 6; i < 9; i++) {
            if (fields.get(i).isX()) {
                stringBuilder.append("X|");
            } else if (fields.get(i).isO()) {
                stringBuilder.append("O|");
            } else if (b=='b'){
                stringBuilder.append(fields.get(i).getNumber());
                stringBuilder.append("|");
            }
            else {
                stringBuilder.append(" |");
            }
        }
        return stringBuilder.toString();
    }


    public void cleanFields(){
        for (Field f : fields){
            f.setO(false);
            f.setX(false);
        }
    }



    public boolean checkNumber(String field) {
        if (field == null) {
            return false;
        }

        int number;
        try {
            number = Integer.parseInt(field);
        } catch (NumberFormatException e) {
            System.out.println("This is not a number: " + field);
            return false;
        }

        if (number > 9 || number < 1) {
            System.out.println("This field does not exist: " + field);
            return false;
        }

        return true;
    }


    public boolean checkPieces(String field){
        if (checkNumber(field)) {
        int number = Integer.parseInt(field);
        if (!getField(number).isX() && !getField(number).isO()){
            return true;
            }
        }
        System.out.println("This field already has a piece");
        return false;
    }

    public boolean checkPiecesWithOutSOUT(String field){
        if (field == null){
            return false;
        }
        if (checkNumber(field)) {
            int number = Integer.parseInt(field);
            if (!getField(number).isX() && !getField(number).isO()){
                return true;
            }
        }
        return false;
    }

    public boolean checkDraw(){
        for (Field f : fields) {
            if (!f.isX() && !f.isO()) {
                return false;
            }
        }
        return true;
    }


    public boolean checkWinnerX(){
        return (checkWinnerLine1X()||checkWinnerLine2X()||checkWinnerLine3X()||checkWinnerColumn1X()||checkWinnerColumn2X()||checkWinnerColumn3X()||checkWinnerDiagonal1X()||checkWinnerDiagonal2X());
    }

    public boolean checkWinnerO(){
        return (checkWinnerLine1O()||checkWinnerLine2O()||checkWinnerLine3O()||checkWinnerColumn1O()||checkWinnerColumn2O()||checkWinnerColumn3O()||checkWinnerDiagonal1O()||checkWinnerDiagonal2O());
    }

    public boolean checkWinnerLine1X(){
        return fields.get(0).isX() && fields.get(1).isX() && fields.get(2).isX();
    };
    public boolean checkWinnerLine2X(){
        return fields.get(3).isX() && fields.get(4).isX() && fields.get(5).isX();
    };
    public boolean checkWinnerLine3X(){
        return fields.get(6).isX() && fields.get(7).isX() && fields.get(8).isX();};
    public boolean checkWinnerColumn1X(){
        return fields.get(0).isX() && fields.get(3).isX() && fields.get(6).isX();
    }
    public boolean checkWinnerColumn2X(){
        return fields.get(1).isX() && fields.get(4).isX() && fields.get(7).isX();
    }
    public boolean checkWinnerColumn3X(){
        return fields.get(2).isX() && fields.get(5).isX() && fields.get(8).isX();
    }

    public boolean checkWinnerDiagonal1X(){
        return fields.get(0).isX() && fields.get(4).isX() && fields.get(8).isX();
    }

    public boolean checkWinnerDiagonal2X(){
        return fields.get(2).isX() && fields.get(4).isX() && fields.get(6).isX();
    }

    public boolean checkWinnerLine1O(){
        return fields.get(0).isO() && fields.get(1).isO() && fields.get(2).isO();
    };
    public boolean checkWinnerLine2O(){
        return fields.get(3).isO() && fields.get(4).isO() && fields.get(5).isO();
    };
    public boolean checkWinnerLine3O(){
        return fields.get(6).isO() && fields.get(7).isO() && fields.get(8).isO();};
    public boolean checkWinnerColumn1O(){
        return fields.get(0).isO() && fields.get(3).isO() && fields.get(6).isO();
    }
    public boolean checkWinnerColumn2O(){
        return fields.get(1).isO() && fields.get(4).isO() && fields.get(7).isO();
    }
    public boolean checkWinnerColumn3O(){
        return fields.get(2).isO() && fields.get(5).isO() && fields.get(8).isO();
    }

    public boolean checkWinnerDiagonal1O(){
        return fields.get(0).isO() && fields.get(4).isO() && fields.get(8).isO();
    }

    public boolean checkWinnerDiagonal2O(){
        return fields.get(2).isO() && fields.get(4).isO() && fields.get(6).isO();
    }
}
