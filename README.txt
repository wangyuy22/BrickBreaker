=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: wangyuy
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Arrays
  
  I use 2D arrays to implement my brickArr class, which is a 2D array of "bricks". This is an appropriate
  place to use 2D arrays as that is essentially the way the bricks in the game are layed out in. There are 
  rows and columns that represent certain areas on the screen and each area either has a block (with some
  number of hits) or no blocks at all. I did not receive feedback after submitting my proposal.

  2. Collections
  
  I use collections, more specifically linked lists, at the beginning of the game (and after every reset and
  resume) where I have to launch the ball. The linked list stores "lines", which is another object that I 
  created to represent a preview of the direction that the ball will be launched to. I think that this is a 
  good place to use linked lists as it adds to the front of the linked list and lines can easily be "peeked"
  or "popped" and can be constantly added due to the resizability of a linked list. I did not receive feedback
  after submitting my proposal.

  3. File I/O
  
  I use File I/O in the pausing and resuming portion of my game. After running the code and playing the game,
  midway through any game I want to have the ability to pause my game, close the application, rerun it, and
  open up with the same game (same brickArr setup). I do this through using a filewriter that writes down the
  number of hits of each index of the brickArr on a separate text file. After, when I want to resume, I also
  have a file reading function in the constructor of the brickArr to recreate the previous game state. 
  Moreover, the 3 levels in the game ("Easy", "Medium", and "Hard") are also text files that are read by
  the constructor of the brickArr object. I did not receive feedback after submitting my proposal.

  4. Testable Component
  
  I use tests to test some of the basic, yet essential, functions in various classes to make sure that my
  GameCourt.java and Game.java can run smoothly. For example, I test whether or not the brickMap is correctly
  deemed empty or not. I also check the brick objects behave correctly after being hit by a ball as their 
  "hits" should decrease by 1. I also check that if a brick has a hits of 0, its spot in the brickArr is 
  turned to null. I also test whether or not the circle object behaves normally when bouncing, intersecting,
  and launching. These are the main things that I test as these functions are used in conjunction with one
  another in GameCourt.java and therefore are key for the game to work correctly. I did not receive feedback
  after submitting my proposal.


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  The GameObj.java class is from the original code provided, and it is used to defined certain objects that
  are used in the game such as Circle or Square. The Circle.java class creates a circle object that is similar
  to the original code, except that the only thing that is final is the size of the circle object. It also has
  a launch aspect that checks if the ball is launched or not and determines in what direction the circle is
  launched in. The square is similar to the original code, except the square can no longer move vertically 
  and only horizontally at the bottom of the screen. Line is just an object that has a start x and y, and an 
  end x and y. This is used when creating previews of the launch. Direction.java is the same as the original
  code. GameCourt.java puts everything in place, allowing the ball, square, and bricks to interact correctly,
  allowing the launch to operate correctly, and having methods to save and resume. Game.java allows the game
  screen the function correctly and allows all the JButtons to work.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  
  There were some stumbling blocks as at times the way the bricks and the ball interacted when intersecting
  was strange, however I think that it is resolved now. I also found it somewhat difficult to create the 
  preview line, but I think that I am now satisfied with it. Finally, there were some difficulties in the 
  File I/O portion, especially when closing the window, rerunning the program, and resuming the game. I had
  some difficulty in making sure that I was recording the hits of each brick and not their memory location.
  Overall, I am proud of how I was able to overcome these challenges that I faced when developing my game.


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  
  I think that there is a good separation of functionality in my game as I created a good amount of classes
  and distributed the methods across these classes in the way that makes the most sense. I think the the
  private states is mostly encapsulated, as only some parts are not. I think that I would refactor the
  GameCourt.java file in some ways as I do feel like some places are redundant and can be refactored.
  
  

========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  
  I didn't use mushc external resources, I mainly just looked at lecture notes and previous homeworks.
