package mapper;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonMapper 
{
	// Converts the input Json String to Json Object  
    public JSONObject convertJsonToObject(String jsonString) throws JSONException 
    {
        JSONObject jsonObject = new JSONObject(jsonString);
        return jsonObject;
    }

    // Checks if value exists in Json to evaluate the expression
    public Boolean checkIfValueExists(JSONObject jsonObject, String key) 
    {
        Boolean isValuePresent = false;
        if (key.contains(".")) 
        {
            JSONObject object = jsonObject;
            String[] nestedExpression = key.split("\\.");

            for (int i = 0; i < nestedExpression.length; ++i) 
            {
                if (i == nestedExpression.length - 1) 
                {
                    isValuePresent = object.has(nestedExpression[i]);
                } 
                else 
                {
                    try 
                    {
                        object = object.getJSONObject(nestedExpression[i]);
                    } 
                    catch (JSONException e) 
                    {
                        isValuePresent = false;
                        break;
                    }
                }
            }
        } 
        else 
        {
            isValuePresent = jsonObject.has(key);
        }
        return isValuePresent;
    }

    // Check for value in Json to evaluate the expression
    public Boolean checkForValueInJson(JSONObject jsonObject, String key, String value) throws JSONException 
    {
        Boolean isEqual = false;
        if (key.contains(".")) 
        {
            JSONObject object = jsonObject;
            String[] nestedExpression = key.split("\\.");

            for (int i = 0; i < nestedExpression.length; ++i) 
            {
            	if (i == nestedExpression.length - 1) 
                {
                    isEqual = convertToDataType(value).equals(object.get(nestedExpression[i]));
                } 
                else 
                {
                    try 
                    {
                    	object = object.getJSONObject(nestedExpression[i]);
                    } 
                    catch (JSONException e) 
                    {
                        isEqual = false;
                        break;
                    }
                }
            }
        } 
        else 
        {
            try 
            {
            	isEqual = convertToDataType(value).equals(jsonObject.get(key));
            } 
            catch (JSONException e) 
            {
                isEqual = false;
            }
        }
        return isEqual;
    }

    // Checks for datatype and converts it to compare in checkForValueInJson function
    private Object convertToDataType(String x) 
    {
        if (x.contains("'")) 
        {
            return x.replaceAll("'", "").toString();
        } 
        else if (x.matches("[0-9.]*")) 
        {
            if (x.contains(".")) 
            {
                return Double.parseDouble(x);
            } 
            else 
            {
                return Integer.parseInt(x);
            }
        } 
        else 
        {
            return Boolean.parseBoolean(x);
        }
    }
}
