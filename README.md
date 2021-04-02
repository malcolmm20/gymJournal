# Gym Journal

## A openWorkout recording app to help you workout intelligently.

This application is designed to make tracking your workouts more efficient.  

Its features will include:
- Recording your workouts statistics
- Calculating your "one rep max" for exercises you have performed  
- Saving custom  workoutExercise routines 
 
Recording your workouts is important for every level of athlete. It helps 
set and achieve goals, and it is useful to consult your workoutExercise history
when designing new openWorkout routines. When I started working out, I
used a pen and paper, or the *Notes* app to record the weight, reps, and sets 
for every workoutExercise I performed. Unfortunately, this method proved to be time-
consuming and inefficient. With the ***Gym Journal*** application, users will
be able to record their exercises, save routines that they often repeat, and
view a calculated estimate of their one rep max for each workoutExercise that they have 
done.


## User Stories
- As a user, I want to be able to record workouts in my GymJournal.
- As a user, I want to be able to add an exercise to my ongoing workout.
- As a user, I want to be able to save routines that I repeat.  
- As a user, I want to be able to start a workout that follows an existing routine.
- As a user, I want to be able to see past workout stats.
- As a user, I want to be able to see my personal best and one rep max for exercises
 I have performed.
- As a user, I want to be able to save the contents in my GymJournal to a file.
- As a user, I want to be able to load the contents of my saved GymJournal into 
my current GymJournal. 

## Phase 4: Task 2
I used the map interface in the GymJournal class for the GymJournals routines
because order does not matter for routines, and I referenced each routine by its
name, so it was easy to make key value pairs.

## Phase 4: Task 3
After viewing the UML diagram I realized that my GymJournalGUI class doesn't need 
to have individual screen fields, because when I instantiate the screens, I add
them to the screen hashMap, and reference them using the hashMap throughout the
project. Otherwise, I am happy with the class hierarchies and associations
I implemented. The only project feature I would change would be some options
to make inputting workout values less tedious, although if it were an app on
a touchscreen, I think it would be far less tedious.