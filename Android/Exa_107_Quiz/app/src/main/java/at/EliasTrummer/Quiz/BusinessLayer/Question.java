package at.EliasTrummer.Quiz.BusinessLayer;

import java.util.List;

public class Question {
    private final String question;
    private final List<String> answers;
    private final int correctAnswer;

    public Question(String question, List<String> answers, int correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
