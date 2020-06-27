package com.lym.future;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 特此说明，此测试类不是Guava的，是 Java8中提供的，对Future的扩展 CompletableFuture
 * 总结：
 * 一、创建 CompletableFuture 对象
 *      public static <U> CompletableFuture<U>  completedFuture(U value)
 *      public static CompletableFuture<Void>   runAsync(Runnable runnable)
 *      public static CompletableFuture<Void> 	runAsync(Runnable runnable, Executor executor)
 *      public static <U> CompletableFuture<U> 	supplyAsync(Supplier<U> supplier)
 *      public static <U> CompletableFuture<U> 	supplyAsync(Supplier<U> supplier, Executor executor)
 *
 * 二、记录计算结果，返回原始 Completable 对象
 *      public CompletableFuture<T> 	whenComplete(BiConsumer<? super T,? super Throwable> action)
 *      public CompletableFuture<T> 	whenCompleteAsync(BiConsumer<? super T,? super Throwable> action)
 *      public CompletableFuture<T> 	whenCompleteAsync(BiConsumer<? super T,? super Throwable> action, Executor executor)
 *      public CompletableFuture<T>     exceptionally(Function<Throwable,? extends T> fn)
 *
 * 三、处理计算结果，返回新的 CompletableFuture 对象。（可以处理异常）
 *      public <U> CompletableFuture<U> 	handle(BiFunction<? super T,Throwable,? extends U> fn)
 *      public <U> CompletableFuture<U> 	handleAsync(BiFunction<? super T,Throwable,? extends U> fn)
 *      public <U> CompletableFuture<U> 	handleAsync(BiFunction<? super T,Throwable,? extends U> fn, Executor executor)
 *
 * 四、转换计算结果，只能等前一个 stage 执行完才能执行，不能处理异常
 *      public <U> CompletableFuture<U> 	thenApply(Function<? super T,? extends U> fn)
 *      public <U> CompletableFuture<U> 	thenApplyAsync(Function<? super T,? extends U> fn)
 *      public <U> CompletableFuture<U> 	thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
 *
 * 五、消费计算结果，不返回新的值（只消费一个CompletableFuture）
 *      public CompletableFuture<Void> 	thenAccept(Consumer<? super T> action)
 *      public CompletableFuture<Void> 	thenAcceptAsync(Consumer<? super T> action)
 *      public CompletableFuture<Void> 	thenAcceptAsync(Consumer<? super T> action, Executor executor)
 *
 * 六、消费计算结果，不返回新值（两个 CompletableFuture 都正常执行完成后再消费）
 *      public <U> CompletableFuture<Void> 	thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action)
 *      public <U> CompletableFuture<Void> 	thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action)
 *      public <U> CompletableFuture<Void> 	thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action, Executor executor)
 *
 * 七、等待两个 CompletableFuture 计算完成，执行 action，不消费计算结果
 *      public CompletableFuture<Void> 	runAfterBoth(CompletionStage<?> other,  Runnable action)
 *      public CompletableFuture<Void> 	runAfterBoth(CompletionStage<?> other,  Runnable action)
 *      public CompletableFuture<Void> 	runAfterBoth(CompletionStage<?> other,  Runnable action)
 *
 * 八、组合，接收 CompletableFuture 的计算结果，返回新的 CompletableFuture
 *      public <U> CompletableFuture<U>     thenCompose(Function<? super T,? extends CompletionStage<U>> fn)
 *      public <U> CompletableFuture<U> 	thenComposeAsync(Function<? super T,? extends CompletionStage<U>> fn)
 *      public <U> CompletableFuture<U> 	thenComposeAsync(Function<? super T,? extends CompletionStage<U>> fn, Executor executor)
 *
 * 九、组合，两个 CompletableFuture 并行执行，两个都执行成功，再执行action
 *      public <U,V> CompletableFuture<V> 	thenCombine(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn)
 *      public <U,V> CompletableFuture<V> 	thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn)
 *      public <U,V> CompletableFuture<V> 	thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn, Executor executor)
 *
 * 十、either，当任意一个CompletionStage完成的时候，action这个消费者就会被执行（消费）
 *      public CompletableFuture<Void> 	acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action)
 *      public CompletableFuture<Void> 	acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action)
 *      public CompletableFuture<Void> 	acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action, Executor executor)
 *
 * 十一、either，任意一个CompletionStage完成的时候，fn会被执行，它的返回值会当作新的CompletableFuture<U>的计算结果（生产）
 *      public <U> CompletableFuture<U> applyToEither(CompletionStage<? extends T> other, Function<? super T,U> fn)
 *      public <U> CompletableFuture<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T,U> fn)
 *      public <U> CompletableFuture<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T,U> fn, Executor executor)
 *
 * 十二、allOf，当所有的CompletableFuture都执行完后执行计算
 *      public static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs)
 *
 * 十三、anyOf，当任意一个CompletableFuture执行完后就会执行计算，计算的结果相同
 *      public static CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs)
 *
 * 十四、扩展，支持传入一组CompletableFuture，返回一个新的CompletableFuture<List<T>>
 *
 * Created by liuyanmin on 2020/6/27.
 */
