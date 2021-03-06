package de.golgolex.network.api.database;

/*
===========================================================================================================================
# 
# Copyright (c) 2021 Pascal Kurz
# Class created at 02.09.2021, 00:17
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

import de.golgolex.network.api.api.NetworkAPI;
import de.golgolex.network.api.database.mongod.IMongoConnector;
import de.golgolex.network.api.database.mongod.IMongoFetcher;
import de.golgolex.network.api.database.mongod.MongoConnector;
import de.golgolex.network.api.database.mongod.SimpleMongoFetcher;

public abstract class DatabaseAPI {

    private static volatile DatabaseAPI service;

    protected DatabaseAPI() {
        service = this;
    }

    public IMongoFetcher getIMongoFetcher(String collectionName) {
        return new SimpleMongoFetcher(NetworkAPI.getInstance().getNetworkPlayerMongoConnector().getMongoDatabase().getCollection(collectionName));
    }

    public IMongoConnector getIMongoConnector() {
        return new MongoConnector();
    }

    public static DatabaseAPI getService() {
        return service;
    }
}
