# valuestreams

[![Build Status](https://travis-ci.org/kmehrunes/valuestreams.svg?branch=master)](https://travis-ci.org/kmehrunes/valuestreams)

A set of some optional-like value wrappers which provide more validation and mapping options. This project was created just for fun, but turned out to be quite convienent so I decided to share it. May it serve you in the projects to come.

## Overview
The provided classes expose an interface which is similar in a way to optional values. They allow applying a series of operations on a wrapped value. The provided classes are:
- **Value**: a generic class for general purpose objects.

- **StringValue**: a class specific for processing `String` values

- **NumericalValue**: a generic class for any type `N` which represents a number whether built into the a language or custom; specific sub-classes are provided: `IntegerValue`, `LongValue`, and `DoubleValue`

- **DateValue**: a class specific for processing `Date` values

Just like optionals, creating an value instance must be done by using the functions `of()` and `empty()` provided by every class. For example, `Value.of("test")` will return an instance of type `Value<String>`. Note that `Value<String>` isn't the same as `StringValue`, if you need a `StringValue` then you need to use `StringValue.of("test")`.

## Examples
- Generic values
```java
/* some example */
```

- String values
```java
/* some example */
```

- Numerical values
```java
/* some example */
```

- Date values
```java
/* some example */
```