public class CompletableFutureDemo {

    /******************************** 创建CompletableFuture对象 ******************************/
    /**
     * 1、public static <U> CompletableFuture<U> completedFuture(U value)   用来返回一个已经计算好的 CompletableFuture
     */
    @Test
    public void completedFuture() {
        CompletableFuture<String> completableFuture = CompletableFuture.completedFuture("hello world");
        System.out.println(completableFuture.join());
    }

    /**
     * 2、以下四个静态方法为异步执行的代码创建 CompletableFuture 对象
     * public static CompletableFuture<Void>    runAsync(Runnable runnable)
     * public static CompletableFuture<Void> 	runAsync(Runnable runnable, Executor executor)
     * public static <U> CompletableFuture<U> 	supplyAsync(Supplier<U> supplier)
     * public static <U> CompletableFuture<U> 	supplyAsync(Supplier<U> supplier, Executor executor)
     */
    @Test
    public void runAsyncAndSupplyAsync() {
        // ①. runAsync(Runnable runnable) 无返回结果，没有指定线程池，默认会在ForkJoinPool.commonPool()中执行
        CompletableFuture.runAsync(() -> System.out.println("hello world1"));//hello world1

        // ②. runAsync(Runnable runnable, Executor executor) 无返回结果，使用指定的线程池执行Runnable任务
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture.runAsync(() -> System.out.println("hello world2"), executorService);// hello world2

        // ③. supplyAsync(Supplier<U> supplier) 有返回结果，没有指定线程池，默认会在ForkJoinPool.commonPool()中执行
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> "hello world3");
        System.out.println(completableFuture1.join());//hello world3

