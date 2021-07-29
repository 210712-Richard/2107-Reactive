# Cassandra

A distributed NoSQL database.

## CAP Theorem

Choose Two:
* *Consistency*: Every read to the database receives the most recent writes or an error
* *Availability*: Every request receives a non-error response
* *Partition Tolerance*: The system continues to operate despite an arbitrary number of messages being dropped by the network between nodes.

In an SQL database, we usually choose Consistency and Availability. ACID transactions are therefore possible.

In most NoSQL databases, data is partitioned and so we have choose between consistency and availability. If the partitions fail to communicate with one another (messages are dropped), then one of two things can happen:
* Cancel the operation, send back an error (sacrifices Availability)
* Proceed with the operation, but maybe with stale data (sacfifices Consistency)

__Partition__: We can separate the db into pieces that each operate independently of one another such that when one partition (or node) fails, we don't lose the entire db. In a non-partitioned system, the db is a single point of failure. If the db goes down, you have to wait for it to come back up.

### BASE
* **B**asically **A**vailable - Data is guaranteed to be available. It might not be up to date.
* **S**oft-state - The system may change over time. Even if you are not sending requests, the system might still be working on a previous request.
* **E**ventually consistent - When left alone, eventually all nodes will contain the most up to date data.

## NoSQL
NoSQL (sometimes referred to as *N*ot *O*nly *SQL*) databases are databases that provide us a way to store and retrieve data in a way that is not based upone the Structured Query Language and the relational data it manages.

NoSQL refers to any database that isn't a relational database (based on SQL) and therefore is a catch-all term and doesn't really mean anything.

Relational databases have a fixed schema and NoSQL databases do not have to.

NoSQL databases are excellent at a variety of things:
* Data that has no consistent structure and would therefore be really *inefficient* to store in a relational database.
* Data that must not be lost (partioned databases)

## Cassandra round 2
*Distributed database*: No single point of failure, each node is identical (though it does not necessarily store the same data or even the same types of data).

Highly scalable, fault-tolerant, highly available, eventually consistent, column-oriented

### Features
* Elastic Scalability: More hardware added to your cluster means you can store more data and handle more request.
* Linearly Scalable: You scale capacity linearly with the addition of nodes.
* Always on: no single point of failure
* Flexible data storage: allows structured, semi-structured, and unstructed data.
* Distributes and replicates data as needed according to configuration.

## Relational vs Non-Relational Tweet

Tweet: Text, Author, Hashtags, and Mentions
##### How we would store the data in a NoSQL database
```json
{
    'author': 'Richard',
    'text': '@Jacob You smell. #roasted',
    'mentions': ['Jacob'],
    'hashtags': ['roasted']
}
{
    'author': 'Jacob',
    'text': 'Congratulations',
    'type': 'CONGRATS'
}
```
```
id  |   author      | text                          | hashtag       | mentions        | Type
1   |   Richard     | '@Jacob You smell. #roasted   |   ['Jacob']   |   ['roasted']   | null
2   |   Jacob       | 'Congratulations'             | null          | null             | 'CONGRATS'
```
##### How we would store the data in an SQL database
```
Tweet Table
id  | author     | text                             | Congrats
1   | Richard    | '@Jacob You smell. #roasted'     | null
2   | Jacob      | 'Congratulations'                | 'CONGRATS'

Tweet-Mention Table
tweet-id    |   Mention
1           |   Jacob

Tweet-Hashtag Table
tweet-id    | Hashtag
1           | 'roasted'
```

### Definitions
* **Cassandra Query Language**: A SQL-like language used to create and update database schemas and access and modify data.
* **Keyspace**: A definition for how data is replicated.
* **Table**: Defines the typed schema for a collection of partitions. You can add new columns (and partitions) to the data whenever necessary.
* **Partition**: Defines the part of the primary key all rows in Cassandra must have for that table.
* **Row**: A collection of columns identified by a primary key
* **Column**: A single piece of data with a type belonging to a row.

### Data Types

* BigInt: Long (64-bit integer value)
* Blob: Arbitrary bytes (Binary large object)
* Boolean: true/false
* Date: with no time
* Decimal: variable precision decimal, larger than double
* Double: Double (64-bit decimal value)
* Float: Float (32-bit decimal value)
* Int: Integer (32-bit integer value)
* Text: UTF8 encoded String
* Time
* Timestamp
* uuid: 128bit number
* varchar: utf8 encoded string
* varint: BigInteger (arbitrary length int)

