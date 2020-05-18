# Hands-on Netty

Cette mise en pratique a pour objectif de présenter les fonctionnalités de base de la programmation réseau asynchrone 
avec le framework Netty.


## Netty

Netty est un framework pour les I/O non bloquantes conçu pour le développement d'applications réseaux 
asynchrones en java, maintenables, évolutives et performantes.

Il simplifie la programmation réseau en permettant un développement rapide et facile des applications réseau telles que
les serveurs de protocole et les clients. Pour plus détails voir [https://netty.io](https://netty.io/)


## Pourquoi Netty 

Comprendre le fonctionnement de Netty est très important parcequ'il est utilisé aujourd'hui comme backend pour la
la gestion asynchrone des connexions réseau en environnement concurrent par beaucoup de frameworks `reactive` comme 
`Vert.x`, `Reactor`, `RxNetty`, etc.

Afin de bien profiter de ces frameworks haut niveau qui masquent la complexité, il est toujours bon de savoir se qui
se passe dans Netty. Ces connaissances de base peuvent aider à comprendre des dysfonctionnements dans nos applications 
en environnement de production. 

 
## Comment utiliser

   * `netty-mini-chat`  (voir `netty-mini-chat/README`)
