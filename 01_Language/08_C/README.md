# 语言篇之C

1. [What is the difference between char * const and const char *?](https://stackoverflow.com/questions/890535/what-is-the-difference-between-char-const-and-const-char)

   What's the difference between:

   ```c
   char * const 
   ```

   and

   ```c
   const char *
   ```

   

   The difference is that `const char *` is a pointer to a `const char`, while `char * const` is a constant pointer to a `char`.

   The first, the value being pointed to can't be changed but the pointer can be. The second, the value being pointed at can change but the pointer can't (similar to a reference).

   There is also a

   ```c
   const char * const
   ```

   which is a constant pointer to a constant char (so nothing about it can be changed).

   Note:

   The following two forms are equivalent:

   ```c
   const char *
   ```

   and

   ```c
   char const *
   ```

   The exact reason for this is described in the C++ standard, but it's important to note and avoid the confusion. I know several coding standards that prefer:

   ```c
   char const
   ```

   over

   ```c
   const char
   ```

   (with or without pointer) so that the placement of the `const` element is the same as with a pointer `const`.