Collections:
* list - an *ordered* group of one or more elements
* map - an object consisting of key-value pairs
* set - a group of one or more unique elements
* tuple - a group of 2-3 items treated as a single item

### Primary Key

A unique identifier for a group of data in a table.

**Candidate Key**: A piece of a group of data (a column or group of columns in a table) that *could* be utilized to identify that group.
Good Candidate Key:
* Exists for each group (row) of data.
* Unique for each group of data.
* Non-volatile (doesn't change)
* Non-sensitive

Types of keys:
* **Natural Key**: A piece of data that arises from the natural dataset. For example: someone's name, someone's ssn, a book's isbn
* **Surrogate Key**: A piece of data added to the set (generated) in order to serve as the primary key.

### Primary Keys in Cassandra
A primary key in Cassandra can have two parts. The first part is known as the Partition key and the second part is known as the clustering key. If a Cassandra table only has a single column primary key that it only has a partition key and no clustering key. Otherwise, the first column in a primary key is the partition key and the remaining columns are clustering keys.

#### Partition Key
The key in charge of partitioning data. Data locality (which partition the data is stored in) is determined by the hashing of the Partition key of the data.

Choosing a partition key with low variance (a lot of data has the same value) might result in clustering in one partiton over the others.

##### Complex Partition Keys
You can make more than one column into the partition key by declaring it as part of a tuple.

* Note the below is cql, but Markdown isn't smart enough to know what that is, so I used sql
```sql
create table book (title varchar, author varchar, publisher varchar, price float, primary key ( (title, author), publisher)
```
The above snippet creates a table where the partition key is the combination of title *and* author, possibly creating a very large number of partitions, with a cluster key of publisher.

#### Complex Key
`create table if not exists book (title, author, publisher, genre, primary key (title, author, publisher))`
Creates a primary key with a Partition key of `title`, a clustering key of `author` and a second clustering key of `publisher`

#### Clustering Key
A key that orders data within a partition. In our above example, we have partitioned books by title, separating them and are now ordering books based on their author and then their publisher. A query that wanted to get all books by a single author would have to query across multiple partitions but would at least be able to get the author information quickly on each partition.

#### Optimizing Queries
In order to optimize our queries we need to model out tables effectively. We want to spread our data evenly across partitions and we want to minimize the number of partitions we need to query with each query.

If we know that we usually want to retrieve a list of books by author with might consider using it as the partition key instead of title.

#### Indexing
An index is an entity that tracks all the records for a particular column in the database. Every node in the database has access to the index which tells them where (which partition) a particular value (row) resides. The index is *just* the primary keys of each row. Since the index has to be updated every time you insert into the database, this can affect performance (which is why we don't make the entire table into the primary key). When you're trying to query the database, checking the index will quickly tell Cassandra where to look, speeding up retrieval. This allows us to query effiently and it's why we cannot query on elements that are not in the primary key.

#### Secondary Indexing
**NOTE:** AWS Keyspaces does not support Secondary Indices

A Secondary Index is an index that we create in order to enable querying on an element that is not part of the primary key. We essentially create a secondary primary key with it's own index that we can query on. This greatly impacts performance (as now we have to update *two* indices on *each* node in the cluster for *every* write to the table), but enables more powerful querying.


### Data Replication

Every Keyspace (set of tables) in a Cassandra cluster defines a replication strategy. This replication strategy dictates how and how often data is replicated or copied across different nodes (cassandra instances) in the cluster.

* **SimpleStrategy**: For use when all clusters exist locally, as in, all the clusters are on the same rack in the same datacenter, as in, they're all physically connected instead of connected via the internet. Places the first replica of a pice of data on a node determined by the partioner and additional replicas are placed on the next node clockwise in the ring.

* **NetworkTopologyStrategy**: Places replicas in the same datacenter by walking the ring clockwise until reaching the first node in a different rack (different computers/not physically connected) to reduce the chance that a rack failure can prevent data accessibility. Can also be configured ot place 2 or 3 replicas in EVERY datacenter that we have nodes in (in case of datacenter failure).

### Deletion
How do we ensure that all nodes with a piece of data in it no longer have that piece of data in it, if we also require high availability and can't just fail to delete something if one of the nodes with that piece of data in it does not respond?

If you replicate a piece three times, we need to delete three times, but if a node is unavailable it doesn't get the delete request and then it will create ZOMBIE DATA when it comes back, sees that it is the only replica of that data, and causes the data to become replicated.

#### Tombstones

When we delete data in Cassandra we don't actually delete it right away. We turn it into a "Tombstone". This tombstone exists for a specified "grace period" during which the data will not be returned by queries and the tombstone will be replicated to any nodes that previously held that data. At the end of the grace period, the data is erased.

* problem: if the grace periods ends before a node containing the data is able to rejoin the ring, we still get zombie data. The default grace period is 10 days.

## Data Access Objects
Data Access Objects are objects that handle the database access for the rest of the application, allowing you to modularize that functionality to a specific location and loosely couple database implementation from your app. DAO's generally consist of CRUD operations with more complex functionality being part of the Service layer, though a DAO might have some more complex operations if needed.

### CRUD
* CREATE - Insert
* READ - Select
* UPDATE - Update
* DELETE - Delete

## Using AWS Keyspaces
### Set Up Our IAM User
*IAM*: A service that manages access to other services within your account and from outside your account.
1. Navigate to the IAM service.
2. Create a `Group` with the `Keyspaces Full Access` policy.
   1. Select `User Groups`
   2. `Create Group`
   3. Give the group a name
   4. Attach permissions polices: search `AmazonKeyspacesFullAccess`, check the box.
   5. `Create Group`
3. Create a user with programmatic access and add it to the gropu we just created.
   1. Select `Users`
   2. `Add Users`
   3. Select a username
   4. Select `Programmatic access`
   5. `Next: Permissions`
   6. Under `Add user to Group`, select the group we created, and then select `Next: Tags`
   7. `Next: Review`
   8. `Create User`
   9. `Close`
4.  Generate programmatic credentials.
    1.  Select the user you just created.
    2.  Select `Security Credentials` 
    3.  Under `Credentials for Amazon Keyspaces` select `Generate credentials`
    4.  Download those and keep them safe. NEVER PUSH THESE CREDENTIALS TO A REPOSITORY. PUSHING CREDENTIALS TO A REPOSITORY IS HOW YOU PAY AWS $10,000
5. Determine what region you are in. (I'm in us-east-2)

### Create our Java Setup
1. Have a Java Project you wish to create a Keystore for.

#### Datastax
1. Obtain a dependency for Datastax's Cassandra Driver
   ```xml
        <!-- Datastax Cassandra Driver -->
		<dependency>
			<groupId>com.datastax.oss</groupId>
			<artifactId>java-driver-core</artifactId>
			<version>4.11.0</version>
		</dependency>
   ```
2. Obtain a dependency for the AWS Plugin for Cassandra authentication
   1. First, you'll have to add the dependency manager for AWS SDK:
    ```xml
    <dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>software.amazon.awssdk</groupId>
				<artifactId>bom</artifactId>
				<version>2.15.43</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>
    ```
    ```xml
        <!-- AWS plugin for Cassandra authentication -->
		<dependency>
			<groupId>software.aws.mcs</groupId>
			<artifactId>aws-sigv4-auth-cassandra-java-driver-plugin</artifactId>
			<version>4.0.4</version>
		</dependency>
		<dependency>
			<groupId>software.amazon.awssdk</groupId>
			<artifactId>s3</artifactId>
		</dependency>

        <dependency>
			<groupId>org.apache.httpcomponents</groupId>
			<artifactId>httpcore</artifactId>
			<version>4.4.14</version>
		</dependency>
    ```
3. Create file named `application.conf`

    ```
    datastax-java-driver {

        basic.contact-points = [ "cassandra.<YOUR AWS REGION HERE>.amazonaws.com:9142"]
        advanced.auth-provider{
            class = PlainTextAuthProvider
            username = ${AWS_USER}
            password = ${AWS_PASS}
        }
        basic.load-balancing-policy {
            local-datacenter = "<YOUR AWS REGION HERE>"
        }

        advanced.ssl-engine-factory {
            class = DefaultSslEngineFactory
            truststore-path = "./src/main/resources/cassandra_truststore.jks"
            truststore-password = "p4ssw0rd"
        }
    }
    ```
    3. Set up the `AWS_USER` and `AWS_PASS` environment variables.
a
### Setup our Trustore for AWS Keyspaces
1. Open a BASH terminal inside of `src/main/resources` folder.
2. `curl https://certs.secureserver.net/repository/sf-class2-root.crt -O`
3. `openssl x509 -outform der -in sf-class2-root.crt -out temp_file.der`
4. `keytool -import -alias cassandra -keystore cassandra_truststore.jks -file temp_file.der`
   1. Highly suggest the password: `p4ssw0rd`
   2. say yes when it asks.

### Create our Keyspace
1. Go to Amazon Keyspaces
2. `Create Keyspace`
3. Give it a name
4. `Create Keyspace`