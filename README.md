# Json-Expression-Evaluator

Evaluating Expression against the Json provided

Expression will contain:


      i. Variable (which will start with $), could also be nested (dot separated)
      
      
      ii. Constant:
        1) String within quotes
        2) Boolean -  true or false
        3) Decimal 
        4) Number
        
        
      iii. Logical Operator:
        1) AND
        2) OR
        3) NOT
        4) (  )   (Brackets)
        
        
      iv. Comparison Operators
        1) ==
        2) EXISTS
        
     
Returns true if expression is true on evaluation against a  json


Example:

Exp: "$mattress.name == 'king' AND $cost == 100.0"
Json: {"color":"red","size":10,"cost":100.0,"mattress":{"name":"king"},"big":true,"legs":[{"length":4}]}
Output: true
        
        
Exp: "NOT EXISTS $color"
Json: {"color":"red","size":10,"cost":100.0,"mattress":{"name":"king"},"big":true,"legs":[{"length":4}]} 
output: false
