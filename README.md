# NetworkAPI-V1
This is a network API that simplifies methods and can control the network through an API.

# Database
MongoDB - Installation: 

  __  __                         _____  ____  
 |  \/  |                       |  __ \|  _ \ 
 | \  / | ___  _ __   __ _  ___ | |  | | |_) |
 | |\/| |/ _ \| '_ \ / _` |/ _ \| |  | |  _ < 
 | |  | | (_) | | | | (_| | (_) | |__| | |_) |
 |_|  |_|\___/|_| |_|\__, |\___/|_____/|____/ 
                      __/ |                   
                     |___/                    
------------------------------------------------
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
 - # bei 'security' entfernen
 - Darunter 'authorization: "enabled"' hinzufügen ('' nicht beachten)
