# js-functions
Kotlin with Javascript dynamic functions Proof of Concept.

This exercise presumes that only two levels are allowed and the low levels functions are defined previously. 

However, since the functions have an default interface, its possible to run more than two level functions with some overhead. This occurs because the function has to be loaded before execution. If one high-level function needs another high-level functions. Both have to be evaluated (`eval`) before execution. In this case, each function has to have unique name.

The high level functions are located as resource files. In a production scenario, the function source code can be placed in cloud solutions like S3.

## Low Level Functions

Low level functions are functions implemented in pure kotlin or native code.

## High Level Functions

High level functions are JavaScript functions that respect a default interface and can orcherstrate the execution of low level functions.

## Improvements

- Babel for using EC2015/EC6
- Kotlin scripts to javascript
