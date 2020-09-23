package business;

import model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionListGenerator {

    public List<Question> generateQuestion() {
        List<Question> result = new ArrayList<Question>();
        result.add(new Question("User_1", "What's the meaning of life?", "cannot find out :(",1));
        result.add(new Question("User_2", "How to delete system 32?", "it take too much space...",2));
        result.add(new Question("User_3", "Where is my cat?","just lost it...",3));
        result.add(new Question("User_4", "Why birds doesn't have arms ?","so weird...",4));
        return result;
    }

    public Question getQuestionById(int id){
        List<Question> result = generateQuestion();
        for(Question q : result){
            if(q.getId() == id){
                return q;
            }
        }
        return null;
    }
}
