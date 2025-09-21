package io.github.baddel73.dots.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import io.github.baddel73.dots.language.psi.DotsTypes;

%%

%class DotsLexer
%implements FlexLexer
%function advance
%type IElementType
%eof{  return;
%eof}

WHITE_SPACE = [ \t\f\r\n]+
LINE_COMMENT = "//"[^\r\n]*
BLOCK_COMMENT = "/*"[^*]*~"*/"

IDENTIFIER = [a-zA-Z_][a-zA-Z0-9_]*
NUMBER = [0-9]+

// Hexadecimal numbers
HEX_NUMBER = 0[xX][0-9a-fA-F]([0-9a-fA-F']*[0-9a-fA-F])?

// Binary numbers
BINARY_NUMBER = 0[bB][01]([01']*[01])?

// Decimal numbers with optional fractional part and scientific notation
DECIMAL_NUMBER = ([0-9]([0-9']*[0-9])?\\.?[0-9]*([0-9']*[0-9])?)|(\\.[0-9]([0-9']*[0-9])?)
SCIENTIFIC_NOTATION = [eE][+\-]?[0-9]([0-9']*[0-9])?
NUMBER_SUFFIX = [LlUuFf]|[uU][lL]|[lL][lL]|[uU][lL][lL]

%%

<YYINITIAL> {
  {WHITE_SPACE}         { return com.intellij.psi.TokenType.WHITE_SPACE; }
  {LINE_COMMENT}        { return DotsTypes.LINE_COMMENT; }
  {BLOCK_COMMENT}       { return DotsTypes.BLOCK_COMMENT; }

  // Keywords
  "struct"              { return DotsTypes.STRUCT; }
  "enum"                { return DotsTypes.ENUM; }

  // Struct attributes
  "cached"              { return DotsTypes.CACHED; }
  "cleanup"             { return DotsTypes.CLEANUP; }
  "substruct_only"      { return DotsTypes.SUBSTRUCT_ONLY; }
  "persistent"          { return DotsTypes.PERSISTENT; }
  "internal"            { return DotsTypes.INTERNAL; }

  // Property attributes
  "key"                 { return DotsTypes.KEY; }

  // Primitive types - using the correct constant names from DotsTypes
  "uuid"                { return DotsTypes.UUID; }
  "bool"                { return DotsTypes.BOOL; }
  "int8"                { return DotsTypes.INT_8; }
  "int16"               { return DotsTypes.INT_16; }
  "int32"               { return DotsTypes.INT_32; }
  "int64"               { return DotsTypes.INT_64; }
  "uint8"               { return DotsTypes.UINT_8; }
  "uint16"              { return DotsTypes.UINT_16; }
  "uint32"              { return DotsTypes.UINT_32; }
  "uint64"              { return DotsTypes.UINT_64; }
  "float32"             { return DotsTypes.FLOAT_32; }
  "float64"             { return DotsTypes.FLOAT_64; }
  "float128"            { return DotsTypes.FLOAT_128; }
  "duration"            { return DotsTypes.DURATION; }
  "timepoint"           { return DotsTypes.TIMEPOINT; }
  "steady_timepoint"    { return DotsTypes.STEADY_TIMEPOINT; }
  "string"              { return DotsTypes.STRING; }
  "property_set"        { return DotsTypes.PROPERTY_SET; }

  // Collection types
  "vector"              { return DotsTypes.VECTOR; }

  // Boolean literals
  "false"               { return DotsTypes.FALSE; }
  "true"                { return DotsTypes.TRUE; }

  // Operators and punctuation
  "="                   { return DotsTypes.EQ; }
  "<"                   { return DotsTypes.LT; }
  ">"                   { return DotsTypes.GT; }
  ":"                   { return DotsTypes.COLON; }
  ";"                   { return DotsTypes.SEMICOLON; }
  ","                   { return DotsTypes.COMMA; }

  // Brackets
  "{"                   { return DotsTypes.LBRACE; }
  "}"                   { return DotsTypes.RBRACE; }
  "["                   { return DotsTypes.LBRACK; }
  "]"                   { return DotsTypes.RBRACK; }

  // Numbers (order matters - more specific patterns first)
  {HEX_NUMBER}{NUMBER_SUFFIX}?        { return DotsTypes.NUMBER; }
  {BINARY_NUMBER}{NUMBER_SUFFIX}?     { return DotsTypes.NUMBER; }
  {DECIMAL_NUMBER}{SCIENTIFIC_NOTATION}?{NUMBER_SUFFIX}? { return DotsTypes.NUMBER; }
  {NUMBER}              { return DotsTypes.NUMBER; }

  // Identifiers (must come after keywords)
  {IDENTIFIER}          { return DotsTypes.IDENTIFIER_TOKEN; }
}

[^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }