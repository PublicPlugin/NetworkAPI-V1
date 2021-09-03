package de.golgolex.network.api.database.mongod;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import de.golgolex.network.api.api.object.user.NetworkPlayerDataProperty;
import org.bson.Document;

import java.util.UUID;

/*
===========================================================================================================================
#
# Copyright (c) 2021 Pascal Kurz
# Class created at 12.08.2021, 04:14
# Class created by: Pascal
#
# Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation
# files (the "Software"),
# to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish,
# distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software
# is furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
# INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
# AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
#  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
#
===========================================================================================================================
*/

public class SimpleMongoFetcher implements IMongoFetcher {

    private final MongoCollection<Document> collection;

    public SimpleMongoFetcher(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    @Override
    public void updateDocument(UUID uuid, Document document) {
        this.collection.replaceOne(Filters.eq(NetworkPlayerDataProperty.UUID.name(), uuid), document);
    }

    @Override
    public void updateDocument(String name, Document document) {
        this.collection.replaceOne(Filters.eq(NetworkPlayerDataProperty.NAME.name(), name), document);
    }

    @Override
    public Document searchPlayer(UUID uuid) {
        return this.collection.find().filter(Filters.eq(NetworkPlayerDataProperty.UUID.name(), uuid)).first();
    }

    @Override
    public Document searchPlayer(String username) {
        return this.collection.find().filter(Filters.eq(NetworkPlayerDataProperty.NAME.name(), username)).first();
    }

    @Override
    public void updatePlayer(final UUID uuid, final String key, final Object object) {
        Document update = new Document(key, object);
        Document updater = new Document("$set", update);
        this.collection.updateOne(Filters.eq(NetworkPlayerDataProperty.UUID.name(), uuid), updater);
    }

    @Override
    public void updatePlayer(final String username, final String key, final Object object) {
        Document update = new Document(key, object);
        Document updater = new Document("$set", update);
        this.collection.updateOne(Filters.eq(NetworkPlayerDataProperty.NAME.name(), username), updater);
    }

}