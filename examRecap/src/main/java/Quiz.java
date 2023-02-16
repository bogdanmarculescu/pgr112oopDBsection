import java.util.ArrayList;

public class Quiz {
    ArrayList<Question> questions = new ArrayList<>();
    final int MAX_QUIZ_SIZE = 5;

    public boolean addQuestion(Question q){
        if(this.getSize() <= MAX_QUIZ_SIZE) {
            questions.add(q);
            return true;
        }
        return false;
    }

    public int getSize(){
        return questions.size();
    }


}
