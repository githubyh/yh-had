package com.yh.kafka;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import kafka.consumer.*;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

import org.apache.commons.collections.CollectionUtils;

/**org.elasticsearch.river.kafka.KafkaWorker.run(KafkaWorker.java:80)
 * <pre>
 * Created by zhaoming on 14-5-4 下午3:32
 * </pre>
 * 2016-03-11 20:07:42,152][INFO ][cluster.metadata         ] [Headknocker] [[_river]] remove_mapping [[kafka-river]]
Exception in thread "elasticsearch[Headknocker][riverClusterService#updateTask][T#1]" java.lang.NullPointerException
        at org.elasticsearch.river.kafka.KafkaRiver.close(KafkaRiver.java:103)
        at org.elasticsearch.river.RiversService.closeRiver(RiversService.java:203)
        at org.elasticsearch.river.RiversService$ApplyRivers.riverClusterChanged(RiversService.java:217)
        at org.elasticsearch.river.cluster.RiverClusterService$1.run(RiverClusterService.java:138)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
        at java.lang.Thread.run(Thread.java:745)
[2016-03-11 20:08:15,262][INFO ][cluster.metadata         ] [Headknocker] [_river] update_mapping [kafka-river] (dynamic)
[2016-03-11 20:08:15,396][INFO ][river                    ] [Headknocker] rivers have been deprecated. Read https://www.elastic.co/blog/deprecating_rivers
RiverConfig init ............................kafka-river   kafka
riverSettings init ............................{"kafka":{"partition":4,"zookeeper.connect":"localhost:2181,localhost:2182","message.type":"json","topic":"rivertopic","zookeeper.connection.timeout.ms":10000},"index":{"action.type":"index","concurrent.requests":1,"index":"kafka-index","type":"kafka","bulk.size":500,"flush.interval":"12h"},"type":"kafka"}
[2016-03-11 20:08:15,436][INFO ][kafka.utils.VerifiableProperties] Verifying properties
[2016-03-11 20:08:15,436][INFO ][kafka.utils.VerifiableProperties] Property auto.commit.enable is overridden to false
[2016-03-11 20:08:15,438][INFO ][kafka.utils.VerifiableProperties] Property consumer.timeout.ms is overridden to 15000
[2016-03-11 20:08:15,438][INFO ][kafka.utils.VerifiableProperties] Property group.id is overridden to elasticsearch-kafka-river
[2016-03-11 20:08:15,441][INFO ][kafka.utils.VerifiableProperties] Property zookeeper.connect is overridden to localhost:2181,localhost:2182
[2016-03-11 20:08:15,441][INFO ][kafka.utils.VerifiableProperties] Property zookeeper.connection.timeout.ms is overridden to 10000
createcc .....................localhost:2181,localhost:2182  groupId:elasticsearch-kafka-river  clientId:elasticsearch-kafka-river
[2016-03-11 20:08:15,444][INFO ][kafka.consumer.ZookeeperConsumerConnector] [elasticsearch-kafka-river_appserver-1457698095444-42abc33f], Connecting to zookeeper instance at localhost:2181,localhost:2182
[2016-03-11 20:08:15,450][INFO ][org.apache.zookeeper.ZooKeeper] Initiating client connection, connectString=localhost:2181,localhost:2182 sessionTimeout=6000 watcher=org.I0Itec.zkclient.ZkClient@b328937
[2016-03-11 20:08:15,457][INFO ][org.I0Itec.zkclient.ZkEventThread] Starting ZkClient event thread.
[2016-03-11 20:08:15,459][INFO ][org.apache.zookeeper.ClientCnxn] Opening socket connection to server localhost/127.0.0.1:2182. Will not attempt to authenticate using SASL (unknown error)
[2016-03-11 20:08:15,467][INFO ][org.apache.zookeeper.ClientCnxn] Socket connection established to localhost/127.0.0.1:2182, initiating session
[2016-03-11 20:08:15,475][INFO ][org.apache.zookeeper.ClientCnxn] Session establishment complete on server localhost/127.0.0.1:2182, sessionid = 0x25365138c290000, negotiated timeout = 6000
[2016-03-11 20:08:15,475][INFO ][org.I0Itec.zkclient.ZkClient] zookeeper state changed (SyncConnected)
consumerConnector ...........kafka.javaapi.consumer.ZookeeperConsumerConnector@3539257c
[2016-03-11 20:08:15,476][INFO ][kafka.consumer.ZookeeperConsumerConnector] [elasticsearch-kafka-river_appserver-1457698095444-42abc33f], begin registering consumer elasticsearch-kafka-river_appserver-1457698095444-42abc33f in ZK
[2016-03-11 20:08:15,481][INFO ][kafka.consumer.ZookeeperConsumerConnector] [elasticsearch-kafka-river_appserver-1457698095444-42abc33f], end registering consumer elasticsearch-kafka-river_appserver-1457698095444-42abc33f in ZK
KafkaRiver init 异常。。。。。。。。。。。。
java.lang.NullPointerException
        at kafka.metrics.KafkaMetricsGroup$$anonfun$6.apply(KafkaMetricsGroup.scala:176)
        at kafka.metrics.KafkaMetricsGroup$$anonfun$6.apply(KafkaMetricsGroup.scala:176)
        at scala.collection.immutable.List.map(List.scala:276)
        at kafka.metrics.KafkaMetricsGroup$.kafka$metrics$KafkaMetricsGroup$$toScope(KafkaMetricsGroup.scala:176)
        at kafka.metrics.KafkaMetricsGroup$class.explicitMetricName(KafkaMetricsGroup.scala:64)
        at kafka.metrics.KafkaMetricsGroup$class.metricName(KafkaMetricsGroup.scala:46)
        at kafka.metrics.KafkaMetricsGroup$class.newGauge(KafkaMetricsGroup.scala:76)
        at kafka.consumer.ZookeeperConsumerConnector.newGauge(ZookeeperConsumerConnector.scala:83)
        at kafka.consumer.ZookeeperConsumerConnector$$anonfun$kafka$consumer$ZookeeperConsumerConnector$$reinitializeConsumer$2.apply(ZookeeperConsumerConnector.scala:874)
        at kafka.consumer.ZookeeperConsumerConnector$$anonfun$kafka$consumer$ZookeeperConsumerConnector$$reinitializeConsumer$2.apply(ZookeeperConsumerConnector.scala:869)
        at scala.collection.mutable.ResizableArray$class.foreach(ResizableArray.scala:59)
        at scala.collection.mutable.ArrayBuffer.foreach(ArrayBuffer.scala:48)
        at kafka.consumer.ZookeeperConsumerConnector.kafka$consumer$ZookeeperConsumerConnector$$reinitializeConsumer(ZookeeperConsumerConnector.scala:869)
        at kafka.consumer.ZookeeperConsumerConnector.consume(ZookeeperConsumerConnector.scala:240)
        at kafka.javaapi.consumer.ZookeeperConsumerConnector.createMessageStreams(ZookeeperConsumerConnector.scala:85)
        at kafka.javaapi.consumer.ZookeeperConsumerConnector.createMessageStreams(ZookeeperConsumerConnector.scala:97)
        at org.elasticsearch.river.kafka.KafkaConsumer.<init>(KafkaConsumer.java:53)
        at org.elasticsearch.river.kafka.KafkaRiver.<init>(KafkaRiver.java:49)
        at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
        at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
        at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
        at java.lang.reflect.Constructor.newInstance(Constructor.java:422)
        at org.elasticsearch.common.inject.DefaultConstructionProxyFactory$1.newInstance(DefaultConstructionProxyFactory.java:54)
        at org.elasticsearch.common.inject.ConstructorInjector.construct(ConstructorInjector.java:86)
        at org.elasticsearch.common.inject.ConstructorBindingImpl$Factory.get(ConstructorBindingImpl.java:98)
        at org.elasticsearch.common.inject.FactoryProxy.get(FactoryProxy.java:52)
        at org.elasticsearch.common.inject.ProviderToInternalFactoryAdapter$1.call(ProviderToInternalFactoryAdapter.java:45)
        at org.elasticsearch.common.inject.InjectorImpl.callInContext(InjectorImpl.java:837)
        at org.elasticsearch.common.inject.ProviderToInternalFactoryAdapter.get(ProviderToInternalFactoryAdapter.java:42)
        at org.elasticsearch.common.inject.Scopes$1$1.get(Scopes.java:57)
        at org.elasticsearch.common.inject.InternalFactoryToProviderAdapter.get(InternalFactoryToProviderAdapter.java:45)
        at org.elasticsearch.common.inject.InjectorBuilder$1.call(InjectorBuilder.java:200)
        at org.elasticsearch.common.inject.InjectorBuilder$1.call(InjectorBuilder.java:193)
        at org.elasticsearch.common.inject.InjectorImpl.callInContext(InjectorImpl.java:830)
        at org.elasticsearch.common.inject.InjectorBuilder.loadEagerSingletons(InjectorBuilder.java:193)
        at org.elasticsearch.common.inject.InjectorBuilder.injectDynamically(InjectorBuilder.java:175)[2016-03-11 20:08:15,493][INFO ][kafka.consumer.ZookeeperConsumerConnector] [elasticsearch-kafka-river_appserver-1457698095444-42abc33f], starting watcher executor thread for consumer elasticsearch-kafka-river_appserver-1457698095444-42abc33f

        at org.elasticsearch.common.inject.InjectorBuilder.build(InjectorBuilder.java:110)
        at org.elasticsearch.common.inject.InjectorImpl.createChildInjector(InjectorImpl.java:131)
        at org.elasticsearch.common.inject.ModulesBuilder.createChildInjector(ModulesBuilder.java:69)
        at org.elasticsearch.river.RiversService.createRiver(RiversService.java:141)
        at org.elasticsearch.river.RiversService$ApplyRivers$2.onResponse(RiversService.java:274)
        at org.elasticsearch.river.RiversService$ApplyRivers$2.onResponse(RiversService.java:268)
        at org.elasticsearch.action.support.TransportAction$ThreadedActionListener$1.run(TransportAction.java:113)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
        at java.lang.Thread.run(Thread.java:745)
Exception in thread "elasticsearch[Headknocker][Kafka River Worker][T#1]" java.lang.NullPointerException
        at org.elasticsearch.river.kafka.KafkaWorker.run(KafkaWorker.java:80)
        at java.lang.Thread.run(Thread.java:745)
 */
