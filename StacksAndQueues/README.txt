Stacks and Queues

These were implemented together during class. Using ArrayList and DList is easy, as most of the methods have corresponding methods in their
respective classes. 

The DNode implementation is a little bit trickier, as you have to make sure the pointers are referencing the correct things.

The Array implementation is even trickier, because you must worry about resizing at the appropriate time, casting it to a generic array
correctly, and copying it over. This is harder for the queue because you have to make two copies of the array, to ensure that the first and
last pointers still point to the right thing.


*Note: only StackAL and QueueAL have comprehensive examples of properly thrown exceptions
