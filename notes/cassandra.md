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