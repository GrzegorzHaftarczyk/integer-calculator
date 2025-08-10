grammar IntegerCalculator;

STAR: '*';
SLASH: '/';
PLUS: '+';
MINUS: '-';
NUMBER: '-'?[0-9]+;
SPACE: ' ';

start : expression EOF;

expression
   : NUMBER                                                 # Number
   | left=expression SPACE operator=(STAR|SLASH) SPACE right=expression # MultiplicationOrDivision
   | left=expression SPACE operator=(PLUS|MINUS) SPACE right=expression # AdditionOrSubtraction
   ;