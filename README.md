# LifeSim
A simple life simulator game. Each character has its own set of actions that can happen to them, and they choose from these.
The game also has events, which happen with a given probability.

## Running
Needs Java 22+ and Maven.

```sh
mvn compile exec:java -Dexec.mainClass=org.model.Main   # 500 characters, one of them yours
```

## OOP
I aimed to be as object-oriented as this simple project demands, and to make it very extensible.
Actions, stat types, characters, player types and almost everything else can be easily extended.
The game is truly a simulation: every character is equal, whether a player or a robot plays it.

## UI
The UI follows MVC with Java Swing, and is completely independent of the model.

## Credits
Character names come from [name-machine](https://github.com/ajbrown/name-machine) by ajbrown (Apache 2.0), included under `org.model.helper.ajbrown.namemachine`.
