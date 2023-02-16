import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionTests {


    /***
     * The point of this method is to generate a number of questions for testing purposes
     * @param number - the number of questions to be generated
     * @return - returns an ArrayList of objects of type question
     */
    public ArrayList<Question> generateQuestions(int number){
        ArrayList<Question> questions = new ArrayList<>();

        for(int i = 0; i < number; i++){
            ArrayList<String> options = new ArrayList<>();
            for (int j = 0; j < 4; j++){
                options.add("Q" + i + ",Option:" + j);
            }
            questions.add(new MultipleChoiceQuestion("Question:" + i,
                    options,
                    "" + i % 4 //fairly dummy way of picking correct answers so that they are not all the same
            ));
        }
        return questions;
    }
    @Test
    public void basicQuestionText(){
        ArrayList<String> a1 = new ArrayList<>();
        a1.add("a1");
        a1.add("a2");
        MultipleChoiceQuestion m1 = new MultipleChoiceQuestion("q1?", a1, "0");

        MultipleChoiceQuestion m2 = new MultipleChoiceQuestion();
        m2.setQuestionText("Is this another question?");
        m2.addOption("Yes, it is");
        m2.addOption("It absolutely is");
        m2.addOption("Nyah!");
        m2.setCorrectOption("1");

        assertTrue(m1.verifyAnswer(m1.correctOption));


    }

    @Test
    public void quizSelectionTest(){
        ArrayList<Question> questions = generateQuestions(15);
        QuizManager qm = new QuizManager();
        qm.setAllQuestions(questions);

        Quiz q = qm.generateQuizSmarter();

        assertEquals(q.MAX_QUIZ_SIZE, q.getSize());
    }
}
