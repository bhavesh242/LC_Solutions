Scalability : David Malan

Webhost:  

SFTP: Secure File transfer protocol. Encryption of sensitive data.

VPS: Virtual Private Server. You get your own copy of OS. Sharing resources by getting a slice of hardware not shared by anyone else.  VPS data is private from other customers but not the VPS company.

Eg: Linode, Go Daddy, Amazon EC2

**Vertical Scaling**: If running low on RAM, memory or cpu cycles, just throw new resources to the problem. 

Catch: You will either exhaust money or you will exhaust state of the art technology limitations.

Multi-core machines can handle multiple requests.  

**Horizontal Scaling :** Accepting the ceiling, we can stay below it by rather not buying expensive hardware but using cheap relatively older hardware, instead of one really good maching, n number of slower machines. Example : Data center.

If you have a whole bunch of servers, distribute the server requests over these multiple machines in a fair manner.

` `Load Balancer: to distribute traffic coming on the internet. URL - > return load balancer’s public ip -> which then gets figures out which backend server will fulfill this request.

DNS server can be a load server that can provide server addresses in a round robin order in it’s simplest form. (BIND is a popular dns server). But if we only do round robin 

Downside : Some serves may get more heavyweight users compared to other and the round robin will still continue sending these server requests.  Caching : I you are a heavy user with substantial number of requests and you have cached the server’s IP instead of performing a dns lookup for every request, you will not be sent to a different server with your subsequent requests. Rather, new users will be sent to the other servers, this is a downside of caching. (DNS has a TTL).

Sessions are specific to a given machine and if you are sent to another server via round robin, you will lose your session. And you will need to login again or lose things in your shopping cart etc.

Computers in Datacenters can use RAID.

**RAID**: Redundant Array of Independent Disks. Raid can come in few forms such as RAID0, RaID5, RAID6, RAID10 etc.  All these versions of RAID assume you have multiple hard drives, typically identical in nature. It performs striping of data which means data is written to each hard drives in bits and parts (striping), effectively doubling write speed. (RAID0)

RAID1: You mirror data into both places simultaneously. This helps retrieve data even if one of the drives in down. It has a small overhead too though. 

RAID10: 4 drives, both striping and redundancy

RAID5 and 6 : Middle grounds with RAID1. RAID5 : if you have 3,4,5 drives only one is used for redundancy.

RAID6: any 2 disks can die.

Sticky Sessions:

Shared Storage: FibreChannel, MySQL, NFS.

Cookies:  One option is for the load balancer to insert cookies of their own and then the requesting machine can send back these cookie data to the load balancer which will have it’s own mechanism to direct these request to a specific backend server depending on the cookie data received. 

Sessions: Replication across all servers is one option.

Load Balancers: 

S/W: ELB, HAProxy

H/W: Barracuda, Cisco, Citrix

MySQL Query Cache : query\_cache\_type = 1

Memchached : 

Memory cache, piece of software running on a server, Stores whatever you want in RAM.

Cache is finite, RAM Is finite, it will eventually get so big, we wont be able to keep it on machine, so remove least recently used one.

innoDB: Storage Engine, i.e. the format in which the data is stored in the Database. InnoDB uses transactions. 

Replication: Master- Slave Databases.

Whatever is in the master, is copied down to the slave database.

Adv: If master goes down, one of the slaves can be used as a new master.

Why is a master slave topology good for website that is read-heavy? 

You can have read requests balanced across the slaves and the writes only to the master.

Downside: If master goes down, you could read data but not write.

Master-Master Replication: Write to either masters, that query gets replicated to the other server or wise versa.

If one of the servers goes offline, we still have another master to handle write requests. 

Load Balancers can be single points of Failures, but that can be handled using an Active-Active model.

Partitioning, High Availability 

Load balancing Across Datacenters based in different geolocations, Using the DNS servers.

Security into a Traffic Allowed into Datacenters : TCP80, 443, port 22

Load balancer to Datacenter : Everything to load balancer can be SSL. But you can drop the encryption (offload the ssl to loadbalancer) from Loadbalancer to webservers and down below. 

Everything from internet to loadbalancer is encrypted but not beyond that.

Webserver to Database : TCP 3306

Design Primer

4 steps : 

Use cases and constraints, number of users etc

Create high level design

Design core components

Scale Design

(Maybe you need back of envelope calculations)

Scalability Articles: 

1. **Clones**: Scalable services have multiple servers behind a load balancer. One should get same result even if their request lands on different server, meaning every server should have the same exact codebase and should not have any user specific data like sessions. Sessions are stored in a centralized store accessible to all servers, example in an external persistent database or  persistent cache like Redis. Persistent Cache  > Persistent DB. Deployment : How to make sure code change is made across all servers (Tools like Capistrano help).

After outsourcing sessions and serving same codebase to all servers, create image file of one of the servers as a super clone upon which all your new instances will be based.

1. **Database :** After horizontally scaling servers in above part, you can serve thousands of request. But the application will get slower and slower if you are using mysql. You need to make some more changes in order to maintain performance
1) Stick to MySQL. Hire DBA who will perform Master slave replication and upgrade master with more and more RAM. Down the line you will need Database sharding, denormalization and SQL tuning. As time goes, newer actions to keep DB running become more and more expensive.
1) Denormalize db right form the beginning and do not include more joins in any database query. You can either take MySQL and use it like a NoSQL db or use NOSQL db like Mongo or CouchDB. Joins will now be done in the application code. Sooner you do this, lesser code needs to be changed in future. But even if you successfully switch to the latest NoSQL database and let your app do the dataset-joins, soon your database requests will again be slower and slower. You will need to introduce a cache.

