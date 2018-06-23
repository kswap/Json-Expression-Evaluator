package mapper;

import org.json.JSONException;
import org.json.JSONObject;
import evaluator.ExpressionEvaluator;

public class ExpressionMapper 
{
	private ExpressionEvaluator expressionEvaluator;
	private static final String OPENING_BRACKET= "(";
	private static final String CLOSING_BRACKET= ")";
	
    public ExpressionMapper(ExpressionEvaluator expressionEvaluator) 
    {
        this.expressionEvaluator = expressionEvaluator;
    }

    /* Breaks Expression into single unbreakable expression(s) , evaluates them and then evaluate the whole expression
    	for ex:
    	((XYZ == Company) AND (AIM == "Revolution"))
    	It breaks it into 2 expressions:
    	1. (XYZ == Company)
    	2. (AIM == "Revolution")
    	
    	And then evaluating whole expression:
    	(TRUE AND TRUE) 
    	which return TRUE :)
    */
    	
    public Boolean mapper(JSONObject jsonObject, String expression) throws JSONException 
    {
        // Checks for Brackets and recursively solves one bracket at a time
    	try
    	{
    		while (expression.contains(OPENING_BRACKET)) 
            {
                expression = findAndEvaluateSingleExpression(expression, jsonObject);  
            }
    		return expressionEvaluator.evaluate(expression, jsonObject);
    	}
        catch (Exception e)
        {
        	System.out.println("Incorrect Expression");
        	return false;
        }
    }

    // Finds a single unbreakable expression and evaluates it(replaces it with boolean value)
   
    private String findAndEvaluateSingleExpression(String expression, JSONObject jsonObject) throws Exception 
    {
    	String stringToBeEvaluated = getSingleExpression(expression);
    	System.out.println(stringToBeEvaluated);
    	String stringAfterEvaluation = expressionEvaluator.evaluate(stringToBeEvaluated, jsonObject).toString();
    	
        return expression.replace(OPENING_BRACKET + stringToBeEvaluated + CLOSING_BRACKET, stringAfterEvaluation);
    }

    // Finds the Single Unbreakable expression
    private String getSingleExpression(String expression) throws Exception 
    {
    	int openingBracketIndex = expression.lastIndexOf(OPENING_BRACKET);
    	int closingBracketIndex = expression.substring(openingBracketIndex).indexOf(CLOSING_BRACKET);
    	// If there is bracket mismatch throws an exception
    	if(closingBracketIndex == -1)
    	{
    		throw new Exception();
    	}
        String smallestExpression = expression.substring(openingBracketIndex + 1, expression.substring(openingBracketIndex).indexOf(CLOSING_BRACKET) + openingBracketIndex);
        return smallestExpression;
    }
}
