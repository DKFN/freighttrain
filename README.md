```aidl
                    ___                  _     __ _  _       _     _____                  _                                
     o O O         | __|   _ _   ___    (_)   / _` || |_    | |_  |_   _|   _ _  __ _    (_)   _ _                         
    o        _     | _|   | '_| / -_)   | |   \__, || ' \   |  _|   | |    | '_|/ _` |   | |  | ' \     _      _     ___    
    S__[O] _(_)_  _|_|_  _|_|_  \___|  _|_|_  |___/ |_||_|  _\__|  _|_|_  _|_|_ \__,_|  _|_|_ |_||_|  _(_)_  _(_)_  (0.1)  
 ){======_|"""""_| """ _|"""""_|"""""_|"""""_|"""""_|"""""_|"""""_|"""""_|"""""_|"""""_|"""""_|"""""_|"""""_|"""""_|"""""| 
 ./o--000"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-' 
```

Freight Train aims to a simple docker linking tool configured  with YAML

Its goal will be launch a full dev environnement with all its dependencies dockers out of the box 
(databases, dependant services, etc ...) providing developpers with decent mocking for developpement.

It is still heavily WIP and is mainly done for my own use but feel free to use it or contribute to it.

#My first .freight file

# How to run ?

You have two ways to run the deamon, both needs Docker at least and one needs Java + Docker

### I have a debian based distro with java installed

Download the latest .deb build : http://cdn.deadlykungfu.ninja/freighttrain.deb

There you can run `dpkg -i freightrain.deb`

You can then go to a directory with a .freight file and simply run `freighttrain `

### I don't have debian based distro or java

If you have docker you can run the freighttrain image in 


# Build
Run in dev with example .freight file :

`sbt run`

Build binary for portable use

```sbt dist``` (Java compilation)

Debian binary (still needs java installed but its in .deb)

```sbt debian:packageBin```

All are exported in ./target directory