1. **Caches :** Once the scaling of the databases is done, you will be able to store Tbs of data. But page request will get slower if lot of data is fetched. Use in-memory chches like Memcache or Redis. An in memory cache is a simple Key-value store that resides in buffering later between application and data. Hit and Trial. Cache is lightning fast as everything is stored in Ram. Redis can do 100s of 1000s of Read operations per second. Also writes are very fast, especially increments. 
1. Cached Queries : Whenever you do a query on DB, store result in cache. Hashed version of query is cache key. This pattern has several issues. One issue is expiration, it is hard to delete a cached result when you cache a complex query. Secondly if one cell in Db changes, you need to delete cached queries that include that cell.
1. Cached Objects : See your data as an object. Let class assembly dataset from your database and store complete instance of class in the cache. When your class has finished assembling data, directly store the complete instance in the cache. This allows you to completely get rid of an object once anything changes. 

Ex : Sessions, fully rendered blog articles, activity streams, user-friend relationships

\4. **Asynchronism** : 

\1. Async# 1: Doing the time consuming work in well advance and serving the finished work with low request time.  This is usually used to turn dynamic content to static content. These computing tasks are done frequently using a cronjob. Precomputing can extremely improve websites and make apps scalable and performant. 

\2. Async #2: When user comes to the website to do some intensive task that takes several min to finish, frontend sends the job to a job-queue and signals to user that job is in process, please continue browsing. Jobqueue is constantly scanned by workers that pick up and complete jobs and send signals that they were completed. (RabbitMQ or ActiveMQ).    

High level Tradeoffs : 

1. **Performance vs Scalability**:  A service is scalable if the performance increases in a manner proportional to the resources added. Increasing performance implies serving more units of work but it can also mean larger units of work to handle for growing datasets. 

Generally performance of a system designed to be scalable declines with the system size due to management of environment cost. Eg : N/W speed may be slower due to distance b/w machines. Some tasks may be atomic in nature and would bottleneck the speed up. A scalable architecture avoids such a scenario.

1. **Latency vs Throughput:** 

Latency is time required to perform an action to deliver the result.

Throughput is the number of such actions per unit of time.

Aim for Maximum throughput with acceptable latency.

The two measures correspond to the following unit costs:

- Number of messages globally sent by the nodes of the system regardless of the message size.
- Size of messages representing the volume of data exchanges.

For complicated systems with complicated topologies, network load and variations, heterogeneity in software and hardware components involved in data processing and routing, it is difficult to develop a precise cost model that would take into account all these factors, therefore we live with these rough but robust estimates.

1. **Consistency vs Availability:**  

Consistency: Every read has the recent write or error

Availability: Every request gets response, may not be the latest write tho

Partial Tolerance:  System continues to operated despite arbitrary partition due to network failures.

Cluster works despite failure of communication b/w nodes.

**CAP Theorem**

You need to support partition tolerance as n/ws are reliable. Tradeoff b/w Consistency and Availability.

CP :  waiting on response from partition node can give a timeout error. CP is good for system with atomic read and writes.

AP : Responses return recent data available on nodes, which might not be the latest. This system will also accept writes that will  be processed later. availability is a compelling option when the system needs to continue to function in spite of external errors.

**Consistency Patterns :** 

Multiple copies of same data, how to synchronize them?

Weak Consistency : After a write, read may not see it. (Real time systems such as VOIP and Gaming)

Eventual Consistency : After a write, reads will eventually see the  it (Milliseconds). Data is replicated asynchronously. Used in DFS and email (Highly available systems)

Strong Consistency : after the right reads will see it immediately data is replicated synchronously First off this approach is seen in file systems and RDBMS works well, in system start need transactions.

**Availability Patterns :** 

2 patters to support high availability:

**Fail-over**

**Active-passive:** heartbeats are sent between the active and passive server(on standby). if heartbeat is interrupted the passive server takes over the IP addresses of active server and resume service. Downtime is determined by whether passive server is already in hot standby or cold standby. only active server handles traffic

**Active-Active:** Both servers manage traffic, spreading load between them. If servers are public-facing, then DNS then would need to know about the public IP's of both servers. If servers are internal facing you would need application logic to balance load between both servers. 

Active-Active is also known as master-master failover.

Disadvantages: 

Adds more hardware and complexity

Potential loss of data is Active system fails before newly written data can be replicated to passive.** 

**Replication :** 

**Master Slave and master master-master. More about this in database section.**

**99.9% availability: 3 9’s** 

|**Duration**|**Acceptable downtime**|
| :- | :- |
|Downtime per year|8h 45min 57s|
|Downtime per month|43m 49.7s|
|Downtime per week|10m 4.8s|
|Downtime per day|1m 26.4s|
**99.99% availability - four 9s**

|**Duration**|**Acceptable downtime**|
| :- | :- |
|Downtime per year|52min 35.7s|
|Downtime per month|4m 23s|
|Downtime per week|1m 5s|
|Downtime per day|8.6s|


`    `**Availability in Sequence vs Parallel:**

Sequence : Availability will decrease if 2 components with <100% availability are in sequence.

Availability (Total) = Availability (Foo) \* Availability (Bar)

Foo = 99.9% and bar = 99.9% then Availability = 99.8%

Parallel : Overall availability increases when two components with availability < 100% are in parallel:

Availability (Total) = 1 - (1 - Availability (Foo)) \* (1 - Availability (Bar))

Foo = 99.9% and bar = 99.9% then Availability = 99.9999%


**Domain Name system:** 

A Domain Name System translates domain name into IP addresses.

