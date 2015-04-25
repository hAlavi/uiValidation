# uiValidation
UI Validation Platform based on Dependency Injection design pattern.
The base classes are:

1- ValidationPair : Stored data of elements that should be checked with the name of the 
related field and other usable data.

2- Validator : Interface of Validation Strategies.

3- ValidationState: States of Validation which are ERROR, VALID, WARNING

4- RegExConfigHolder: A simple interface for storing Regular Expressions and their related configs.