        // ④. supplyAsync(Supplier<U> supplier, Executor executor) 有返回结果，使用指定的线程池执行Runnable任务
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> "hello world4", executorService);
        System.out.println(completableFuture2.join());//hello world4
    }


    /******************************** 处理计算的结果 ******************************/
    /**
     * 当运行完成时，对结果的记录。这里的完成时有两种情况，一种是正常执行，返回值。
     * 另外一种是遇到异常抛出造成程序的中断。这里为什么要说成记录，因为这几个方法都会返回CompletableFuture，
     * 当Action执行完毕后它的结果返回原始的CompletableFuture的计算结果或者返回异常。所以不会对结果产生任何的作用。
     */
    /**
     * 1、whenComplete 方法，没有Async使用当前线程
     */
    @Test
    public void whenComplete() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "hello world")
                .whenComplete((v, e) -> {// v: 结果，e: 抛出的异常
                    System.out.println(v);
                    System.out.println(e);
                });
        System.out.println(completableFuture.join());
    }

    /**
     * 2、whenCompleteAsync 方法，有Async但是没有指定线程池，使用ForkJoinPool.commonPool()
     */
    @Test
    public void whenCompleteAsync() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "hello world")
                .whenCompleteAsync((v, e) -> {
                    System.out.println(v);//②.hello world
                    System.out.println(e);//③.null
                    System.out.println("whenCompleteAsync 内线程名: " + Thread.currentThread().getName());//④.whenCompleteAsync 内线程名: ForkJoinPool.commonPool-worker-1
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                });
        System.out.println("whenCompleteAsync 外线程名: " + Thread.currentThread().getName());//①.whenCompleteAsync 外线程名: main
        System.out.println("结果: " + completableFuture.join());//⑤.结果: hello world
    }

    /**
     * 3、whenCompleteAsync 方法，有Async但是没有指定线程池，使用 executor
     */
    @Test
    public void whenCompleteAsync2() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "hello world")
                .whenCompleteAsync((v, e) -> {
                    System.out.println(v);//②.hello world
                    System.out.println(e);//③.null
                    System.out.println("whenCompleteAsync 内线程名: " + Thread.currentThread().getName());//④.whenCompleteAsync 内线程名: pool-1-thread-1
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }, executorService);
        System.out.println("whenCompleteAsync 外线程名: " + Thread.currentThread().getName());//①.whenCompleteAsync 外线程名: main
        System.out.println("结果: " + completableFuture.join());//⑤.结果: hello world
    }

    /**
     * 4、exceptionally 方法
     * exceptionally方法返回一个新的CompletableFuture，当原始的CompletableFuture抛出异常的时候，
     * 就会触发 exceptionally 方法，否则如果原始的CompletableFuture正常计算完后，直接返回 CompletableFuture。
     * 也就是这个exceptionally方法用来处理异常的情况。
     */
    @Test
    public void exceptionally() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            if (1 == 1) {
                throw new RuntimeException();
            }
            return "complete";
        }).whenComplete((v, e) -> {
            System.out.println("whenComplete 结果: " + v);//null
            System.out.println("whenComplete 异常: " + e);//whenComplete 异常: java.util.concurrent.CompletionException: java.lang.RuntimeException
        }).exceptionally(e -> {
            System.out.println("捕获到异常: " + e);//捕获到异常: java.util.concurrent.CompletionException: java.lang.RuntimeException
            return "catch exception";
        });
        System.out.println(completableFuture.join());//catch exception
    }

    /**
     * 5、handle 方法
     * 下面一组方法虽然也返回CompletableFuture对象，但是对象的值和原来的CompletableFuture计算的值不同。
     * 当原先的CompletableFuture的值计算完成或者抛出异常的时候，会触发这个CompletableFuture对象的计算，
     * 结果由BiFunction参数计算而得。因此这组方法兼有whenComplete和转换的两个功能。
     */
    @Test
    public void handle() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "hello")
                .handle((v, e) -> {
                    System.out.println("handle处理线程: " + Thread.currentThread().getName());//handle处理线程: ForkJoinPool.commonPool-worker-1
                    System.out.println(e);//null
                    return v + " world";
                });
        System.out.println("主线程: " + Thread.currentThread().getName());//主线程: main
        System.out.println(completableFuture.join());//hello world
    }

    @Test
    public void handleAsync1() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "hello")
                .handleAsync((v, e) -> {
                    System.out.println("handle处理线程: " + Thread.currentThread().getName());//handle处理线程: ForkJoinPool.commonPool-worker-1
                    System.out.println(e);//null
                    return v + " world";
                });
        System.out.println("主线程: " + Thread.currentThread().getName());//主线程: main
        System.out.println(completableFuture.join());//hello world
    }

    @Test
    public void handleAsync2() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "hello")
                .handleAsync((v, e) -> {
                    System.out.println("handle处理线程: " + Thread.currentThread().getName());//handle处理线程: pool-1-thread-1
                    System.out.println(e);//null
                    return v + " world";
                }, executorService);
        System.out.println("主线程: " + Thread.currentThread().getName());//主线程: main
        System.out.println(completableFuture.join());//hello world
    }


    /******************************** 转换 ******************************/
    /**
     * CompletableFuture可以作为monad(单一)和functor(单元)。由于回调风格的实现，
     * 我们不必因为等待一个计算完成而阻塞着调用线程，而是告诉CompletableFuture当计算完成的时候请执行某个function。
     * 而且我们还可以将这些操作串联起来，或者将CompletableFuture组合起来。
     *
     * 需要注意的是，这些转换并不是马上执行的，也不会阻塞，而是在前一个stage完成后继续执行。
     *
     * 它们与handle方法的区别在于handle方法会处理正常计算值和异常，因此它可以屏蔽异常，避免异常继续抛出。
     * 而thenApply方法只是用来处理正常值，因此一旦有异常就会抛出。
     */
    /**
     * public <U> CompletableFuture<U> 	thenApply(Function<? super T,? extends U> fn)
     * public <U> CompletableFuture<U> 	thenApplyAsync(Function<? super T,? extends U> fn)
     * public <U> CompletableFuture<U> 	thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
     */
    @Test
    public void test() {
        // thenApply(Function<? super T,? extends U> fn)
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> "hello")
                .thenApply(v -> {
                    if ( 1 != 1) {
                        throw new RuntimeException();
                    }
                    return v + " world";
                })
                .thenApplyAsync(v -> {
                    String str = v + "!";
                    return str;
                });
        System.out.println(completableFuture1.join());//hello world!

        // thenApplyAsync(Function<? super T,? extends U> fn)
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> "hello")
                .thenApplyAsync(v -> {
                    if ( 1 != 1) {
                        throw new RuntimeException();
                    }
                    return v + " world";
                });
        System.out.println(completableFuture2.join());//hello world

        // thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(() -> "hello")
                .thenApplyAsync(v -> {
                    if ( 1 != 1) {
                        throw new RuntimeException();
                    }
                    return v + " world";
                }, executorService);
        System.out.println(completableFuture3.join());//hello world
    }


    /******************************** 消费计算结果 ******************************/
    /**
     * 对执行的结果进行消费，不返回新的值
     * public CompletableFuture<Void> 	thenAccept(Consumer<? super T> action)
     * public CompletableFuture<Void> 	thenAcceptAsync(Consumer<? super T> action)
     * public CompletableFuture<Void> 	thenAcceptAsync(Consumer<? super T> action, Executor executor)
     */
    @Test
    public void thenAccept() {
        // ①.thenAccept(Consumer<? super T> action)
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> 100)
                .thenAccept(x -> System.out.println(x * 10));//1000
        System.out.println(completableFuture.join());//null

        // ②.thenAcceptAsync(Consumer<? super T> action)
        CompletableFuture.supplyAsync(() -> 100)
                .thenAcceptAsync(x -> System.out.println(x * 5));//500

        // ③.thenAcceptAsync(Consumer<? super T> action, Executor executor)
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture.supplyAsync(() -> 100)
                .thenAcceptAsync(x -> System.out.println(x * 3), executorService);//300
    }

    /**
     * thenAcceptBoth以及相关方法提供了类似的功能，当两个CompletionStage都正常完成计算的时候，就会执行提供的action，它用来组合另外一个异步的结果。
     *
     * runAfterBoth是当两个CompletionStage都正常完成计算的时候,执行一个Runnable，这个Runnable并不使用计算的结果。
     *
     * public <U> CompletableFuture<Void> 	thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action)
     * public <U> CompletableFuture<Void> 	thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action)
     * public <U> CompletableFuture<Void> 	thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action, Executor executor)
     */
    @Test
    public void thenAcceptBoth() {
        // ①.thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action)
        CompletableFuture.supplyAsync(() -> 100)
                .thenAcceptBoth(CompletableFuture.completedFuture(200), (x, y) -> System.out.println(x + y));//300

        // ②.thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action)
        CompletableFuture.supplyAsync(() -> 100)
                .thenAcceptBothAsync(CompletableFuture.completedFuture(300), (x, y) -> System.out.println(x + y));//400

        // ③.thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action, Executor executor)
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture.supplyAsync(() -> 100)
                .thenAcceptBothAsync(CompletableFuture.completedFuture(400), (x, y) -> System.out.println(x + y), executorService);//500
    }

    /**
     * 两个 CompletableFuture 都正常执行完之后，执行 action，actino是Runnable的，所以不使用两个 CompletableFuture 计算的结果。
     * 当其中一个 CompletableFuture 抛出异常时，不会执行 action。
     * public CompletableFuture<Void> 	runAfterBoth(CompletionStage<?> other,  Runnable action)
     * public CompletableFuture<Void> 	runAfterBoth(CompletionStage<?> other,  Runnable action)
     * public CompletableFuture<Void> 	runAfterBoth(CompletionStage<?> other,  Runnable action)
     */
    @Test
    public void runAfterBoth() throws InterruptedException {
        // ①.runAfterBoth(CompletionStage<?> other, Runnable action)
        CompletableFuture.supplyAsync(() -> 100)
                .runAfterBoth(CompletableFuture.completedFuture(200),
                        () -> System.out.println("执行Runnable任务1"));//执行Runnable任务1

        // ②.runAfterBothAsync(CompletionStage<?> other, Runnable action)
        CompletableFuture.supplyAsync(() -> 1/0)
                .runAfterBothAsync(CompletableFuture.completedFuture(300),
                        () -> System.out.println("执行Runnable任务2"));

        // ③.runAfterBothAsync(CompletionStage<?> other, Runnable action, Executor executor)
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture.supplyAsync(() -> 100)
                .runAfterBothAsync(CompletableFuture.completedFuture(300),
                        () -> System.out.println("执行Runnable任务3"), executorService);//执行Runnable任务3
    }

    /**
     * thenRun 方法，当 CompletableFuture 计算完成的时候执行 action，与 thenAccept 不同，thenRun 不使用计算的结果。
     * 如果 CompletableFuture 计算抛出异常并且没有捕获（没有 exceptionally方法），thenRun 则不执行
     *
     * public CompletableFuture<Void> 	thenRun(Runnable action)
     * public CompletableFuture<Void> 	thenRunAsync(Runnable action)
     * public CompletableFuture<Void> 	thenRunAsync(Runnable action, Executor executor)
     */
    @Test
    public void thenRun() {
        // ①.thenRun(Runnable action)
        CompletableFuture.supplyAsync(() -> 100)
                .thenRun(() -> System.out.println("CompletableFuture 计算完成，执行 action"));//CompletableFuture 计算完成，执行 action

        // ②.thenRunAsync(Runnable action)
        CompletableFuture.supplyAsync(() -> 200)
                .thenRunAsync(() -> System.out.println("CompletableFuture 计算完成，执行 action"));//CompletableFuture 计算完成，执行 action

        // ③.thenRunAsync(Runnable action, Executor executor)
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture.supplyAsync(() -> 1/0)
                .exceptionally(e -> {
                    System.out.println(e);// java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
                    return 1;
                })
                .thenRunAsync(() -> System.out.println("CompletableFuture 计算完成，执行 action"), executorService);//CompletableFuture 计算完成，执行 action
    }


    /**
     * 总结：
     * 因此，你可以根据方法的参数的类型来加速你的记忆。
     * Runnable类型的参数会忽略计算的结果，Consumer是纯消费计算结果，BiConsumer会组合另外一个CompletionStage纯消费，
     * Function会对计算结果做转换，BiFunction会组合另外一个CompletionStage的计算结果做转换。
     */


    /******************************** 组合 ******************************/
    /**
     * 这一组方法接受一个Function作为参数，这个Function的输入是当前的CompletableFuture的计算值，
     * 返回结果将是一个新的CompletableFuture，这个新的CompletableFuture会组合原来的CompletableFuture和函数返回的CompletableFuture。
     *
     * public <U> CompletableFuture<U> 	thenCompose(Function<? super T,? extends CompletionStage<U>> fn)
     * public <U> CompletableFuture<U> 	thenComposeAsync(Function<? super T,? extends CompletionStage<U>> fn)
     * public <U> CompletableFuture<U> 	thenComposeAsync(Function<? super T,? extends CompletionStage<U>> fn, Executor executor)
     */
    @Test
    public void thenCompose() {
        // ①.thenCompose(Function<? super T,? extends CompletionStage<U>> fn)
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> 100)
                .thenCompose(x -> CompletableFuture.supplyAsync(() -> x + 100));
        System.out.println(completableFuture1.join());//200

        // ②.thenComposeAsync(Function<? super T,? extends CompletionStage<U>> fn)
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> 100)
                .thenComposeAsync(x -> CompletableFuture.supplyAsync(() -> x + 200));
        System.out.println(completableFuture2.join());//300

        // ③.thenComposeAsync(Function<? super T,? extends CompletionStage<U>> fn, Executor executor)
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture<Integer> completableFuture3 = CompletableFuture.supplyAsync(() -> 100)
                .thenComposeAsync(x -> CompletableFuture.supplyAsync(() -> x + 300), executorService);
        System.out.println(completableFuture3.join());//400
    }

    /**
     * thenCombine()
     * 两个CompletionStage是并行执行的，它们之间并没有先后依赖顺序，other并不会等待先前的CompletableFuture执行完毕后再执行。
     *
     * 其实从功能上来讲,它们的功能更类似thenAcceptBoth，只不过thenAcceptBoth是纯消费，它的函数参数没有返回值，而thenCombine的函数参数fn有返回值。
     *
     * public <U,V> CompletableFuture<V> 	thenCombine(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn)
     * public <U,V> CompletableFuture<V> 	thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn)
     * public <U,V> CompletableFuture<V> 	thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn, Executor executor)
     */
    @Test
    public void thenCombine() {
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.completedFuture(100);
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.completedFuture(200);
        CompletableFuture<Integer> result = completableFuture1.thenCombine(completableFuture2, (x, y) -> x + y);
        System.out.println(result.join());//300
    }

    /**
     * thenAcceptBoth 和 runAfterBoth 是当两个 CompletableFuture 都计算完成，
     * 而我们下面要了解的方法是当任意一个 CompletableFuture 计算完成的时候就会执行。
     *
     * public CompletableFuture<Void> 	acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action)
     * public CompletableFuture<Void> 	acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action)
     * public CompletableFuture<Void> 	acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action, Executor executor)
     *
     * public <U> CompletableFuture<U> 	applyToEither(CompletionStage<? extends T> other, Function<? super T,U> fn)
     * public <U> CompletableFuture<U> 	applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T,U> fn)
     * public <U> CompletableFuture<U> 	applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T,U> fn, Executor executor)
     */
    @Test
    public void acceptEiter() {
        Random random = new Random();
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 200;
        }), x -> System.out.println(x));// 返回结果不固定，100或者200，哪个CompletableFuture 先执行完就返回哪个结果
    }

    @Test
    public void applyToEither() {
        Random random = new Random();
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 200;
        }), x -> x);
        System.out.println(completableFuture.join());// 返回结果不固定，100或者200，哪个CompletableFuture 先执行完就返回哪个结果
    }


    /***************************** 辅助方法 allOf 和 anyOf **************************/
    /**
     * allOf方法是当所有的CompletableFuture都执行完后执行计算。
     * anyOf方法是当任意一个CompletableFuture执行完后就会执行计算，计算的结果相同。
     *
     * public static CompletableFuture<Void> 	allOf(CompletableFuture<?>... cfs)
     * public static CompletableFuture<Object> 	anyOf(CompletableFuture<?>... cfs)
     */
    @Test
    public void allOf() {
        Random random = new Random();
        CompletableFuture future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });
        CompletableFuture future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 200;
        });
        CompletableFuture future3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 300;
        });

        // allOf()
        CompletableFuture<Void> allOf = CompletableFuture.allOf(future1, future2, future3);
        System.out.println(allOf.join());//null
        System.out.println("future1: " + future1.join());//future1: 100
        System.out.println("future2: " + future2.join());//future2: 200
        System.out.println("future3: " + future3.join());//future3: 300

        // anyOf()
        CompletableFuture anyOf = CompletableFuture.anyOf(future1, future2, future3);
        System.out.println("anyOf 结果: " + anyOf.join());//anyOf 结果: 100
    }


    /**
     * 扩展：
     * 将多个CompletableFuture组合成一个CompletableFuture，这个组合后的CompletableFuture的计算结果是个List，
     * 它包含前面所有的CompletableFuture的计算结果
     */
    public static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
        CompletableFuture<Void> allDoneFuture = CompletableFuture
                .allOf(futures.toArray(new CompletableFuture[futures.size()]));
        return allDoneFuture.thenApply(v -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList()));
    }
    @Test
    public void testSequence() {
        List<CompletableFuture<Integer>> list = Lists.newArrayList(
                CompletableFuture.completedFuture(100),
                CompletableFuture.completedFuture(200),
                CompletableFuture.completedFuture(300)
        );
        CompletableFuture<List<Integer>> future = sequence(list);
        System.out.println(future.join());//[100, 200, 300]
    }
}