DNS is hierarchical with a few authoritative servers on top level. Your router of ISP will provide info about what DNS server to contact during a lookup. Lower DNS servers cache the mappings. Mappings can get stale during DNS propagation delays. DNS results can also be cached by browser for a certain period of time based on Time to Live (TTL).

NS record: Name server : Specifies the DNS servers for your domain/subdomain.

MX record: Mail Exchange: points to mail servers for accepting messages.

A record (address) : Points to IP address

CNAME: Canonical name: Points a name to another name or CNAME or to an A record.

**Some DNS Routing Methods:**

- **Weighted Round Robin**: Round robin algorithms pair requests to servers in a cyclic nature, which is a common way of many network load balancing needs. 

Weighted round Robin provides a clean and effective way of focusing on fairly distributing the loan among available resources. Each destination server is assigned value that signifies the relative performance of that server compared to other servers. This date determines how many more requests are sent that servers way compared to others in the pool.

Adv:

- Prevent traffic from going to servers under maintenance
- Balance b/w varying cluster sizes.

- **Latency Based:** If application is hosted on multiple regions (AWS regions), you can improve performance by serving user requests from regions that provide lowest latency. DNS will have latency records to determine which server will provide least latency and report back that IP address for the user.
- **Geolocation Based:** This type of routing lets you choose the servers for your traffic based on the geographic location of the users. That is user’s requests will be served up by the servers geographically closest to them. Geolocation routing can be used for content localization and restriction of distribution to only regions where you have distribution rights.


**Content Delivery Network:** 

A CDN is a globally distributed network of proxy servers, serving from locations closer to the user. Their main goal is to minimize the delays in loading web pages content by reducing the distance between server and user. Generally static files such as HTML, CSS, media and files are served from the CDN (as they are cached there), (although some CDNs like Amazon’s CloudFront) also serve dynamic content. The site’s DNS resolution tells client what server to contact.

CDN stores cached version of Website content in multiple geographic locations

Advantages (Performance Improvement)

- Users receive content from data centers close to them
- Servers need not fulfill requests that CDN fulfill.

**Push CDN**: Push CDN receive new content whenever changes occur to your server. You take full responsibility for the content, directly uploading it to the CDN and rewriting URL’s to point to the CDN. You can configure the content expiry time when it is updated. Content is uploaded only when it is new or changed, minimizing traffic but maximizing storage.

Sites with small traffic where content is not often updated works well with Push CDN. Content is placed once, instead of being re-pulled regularly.

**Pull CDN:** Pull CDNs grab new content from the server when the first user requests the content. You leave content on the server and rewrite URLs to grab it from the CDNs. This results in a slower request until the content is finally cached by the CDN.

TTL determines how long the content is cached. Pull CDN minimize storage but can create redundant traffic if files expire and are pulled before they have changed. 

Pull CDNs are beneficial to sites that have heavy traffic as traffic will spread out more evenly with only recently requested content remaining on CDN. 

**Disadvantages(s) of CDN:** 

- CDNs require changing url for static content to point to CDN
- Content might be stale if updated before the TTL expires for it
- CDN cost may be significant depending on the traffic, although it will save additional costs for not having CDNs

**Load Balancer:**

Load balancers distribute incoming users requests to computing resources such as servers and databases and then returns the response from the resource to the appropriate client. Load balancers are used for :

- Prevent requests going to unhealthy servers.
- Prevent overloading resources.
- Helping eliminate single point of failure.

Load balancers can be H/W or S/W like HAProxy. 

Additional Benefits: 

**SSL termination:** Decrypt incoming server requests and encrypt server responses so backend servers do not have to perform these potentially expensive operations.

**Session persistence:** issues cookies and route a specific client’s request to same instance if the web apps do not keep track of the sessions.

Load Balancers can be protected against failures by setting them up in Active-Active and Active-passive mode.

Load Balancers use route traffic based on

- Random
- Least loaded 
- Session/Cookies
- Round robin / Weighted round robin
- Layer 4 (Transport Layer):  Looking at Source and Destination IP addresses and ports in the header but not the contents. Layer 4 load balancers make routing decisions based on few first packets extracted from the TCP stream but not the packet content. Layer 4 load balancers forward network packets from and to the upstream server.
- Layer 7 (Application Layer): Look at Application layers to decide how to distribute packets. Look at header, message, and cookies. Terminate network traffic, real the message, make decision and open connection to selected server. 

**Horizontal Scaling:** Balancers can help with horizontal scaling to improve performance and availability. Scaling out using commodity machines is cost effective and gives higher availability than vertical scaling (expensive H/W on single Machine). 

**Horizontal Scaling Disadvantages:** 

- Complexity increases and servers are cloned
  - These cloned servers should be stateless and not contain user-specific data
  - Sessions need to be stored in a centralized datastore (dB) or persistent cache.
- Downstream severs need to handle more simultaneous requests as upstream servers scale out.

Load balancer Disadvantages: 

- LB can be a performance bottleneck if lack of resources or not configured correctly
- Increases complexity of the system
- Single LB is a single point of failure, configuring multiple LBs increases complecity.

**Proxies or Reverse Proxy (Web Server):**
**
` `It is a webserver that centralizes the internal services and provides unified interfaces to the public to access these services. Requests from clients are forwarded to the server that can fulfill it before the reverse proxy returns server’s response to client. 

**Disadvantages**: Complexity and Single Point of Failure (Like LB)

**Benefits**: 

- Increased Security : Hide info about backend servers, limit # of connections, blacklist IP
- Increased Scalability and Flexibility : Clients only see the Proxy’s Ip so you can scale servers and change configuration
- SSL Termination
- Compression
- Caching
- Static Content

**Load Balancer vs Reverse Proxy:**

