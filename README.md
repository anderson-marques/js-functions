# js-functions
Kotlin with Javascript dynamic functions Proof of Concept.

This exercise presumes that only two levels are allowed. However, since the functions have an default interface, its possible to run more than two level functions with some overhead. This occurs because the function has to be loaded before execution. If one high-level function needs another high-level functions. Both have to be evaluated (`eval`) before execution. In this case, instead of having unique function names. Each one have to have new names.

## Low Level Functions

Low level functions are functions implemented in pure kotlin.

## High Level Functions

High level functions are JavaScript functions that respect a default interface and can orcherstrate the execution of low level functions.
