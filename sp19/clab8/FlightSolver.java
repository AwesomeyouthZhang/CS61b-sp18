import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {
    private int MaxPassengers = 0;

    public FlightSolver(ArrayList<Flight> flights) {

        /* FIX ME */
        Comparator<Flight> startTimeComparator =(Flight f1, Flight f2 )->(f1.startTime - f2.startTime);
        Comparator<Flight> endTimeComparator = (Flight f1, Flight f2) ->(f1.endTime - f2.endTime);
        PriorityQueue<Flight> MinStartTimePQ = new PriorityQueue(startTimeComparator);
        PriorityQueue<Flight> MinEndTimePQ = new PriorityQueue<>(endTimeComparator);

        MinEndTimePQ.addAll(flights);
        MinStartTimePQ.addAll(flights);
        int currentPassengers = 0;
        while (MinStartTimePQ.peek() != null) {

            if (MinStartTimePQ.peek().startTime < MinEndTimePQ.peek().endTime()) {
                currentPassengers += MinStartTimePQ.poll().passengers;
                if (currentPassengers > MaxPassengers) {
                    MaxPassengers = currentPassengers;
                }
            }
            else {
                currentPassengers -= MinEndTimePQ.poll().passengers;
            }

        }




    }

    public int solve() {
        /* FIX ME */


        return MaxPassengers;
    }


}
