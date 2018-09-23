```aidl
                    ___                  _     __ _  _       _     _____                  _                                
     o O O         | __|   _ _   ___    (_)   / _` || |_    | |_  |_   _|   _ _  __ _    (_)   _ _                         
    o        _     | _|   | '_| / -_)   | |   \__, || ' \   |  _|   | |    | '_|/ _` |   | |  | ' \     _      _     ___    
    S__[O] _(_)_  _|_|_  _|_|_  \___|  _|_|_  |___/ |_||_|  _\__|  _|_|_  _|_|_ \__,_|  _|_|_ |_||_|  _(_)_  _(_)_  (0.1)  
 ){======_|"""""_| """ _|"""""_|"""""_|"""""_|"""""_|"""""_|"""""_|"""""_|"""""_|"""""_|"""""_|"""""_|"""""_|"""""_|"""""| 
 ./o--000"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-' 
```
![Build](http://ns309716.ip-188-165-194.eu/app/rest/builds/buildType:%28id:Freighttrain_Build%29/statusIcon.png)

Freight Train aims to a simple docker linking tool configured  with YAML

Its goal will be launch a full dev environnement with all its dependencies dockers out of the box 
(databases, dependant services, etc ...) providing developpers with decent mocking for developpement.

It is still heavily WIP and is mainly done for my own use but feel free to use it or contribute to it.

#My first .freight file

# How to run ?

You have two ways to run the deamon, both needs Docker at least and one needs Java + Docker

- ### I have a debian based distro with java installed

Download the latest .deb build : http://cdn.deadlykungfu.ninja/freighttrain.deb

There you can run `dpkg -i freightrain.deb`

You can then go to a directory with a .freight file and simply run `freighttrain `

- ### I don't have debian based distro or java

If you have docker you can run the freighttrain image in docker !

Just pull the latest version from docker hub (only dev branch available at the moment)

```zsh
docker pull dkfn/freighttrain:dev
```

Go to your project directory containing the .freight file.

You then have to run this command to attach your project directory and the host docker socket to the freighttrain container.

```= ran out of battery sorry=```

# My first dev configuration

Here is an example of a starter dev environnement using a Nginx web server, a redis and a cassandra

Create and empty folder and a .freight text file containing this :
```yaml
MainContainer:
    image: nginx
    ports:
        - [ 80, 80 ]
RedisContainer:
    image: redis
    preScripts:
        - ./tests/import-keys.sh

Cassandra:
    image: cassandra
    preScripts:
        - ./tests/import-keys.sh
```
Launching freighttrain via docker or the binary

After your containers are all launched and accessible via the host machine just like you ran the freighttrain binary or spawned them by hand


# Actual todos
listed by priority order

- Tests
- Provide envs for all containers
- Multiple networks
- Remap ports if used (disablable)
- Daemon option for freighttrain container
- Make a better demo app
- Provide utility commands
- Allow script name parameter for multiple configurations

# Build
Run in dev with example .freight file :

```sbt run```

Build binary for portable use

```sbt dist``` (Java compilation)

Debian binary (still needs java installed but its in .deb)

```sbt debian:packageBin```

All are exported in ./target directory


