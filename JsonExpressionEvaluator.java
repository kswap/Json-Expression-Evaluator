import org.json.JSONException;
import org.json.JSONObject;

import evaluator.JsonEvaluator;
import evaluator.ExpressionEvaluator;

import mapper.ExpressionMapper;
import mapper.JsonMapper;

public class JsonExpressionEvaluator 
{
	public static void main(String[] args) throws JSONException 
	{  
        String json = "{\"color\":\"red\",\"size\":10,\"cost\":100.0,\"mattress\":{\"name\":\"king\"},\"big\":true,\"legs\":[{\"length\":4}]}";
        String expression = "$mattress.name == 'king' AND $cost == 100.0";
        
        // Converting Json String to Json Object <Mapping>
        JsonMapper jsonMapper = new JsonMapper();
        JSONObject jsonObject = jsonMapper.convertJsonToObject(json);
        
        // Checking expression with the mapped json object
        JsonEvaluator jsonEvaluator = new JsonEvaluator(jsonMapper);      
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(jsonEvaluator);      
        ExpressionMapper expressionMapper = new ExpressionMapper(expressionEvaluator);     
        System.out.println(expressionMapper.mapper(jsonObject, expression));
    }
}
