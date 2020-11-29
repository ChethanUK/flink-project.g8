# Flink application project using Scala and SBT

To run and test your application locally, you can just execute `sbt run` and then select the main class that contains the Flink job.

```bash
sbt clean assembly
```

You can also package the application into a fat jar with `sbt assembly`,
then submit it as usual, with something like:

```bash
flink run -c org.example.WordCount ./path/to/target/scala-2.11/testme-assembly-0.1-SNAPSHOT.jar
```

## IntelliJ

You can also run your application from within IntelliJ:  

- Select the classpath of the 'mainRunner' module in the run/debug configurations.
- Simply open 'Run -> Edit configurations...' and t
- Then select 'mainRunner' from the "Use classpath of module" dropbox.

## SBT Tasks

List of Task:

- Running the dependencyUpdates command displays the currently available updates:

```bash
sbt dependencyUpdates
```

- Running the dependencyGraph command will output all the dependencies in an ASCII tree graph:

```bash
sbt dependencyGraph
```

## Doit

Install doit:

```bash
pip install doit
```

Then list the `doit tasks` and execute the task:

```bash
doit list 
doit sbtclean
```
