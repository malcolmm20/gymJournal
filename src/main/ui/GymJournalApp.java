package ui;


import model.*;

import java.util.Scanner;

// Gym journal application
// Structure follows TellerApp structure
public class GymJournalApp {
    private Scanner input;
    private GymJournal gymJournal;

    // EFFECTS: runs gym journal app
    public GymJournalApp() {
        runGymJournalApp();
    }

    // MODIFIES: this
    // EFFECTS: process user input
    private void runGymJournalApp() {
        boolean keepGoing = true;
        String command;

        initialize();

        while (keepGoing) {
            displayOptions();
            command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        switch (command) {
            case "w":
                startWorkout();
                break;
            case "h":

                break;
            case "r":
                createRoutine();
                break;
            case "b":

                break;
            case "o":

                break;
            default:
                System.out.println("Selection not valid. Please choose a valid option.");
        }
    }


    // EFFECTS: displays options user has
    private void displayOptions() {
        System.out.println("if you would like to:\n"
                + "start a workout - enter 'w'\n"
                + "view workout history - enter 'h'\n"
                + "create a workout routine - enter 'r'\n"
                + "check your personal best for an exercise - enter 'b'\n"
                + "check your estimated one rep max for an exercise - enter 'o'\n"
                + "quit - enter 'q'");
    }

    // MODIFIES: this
    // EFFECTS: initializes journal and scanner
    private void initialize() {
        gymJournal = new GymJournal();
        input = new Scanner(System.in);
    }

    // MODIFIES: this
    // EFFECTS: starts either an empty workout or a routine
    private void startWorkout() {
        System.out.println("would you like to start an empty workout, or begin a workout routine?\n"
                + "start an empty workout - enter 'e'\nstart a workout routine - enter 'r'");
        String command = input.nextLine();

        if (command.equals("e")) {
            startEmptyWorkout();
        } else if (command.equals("r")) {
            findWorkoutRoutine();
        } else {
            System.out.println("Please select a valid option");
            startWorkout();
        }
    }

    // EFFECTS: if user enters valid routine, starts the routine workout
    private void findWorkoutRoutine() {
        System.out.println("Which routine will you be doing today?");
        String search = input.nextLine();
        Routine routine = gymJournal.findRoutine(search);
        if (!(routine == null)) {
            startWorkoutRoutine(routine);
        } else if (search.equals("q")) {
            System.out.println("Routine does not exist. Please enter a valid routine, or enter 'q' to quit.");
            findWorkoutRoutine();
        }
    }

    // MODIFIES: this
    // EFFECTS: create routine, add to gym journal
    private void createRoutine() {
        System.out.println("What would you like to name your routine?");
        String name = input.nextLine();
        Routine routine = new Routine(name);
        recordExercises(routine);
        gymJournal.addRoutine(routine);
    }

    // REQUIRES: routine to not be null
    // MODIFIES: this
    // EFFECTS: creates workout, adds exercises from routine to workout
    private void startWorkoutRoutine(Routine routine) {
        OpenWorkout openWorkout = new OpenWorkout();
        for (RoutineExercise routineExercise : routine.getExercises()) {
            WorkoutExercise workoutExercise = new WorkoutExercise(routineExercise.getName());
            for (int i = 0; i < routineExercise.getSets(); i++) {
                System.out.println("How many reps completed for set " + (i + 1)
                        + " of " + routineExercise.getName() + "?");
                int reps = input.nextInt();
                System.out.println("What weight did you use for set "
                        + (i + 1) + " of " + routineExercise.getName() + "?");
                int weight = input.nextInt();
                ExerciseSet set = new ExerciseSet(reps, weight);
                workoutExercise.addSet(set);
            }
            openWorkout.addExercise(workoutExercise);
        }
        gymJournal.addWorkout(openWorkout);
    }

    // MODIFIES: this
    // EFFECTS: creates workout, adds workout to journal when done
    private void startEmptyWorkout() {
        OpenWorkout openWorkout = new OpenWorkout();
        recordExercises(openWorkout);
        gymJournal.addWorkout(openWorkout);
    }

    private void recordExercises(Workout workout) {
        boolean keepGoing = true;
        int count = 1;

        while (keepGoing) {
            System.out.println("Enter Exercise " + count + " name:");
            String name = input.nextLine();
            System.out.println("How many sets?");
            int num = input.nextInt();
            input.nextLine();
            workout.callExerciseDetails(this, name, num);
            System.out.println("Add another exercise?\n Enter 'y' for yes\n Enter any key for no");
            String command = input.nextLine();
            if (command.equals("y")) {
                count++;
            } else {
                keepGoing = false;
            }
        }
    }

    // MODIFIES: workout
    // EFFECTS: records reps and weight in set objects, adds set objects to exercise
    public void workoutExerciseDetails(OpenWorkout workout, String name, int num) {
        WorkoutExercise exercise = new WorkoutExercise(name);

        for (int i = 0; i < num; i++) {
            System.out.println("How many reps for set " + (i + 1) + "?");
            int reps = input.nextInt();
            System.out.println("How much weight for set " + (i + 1) + "?");
            int weight = input.nextInt();
            ExerciseSet set = new ExerciseSet(reps, weight);
            exercise.addSet(set);
        }
    }

    public void routineExerciseDetails(Routine workout, String name, int num) {
        RoutineExercise exercise = new RoutineExercise(name, num);
        workout.addExercise(exercise);
    }
}