- LB is usually beneficial when you have multiple servers. LB’s route traffic to set of servers doing the same function.
- Reverse proxy can be deployed even if you have only one webserver due to the benefits it provides. 
- Solutions such as HAProxy and NGINX can be used or layer 7 Load Balancing or Reverse proxying.

**Application Layer:**

Separating out Web layer from application/platform layer allows you to scale both layers independently. Adding a new API results in adding a new Application Server but not a new Web Server. **Single Responsibility Principle**

Webserver: Accepts and Fulfills requests from clients for static content from a website. Handles HTTP requests and responses only

Application Server: Exposes business logic to the clients which generates dynamic content. S/W framework that transforms data to provide the specialized functionality. 

Microservice Vs Monolith

||Monolith|Microservice|
| :- | :- | :- |
|Advantages|<p>- Not so complex</p><p>- Works in teams that are small or cohesive in nature</p><p>- Can reuse code</p><p>- This architecture is faster in nature</p>|<p>- Easier to scale </p><p>- New developers can be assigned tasks without being given full application’s context</p><p>- Parallel Deployment is easy</p><p>- Lesser hidden parts, can scale individual services.</p>|
|Disadvantages|<p>- New members need all applications context</p><p>- Deployment of entire application must be done for every change</p><p>- Tests are complicated due to tight coupling</p><p>- Too much responsibility on servers</p>|Needs a good design for an intelligent architecture|

Microservices are a suite of independently deployable, small and modular services. Each service runs a unique process and communicates through a well defined, lightweight mechanism to serve a business goal.

**Service Discovery**: Systems like Zookeeper, Etcd, Consul etc can help services find each other by keeping track of their registered names, addresses and ports. Health checks are used to verify the service’s integrity and are often done using am HTTP endpoint. Consul and ETcd have built in key-value store that can be useful for storing config values and other shared data. 

**Databases:**

RDBMS : A relational database like SQL is a collection of Data items organized into tables.

ACID properties: 

**Atomicity:** Every transaction is all or nothing

**Consistency**: Any transaction will bring DB from one valid state to another

**Isolation:** Executing transactions concurrently will be same as executing them sequentially.

**Durability:** Once a transaction is committed it will remain so.

Ways to scale a relational Database:

**Master-Slave Replication:**

Master serves reads and write requests, replicating writes to one or more slaves, which serves only reads. Slaves can also replicate to additional slaves in a tree like fashion. When master goes offline, system goes into read-only mode until a slave is promoted to a new master or a new master is provided.

Disadv : 

Additional Logic is needed to promote slaves to Master.

**Master-Master replication:** Both Masters serve reads and writes and coordinate with each other on writes. If either master goes down, system continues to operate with both reads and writes.

Disadv:   

Needs load balancer or changes to application logic to determine where tow write.

Most master-master systems may lose consistency or have increased write latency due to synchronization

Conflict resolution comes into play more

Replication Disadvantages:

Loss of fata if master fails before newly written data can be replicated to other nodes

Writes are replayed to read replicas. As number of writes increase, read replicas can slow down and won’t be able to do as many reads.

Adds more H/W and complexity

**A word about indexes:**

Database indexing: Goal of indexing is to make searching through a table in the database faster. Indexes can be created using one or more column, proving basis for rapid lookups and efficient access of ordered records. 

A index is a data structure that can be perceived as a table of contents that points us to the location where actual data lives. So, when we create an index on a column of a table, we store that column and a pointer to the whole row in the index.

Just like a traditional relational data store, we can also apply this concept to larger datasets. The trick with indexes is that we must carefully consider how users will access the data. In the case of data sets that are many terabytes in size, but have very small payloads (e.g., 1 KB), indexes are a necessity for optimizing data access. Finding a small payload in such a large dataset can be a real challenge, since we can’t possibly iterate over that much data in any reasonable time. Furthermore, it is very likely that such a large data set is spread over several physical devices—this means we need some way to find the correct physical location of the desired data. Indexes are the best way to do this.

An index can dramatically speed up data retrieval but may itself be large due to the additional keys, which slow down data insertion & update.

When adding rows or making updates to existing rows for a table with an active index, we not only have to write the data but also have to update the index. This will decrease the write performance. This performance degradation applies to all insert, update, and delete operations for the table. For this reason, adding unnecessary indexes on tables should be avoided and indexes that are no longer used should be removed

**Federation or Vertical Partitioning**:

Federation aka functional Partitioning splits databases by functions. Instead of a single monolithic database, you could have 3 databases such as Forums, Users and Products, which results in lesser read and write time to each database and therefore less replication lag.

Smaller dB -> More data can fit in memory. Resulting in more hit% in cache. No single central master serializing writes, so parallel writes are allowed too, which increases throughput.

Disadvantages:

- Federation is not effective if your schema requires huge tables or functions.
- Need to update application logic to determine which database to read and write
- Joining tables from two databases is more complex 
- H/W and added complexity.

**Sharding or Horizontal Scaling:**

Sharding is the distribution of data across different databases such that each database can only manage a subset of the data. For example Users database, as number of users increases, more shards are added to the cluster. 

Like federation, sharding provides less read and write traffic, less replication, more cache hits. Index size is also reduced which increases performance with faster queries. If one shard goes down, other shards are still operational, although you can add replication to avoid loss of data. Like federation, there is no centralized master serializing writes, so you can write in parallel and this increases throughput. 

Common ways of sharding : Geographical Location, User’s last name initial

**Disadvantages:**

- Need to update application logic. Can result in complicated SQL queries.
- Number of shards once decided cannot be changed. 
  - Using hierarchical sharding, this can be solved.
- Lopsided data distribution can load one shard more heavily compared to others. 
  - Rebalancing adds additional complexity. A sharding function based on consistent hashing can reduce the amount of transferred data.
