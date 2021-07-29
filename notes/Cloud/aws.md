# AWS
Amazon Web Services - Cloud Computing Service

## Cloud Computing Service
A service that enables you to utilize someone else's computer.

We need computing resources. We don't want to manage them.

### Running a Datacenter
* IT Personnel
* Backups
* back-up generators
* insurance
* hardware (constantly upgrading and buying new hardware and decomissioning old hardware)
* AC (You need a lot of air conditioning and it CANNOT fail)
* Internet connection (A really good one)
* Security personnel
* COPPER
* Software
* Real estate
* Electricity
* maintenance personnel

## Regions
A physical location on the globe. A geographical location where AWS is putting datacenters.
`us-east-1` in Virginia, `ap-northeast-2` in Seoul.

### Availability Zones
A datacenter within a region connected by dedicated network cables. Each region is connected to the internet.

### Edge Locations

A connection point providing direct network access to a region.

## EC2

Elastic Cloud Compute service. A virtual machine running in the cloud. EC2 allows us to provision a variable amount of processing power and RAM. There are a bunch of different kinds of EC2 optimized for different workloads. You pay for what you use. When you provision a cloud computing resource you pay for that resource by the hour. (Some EC2s are actually able to charge by the minute, now). If you release the resource, you stop paying for it.

### Provision an EC2
1. Go to EC2 in your Services list
2. Go to `instances` click `Launch Instance`.
3. Select Amazon Linux 2 AMI
4. Select `t2.micro`, click `Next`
5. Click `Next`
6. Click `Next`
7. Add a tag called `Name` with a name to identify your machine. Click `Next`
8. Make your Security group with SSH and 8080 access to your ip address. Click `Review and Launch`
9. Click `Launch`
10. Generate a key and save it. If you lose this key you have to terminate your instance and make a new one.
11. After you have downloaded (or ensured that you already have) your keypair click `Launch Instances`.

### Connecting to an EC2
1. Get your public IP address.
2. SSH in using your private key: `ssh -i <your key> ec2-user@<your ip address>`
   1. If you're on mac you need to: `sudo chmod 600 <your key>`
3. Congratulations!

### Running a Javalin application on EC2
1. `sudo yum install git`
2. `sudo yum install maven`
3. `git clone https://github.com/210712-Richard/2107-Reactive`
4. `cd` into the repository and the particular project you wish to build
5. `mvn package`

```xml
<build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-shade-plugin</artifactId>
				<version>3.0.0</version>
				<executions>
					<execution>
						<phase>package</phase>
						<goals>
							<goal>shade</goal>
						</goals>
						<configuration>
							<transformers>
								<transformer
									implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
									<manifestEntries>
										<Main-Class>com.revature.Driver</Main-Class>
									</manifestEntries>
								</transformer>
							</transformers>
						</configuration>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>
```


## AMI
Amazon Machine Image - An image of a virtual machine that can be used as a starting point, a backup, or a template for launching EC2s. The AMI will be loaded onto an EBS volume and the EBS volume will be attached to your EC2, providing your basic operating system and software.

* Quickstart: AMIs provided by Amazon that basically just have operating systems on them
* Custom AMIs: AMIs that we have created by saving the state of an EC2 we provisioned.
* Marketplace AMIs: AMIs that come with a price tag so that you can utilize pre-installed licensed software
* Community AMIs: AMIs that someone created and then uploaded for everyone's use.

## EBS
Elastic Block Storage - Essentially a harddrive for EC2. In reality is usually a partition on a much larger harddrive that is attached to our virtual machine. There are a bunch of different types of EBS including `General Purpose SSD`, `Provisioned IOPS SSD`, and `Magnetic`.

## Security Groups

A security group is a whitelist of IP-addresses and port-ranges that are allowed to access your resource. By default, nothing can access your resource.
