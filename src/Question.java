import java.util.List;
import java.util.ArrayList;

public class Question {
    private int rightAnswerIndex;
    private String questionText;
    private List<String> answersList;

    public Question() {
        this.answersList = new ArrayList<String>();
    }

    public String getQuestionText() {
        return this.questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getAnswersList() {
        return this.answersList;
    }

    public String getAnswerAtIndex(int index) {
        return this.answersList.get(index);
    }

    public void addAnswer(String answer) {
        this.answersList.add(answer);
    }

    public int getRightAnswerIndex() {
        return this.rightAnswerIndex;
    }

    public String getRightAnswerAsText() {
        return this.answersList.get(this.rightAnswerIndex);
    }

    public void setRightAnswerIndex(int index) {
        this.rightAnswerIndex = index;
    }
}