public class kafkaConsumer {

	public static void main(String[] args) throws InterruptedException,
			UnsupportedEncodingException {

		Properties properties = new Properties();
		properties.put("zookeeper.connect", "127.0.0.1:2181");
		properties.put("auto.commit.enable", "true");
		properties.put("auto.commit.interval.ms", "60000");
		properties.put("group.id", "test-group");

		ConsumerConfig consumerConfig = new ConsumerConfig(properties);

		ConsumerConnector javaConsumerConnector = Consumer
				.createJavaConsumerConnector(consumerConfig);

		// topic的过滤器
		Whitelist whitelist = new Whitelist("test-topic");
		List<KafkaStream<byte[], byte[]>> partitions = javaConsumerConnector
				.createMessageStreamsByFilter(whitelist);

		if (CollectionUtils.isEmpty(partitions)) {
			System.out.println("empty!");
			TimeUnit.SECONDS.sleep(1);
		}

		// 消费消息
		for (KafkaStream<byte[], byte[]> partition : partitions) {

			ConsumerIterator<byte[], byte[]> iterator = partition.iterator();
			while (iterator.hasNext()) {
				MessageAndMetadata<byte[], byte[]> next = iterator.next();
				System.out.println("partiton:" + next.partition());
				System.out.println("offset:" + next.offset());
				System.out.println("message:"
						+ new String(next.message(), "utf-8"));
			}

		}

	}
}