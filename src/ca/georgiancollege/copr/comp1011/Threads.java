package ca.georgiancollege.copr.comp1011;

import java.sql.Time;
import java.util.concurrent.*;

public class Threads {

    /*
        Threads: a process to execute
                    a worker

                why threads
                    a thread = one process
                        one process = one responsibility

     */

    static Runnable task = () -> {

        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getState());
    };
    static Runnable timedTask = () -> {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "--started");
        try {
            //TimeUnit.MILLISECONDS.sleep(3000);
            TimeUnit.SECONDS.sleep(3);
        }
        catch (InterruptedException e){
            System.err.println(threadName + " was interrupted!");
        }
        System.out.println(threadName + "--ended");
    };

    public static void main(String[] args) {
        example8();
    }
    static void example1(){

        /*

         */
        Thread t1 = new Thread(); //Thread-indexNumberStartingAtZero
        t1.start();

        Thread t2 = new Thread();
        t2.start();

        Thread t3 = new Thread("My Thread");
        t3.start();

    }
    /*
        NEW
        RUNNING

            BLOCKED
            WAITING

        TERMINATED

     */
    static void example2(){



        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task, "My-Thread");

        t1.start();
        t2.start();

        Integer.parseInt("abc");


    }

    static void example3(){

        /*
            You are at work, in the middle of finishing your tasks
                your boss comes in and says, hey, I need you to go this, that, and the third

         */

        task.run(); // running a set of instructions on the current thread

    }
    static void example4() {
        new Thread(timedTask).start();
    }

    static void example5(){

        /*
            many libraries/objects that manage threads
         */

        ExecutorService service = Executors.newSingleThreadExecutor();

        service.submit(timedTask);
        service.submit(task);
        service.submit(timedTask);
        service.submit(task);
        service.shutdown();



    }
    static void example6(){

        /*
            many libraries/objects that manage threads
         */

        ExecutorService service = Executors.newFixedThreadPool(2);

        service.submit(timedTask);
        service.submit(timedTask);
        service.submit(timedTask);
        service.submit(timedTask);
        service.submit(timedTask);
        service.submit(timedTask);
        service.shutdown();



    }

    static void example7(){

        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);

        service.scheduleWithFixedDelay(timedTask, 30, 60, TimeUnit.SECONDS);

        try {
            TimeUnit.SECONDS.sleep(10);
            service.shutdownNow(); //shutDown(): gentle shutdown: wait until all process/threads terminated state
        }
        catch (InterruptedException e){
            System.err.println(e);
        }
    }
    /*

        email services
            research for emails every minute

     */

    static void example8(){


        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Callable<Integer> taskThatReturns = () ->  {

            String threadName = Thread.currentThread().getName();
            System.out.println( threadName+ " -- started");
            try{
                TimeUnit.SECONDS.sleep(2);
                return 123;
            }
            catch (InterruptedException e){
                throw new InterruptedException(threadName + " interrupted");
            }
        };

        Future<Integer> futureValue = executorService.submit(taskThatReturns);

        try {
            int result = futureValue.get(3, TimeUnit.SECONDS);
            System.out.println("The value returned is " + result);
        }
        catch (InterruptedException | ExecutionException | TimeoutException e){
            System.err.println(e);
        }

        executorService.shutdown();



    }

}
