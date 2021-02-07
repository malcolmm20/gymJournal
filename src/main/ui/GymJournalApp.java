package ui;


import model.Exercise;
import model.ExerciseSet;
import model.Workout;
import model.GymJournal;

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
        String command = null;

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
                printHistory();
                break;
            case "r":
                startCreateRoutine();
                break;
            case "b":
                checkPersonalBest();
                break;
            case "o":
                checkOneRepMax();
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

    private void checkOneRepMax() {
    }

    private void checkPersonalBest() {
    }

    private void startCreateRoutine() {
    }

    private void printHistory() {
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
            startWorkoutRoutine();
        } else {
            System.out.println("Please select a valid option");
            startWorkout();
        }
    }


    private void startWorkoutRoutine() {
    }

    // MODIFIES: this
    // EFFECTS: creates workout, adds workout to journal when done
    private void startEmptyWorkout() {
        Workout workout = new Workout();
        recordExercises(workout);
        gymJournal.addWorkout(workout);
    }

    // MODIFIES: workout
    // EFFECTS: creates exercises, adds to workout
    private void recordExercises(Workout workout) {
        boolean keepGoing = true;

        while (keepGoing) {
            int count = 1;
            System.out.println("Enter Exercise " + Integer.toString(count) + " name:");
            String name = input.nextLine();
            Exercise exercise = new Exercise(name);
            exerciseDetails(exercise);
            System.out.println("Add another exercise?\n Enter 'y' for yes\n Enter any key for no");
            String command = input.next();
            if (command.equals("y")) {
                workout.addExercise(exercise);
                count++;
            } else {
                keepGoing = false;
            }
        }
    }

    // MODIFIES: exercise
    // EFFECTS: records reps and weight in set objects, adds set objects to exercise
    private void exerciseDetails(Exercise exercise) {
        System.out.println("How many sets?");
        int num = input.nextInt();

        for (int i = 0; i < num; i++) {
            System.out.println("How many reps for set " + Integer.toString(i + 1) + "?");
            int reps = input.nextInt();
            System.out.println("How much weight for set " + Integer.toString(i + 1) + "?");
            int weight = input.nextInt();
            ExerciseSet set = new ExerciseSet(reps, weight);
            exercise.addSet(set);
        }
    }

}