- Joining data from multiple shards is more complex.
- H/W and additional complexity.

You can use a Master slave architecture to manage fault tolerance if one if the shard goes down.

**What is consistent hashing?**

Distributed hash tables is one of the fundamental components used in distributed scalable systems. These hash tables use Key Value pairs.

In a system with distributed caching, suppose we have n servers, an intuitive hash function would be to use key%n. But there are two major drawbacks to this approach:

1) It is not horizontally scalable. If you want to add one more cache server, all existing mapping will break. 
1) It may not be load balanced if the data is not uniformly distributed.

Consistent hashing is a useful strategy in distributed caching, databases etc. It allows us to distribute data cross clusters in such a way that it will minimize reorganization when nodes are added or removed, making it easier to scale up or down.

When a hash table is resized, only k/n keys need to be remapped, where k is the total number of keys and n is the total number of servers. 

In consistent hashing, objects are mapped to the same host if possible. When a host is removed from the system, the objects on the host are shared by other hosts and when a host is added, it takes a share from a few hosts without touching other’s share.

Servers and keys map to the same range of values. 

Given you have a list of servers, first you hash them to assign them integers in a range.

For each key, you

1. Hash it to an integer
1. Move clockwise in a ring until finding the first cache it encounters.
1. That cache is the one that contains the key.

Therefore, whenever new servers are added or removed, only a small number of keys will be moved around.

Making virtual replicas of these servers help in ensuring that the data distribution is not skewered i.e. a small subset of server does not handle considerably higher amount of load compared to other servers. 

Virtual Replicas: Use multiple hash functions to cache assign each server multiple points in the ring. This way each server is associated with multiple servers in the ring. Using such multiple hash functions, we can almost ensure that we do not end up with lopsided distributions.

**Denormalization:**

Denormalization is a strategy used on previously normalized data to increase performance. It is the process of increasing read performance of data at the expense of losing some write performance, by adding some redundant copies of data or by grouping data together.  Redundant copies of data are written in multiple tables to avoid expensive joins. PostgreSQL or Oracle support materialized views which handle the work of storing redundant info and keeping redundant copies consistent. 

As the data becomes more complex due to federation or sharding, managing joins across different data stores can get complicated. Denormalization will help us circumvent this. 

Many systems have huge read:write rations such as 100:1 or even 1000:1. A read resulting in a complex db join can be expensive by spending significant amount of time on disk operation.

**Disadvantages:**

Data is duplicated

Denormalized DB under heavy write load will perform poorly.

One needs to keep these redundant copies in sync using constraints, which adds to DB design complexity.

**SQL Tuning:**

The iterative process of improving SQL statements to meet performance standards when they are not able to with increasing datasets.

Benchmark and Profile using ab and Slow Query Log.

The slow query log consists of SQL statements that take more than [long_query_time](https://dev.mysql.com/doc/refman/5.7/en/server-system-variables.html#sysvar_long_query_time) seconds to execute and require at least [min_examined_row_limit](https://dev.mysql.com/doc/refman/5.7/en/server-system-variables.html#sysvar_min_examined_row_limit) rows to be examined.

**Tighten Up shema:**

- MySQL dumps to disk in contiguous blocks for fast access
- Char instead of Varchar for fixed-fields
- TEXT for large blocks of text. Used to store pointer on disk that stores location of text block.
- Use int for numbers upto 4 billion
- Decimal for currency to avoid floating point representation errors
- Avoid large blobs, instead store locations of where to get those objects.
- Set Not null constraint wherever applicable/

**Use good indices**

- Column querying will be faster with indices
- Bad indexing  can make writes slower/

Avoid expensive joins through Normalization.

Tune the query cache .

**NoSQL**:

Collection of Data items represented in a 

- key-value store
- document store
- wide column store
- graph database

Data is denormalized and joins are usually done in application code. Lack true ACID properties and favor eventual consistency. 

BASE properties.

- **Basically Available**: the system guarantees availability.
- **Soft State**: the state of the system may change over time even without input.
- **Eventual Consistency**: The system will become consistent over a period, given that the system does not receive input during that period.

**Key-Value Store**: A key value store generally provides o(1) reads and writes and is often backed by Memory or SSD. Data Stores can maintain keys in lexicographic order, allowing efficient retrieval of key ranges. 

Key Value stores provide high performance and are usually used for simple data models or rapidly changing data, such as in-memory cache layer. Provides a limited set of functionality therefore the complexity is shifted to application layer.

Key value stores can allow for storing a metadata with a value.

Key-value stores are basis for more complex models such as document store and in some cases, graph DB

Example : Redis

**Document Store:**

A document store is centered around documents (XLM, JSON, binary etc), where a document stores all information for a given object. Document stores provides APIs or a query language to query based on the internal structure of the document itself.

Based on underlying implementation, documents are organized by collections, tags, metadata or directories. Although documents can be group together, they can have fields completely different from each other. 

Document stores like MongoDB and CouchDB provide SQL like language for performing complex querying. DynamoDB supports both documents and key-value stores.

Provide High flexibility and used for working with occasionally changing data.

**Wide Column Store:**

For a wide column store the basic unit of Data is a Column (name/value pair). A column can be grouped in column families. Super column families group column families. Columns with same row key form a same row. Each value has timestamp for versioning and conflict resolution.

- BigTable by google
- HBase often used by Hadoop ecosystem
- Cassandra by facebook

All of these use a lexicographic ordering for keys.

Wide Column stores offer high availability and high scalability and are used for large data sets.

**Graph Database:**

In a graph database, each node is a record and an arc is a relationship b/w the two nodes. Graph DBs are optimized to represent complex relationships with man foreign keys or many-to-many relationship. 

They are good for high performance with complex datamodels such as Social N/W. Many graphs can only be accessed using REST API’s 

Ex : 

FlockDB,

Neo4J


|Key-Value Store|Redis|
| :- | :- |
|Document-Store|MongoDB, CouchDB|
|Wide Colum Store|Apache Cassandra, BigTable, HBase|
|Graph Database|Amazon Neptune, FlockDB, Neo4j|

**NoSQL vs SQL**

|**SQL**|**NoSQL**|
| :- | :- |
|Structured Data|Semi Structured Data|
|Strict Schema|Dynamic or flexible schema|
|Relational Data|Non relational data|
|Need for complex joins|Store TBs or PBs of data|
|Transactions|Very data intensive workload|
|Clear patterns for scaling|Very high throughput for IOPS (input output operations per second)|
|More established community and code||
|Index based lookups are very fast||

**Sample Data well Suited for NoSQL :** 

- Rapid ingestion for clickstream or log data
- Leaderboard or scoring data
- Temporary data such as shopping cart
- Frequently accessed hot tables
- Metadata/ lookup tables

**CAP Theorem on Databases:** 

|**RDBMS**|**Consistent**|**All reads and write go to the same machine. Not available as replicated servers take time to become masters.**|
| :- | :- | :- |
|**Mongo**|**Consistent and Partition Tolerance**|**One Primary and multiple replica secondaries who update themselves asynchronously using operation logs. If primary goes down, one of the secondaries is elected, but system remains unavailable till then, compromising availability**|
|**Cassandra**|**Availability and Partition Tolerance**|**Peer to peer system, in a ring-based hash.  Multiple systems, read to serve read and write requests. Masterless architecture with multiple points of failure. One situation occurs where partition happens, and replica may not get updated copy of data. In such situation, dB is available but inconsistent. Replication factor 3 : Will replicate data to three nodes.**|


**Caching:** 

Caching improves page load times and can decrease the load on the servers and databases. Dispatcher first looks at the cache to find a previous result otherwise hit the DB.

Some Benefits of Cache include

1) Avoid N/W calls
1) Avoid repeated computations
1) Avoid DB load



