package ui;

import model.*;

import java.util.Scanner;

// Gym journal application
// Structure follows TellerApp structure
public class GymJournalApp {
    private static final String JSON_STORE = "./data/gymJournalLog.json";
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
                workoutHistory();
                break;
            case "r":
                createRoutine();
                break;
            case "b":
                displayPersonalBests();
                break;
            case "o":
                displayOneRepMaxes();
                break;
            case "s":
                saveGymJournal();
                break;
            case "l":
                loadGymJournal();
                break;
            default:
                System.out.println("Selection not valid. Please choose a valid option.");
        }
    }

    private void loadGymJournal() {
    }

    private void saveGymJournal() {
    }

    private void displayOneRepMaxes() {
        System.out.println(gymJournal.displayOneRepMaxes());
        System.out.println("Press 'r' to return to the menu");
        String command = input.nextLine().toLowerCase();
        if (!command.equals("r")) {
            workoutHistory();
        }
    }

    private void displayPersonalBests() {
        System.out.println(gymJournal.displayPersonalBests());
        System.out.println("Press 'r' to return to the menu");
        String command = input.nextLine().toLowerCase();
        if (!command.equals("r")) {
            workoutHistory();
        }
    }

    // EFFECTS: prints past workouts
    private void workoutHistory() {
        System.out.println("How many past workouts would you like to view?");
        int num = input.nextInt();
        input.nextLine();
        System.out.println(gymJournal.workoutString(num));
        System.out.println("Press 'r' to return to the menu");
        String command = input.nextLine().toLowerCase();
        if (!command.equals("r")) {
            workoutHistory();
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
                + "save your gym journal to file - enter 's'\n"
                + "load your gym journal from file - enter 'l'\n"
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
        String command = input.nextLine().toLowerCase();

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
        System.out.println("Which routine will you be doing today?\n "
                + "Enter the name of the routine as it appears below.\n");
        if (gymJournal.routineString().equals("")) {
            System.out.println("No routines have been created. Enter 'q' to return to the menu.");
        } else {
            System.out.print(gymJournal.routineString());
        }
        String search = input.nextLine().toLowerCase();
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
        String name = input.nextLine().toLowerCase();
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
                input.nextLine();
                System.out.println("What weight did you use for set "
                        + (i + 1) + " of " + routineExercise.getName() + "?");
                int weight = input.nextInt();
                input.nextLine();
                WorkoutSet set = new WorkoutSet(reps, weight);
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

    // REQUIRES: workout to not be null
    // EFFECTS: records exercise names and number of sets
    private void recordExercises(OpenWorkout workout) {
        boolean keepGoing = true;
        int count = 1;

        while (keepGoing) {
            System.out.println("Enter Exercise " + count + " name:");
            String name = input.nextLine().toLowerCase();
            System.out.println("How many sets?");
            int num = input.nextInt();
            input.nextLine();
            workoutExerciseDetails(workout, name.toLowerCase(), num);
            String command = checkAnotherExercise();
            if (command.equals("y")) {
                count++;
            } else {
                keepGoing = false;
            }
        }
    }

    // REQUIRES: workout to not be null
    // EFFECTS: records exercise names and number of sets
    private void recordExercises(Routine routine) {
        boolean keepGoing = true;
        int count = 1;

        while (keepGoing) {
            System.out.println("Enter Exercise " + count + " name:");
            String name = input.nextLine().toLowerCase();
            System.out.println("How many sets?");
            int num = input.nextInt();
            input.nextLine();
            exerciseDetails(routine, name.toLowerCase(), num);
            String command = checkAnotherExercise();
            if (command.equals("y")) {
                count++;
            } else {
                keepGoing = false;
            }
        }
    }

    // EFFECTS: calls exerciseDetails in gymJournalApp
    public void exerciseDetails(Routine routine, String name, int num) {
        RoutineExercise exercise = new RoutineExercise(name, num);
        routine.addExercise(exercise);
    }

    // EFFECTS: asks if user wants to add another exercise
    private String checkAnotherExercise() {
        System.out.println("Add another exercise?\n Enter 'y' for yes\n Enter any key for no");
        return input.nextLine().toLowerCase();
    }

    // MODIFIES: workout
    // EFFECTS: records reps and weight in set objects, adds set objects to exercise, adds exercise to workout
    public void workoutExerciseDetails(OpenWorkout workout, String name, int num) {
        WorkoutExercise exercise = new WorkoutExercise(name);

        for (int i = 0; i < num; i++) {
            System.out.println("How many reps for set " + (i + 1) + "?");
            int reps = input.nextInt();
            input.nextLine();
            System.out.println("How much weight for set " + (i + 1) + "?");
            int weight = input.nextInt();
            input.nextLine();
            WorkoutSet set = new WorkoutSet(reps, weight);
            exercise.addSet(set);
        }
        workout.addExercise(exercise);
    }
}
