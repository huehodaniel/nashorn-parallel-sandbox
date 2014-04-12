# Parallel JS Sandbox
*(AKA messing around in Nashorn)*

## Huh?

I been playing with the Nashorn JS engine, which was released along the brand new JDK8. It's fun, it's cool, and of course I'm trying to shoehorn some concurrency stuff into JS because why not, in JVM land we do it like the Duke.

## What is it?

This is a small Eclipse project with a few classes mostly playing with:

* Creating Java classes for use inside JavaScript
* Creating a ExecutorService clone interface for use with Nashorn
* Benchmarking dumb functions executed in parallel and sequentially

Apparently all of that is actually useless because Nashorn is friggin magical and you can implement Java interfaces and extend Java classes in runtime, so I could just feed a plain ExecutorService some JS functions and it would work the same... At least I think so. Gonna try some other day.

## License
Nobody will actually care about it.

Hence, Unlicense:

```
This is free and unencumbered software released into the public domain.

Anyone is free to copy, modify, publish, use, compile, sell, or
distribute this software, either in source code form or as a compiled
binary, for any purpose, commercial or non-commercial, and by any
means.

In jurisdictions that recognize copyright laws, the author or authors
of this software dedicate any and all copyright interest in the
software to the public domain. We make this dedication for the benefit
of the public at large and to the detriment of our heirs and
successors. We intend this dedication to be an overt act of
relinquishment in perpetuity of all present and future rights to this
software under copyright law.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

For more information, please refer to <http://unlicense.org/>
```