Why not store everything in cache?

- H/W on cache runs is expensive (SSD)
- As you store more and more data on cache, search time will increase

**Cache Eviction Policies:** 

1) FIFO
1) LIFO
1) LRU
1) LFU
1) RR (Random Replacement)
1) Some newer policies such as sliding Window.

**Problems due to poor Caching:**

- Extra Calls
- Trashing
- Consistency problems

**Client Caching:** Caches located on client side such as Browser, server side or distinct cache layer

**CDN Caching:** CDNs are considered a type of Cache

**Web Server Caching:** Reverse proxies like Varnish can serve up Static and Dynamic Content Directly. Web Servers can also cache results, returning responses  without  having to contact application servers. 

**Database caching:** Dbs usually include some level of caching in their default config for generic use cases. These settings can be tweaked in order to optimize them according to your usage patterns.

**Application Caching:** In-memory cache such as Memcached and Redis are key-value stores between application layer and DB. This data is much faster thank disk as it is held in RAM. Cache invalidation policies such as LRU caching help always keep hot data and get rid of cold data. 

**Redis**: Global Persistent Cache. (Key-Value based store)

Redis has:

- Persistence Option
- Built in data structures such as Sorted sets and lists

**Avoid File bases caching as it makes auto-scaling and cloning difficult.**

Usually We cache 2 things : Database Queries and Objects.

**Caching at DB query level**: 

Hash the query as key, store the results to cache. 

Disadvantages: 

- Hard to delete cached results with complex queries
- If one cell of data changes, all the cached results associated with that piece get invalidated.

**Caching at Object level:**

See the data as an object like how you do at application code. Have application assemble the dataset from the database into a class instance of Data Structure.

- Remove object if underlying data has changed
- Allows Asynchronous processing, workers assemble objects by consuming latest cached object.

What can be cached:

- User sessions
- Fully rendered Web pages
- Activity Streams
- User graph data.

**When to Update the Cache?**

Determine a cache update strategy.

|**Strategy**|**Explanation and Advantages**|**Disadvantages**|
| :- | :- | :- |
|Cache-aside/ lazy loading|<p>Application is responsible for reading and writing the storage. The cache does not interact with storage directly. </p><p>- Look for entry in cache</p><p>- Load entry from db</p><p>- Add entry to cache</p><p>- Return entry</p><p>Memcached is used in this manner. Subsequent reads are fast. Avoids filling up cache with data that is not loaded.</p>|<p>- Each cache miss takes three trips and can cause delays</p><p>- Data can get stale if it updates in database. Need a TTL to tackle this.</p><p>- When node fails, a new node replaces it which increases efficiency/</p>|
|Write-through|<p>The application uses cache as main data store, reading and writing data to it, while cache is responsible for reading and writing data to DB</p><p>- App adds/updates entry to cache</p><p>- Cache synchronously writes entry to data store</p><p>- Return</p><p>This is slower overall operation due to write operation, but subsequent reads of just written data are fast. Data in the cache is not stale</p>|<p>- When a new node is created due to scaling or failure, the new node will not cache entries until the entry is updated in the database. Cache-aside along with Write through can solve this.</p><p>- Most data written might never be read, this can be tackled using a TTL.</p>|
|Write-back (Write behind)|<p>Data is written to the cache alone and immediately confirmed to the client. Write to Db is done after specified intervals or under certain conditions.</p><p>- Add/ update entry to the cache.</p><p>- Asynchronously write entry to the data store</p><p>This decreases throughput and increases write performance.</p>|<p>- Potential data loss If the cache goes down prior to the contents hitting the data store.</p><p>- More complex to implement than cache aside or write through</p>|
|Refresh-ahead|<p>You can configure the cache to automatically refresh any recently accessed cache entry prior to its expiration.</p><p>It can increase throughput and reduce latency if it accurately predicts which entries it will be needing in the future.</p>|- Not accurately predicting items likely to be used in the future will result in reduced performance.|

