package evaluator;

import org.json.JSONException;
import org.json.JSONObject;

public class ExpressionEvaluator 
{
	private JsonEvaluator jsonEvaluator;
	private static final String AND = "AND";
	private static final String OR = "OR";
	private static final String NOT = "NOT";

    public ExpressionEvaluator(JsonEvaluator jsonEvaluator) 
    {
        this.jsonEvaluator = jsonEvaluator;
    }

    // Evaluates the expression using Json Object
    public Boolean evaluate(String expression, JSONObject jsonObject) throws JSONException 
    {
        Boolean result = false;
        
     // Using trim function to remove the space in front of the string
        if (expression.contains(AND)) 
        {
            String[] expressionArray = expression.split(AND);
            result = jsonEvaluator.evaluate(expressionArray[0].trim(), jsonObject) && jsonEvaluator.evaluate(expressionArray[1].trim(), jsonObject);
        } 
        else if (expression.contains(OR)) 
        {
            String[] expressionArray = expression.split(OR);
            result = jsonEvaluator.evaluate(expressionArray[0].trim(), jsonObject) || jsonEvaluator.evaluate(expressionArray[1].trim(), jsonObject);
        } 
        else if (expression.contains(NOT)) 
        {
            String[] expressionArray = expression.split(NOT);
            result = !jsonEvaluator.evaluate(expressionArray[1].trim(), jsonObject);
        } 
        else 
        {
            result = jsonEvaluator.evaluate(expression.trim(), jsonObject);
        }
        return result;
    }
}
