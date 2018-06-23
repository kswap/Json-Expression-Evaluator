package evaluator;

import org.json.JSONException;
import org.json.JSONObject;
import mapper.JsonMapper;

public class JsonEvaluator 
{
	private static final String EQUAL_TO = "==";
	private static final String EXISTS = "EXISTS";
	private JsonMapper jsonMapper;

    public JsonEvaluator(JsonMapper jsonMapper) 
    {
        this.jsonMapper = jsonMapper;
    }
    
    // Function to evaluate expression with the values mapped from Json
    public Boolean evaluate(String expression, JSONObject jsonObject) throws JSONException 
    {
    	
    	// Using trim function to remove the space in front of the string
    	// substring(1) is used to remove $ present in the expression 
        Boolean isCorrectExpression = false;
        if (expression.contains(EQUAL_TO)) 
        {
            String[] keyValue = expression.split(EQUAL_TO);
            isCorrectExpression = jsonMapper.checkForValueInJson(jsonObject, keyValue[0].trim().substring(1), keyValue[1].trim());
        } 
        else if (expression.contains(EXISTS)) 
        {
            String[] keyValue = expression.split(EXISTS);
            isCorrectExpression = jsonMapper.checkIfValueExists(jsonObject, keyValue[1].trim().substring(1));
        } 
        else 
        {
            isCorrectExpression = Boolean.parseBoolean(expression);
        }
        return isCorrectExpression;
    }

}