**Disadvantages of Cache:**

- Need to maintain consistency b/w Caches and source of truth such as database.
- Cache invalidation is a difficult problem, there is additional complexity associated with when to update cache
- Need to make application changes such as adding Redis or Memcache
- Additional H/W can be expensive such as SSD.



**Asynchronism:**

Asynchronous workflow help reduce the request times for expensive operations which would otherwise be performed in-line. The can also help by doing time consuming work in advance, such as periodic aggregation of data.

**Message Queues:** Message queues receive, hold and deliver messages. If an operation is too slow to perform in-line, the message queue will use the following workflow:

- An application published a job to the queue and notifies the user of the job status.
- A worker picks up the job from the queue, processes it and signals that the job was complete.

The user is not blocked, and the job will process in the background. Client can do small bit of processing to seem as though job has been completed, such as if posting a tweet, show it instantly posted on timeline but maybe it would take some time to actually deliver it to all your followers.


|**Redis** can be a message broker|Messages can be lost though|
| :- | :- |
|**RabbitMQ** is popular|But it needs you to adapt to the “AMQ” protocol and manage your own nodes|
|**Amazon** SQS|Can have high latency and possibility of messages being delivered twice|

**Task Queues**: 

Task Queues receive tasks and their related data, runs them, and delivers their results. They can support scheduling and can be used to run heavy computational tasks in the background. 

**Difference b/w task and message queues:**

Message queues hold messages. They are a mechanism of sharing information between systems, processes, threads etc. Based on the contents of message queue, workers start processing the tasks.

Task queue is to hold “defined tasks” with worker codes. When something needs to be done in the background, we can call these tasks or schedule them for processing later. Workers would launch these tasks, know how to run them, and store their results.


**Back Pressure:**

If queues start growing significantly, queue sizes can become larger than memory, resulting in cache misses, resulting in disk reads which leads to slower performance. Back pressure can help by limiting queue size which leads to high throughput rates and good response time for jobs already in queue. Once the queue is full, clients get a server busy or HTTP 503 response to try again later.

**Communication:**

**HTTP:** For response/requests. HTTP is self-contained, therefore it allows requests/response to flow through many intermediate routers. 

**HTTP verbs**

|**Verb**|**Description**|**Idempotent\***|**Safe**|**Cacheable**|
| :- | :- | :- | :- | :- |
|GET|Reads a resource|Yes|Yes|Yes|
|POST|Creates a resource or trigger a process that handles data|No|No|Yes, if response contains freshness info|
|PUT|Creates or replace a resource|Yes|No|No|
|PATCH|Partially updates a resource|No|No|Yes, if response contains freshness info|
|DELETE|Deletes a resource|Yes|No|No|

Safe: HTTP methods are safe if they do not alter the state of the server.

Idempotent: Can be called several times without different outcomes.

**TCP:**

- Connection oriented protocol over an IP network. 
- Connection establishment and termination using handshake.
- Contains acknowledgements and auto transmissions.
- Each packet has checksum fields and sequence numbers.
- If there are multiple timeouts, the connection is dropped.
- TCP also implements flow control and congestion control. There guarantees cause delays and make TCP slower than UDP.
- Web servers can keep a large number of TCP connections open simultaneously, resulting in high memory usage. 
- Connection pooling can be used to avoid this, apart from using UDP wherever applicable.
- TCP is used for applications than need high reliability but are less time critical. 
  - Web Servers
  - Database info
  - SMTP
  - FTP
  - SSH

**UDP:**

- Connectionless
- Datagrams (packets) may or may not reach their destination.
- They may reach but not in the desired order.
- Does not support congestion control.
- Generally faster than TCP as it does not provide guarantees.
- Can broadcast, sending datagrams to all devices on subnet.
- Useful with DHCP as client has not received IP address yet, which prevents TCP to stream without IP address.
- Less reliable but works well in real time use cases like
  - VoIp
  - Video chat
  - Streaming
  - Online multiplayer games

**Remote Procedure Call (RPC):**

In an RPC, a client causes procedure to execute in a different address space, usually a remote server. Procedure is coded as if being a local function call, abstracting communication details b/w client and server programs. RPCs are usually slower and less reliable than local calls.

Ex: Protobuf, thrift, Avro.

- **Client Procedure:** Calls the client stub procedure. Parameters are pushed onto the stack like a local call
- **Client Stub Procedure:** Packs procedure id and arguments into a request message.
- **Client Communication Module:** OS sends message from client to server.
- **Server Communication Module:** OS passes the incoming packets to server stub procedure.
- **Server Stub Procedure:** unpacks the results, calls the server procedure matching procedure id and passes the given arguments
- **The server repeats the same steps as above in reverse.**

RPC is focused on exposing behaviours. RPCs are often used for performance reasons with internal communications, as you can hand-craft native calls to better fit your use cases.

Chose native Library  when:

- You know target platform
- You want to control how your logic is accessed
- You want to know how error control happens off your library
- Performance and user experience is your primary concern.

**Representational State Transfer:** 

Rest in an architectural style that enforces a client/server model where the client acts on a set of resources managed by the server. The server provides a representation of resources and actions that can either manipulate or get a new representation of these resources. All communication must be stateless and cacheable.

**4 qualities of a RESTFUL interface:**

