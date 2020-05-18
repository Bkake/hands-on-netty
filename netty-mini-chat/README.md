# Netty mini chat

Netty mini chat est une petite application server de chat qui utilise la communication TCP.

L'objectif est la mise en pratique de la création d'une application serveur basée sur le framework 
[Netty](https://netty.io/).

## Fonctionnalités
* Génération d'un login fictif via l'id de l'adresse ip du client netcat
* Un client netcat peut envoyer un message à tout les autres clients netcat connectés
* Un client netcat peut envoyé un message à un client particulier en utilisant le login du client

## Comment utiliser 


* Prérequis 
```
    JDK 11 et +
```

* Run de l'application les scripts build et de run 
```
    $ ./build.sh && ./run.sh
```
* Connectez-vous au serveur via la commande netcat. 
* And login with login command.
```
    $ netcat localhost 8080
    CodeWorkers-50792>
```
* Connectez-vous au serveur via la commande netcat sur un autre terminal.
```
    $ netcat localhost 8080
    CodeWorkers-50793>
```

* Encore une autre connexion sur un autre terminal.
```
    $ netcat localhost 8080
    CodeWorkers-50794>
```

* A partir de maintenant, vous pouvez envoyer un message à tous les clients
 en `BroadCast` à partir du `CodeWorkers-50792`
```
    CodeWorkers-50792>Hello CodeWorkers
```

* En suite les deux autres clients receveront le message ci-dessous
```bash
    CodeWorkers-50793> @CodeWorkers-50792 : Hello Code Workers
    CodeWorkers-50794> @CodeWorkers-50792 : Hello Code Workers
```

* Envois d'un message à un client spécifique par exemple `CodeWorkers-50794` à `CodeWorkers-50793` 
```
   CodeWorkers-50794>CodeWorkers-50792::Hi hi mister
```

