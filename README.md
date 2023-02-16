Let's use Scala CLI to generate a Bloop project, and also get the error:
```text
$ scala-cli Hello.scala
Compiling project (Scala 3.3.0-RC2, JVM)
Error compiling project (Scala 3.3.0-RC2, JVM)
Error: Unexpected error when compiling project_9a380f230a-70bf441a95: 'Failed to find name hashes for Hello$.$method$hello$macro$1'
Compilation failed
```

Let's compile ourselves the project with `bloop`:
```text
$ cd .scala-build

$ bloop about
bloop v1.5.6

Using Scala v2.12.15 and Zinc v1.7.2
Running on Java JDK v17.0.5 (/Users/alexandre/Library/Caches/Coursier/arc/https/github.com/graalvm/graalvm-ce-builds/releases/download/vm-22.3.0/graalvm-ce-java17-darwin-amd64-22.3.0.tar.gz/graalvm-ce-java17-22.3.0/Contents/Home)
  -> Supports debugging user code, Java Debug Interface (JDI) is available.
Maintained by the Scala Center and the community.

$ bloop projects
project_9a380f230a-70bf441a95

$ bloop compile project_9a380f230a-70bf441a95
Compiling project_9a380f230a-70bf441a95 (1 Scala source)
Compiled project_9a380f230a-70bf441a95 (1474ms)
[E] Unexpected error when compiling project_9a380f230a-70bf441a95: 'Failed to find name hashes for Hello$.$method$hello$macro$1'
[E] Failed to compile 'project_9a380f230a-70bf441a95'
```

Let's use the Scala CLI Bloop server to get the full server stacktrace:
```text
$ scala-cli bloop about
bloop v1.5.6-sc-3

Using Scala v2.12.17 and Zinc v1.8.0
Running on Java JDK v17 (/Users/alexandre/Library/Caches/Coursier/arc/https/github.com/adoptium/temurin17-binaries/releases/download/jdk-17%252B35/OpenJDK17-jdk_x64_mac_hotspot_17_35.tar.gz/jdk-17+35/Contents/Home)
  -> Supports debugging user code, Java Debug Interface (JDI) is available.
Maintained by the Scala Center and the community.

$ scala-cli bloop compile project_9a380f230a-70bf441a95
Compiling project_9a380f230a-70bf441a95 (1 Scala source)
Compiled project_9a380f230a-70bf441a95 (371ms)
[E] Unexpected error when compiling project_9a380f230a-70bf441a95: 'Failed to find name hashes for Hello$.$method$hello$macro$1'
[E] Failed to compile 'project_9a380f230a-70bf441a95'

$ scala-cli bloop output
…
java.lang.RuntimeException: Failed to find name hashes for Hello$.$method$hello$macro$1
        at scala.sys.package$.error(package.scala:30)
        at sbt.internal.inc.bloop.internal.BloopAnalysisCallback.nameHashesForCompanions(BloopAnalysisCallback.scala:3>
        at sbt.internal.inc.bloop.internal.BloopAnalysisCallback.analyzeClass(BloopAnalysisCallback.scala:360)
        at sbt.internal.inc.bloop.internal.BloopAnalysisCallback.$anonfun$addProductsAndDeps$4(BloopAnalysisCallback.s>
        at scala.collection.TraversableLike.$anonfun$map$1(TraversableLike.scala:286)
        at scala.collection.mutable.HashSet.foreach(HashSet.scala:79)
        at scala.collection.TraversableLike.map(TraversableLike.scala:286)
        at scala.collection.TraversableLike.map$(TraversableLike.scala:279)
        at scala.collection.mutable.AbstractSet.scala$collection$SetLike$$super$map(Set.scala:50)
        at scala.collection.SetLike.map(SetLike.scala:105)
        at scala.collection.SetLike.map$(SetLike.scala:105)
        at scala.collection.mutable.AbstractSet.map(Set.scala:50)
        at sbt.internal.inc.bloop.internal.BloopAnalysisCallback.$anonfun$addProductsAndDeps$1(BloopAnalysisCallback.s>
        at scala.collection.TraversableOnce$folder$1.apply(TraversableOnce.scala:196)
        at scala.collection.TraversableOnce$folder$1.apply(TraversableOnce.scala:194)
        at scala.collection.mutable.HashSet.foreach(HashSet.scala:79)
        at scala.collection.TraversableOnce.foldLeft(TraversableOnce.scala:199)
        at scala.collection.TraversableOnce.foldLeft$(TraversableOnce.scala:192)
        at scala.collection.AbstractTraversable.foldLeft(Traversable.scala:108)
        at sbt.internal.inc.bloop.internal.BloopAnalysisCallback.addProductsAndDeps(BloopAnalysisCallback.scala:374)
        at sbt.internal.inc.bloop.internal.BloopAnalysisCallback.get(BloopAnalysisCallback.scala:316)
        at sbt.internal.inc.bloop.internal.BloopIncremental$.$anonfun$compileIncremental$5(BloopIncremental.scala:127)
        at bloop.task.Task.$anonfun$runAsync$7(Task.scala:265)
        at monix.eval.Task$Map.apply(Task.scala:4604)
        at monix.eval.Task$Map.apply(Task.scala:4600)
        at monix.eval.internal.TaskRunLoop$.startFull(TaskRunLoop.scala:170)
        at monix.eval.internal.TaskRestartCallback.syncOnSuccess(TaskRestartCallback.scala:101)
        at monix.eval.internal.TaskRestartCallback.onSuccess(TaskRestartCallback.scala:74)
        at monix.eval.internal.TaskExecuteOn$AsyncRegister$$anon$1.run(TaskExecuteOn.scala:71)
        at java.base/java.util.concurrent.ForkJoinTask$RunnableExecuteAction.exec(ForkJoinTask.java:1395)
        at java.base/java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:373)
        at java.base/java.util.concurrent.ForkJoinPool$WorkQueue.topLevelExec(ForkJoinPool.java:1182)
        at java.base/java.util.concurrent.ForkJoinPool.scan(ForkJoinPool.java:1655)
        at java.base/java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1622)
        at java.base/java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:165)
```