- **Identify Resources (URI in HTTP):** use the same URI regardless of any operation
- **Change with representations (Verbs in HTTP):** use verbs, headers, and body.
- **Self-descriptive error messages (status response in HTTP):** Use status codes, don’t reinvent the wheel.
- **HATEOAS (Hypertext as the engine of application) (HTML interface for HTTP):** You webservice should be fully accessible in a browser.

REST is focused on exposing data. It minimizes the coupling between client/server and is often used for public HTTP API’s. REST uses a more generic and uniform method of exposing resources through URIs, representation through headers and actions through verbs such as GET, POST, PUT, DELETE and PATCH. 

**Being stateless, rest is great for horizontal scaling and partitioning.**

**Disadvantages of REST:**

- It might not be a good fit if resources are not naturally organized or accessed in a simple hierarchy. For example, returning all updated records from the past hour matching a particular set of events is not easily expressed as a path. With REST, it is likely to be implemented with a combination of URI path, query parameters, and possibly the request body.
- Relies on a few verbs that sometimes don not fit your use case.
- Fetching complicated resources with nested hierarchies may require multiple round trips to render single views. Over variable networks, these multiple roundtrips are undesirable.
- Over time, more field may get added to the an API response and older clients will receive these new fields even if they don’t need them. This bloating of payload sizes leads to larger latencies.

|**Operation**|**RPC**|**REST**|
| :- | :- | :- |
|Signup|**POST** /signup|**POST** /persons|
|Resign|**POST** /resign<br>{<br>"personid": "1234"<br>}|**DELETE** /persons/1234|
|Read a person|**GET** /readPerson?personid=1234|**GET** /persons/1234|
|Read a person’s items list|**GET** /readUsersItemsList?personid=1234|**GET** /persons/1234/items|
|Add an item to a person’s items|**POST** /addItemToUsersItemsList<br>{<br>"personid": "1234";<br>"itemid": "456"<br>}|**POST** /persons/1234/items<br>{<br>"itemid": "456"<br>}|
|Update an item|**POST** /modifyItem<br>{<br>"itemid": "456";<br>"key": "value"<br>}|**PUT** /items/456<br>{<br>"key": "value"<br>}|
|Delete an item|**POST** /removeItem<br>{<br>"itemid": "456"<br>}|**DELETE** /items/45|




RPC vs REST

|**RPC**|**REST**|
| :- | :- |
|<p>- Clients become Tightly coupled to service, becomes very hard to change service implementation without braking clients.</p><p>- Clients need to know procedure names.</p><p>- Procedure parameters order, types, and count matters. It is not easy to change procedure signatures on server side without breaking client implementations.</p><p>- RPC only exposes procedure endpoints + procedure arguments. It is impossible to know what to do next</p>|<p>- Very easy to guide clients by including control information in representation (HTTP headers + representation)</p><p>- It is possible to embed links annotated with link relation types which convey meanings of the URIs</p><p>- Client implementations need not depend on procedure names and arguments. Instead, clients depend on message formats.</p><p>- It is possible to change URI’s without breaking clients as far as they only depend on registered (or domain specific) link relations.</p><p>- It is possible to embed form like structures in representations giving clients possibility to expose these descriptions as UI capabilities if end user is human.</p><p>- Support for Caching</p><p>- Standardized status codes.</p>|

**Why is REST favorable to scaling?**

The server end of REST is stateless. This means that the server does not have to store anything across requests. This means there does not need to be communication b/w servers, making it horizontally scalable.

A load balancer can easily route requests to right server if you have good URL’s and GET could go to slave and POST goes to master.

**Security and Permissions**

Basics:

- Encrypt in transit and at rest
- Sanitize all user inputs or any input params exposed to the user to prevent XSS or SQL injections
- Use parameterized queries to prevent SQL injections.
- Use the principle of least privilege. (Every module must be able to access only the information and resources that are necessary for its legitimate purpose).

**Grokking System Design Glossary**

**Key Characteristics of A distributed system**

- **Scalability:**  Horizontal vs Vertical Scaling (MongoDB and Cassandra are good examples of scalable servers)
- **Reliability:** Maybe adding a little bit of redundancies will help
- **Availability:** 

**Reliability vs Availability:** 

If a system is reliable, it is available, but if a system is available, it is not necessarily reliable. Highly available systems can still have problems such as vulnerabilities, security risks, etc.

- **Efficiency: Latency vs Throughput.**
- **Serviceability or Manageability:** How easy is he system to operate and maintain. Serviceability or manageability is simplicity and speed with which the a system can be repaired or maintained. 



**Proxy vs Reverse Proxy:**

|**Proxy**|**Reverse Proxy**|
| :- | :- |
|**Hides identity of Client. Traffic routed for client and make request on behalf of client. Proxy will return response to client**|**Reverse proxy, client does not know which server it is connecting to. Websites will have their own reverse proxies. Clients will only know IP address of reverse proxy and not internal servers.**|
|<p>- **Anonymity**</p><p>- **Caching**</p><p>- **Blocking unwanted size**</p><p>- **GeoFencing**</p>|<p>- **Load balancing**</p><p>- **Caching**</p><p>- **Logging**</p><p>- **Isolating Internal Traffic**</p>|


***Client Server Architecture : HTTP (Request Response)***

***Peer - Peer Architecture : XMPP protocol (pusing messages)***
XMPP is short form for Extensible Messaging Presense Protocol. It is a protocol for streaming XML elements over a neetwork to exchange messages and present information in close real time. Used my IM like whatsapp.
Decentralized, meaning anyone can run their own XMPP server. Original protocol for XMPP is TCP, using open ended XML streams for lovg lived connections.
