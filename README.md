# NetworkAPI-V1
This is a network API that simplifies methods and can control the network through an API.

# Event
An "own" event system is integrated in the API. 
- This means that (in Spigot) you no longer need an "implements listener" and an "@EventHandler, public void handle(PostLoginEvent event) {}" but simply: "((SpigotEventManager) NetworkAPI.getInstance().getEvents().getManager())..register(PlayerLoginEvent.class, event -> {));". In this method you can do all the things you could do with the normal listener!
- For bungeecord, unfortunately, the whole thing looks a little different. For Bungeecord you still need "implements listener" and "@EventHandler, public void handle(PostLoginEvent event) {}" but registering listeners has become easy. For this you simply do: "((ProxiedEventManager) NetworkAPI.getInstance().getEvents().getManager()).registerListener(this);"

# Cloud
A cloud simplifier for CloudNet-v2 is integrated in NetworkAPI-v1. This means that methods such as changing the server status/maintenance are easier. Or the CloudPlayer query is also easier and much more. Examples: 
- CloudPlayer-UUID: CloudSimplifier.getInstance().getIPlayerManager().getCloudPlayer(uuid);
- CloudPlayer-NAME: CloudSimplifier.getInstance().getIPlayerManager().getCloudPlayer(name);
- OfflinePlayer-UUID: CloudSimplifier.getInstance().getIPlayerManager().getOfflinePlayer(uuid);
- OfflinePlayer-NAME: CloudSimplifier.getInstance().getIPlayerManager().getOfflinePlayer(name);
- Maintenance-Togge (for current-group): CloudSimplifier.getInstance().getIServiceManager().changeMaintenance( true / fale );
- Maintenance-Togge (for extra group ): CloudSimplifier.getInstance().getIServiceManager().changeMaintenance( true / fale , GROUPNAME );
- Get cached CloudPlayers from the current server: CloudSimplifier.getInstance().getIServiceManager().getCachedCloudPlayers(); - it returns a map with a UUID and the CloudPlayer!

# Player
Coin management etc. is possible with the "NetworkPlayer". The "NetworkPlayer" can be queried with the query "final INetworkPlayer<?> iNetworkPlayer = NetworkAPI.getInstance().getiNetworkPlayerCache().getINetworkPlayer(playerLoginEvent.getPlayer().getName());". Example: iNetworkPlayer.getCoin();

# Database
MongoDB - Installation: 
MongoDB - 2021 (Debian 10)

1. Installation:
 - sudo apt-get install gnupg#
 - wget -qO - https://www.mongodb.org/static/pgp/server-5.0.asc | sudo apt-key add -
 - echo "deb http://repo.mongodb.org/apt/debian buster/mongodb-org/5.0 main" | sudo tee /etc/apt/sources.list.d/mongodb-org-5.0.list
 - sudo apt-get update
 - sudo apt-get install -y mongodb-org
 
2. Authentifikation aktivieren (Passwort):
 - mongo
 - use admin
 - db.createUser(
{
    user: "Gewünschter Name",
    pwd: "Gewünschtes Passwort",
    roles: [
              { role: "userAdminAnyDatabase", db: "admin" },
              { role: "readWriteAnyDatabase", db: "admin" },
              { role: "dbAdminAnyDatabase", db: "admin" },
              { role: "clusterAdmin", db: "admin" }
           ]
})
 - nano /etc/mongod.conf
 
3. Konfiguration ändern:
 - "#" bei 'security' entfernen
 - Darunter 'authorization: "enabled"' hinzufügen ('' nicht beachten)
